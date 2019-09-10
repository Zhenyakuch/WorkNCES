package by.nces.sertificate;

import java.util.Random;

public class RandomPassLog {

	public static String randomlog() {
		 String name = null;
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890qwertyuioplkjhgfdsazxcvbnm"; 
        StringBuilder salt_login = new StringBuilder();
        Random rnd_login = new Random();
        while (salt_login.length() < 10) { 
            int index = (int) (rnd_login.nextFloat() * SALTCHARS.length());
            name = salt_login.append(SALTCHARS.charAt(index)).toString();
        }
		return name;
	}
	
	public static String randompass() {
		 String password = null;
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890qwertyuioplkjhgfdsazxcvbnm"; 
	        StringBuilder salt_login = new StringBuilder();
	        Random rnd_login = new Random();
	        while (salt_login.length() < 7) { 
	            int index = (int) (rnd_login.nextFloat() * SALTCHARS.length());
	            password = salt_login.append(SALTCHARS.charAt(index)).toString();
	        }
			return password;
	}
}
