package by.nces.sertificate;

import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class InitialLogonForUser {
	public static String resp;
	
	public static  void login() throws ClientProtocolException, IOException {
		
		try {
			
				CredentialsProvider provider2 = new BasicCredentialsProvider();
				System.out.println(Service.getName + "  "  + Service.getPassword);
		 		UsernamePasswordCredentials credentials2 = new UsernamePasswordCredentials(Service.getName, Service.getPassword);
		 		provider2.setCredentials(AuthScope.ANY, credentials2);	                 		
		 		System.out.println("username: " + credentials2.getUserName());
		 		System.out.println("password: " + credentials2.getPassword());	                 		
		 		CloseableHttpClient httpclient2 = HttpClientBuilder.create().setDefaultCredentialsProvider(provider2).build();
				                 	
		 		HttpGet httpGet = new HttpGet("https://cert.nces.by/remote.php/dav/files/" +Service.getName + "/");	                 		
		 		CloseableHttpResponse response2 = httpclient2.execute(httpGet);
		 		resp = response2.getStatusLine().toString();	                 		
		 		System.out.println(response2.getStatusLine());
		 		response2.close();//
			  
		 	    System.out.printf(Service.getName + "\n");
		 	    System.out.printf(Service.puth_file + Service.getName);	    
		 	    
		 	    httpclient2.close();//
		 	    httpGet.abort();//
		 	    
		} catch (ClientProtocolException e1) {
			            String error_message =  e1.toString();
			            final JComponent[] inputs = new JComponent[] {
			            new JLabel( CreateUser.resp),
			        	new JLabel("Произошла ошибка в протоколе HTTP " + error_message)        	
			            	};
			       	 JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);

		} 		catch (IOException e1) {
				    String error_message =  e1.toString();
				    final JComponent[] inputs = new JComponent[] {
				    new JLabel( CreateUser.resp),
				    new JLabel("Произошло исключение ввода/вывода " + error_message)       
				 	 };
			JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);

}
 	    
		
	}
}