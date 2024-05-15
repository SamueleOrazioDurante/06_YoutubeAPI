package utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import config.APIConfig;

public class DefaultConnector{

	public DefaultConnector() {}
	
	public String downloadMP3(String id) throws IOException, InterruptedException {
		
		String url = "https://youtube-mp3-download1.p.rapidapi.com/dl";
		
		//add id parameter
		url += "?id="+id;
		
		System.out.println("Sent downloadMP3 request to API with URL: "+url);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("X-RapidAPI-Key", APIConfig.getRapidAPI_key())
				.header("X-RapidAPI-Host", "youtube-mp3-download1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
		
	}
	
	public String downloadMP4(String id) throws IOException, InterruptedException {
		
		String url = "https://ytstream-download-youtube-videos.p.rapidapi.com/dl";
		
		//add id parameter
		url += "?id="+id;
		
		System.out.println("Sent downloadMP4 request to API with URL: "+url);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("X-RapidAPI-Key", APIConfig.getRapidAPI_key())
				.header("X-RapidAPI-Host", "ytstream-download-youtube-videos.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
		
	}
	
	public String search(String query) throws IOException, InterruptedException {
		
		String url = "https://youtube-v2.p.rapidapi.com/search/";
		
		//url doesnt like spaces so every query should have %20 to represent spaces
		query = query.replaceAll(" ", "%20");
		
		//add query parameter
		url += "?query="+query;
		
		//add other optional parameter from APIConfig
		url += "&lang="+APIConfig.getLang()+"&order_by="+APIConfig.getOrder_by()+"&country="+APIConfig.getCountry()+"";
		
		System.out.println("Sent search request to API with URL: "+url);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("X-RapidAPI-Key", APIConfig.getRapidAPI_key())
				.header("X-RapidAPI-Host", "youtube-v2.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
		
	}

}