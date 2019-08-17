//package sendemail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class SendEmail {


  String senderEmailID = null;
  String senderPassword = null;

  public static void main(String []args) throws IOException, MessagingException, Exception{
      SendEmail a = new SendEmail(args[0]);
      //GmailSenderAndFetch a =new GmailSenderAndFetch();
      //a.fetchMail();

  }
  public SendEmail(String file ) throws IOException, NoSuchProviderException, MessagingException{
    parse par = new parse();
    content con = par.getContent(file);
    this.senderEmailID = con.senderEmailID;
    this.senderPassword=con.senderPassword;
    Properties props = new Properties();
    props.put("mail.smtp.user",con.senderEmailID);
    props.put("mail.smtp.host", con.emailSMTPserver);
    props.put("mail.smtp.port", con.emailServerPort);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.port", con.emailServerPort);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
   // SecurityManager security = System.getSecurityManager();

    try{
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);


        MimeMessage msg = new MimeMessage(session);
        msg.setText(con.emailBody);
        msg.setSubject(con.emailSubject);
        msg.setFrom(new InternetAddress(con.senderEmailID));
        msg.addRecipient(Message.RecipientType.TO,
        new InternetAddress(con.receiverEmailID));

        BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setText("This is message body");
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);

         //messageBodyPart = new MimeBodyPart();
         File directory = new File("");
         String WEB_ROOT = directory.getAbsolutePath();
         Path path = Paths.get(WEB_ROOT + "/attachment.zip");

         String filename = path.toString();
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("file");
         multipart.addBodyPart(messageBodyPart);
         msg.setContent(multipart);
        System.out.println("Send Mail from : "+con.senderEmailID);
        System.out.println("Connection Service is : "+con.emailSMTPserver);
        System.out.println("Send Mail to : "+con.receiverEmailID);
        System.out.println("Email is sending.....");
        Transport.send(msg);
        System.out.println("Message send Successfully:)"); }

    catch (Exception mex){
        mex.printStackTrace();}


    }
  public class SMTPAuthenticator extends javax.mail.Authenticator{
        public PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
  }
}
