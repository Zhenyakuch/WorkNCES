package by.nces.sertificate;
import java.awt.Color;

import javax.swing.JTextPane;

public class Print {
	public static  void printer (String name, String password) {
	
	JTextPane jtp = new JTextPane();
 	jtp.setBackground(Color.white);
 	jtp.setText("Уважаемые  клиенты НЦЭУ!\r\n" + 
 			"\r\n" + 
 			"Для того, чтобы Вы могли получить свой собственный комплект абонента ГОсСУОК Вам необходимо:\r\n" + 
 			"1.перейти по ссылке облачного хранилища НЦЭУ\r\n" + 
 			"2.ввести свой личный логин и пароль\r\n" + 
 			"\r\n" + 
 			"Данные для входа:   " + "\r\n" 
 			+ "login: " + name + "\r\n" + "password: " + password + "\r\n" + "Link: https://doccloud.by ");
 	boolean show = true;
 	
		 	try {
		 	    jtp.print(null, null, show, null, null, show);
		 	} catch (java.awt.print.PrinterException ex) {
		 	    ex.printStackTrace();
		 	}
		 	
 	
	}
}
