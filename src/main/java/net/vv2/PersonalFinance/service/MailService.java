package net.vv2.PersonalFinance.service;

public interface MailService {
    public abstract boolean sentEmail(String to, String subject, String content);
    public abstract boolean sentEmailWithHtml(String to, String subject, String content);
}
