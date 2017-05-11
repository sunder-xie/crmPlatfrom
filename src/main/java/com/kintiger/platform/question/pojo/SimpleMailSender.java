package com.kintiger.platform.question.pojo;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
 
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;
 
/**
 * ���ʼ����������ɵ�����Ⱥ����
 * 
 * @author MZULE
 * 
 */
public class SimpleMailSender {
 
    /**
     * �����ʼ���props�ļ�
     */
    private final transient Properties props = System.getProperties();
    /**
     * �ʼ���������¼��֤
     */
    private transient MailAuthenticator authenticator;
 
    /**
     * ����session
     */
    private transient Session session;
 
    /**
     * ��ʼ���ʼ�������
     * 
     * @param smtpHostName
     *                SMTP�ʼ���������ַ
     * @param username
     *                �����ʼ����û���(��ַ)
     * @param password
     *                �����ʼ�������
     */
    public SimpleMailSender(final String smtpHostName, final String username,
        final String password) {
    init(username, password, smtpHostName);
    }
 
    /**
     * ��ʼ���ʼ�������
     * 
     * @param username
     *                �����ʼ����û���(��ַ)�����Դ˽���SMTP��������ַ
     * @param password
     *                �����ʼ�������
     */
    public SimpleMailSender(final String username, final String password) {
    //ͨ�������ַ������smtp���������Դ�������䶼����
    final String smtpHostName = "smtp." + username.split("@")[1];
    init(username, password, smtpHostName);
 
    }
 
    /**
     * ��ʼ��
     * 
     * @param username
     *                �����ʼ����û���(��ַ)
     * @param password
     *                ����
     * @param smtpHostName
     *                SMTP������ַ
     */
    private void init(String username, String password, String smtpHostName) {
    // ��ʼ��props
    props.put("mail.smtp.host", smtpHostName);
    props.put("mail.smtp.port", "25");
    props.put("mail.smtp.auth", "true");
    // ��֤
    authenticator = new MailAuthenticator(username, password);
    // ����session
    session = Session.getInstance(props, authenticator);
    }
 
    /**
     * �����ʼ�
     * 
     * @param recipient
     *                �ռ��������ַ
     * @param subject
     *                �ʼ�����
     * @param content
     *                �ʼ�����
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException 
     */
    public void send(String recipient, String subject, Object content)
        throws AddressException, MessagingException, UnsupportedEncodingException {
    // ����mime�����ʼ�
    final MimeMessage message = new MimeMessage(session);
    // ���÷�����
    message.setFrom(new InternetAddress(authenticator.getUsername()));
    // �����ռ���
    message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
    // ��������
    message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
    // �����ʼ�����
    message.setContent(content.toString(), "text/html;charset=utf-8");
    // ����
    Transport.send(message);
    }
 
    /**
     * Ⱥ���ʼ�
     * 
     * @param recipients
     *                �ռ�����
     * @param subject
     *                ����
     * @param content
     *                ����
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, String subject, Object content)
        throws AddressException, MessagingException {
    // ����mime�����ʼ�
    final MimeMessage message = new MimeMessage(session);
    // ���÷�����
    message.setFrom(new InternetAddress(authenticator.getUsername()));
    // �����ռ�����
    final int num = recipients.size();
    InternetAddress[] addresses = new InternetAddress[num];
    for (int i = 0; i < num; i++) {
        addresses[i] = new InternetAddress(recipients.get(i));
    }
    message.setRecipients(RecipientType.TO, addresses);
    // ��������
    message.setSubject(subject);
    // �����ʼ�����
    message.setContent(content.toString(), "text/html;charset=utf-8");
    // ����
    Transport.send(message);
    }
 
}
