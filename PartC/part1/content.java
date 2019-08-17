/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package sendemail;

/**
 *
 * @author markf
 */
public class content {

    public String getSenderEmailID() {
        return senderEmailID;
    }

    public void setSenderEmailID(String senderEmailID) {
        this.senderEmailID = senderEmailID;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    public String getEmailSMTPserver() {
        return emailSMTPserver;
    }

    public void setEmailSMTPserver(String emailSMTPserver) {
        this.emailSMTPserver = emailSMTPserver;
    }

    public String getEmailServerPort() {
        return emailServerPort;
    }

    public void setEmailServerPort(String emailServerPort) {
        this.emailServerPort = emailServerPort;
    }

    public String getReceiverEmailID() {
        return receiverEmailID;
    }

    public void setReceiverEmailID(String receiverEmailID) {
        this.receiverEmailID = receiverEmailID;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getBCC() {
        return BCC;
    }

    public void setBCC(String BCC) {
        this.BCC = BCC;
    }

    String senderEmailID = null;
    String senderPassword = null;
    String emailSMTPserver = null;
    String emailServerPort = null;
    String receiverEmailID = null;
    String emailSubject = null;
    String emailBody = null;
    String CC;
    String BCC;

    public content(String Server, String User, String Password,String To, String CC, String BCC, String Subject, String Body){
        this.senderEmailID =User;
        this.senderPassword=Password;
        this.emailSMTPserver=Server;
        this.emailServerPort = "465";
        this.receiverEmailID = To;
        this.CC= CC;
        this.BCC =BCC;
        this.emailSubject = Subject;
        this.emailBody = Body;
    }

    @Override
    public String toString() {
        return "content{" + "senderEmailID=" + senderEmailID + ", senderPassword=" + senderPassword + ", emailSMTPserver=" + emailSMTPserver + ", emailServerPort=" + emailServerPort + ", receiverEmailID=" + receiverEmailID + ", emailSubject=" + emailSubject + ", emailBody=" + emailBody + ", CC=" + CC + ", BCC=" + BCC + '}';
    }


}
