package by.nces.sertificate;
import java.awt.Color;

import javax.swing.JTextPane;

public class Print {
	public static  void printer (String name, String password) {
	
	JTextPane jtp = new JTextPane();
 	jtp.setBackground(Color.white);
 	jtp.setText("���������  ������� ����!\r\n" + 
 			"\r\n" + 
 			"��� ����, ����� �� ����� �������� ���� ����������� �������� �������� ������� ��� ����������:\r\n" + 
 			"1.������� �� ������ ��������� ��������� ����\r\n" + 
 			"2.������ ���� ������ ����� � ������\r\n" + 
 			"\r\n" + 
 			"������ ��� �����:   " + "\r\n" 
 			+ "login: " + name + "\r\n" + "password: " + password + "\r\n" + "Link: https://doccloud.by ");
 	boolean show = true;
 	
		 	try {
		 	    jtp.print(null, null, show, null, null, show);
		 	} catch (java.awt.print.PrinterException ex) {
		 	    ex.printStackTrace();
		 	}
		 	
 	
	}
}
