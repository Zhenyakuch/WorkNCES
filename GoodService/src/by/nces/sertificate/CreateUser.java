package by.nces.sertificate;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class CreateUser  {
	public static final String response = null;

	public static String resp;

	public static void user() throws SftpException, JSchException, IOException {
	
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("**", "**");
		provider.setCredentials(AuthScope.ANY, credentials);
		CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		HttpPost httpPost = new HttpPost(Service.URL);
		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("userid",Service.getName));
		nvps.add(new BasicNameValuePair("password",Service.getPassword));
		nvps.add(new BasicNameValuePair("groups[]","AvPass"));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		System.out.println(response.getStatusLine());
		
		resp = response.getStatusLine().toString();
		System.out.println(EntityUtils.toString(response.getEntity()));
		response.close();
		httpclient.close();//
		httpPost.abort();//
		
 		
}
	public static void user2() throws SftpException, JSchException, IOException {
		
		
			CredentialsProvider provider = new BasicCredentialsProvider();
	 		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("***", "**");
	 		provider.setCredentials(AuthScope.ANY, credentials);
	 		CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
	 		HttpPost httpPost = new HttpPost(Service.URL);
	 		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
	
	 		nvps.add(new BasicNameValuePair("userid",Service.getName));
	 		nvps.add(new BasicNameValuePair("password",Service.getPassword));
	 		nvps.add(new BasicNameValuePair("groups[]","AvBign"));
	
	 		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
	 		CloseableHttpResponse response = httpclient.execute(httpPost);
	 		System.out.println(response.getStatusLine());
	 		
	 		resp = response.getStatusLine().toString();
	 		System.out.println(EntityUtils.toString(response.getEntity()));
	 		response.close();
	 		
	 		httpclient.close();//
	 		httpPost.abort();//
		
 		
}
}
