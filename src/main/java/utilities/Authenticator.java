package utilities;

import config.APIConfig;

public class Authenticator{
	
	private String key;
	
	public Authenticator() {
		//its possible to modify this class to allow the use of multiple API key stored in a database, in this case using one key is easier and enough for authentication
		key = APIConfig.getApiKey();
	}
	
	public boolean verifyKey(String toVerify) {
		boolean verified = false;
		
		if(toVerify == null) {
			return false;
		}
		
		if(toVerify.equals(key)) {
			verified = true;
		}
		
		return verified;
	}
}