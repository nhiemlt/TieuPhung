package com.clinic.plp_clinicmanage.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailService {

    private String numberOTP;

    public String getNumberOTP() {
        return numberOTP;
    }

    public void setNumberOTP(String numberOTP) {
        this.numberOTP = numberOTP;
    }

    public int generateRandomOTP() {
        Random random = new Random();
        // Generate a random number between 100000 and 999999 (6 digits)
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        setNumberOTP(String.valueOf(randomNumber));
        return randomNumber;
    }

    public static String generateRandomPassword() {
        Random random = new Random();
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String allChars = lowerCase + upperCase + digits;

        // Đảm bảo ít nhất một ký tự thuộc mỗi loại
        List<Character> passwordChars = new ArrayList<>();
        passwordChars.add(lowerCase.charAt(random.nextInt(lowerCase.length())));
        passwordChars.add(upperCase.charAt(random.nextInt(upperCase.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));

        // Thêm ngẫu nhiên 3 ký tự từ tất cả các loại
        for (int i = 0; i < 3; i++) {
            passwordChars.add(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Trộn ngẫu nhiên các ký tự trong mật khẩu
        Collections.shuffle(passwordChars);

        // Chuyển danh sách thành chuỗi mật khẩu
        StringBuilder password = new StringBuilder();
        for (char ch : passwordChars) {
            password.append(ch);
        }

        return password.toString();
    }

    public void sendOTP(String emailTo) {
        String otp = String.valueOf(generateRandomOTP());
        String username = "plphethongquanliphongkham@gmail.com";
        String password = "kdfe trau ogzp iyww";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Yêu cầu thay đổi mật khẩu");

            String emailBody = "<html lang='vi'>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<style>"
                    + "body { font-family: 'Times New Roman', serif; font-size: 16px; line-height: 1.6; margin: 0; padding: 0; }"
                    + "h2 { color: #333333; margin-bottom: 10px; }"
                    + "span { color: #555555; display: block; margin-bottom: 20px; }"
                    + "</style>"
                    + "</head>"
                    + "<body style='background-color: #f4f4f4; padding: 20px; margin: 0;'>"
                    + "<div style='background-color: #ffffff; max-width: 600px; margin: 0 auto; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                    + "<h2>Xin chào!</h2>"
                    + "<span>Ai đó đã yêu cầu đặt lại mật khẩu cho tài khoản của bạn. "
                    + "Nếu đây không phải là bạn, vui lòng bỏ qua email này.</span>"
                    + "<span>Sử dụng mã kích hoạt này để khôi phục mật khẩu của bạn: <strong>" + otp + "</strong></span>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            message.setContent(emailBody, "text/html; charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String sendTempPassword(String emailTo) {
        String tempPassword = generateRandomPassword();
        String username = "luanb2207599@student.ctu.edu.vn";
        String password = "nori xiyt omcm pekw";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Cấp mật khẩu tạm thời");

            String emailBody = "<html lang='vi'>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<style>"
                    + "body { font-family: 'Times New Roman', serif; font-size: 16px; line-height: 1.6; margin: 0; padding: 0; }"
                    + "h2 { color: #333333; margin-bottom: 10px; }"
                    + "span { color: #555555; display: block; margin-bottom: 20px; }"
                    + "</style>"
                    + "</head>"
                    + "<body style='background-color: #f4f4f4; padding: 20px; margin: 0;'>"
                    + "<div style='background-color: #ffffff; max-width: 600px; margin: 0 auto; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                    + "<h2>Xin chào!</h2>"
                    + "<span>Mật khẩu tạm thời của bạn là: <strong>" + tempPassword + "</strong></span>"
                    + "<span>Vui lòng thay đổi mật khẩu ngay sau khi nhận được mật khẩu này.</span>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            message.setContent(emailBody, "text/html; charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return tempPassword;  // Trả về mật khẩu tạm thời
    }


}
