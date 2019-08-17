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

      GmailFetch a =new GmailFetch();
      a.fetchMail(args[0],args[1],args[2]);

  }
}
