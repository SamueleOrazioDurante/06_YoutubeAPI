package api_controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.*;

/**
 * Servlet implementation class YT_API
 */
@WebServlet("/")
public class YT_API extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Authenticator auth;
	private DefaultConnector connector;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YT_API() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		auth = new Authenticator();
		connector = new DefaultConnector();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//take the parameters needed from the GET request
		String key = request.getParameter("API-Key");
		PrintWriter out = response.getWriter();
		
		//verify if the API key equals the one in the Authenticator class
		if(auth.verifyKey(key)) {
			String action = request.getServletPath();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			try {
				switch (action) {
				case "/search":
					out.print(search(request, response));
					break;
				case "/download/MP3":
					out.print(download(request, response, "mp3"));
					break;
				case "/download/MP4":
					out.print(download(request, response, "mp4"));
					break;
				default:
					out.print(error("NoMethod"));
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			out.print(error("WrongAPIKey"));
		}
		
		out.flush();
	}
	
	private String search(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
		
		String query = request.getParameter("query");
		String json = "";
		
		if(query == null) {
			return error("NoQuery");
		}
		
		/*
		 USED FOR TESTING PURPOUSE
		 
		String json = defaultJSONResponse.getJsonSearch();
		
		*/
		
		json = connector.search(query);
		
		System.out.println("Richiesta di ricerca con query: "+query);
		
		return json;
	}
	
	private String download(HttpServletRequest request, HttpServletResponse response, String format) throws IOException, InterruptedException {
		
		String id = request.getParameter("id");
		String json = "";
		
		/*
		 USED FOR TESTING PURPOUSE
		 
		if(format.equals("mp4")) {
			json = defaultJSONResponse.getJsonMP4();
			}	
		if(format.equals("mp3")) {
			json = defaultJSONResponse.getJsonMP3();
		}
		*/
		
		
		if(format.equals("mp4")) {
			json = connector.downloadMP4(id);
			}	
		if(format.equals("mp3")) {
			json = connector.downloadMP3(id);
		}
		
		System.out.println("Richiesta di download con formato: "+format);
		
		return json;
	}

	private String error(String error) {
		
		String json = error;
		
		System.out.println("Errore: "+error);
		
		return json;
	}
}
