package paquete;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;



public class Emailer
{
  private static final String SMTP_HOST_NAME = "smtp.gmail.com";
  private static final String SMTP_AUTH_USER = "lpenbici@gmail.com";
  private static final String SMTP_AUTH_PWD  = "miltonturco";


  public void postMail( String destinatarios[ ], String contrasena,Usuario usr) throws MessagingException, AuthenticationFailedException
  {
    boolean debug = false;
    String from = "lpenbici@gmail.com";
    String subject  = "Registro de usuario";
    String nombre = usr.getNombre();
    String apellido = usr.getApellido();
    String email = usr.getEmail();

    // Escribo el mensaje a enviar (formato html)
    String message = "<li>Nombre: "+nombre+"</li> <li>Apellido: "+apellido+"</li> <li>Email: "+email+"</li> <li>Contrasena: "+contrasena+"</li>";

   
    /* Intento mandar archivo html como mensaje - NO ANDA -
    File archivo = new File("/ProyectoFinalV1/WebContent/RegistroExitoso.html");
	try {
		message = Files.toString(archivo,Charsets.UTF_8);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
    
      
    //Configuracion de SMTP en host
    Properties props = new Properties();
    props.put("mail.smtp.host", SMTP_HOST_NAME);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.starttls.enable", "true");
    
    
    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getDefaultInstance(props, auth);

    session.setDebug(debug);

    // Crea el mensaje
    Message msg = new MimeMessage(session);

    // Seteo de quien y hacia quien va dirigido el email
    InternetAddress addressFrom = new InternetAddress(from);
    msg.setFrom(addressFrom);

    InternetAddress[] addressTo = new InternetAddress[destinatarios.length];
    for (int i = 0; i < destinatarios.length; i++)
    {
        addressTo[i] = new InternetAddress(destinatarios[i]);
    }
    msg.setRecipients(Message.RecipientType.TO, addressTo);

    // Seteo el asunto y el tipo de email
    msg.setSubject(subject);
    msg.setContent(message, "text/html");
   
    // Envio email
    Transport.send(msg);
 }

private class SMTPAuthenticator extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        return new PasswordAuthentication(username, password);
    }
}
}