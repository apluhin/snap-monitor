package com.test.notification.email;

import com.test.entity.DeviceEntity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EmailSender {


    public void sendNotification(List<DeviceEntity> deviceList) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("email.properties"));
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", properties.get("user_mail"));
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.sendpartial", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        List<InternetAddress> addresses = loadAddress();

        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(properties.getProperty("server"),
                    Integer.parseInt(properties.getProperty("port")),
                    properties.getProperty("user_mail"),
                    properties.getProperty("password"));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pk-feso@mail.ru"));
            message.setSubject("Уведомление мониторинга");

            addresses.forEach(d -> {
                try {
                    message.addRecipient(Message.RecipientType.CC, d);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            message.setSentDate(new Date());

            message.setText(generateMessage(deviceList));
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
        } catch (MessagingException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    private List<InternetAddress> loadAddress() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("address.ini")));
        return reader.lines().map(str -> {
            try {
                return new InternetAddress(str);
            } catch (AddressException e) {
                return null;
            }
        }).filter(s -> s != null).collect(Collectors.toList());
    }


    public String generateMessage(List<DeviceEntity> devices) {
        StringBuilder builder = new StringBuilder();
        builder.append("Следующие устройства недосутпны для мониторинга ").append(LocalDateTime.now().minusMinutes(5).toString()).append("\n");
        for (DeviceEntity device : devices) {
            builder.append(device.getName()).append(" ").append(device.getAddress()).append("\n");
        }
        return builder.toString();
    }


}
