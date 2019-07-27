package net.vv2.PersonalFinance.service.impl;

import net.vv2.PersonalFinance.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public boolean sentEmail(String to, String subject, String content) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            try {
                mailSender.send(message);
            } catch (Exception e) {
                return false;
            }

        return true;
    }

    @Override
    public boolean sentEmailWithHtml(String to, String subject, String content) {
        return false;
    }
    public static void main(String args[]){
        MailService mailService=new MailServiceImpl();
        System.out.println(mailService.sentEmail("zhongfengshan@qq.com","test your test","hahahahhah"));

    }
}
