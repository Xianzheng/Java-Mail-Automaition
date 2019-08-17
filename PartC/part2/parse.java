/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package sendemail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author markf
 */
public class parse {
    String senderEmailID = null;
    String senderPassword = null;
    String emailSMTPserver = null;
    String emailServerPort = null;
    String receiverEmailID = null;
    String emailSubject = null;
    String emailBody = null;
    String CC;
    String BCC;
    public content getContent(String file) throws FileNotFoundException, IOException{
        File directory = new File("");
        String WEB_ROOT = directory.getAbsolutePath();
        FileReader fr=new FileReader(WEB_ROOT + "/"+file);
        BufferedReader br=new BufferedReader(fr);
        String line;

        while((line = br.readLine())!=null){
            if(line.contains("Server")){
                this.emailSMTPserver=line.replace("Server: ", "");
            }else if(line.contains("User")){
                this.senderEmailID =line.replace("User: ", "");
            }else if(line.contains("Password")){
                this.senderPassword=line.replace("Password: ", "");
            }else if(line.contains("To")&&line.contains("@")){
                this.receiverEmailID = line.replace("To: ", "");
            }else if(line.contains("CC")&&!line.contains("B")){
                this.CC = line.replace("CC: ", "");
            }else if(line.contains("BCC")){
                this.BCC = line.replace("BCC: ", "");
            }else if(line.contains("Subject")){
               this.emailSubject = line.replace("Subject: ", "");
            }else if(line.contains("Body")){
               this.emailBody = line.replace("Body: ", "");
            }


        }

         content detail = new content(this.emailSMTPserver, this.senderEmailID, this.senderPassword,
                        this.receiverEmailID,this.CC,this.BCC,this.emailSubject,this.emailBody);
         //System.out.println(detail.toString());
        return detail;
    }
}
