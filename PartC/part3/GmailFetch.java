//package sendemail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 使用Gmail发送邮件
 */
public class GmailFetch {

    private static String messageContentMimeType = "text/html; charset=gb2312";

    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static Properties getProperties() {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        // Gmail提供的POP3和SMTP是使用安全套接字层SSL的
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.fallback", "false");
        props.setProperty("mail.imap.port", "993");
        props.setProperty("mail.imap.socketFactory.port", "993");

        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");

        props.put("mail.smtp.auth", "true");
        return props;
    }

    /**
     * 构建邮件
     *
     * @param username
     * @param password
     * @param receiver
     * @return
     * @throws AddressException
     * @throws MessagingException
     */


    /**
     * 构建邮件的正文和附件
     *
     * @param msgContent
     * @param attachedFileList
     * @return
     * @throws MessagingException
     */

    /**
     * 字串解码
     *
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    protected static String decodeText(String text)
            throws UnsupportedEncodingException {
        if (text == null)
            return null;
        if (text.startsWith("=?GB") || text.startsWith("=?gb")) {
            text = MimeUtility.decodeText(text);
        } else {
            text = new String(text.getBytes("ISO8859_1"));
        }
        return text;
    }

    /**
     * 分析邮件
     *
     * @param mPart
     */


    /**
     * 抽取内容
     *
     * @param part
     */


    /**
     * 保存附件
     * @param part
     */


    /**
     * 发送邮件
     *
     * @throws AddressException
     * @throws MessagingException
     */

    public void fetchMail(String server, String User, String pass) throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session session = Session.getDefaultInstance(getProperties(), null);
        //用pop3协议：new URLName("pop3", "pop.gmail.com", 995, null,"[邮箱帐号]", "[邮箱密码]");
        //用IMAP协议
        URLName urln = new URLName("imap", server, 995, null,
                User, pass);
        Store store = null;
        Folder inbox = null;
        try {
            //Store用来收信,Store类实现特定邮件协议上的读、写、监视、查找等操作。
            store = session.getStore(urln);
            store.connect();
            inbox = store.getFolder("INBOX");//收件箱
            inbox.open(Folder.READ_ONLY);
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            Message[] messages = inbox.getMessages();
            inbox.fetch(messages, profile);
            //System.out.println("The number of inbox：" + messages.length);
            System.out.println("The number of unread：" + inbox.getUnreadMessageCount());
            System.out.println("The number of inbox：" + inbox.getNewMessageCount());

            for (int i = 0; i < messages.length; i++) {
                // 邮件发送者
                String from = decodeText(messages[i].getFrom()[0].toString());
                InternetAddress ia = new InternetAddress(from);

                System.out.print("  Content :"+ messages[i].getContent().toString().replace("representing the body of the email",""));
                System.out.println( i+" FROM ("+ ia.getAddress() + ")");
            }
        } finally {
            try {
                inbox.close(false);
            } catch (Exception e) {
            }
            try {
                store.close();
            } catch (Exception e) {
            }
        }
    }

}
