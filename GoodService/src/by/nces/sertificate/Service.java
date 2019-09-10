package by.nces.sertificate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class Service extends Print  {
	
    public static String getName = null;
    public static String getPassword = null;
	protected Shell shlLogin;
    public static String URL;
    static String puth_file;
	protected static File File = null;
	public String names;
	public String passwords;
    public static void main(String[] args) {
    	
        try {
            Service window = new Service();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();          
                  }
    }
 
        public void open() {
        Display display = Display.getDefault();
        createContents();
        shlLogin.open();
        shlLogin.layout();
        while (!shlLogin.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }      
    }
	
	    public void createContents() {
        shlLogin = new Shell(SWT.CLOSE | SWT.MIN | SWT.TITLE);
        shlLogin.setAlpha(250);
        shlLogin.setBackground(SWTResourceManager.getColor(135, 206, 235));
        shlLogin.setSize(268, 165);
        shlLogin.setText("Certificate");
        shlLogin.setLayout(new FormLayout());
        shlLogin.addShellListener(new CertificateWindowListener());
        Button btnCreateAvBign = new Button(shlLogin, SWT.NONE);
        btnCreateAvBign.setForeground(SWTResourceManager.getColor(0, 0, 0));
       
        FormData fd_btnCreateAvBign = new FormData();
        fd_btnCreateAvBign.top = new FormAttachment(0, 20);
        fd_btnCreateAvBign.right = new FormAttachment(100, -10);
        btnCreateAvBign.setLayoutData(fd_btnCreateAvBign);
        btnCreateAvBign.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
        
        btnCreateAvBign.addSelectionListener(new SelectionAdapter() {

     		
        	@Override
        	public void widgetSelected(SelectionEvent e) {
              	try {
              		  
              			    Properties props = new Properties();
	              		 	props.load(new FileInputStream(new File("C:\\config_path.ini")));
	              			   
              			    URL = props.getProperty("POST_URL"); 
              			    puth_file = props.getProperty("puth_file");

	                        names = RandomPassLog.randomlog();
	                        passwords = RandomPassLog.randompass();	                        
	                       
	                        getName = names;
	                        getPassword = passwords; //for different class	                        
	                                			   
	                 		CreateUser.user();	 //createUser                      	
	                 		                 		
	                 		InitialLogonForUser.login();//InitialLogonForUser;             		
	                 		
                            CopyFileOnServer.SSH();//add file in path
     	                        	                 	    	                             
	                        Print.printer(names, passwords); //print doc
	                            
	                 		String puth_with_file = puth_file + names;
	                 		System.out.printf(puth_with_file);
	                             
				                            final JComponent[] inputs = new JComponent[] {
				          		        			  new JLabel("Ответ сервера: " + CreateUser.resp),
				          		        			  new JLabel("Пользователь с именем: " + names + "  создан"),
				          		        			  new JLabel("Сформирована папка: " + puth_with_file),
				          		        			  new JLabel("в которой находится комплект абонента")
				          		        			  };
				                             		  JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
              		   					
              		 
                         } catch (JSchException e1) {
						                            String error_message =  e.toString();
						                            final JComponent[] inputs = new JComponent[] {
						                            new JLabel( CreateUser.resp),
						         		        	new JLabel("Ошибка подключения по SFTP " + error_message)   
								                 	};
										        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
		        	
                         }
				        catch (NullPointerException e1) {
								                    String error_message =  e.toString();
								                    final JComponent[] inputs = new JComponent[] {
								                    new JLabel( CreateUser.resp),
								 		        	new JLabel("Произошла ошибка " + error_message)   
								                 	};
								                    JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);

				        }
			            catch (IllegalArgumentException e1) {
								            	    String error_message =  e.toString();
						                            final JComponent[] inputs = new JComponent[] {
						                            new JLabel( CreateUser.resp),
						         		        	new JLabel("Неверный url " + error_message)   
								                 	};
										        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
			                    
			              	}
              			catch(UnsupportedEncodingException e1) {
						                             String error_message =  e.toString();
						                             final JComponent[] inputs = new JComponent[] {
						                             new JLabel( CreateUser.resp),
						          		        	 new JLabel("Указана неверная кодировка " + error_message)     
								                  	 };
								 		        	 JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
 		        	
                 		} catch (ClientProtocolException e1) {
						                             String error_message =  e.toString();
						                             final JComponent[] inputs = new JComponent[] {
						                             new JLabel( CreateUser.resp),
						         		        	 new JLabel("Произошла ошибка в протоколе HTTP " + error_message)        	
								                 	 };
										        	 JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
		        	 
 						} catch (IOException e1) {
						                            String error_message =  e1.toString();
						                            final JComponent[] inputs = new JComponent[] {
						                            new JLabel( CreateUser.resp),
						         		            new JLabel("Произошло исключение ввода/вывода " + error_message)       
								                 	 };
										        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
		        	 
 						} catch (SftpException e1) {
						                            String error_message =  e1.toString();
						                            final JComponent[] inputs = new JComponent[] {
						                            new JLabel( CreateUser.resp),
						        		            new JLabel("Создать пользователся не удалось, проверьте корректность url " + error_message )   
								                 	 };
										        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
 													}
                 		   }

             
         });
        btnCreateAvBign.setText("\u0412\u044B\u0433\u0440\u0443\u0437\u0438\u0442\u044C AvPass");
        
        Button button = new Button(shlLogin, SWT.NONE);
        button.setForeground(SWTResourceManager.getColor(0, 0, 0));
        fd_btnCreateAvBign.left = new FormAttachment(button, 0, SWT.LEFT);
        button.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
            		  
      			    Properties props = new Properties();
          		 	props.load(new FileInputStream(new File("C:\\config_path.ini")));
          			   
      			    URL = props.getProperty("POST_URL"); 
      			    puth_file = props.getProperty("puth_file");

                    names = RandomPassLog.randomlog();
                    passwords = RandomPassLog.randompass();                    
                   
                    getName = names;
                    getPassword = passwords; //for different class                    
                                			   
             		CreateUser.user2();  //createUser           	
             		
             		InitialLogonForUser.login(); //InitialLogonForUser               		
             		                    
             		CopyFileOnServer.SSH();//add file in path
                    	                	    	                             
                    Print.printer(names, passwords);//print doc	
                         
				    String puth_with_file = puth_file + names;
				    System.out.printf(puth_with_file);
                         
		                             final JComponent[] inputs = new JComponent[] {
		          		        			  new JLabel("Ответ сервера: " +  CreateUser.resp),
		          		        			  new JLabel("Пользователь с именем: " + names + "  создан"),
		          		        			  new JLabel("Сформирована папка: " + puth_with_file),
		          		        			  new JLabel("в которой находится комплект абонента"),
		          		        			  new JLabel("вставить инфу" + "о печати")
		          		        			  };
		                             		  JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
      		   					
                 } catch (JSchException e1) {
				                            String error_message =  e.toString();
				                            final JComponent[] inputs = new JComponent[] {
				                            new JLabel( CreateUser.resp),
				         		        	new JLabel("Ошибка подключения по SFTP " + error_message)   
						                 	};
								        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
        	
                 }
				        		catch(UnsupportedEncodingException e1) {
				                    String error_message =  e.toString();
				                    final JComponent[] inputs = new JComponent[] {
				                    new JLabel( CreateUser.resp),
				 		        	 new JLabel("Указана неверная кодировка " + error_message)     
				                 	 };
						        	 JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
				
				} catch (ClientProtocolException e1) {
				                    String error_message =  e.toString();
				                    final JComponent[] inputs = new JComponent[] {
				                    new JLabel( CreateUser.resp),
						        	 new JLabel("Произошла ошибка в протоколе HTTP " + error_message)        	
				                	 };
						        	 JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
				
				} catch (IOException e1) {
				                   String error_message =  e1.toString();
				                   final JComponent[] inputs = new JComponent[] {
				                   new JLabel( CreateUser.resp),
						            new JLabel("Произошло исключение ввода/вывода " + error_message)       
				                	 };
						        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
				
				} catch (SftpException e1) {
				                   String error_message =  e1.toString();
				                   final JComponent[] inputs = new JComponent[] {
				                   new JLabel( CreateUser.resp),
						            new JLabel("Создать пользователся не удалось, проверьте корректность url " + error_message )   
				                	 };
						        	JOptionPane.showConfirmDialog(null, inputs, "Отчет о действии", JOptionPane.PLAIN_MESSAGE);
									}
				}
	
        });
        button.setText("\u0412\u044B\u0433\u0440\u0443\u0437\u0438\u0442\u044C AvBign");
        button.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
        FormData fd_button = new FormData();
        fd_button.left = new FormAttachment(0, 10);
        fd_button.right = new FormAttachment(100, -10);
        fd_button.bottom = new FormAttachment(100, -25);
        button.setLayoutData(fd_button);
    }

}