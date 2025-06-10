package finalmission.global.email;

import mail.domain.Mail;

public record MailDataDto(String formEmail, String subject, String toEmail, String content) {

    public static MailDataDto from(Mail mail) {
        return new MailDataDto(mail.getFromEmail(), mail.getSubject(), mail.getToEmail(), mail.getContent());
    }
}
