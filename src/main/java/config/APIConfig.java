package config;

public class APIConfig {
	
	static private String apiKey = "rLQLrgrl1YYwuS9pRKTQo05NCZ19HO0P0yR7l69v82Q8il83m0";
    static private String rapidAPI_key = "a9941eed57mshd4dab98610d97edp1b60b2jsnd8ebc731ea8f";
    static private String lang = "it";
    static private String country = "it";
    static private String order_by = "this_month";
    
    public static String getApiKey() {
    	return apiKey;
    }

	public static String getRapidAPI_key() {
		return rapidAPI_key;
	}

	public static String getLang() {
		return lang;
	}

	public static String getCountry() {
		return country;
	}

	public static String getOrder_by() {
		return order_by;
	}
    
    
}