package com.ydj.smart.common.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.net.ssl.internal.ssl.Provider;
import com.ydj.smart.api.constant.Constant;

/**
 * @description
 * 
 * @author Frank
 * @version 1.0
 * @create_time 2010-4-20
 */
public class MailUtils {

	public static final Server DEFAULT_SERVER = new Server("smtp.163.com",
			"pop.163.com", null);

	public static final Account REG_NOREPLY = new Account(
			"mail9tong@163.com", "9tong168", "「"+Constant.getPro("sysName")+"」");

	public static final Server BACK_SERVER = new Server("smtp.163.com",
			"pop.163.com", null);

	public static final Account BACK_REG_NOREPLY = new Account(
			"mail9tong@163.com", "9tong168", "「"+Constant.getPro("sysName")+"」");

	/**
	 * 从一个指定邮箱向一个邮箱发送邮件（异步方式）
	 * 
	 * @param account
	 * @param subject
	 *            邮件标题
	 * @param to
	 *            收件人
	 * @param content
	 *            邮件内容
	 * @return
	 */
	public static void asynSend(final Account account, final String to,
			final String subject, final String content) {

		asynSend(DEFAULT_SERVER, account, to, subject, content);

	}

	/**
	 * 从一个指定邮箱向一个邮箱发送邮件（异步方式）
	 * 
	 * @param server
	 * @param account
	 * @param subject
	 *            邮件标题
	 * @param to
	 *            收件人
	 * @param content
	 *            邮件内容
	 * @return
	 */
	public static void asynSend(final Server server, final Account account,
			final String to, final String subject, final String content) {

		new Thread(new Runnable() {
			public void run() {
				send(server, account, to, subject, content);
			}
		}).start();

	}

	/**
	 * 从一个指定邮箱向一个邮箱发送邮件
	 * 
	 * @param account
	 * @param subject
	 *            邮件标题
	 * @param to
	 *            收件人
	 * @param content
	 *            邮件内容
	 * @return
	 */
	public static boolean send(Account account, String to, String subject,
			String content) {

		return send(DEFAULT_SERVER, account, to, subject, content);

	}

	/**
	 * 从一个指定邮箱向一个邮箱发送邮件
	 * 
	 * @param server
	 * @param account
	 * @param subject
	 *            邮件标题
	 * @param to
	 *            收件人
	 * @param content
	 *            邮件内容
	 * @return
	 */
	public static boolean send(Server server, Account account, String to,
			String subject, String content) {

		Message msg = new MimeMessage(server.getSession());
		try {
			msg.setFrom(new InternetAddress(account.address, account.personal));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			subject = MimeUtility.encodeText(subject, "GBK", "B");
			msg.setSubject(subject); // 设置主题
			msg.setSentDate(new Date()); // 设置发送时间

			BodyPart mdp = new MimeBodyPart(); // 新建一个存放信件内容的BodyPart对象
			mdp.setContent(content, "text/html;charset=utf-8"); // 给BodyPart对象设置内容和格式/编码方式
			Multipart mm = new MimeMultipart(); // 新建一个MimeMultipart对象用来存放BodyPart对象(事实上可以存放多个)
			mm.addBodyPart(mdp); // 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			msg.setContent(mm); // 把mm作为消息对象的内容
			msg.saveChanges();
			Transport transport = server.getSession().getTransport("smtp");
			transport.connect(server.getSmtpHost(), account.getAddress(),
					account.getPassword());
			transport.sendMessage(msg,
					msg.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (MessagingException ee) {
			ee.printStackTrace();
//			if (ee.getMessage().indexOf("Could not connect to SMTP host") > -1) {
//				try {
//					Thread.sleep(60000);
//					send(server, account, subject, to, content);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//			}
//			send(BACK_SERVER,BACK_REG_NOREPLY, to, subject, content);
		} catch (Exception e) {
			// System.err.println(e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 从指定的邮件服务器收取邮件
	 * 
	 * @param account
	 * @param delete
	 *            收完后是否删除
	 * @return
	 */
	public static List<Email> recieve(Account account, boolean delete) {
		return recieve(DEFAULT_SERVER, account, delete);
	}

	/**
	 * 从指定的邮件服务器收取邮件
	 * 
	 * @param server
	 * @param account
	 * @param delete
	 *            收完后是否删除
	 * @return
	 */
	public static List<Email> recieve(Server server, Account account,
			boolean delete) {

		Store store = null;
		Folder inbox = null;
		List<Email> list = new ArrayList<Email>();

		try {
			store = server.getSession().getStore("pop3");
			store.connect(server.getPop3Host(), account.getAddress(),
					account.getPassword());
			// 获取默认文件夹
			inbox = store.getDefaultFolder();
			inbox = inbox.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			// FetchProfile profile = new FetchProfile();
			// profile.add(FetchProfile.Item.ENVELOPE);
			Message[] messages = inbox.getMessages();
			// inbox.fetch(messages, profile);
			for (Message msg : messages) {
				if (delete)
					msg.setFlag(Flags.Flag.DELETED, true);
				Email email = new Email();
				email.setSubject(msg.getSubject());
				email.setFrom(((InternetAddress) msg.getFrom()[0]).getAddress());
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(
							msg.getInputStream(), "GBK"));
					StringBuilder content = new StringBuilder();
					String line;
					while (null != (line = br.readLine()))
						content.append(line);

					email.setContent(content.toString());
				} catch (Exception ex) {
				} finally {
					try {
						br.close();
					} catch (Exception e) {
					}
				}
				list.add(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.err.println(e);
		} finally {
			try {
				if (null != inbox)
					inbox.close(delete);
			} catch (Exception e) {
				e.printStackTrace();
				// System.err.println(e);
			}
			try {
				if (null != store)
					store.close();
			} catch (Exception e) {
				e.printStackTrace();
				// System.err.println(e);
			}
		}

		return list;
	}

	public static class Server {

		private Session session;

		private String smtpHost;

		private String pop3Host;

		private String smtpPort;

		public Server() {
			this("www.9tongmail.com", "www.9tongmail.com", null);
		}

		public Server(String smtpHost, String pop3Host, String smtpPort) {
			this.smtpHost = smtpHost;
			this.pop3Host = pop3Host;
			this.smtpPort = smtpPort;
			init();
		}

		private void init() {
			Security.addProvider(new Provider());
			Properties props = new Properties();
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.host", smtpHost);
			props.setProperty("mail.pop3.socketFactory.fallback", "false");
			if (!CommonUtils.isEmptyString(smtpPort)) {
				props.setProperty("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				props.setProperty("mail.smtp.port", smtpPort);
				props.setProperty("mail.smtp.socketFactory.port", smtpPort);
			}
			session = Session.getInstance(props);
		}

		public Session getSession() {
			return session;
		}

		public void setSession(Session session) {
			this.session = session;
		}

		public String getSmtpHost() {
			return smtpHost;
		}

		public void setSmtpHost(String smtpHost) {
			this.smtpHost = smtpHost;
		}

		public String getPop3Host() {
			return pop3Host;
		}

		public void setPop3Host(String pop3Host) {
			this.pop3Host = pop3Host;
		}

	}

	public static class Account {

		private String address;

		private String password;

		private String personal;

		public Account(String address, String password) {

			this(address, password, "");

		}

		public Account(String address, String password, String personal) {

			this.address = address;
			this.password = password;
			this.personal = personal;

		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPersonal() {
			return personal;
		}

		public void setPersonal(String personal) {
			this.personal = personal;
		}

	}

	public static class Email {

		private String subject;

		private String from;

		private String content;

		public Email() {
			super();
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}

	public static void main(String[] args) {
		MailUtils.asynSend(MailUtils.REG_NOREPLY, "369415359@qq.com","新API使用信息", "test");
	}
}
