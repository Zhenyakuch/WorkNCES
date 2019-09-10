package by.nces.sertificate;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class CopyFileOnServer {
	
	public static void SSH() throws SftpException, JSchException {
		try {
		
	     JSch jsch = new JSch();
         com.jcraft.jsch.Session session = null;
         
             session = jsch.getSession("**", "**", **);
             session.setConfig("StrictHostKeyChecking", "no");
             session.setPassword("****");
             session.connect();
             System.out.println("session.isConnected()      "    +session.isConnected());
             System.out.println("session.getUserInfo()      "    +session.getUserInfo());	
             Channel channel = session.openChannel("sftp");
             channel.connect();	                             
             System.out.println("channel.isConnected()     "+channel.isConnected());	
             ChannelSftp channelSftp = (ChannelSftp) channel;	                             
             System.out.println("channelSftp.toString()     "    +channelSftp.toString());	                             
             String path2 = "/var/www/html/owncloud/data"+ "/"+  Service.getName + "/" + "files" + "/";	                             
             System.out.println("path: " + path2);	                             
             channelSftp.cd(path2);	       	                             
             channelSftp.put("C:\\ForClient\\data\\*.cer", path2);
             channelSftp.put("C:\\ForClient\\data\\*.p7b", path2);
             channelSftp.put("C:\\ForClient\\data\\*.crl", path2);	                             
             System.out.println(channelSftp.ls(path2)+ "    --------------- ls folder"); 	                          
             ChannelExec channel2 = (ChannelExec) session.openChannel("exec");
             String copyString = new String("sudo -u www-data php /var/www/html/owncloud/console.php files:scan "+Service.getName+"> /opt/scan_"+Service.getName+".log");
             System.out.println(copyString);
             channel2.setCommand(copyString);
             channel2.connect();
             channel2.disconnect();
             channelSftp.disconnect();
             channel.disconnect();
             session.disconnect();
             
			} catch (JSchException e1) {
	            String error_message =  e1.toString();
	            final JComponent[] inputs = new JComponent[] {
	            new JLabel( CreateUser.resp),
		        	new JLabel("Ошибка подключения по SFTP " + error_message)   
	         	};
	        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);

					}catch (SftpException e1) {
					    String error_message =  e1.toString();
					    final JComponent[] inputs = new JComponent[] {
					    new JLabel( CreateUser.resp),
					     new JLabel("Создать пользователся не удалось, проверьте корректность url " + error_message )   
					 	 };
					 	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
							}


	}

}
