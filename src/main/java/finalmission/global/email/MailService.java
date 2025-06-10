package finalmission.global.email;

import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final SendGridRestClient sendGridRestClient;

    public MailService(SendGridRestClient sendGridRestClient) {
        this.sendGridRestClient = sendGridRestClient;
    }

    public MailDataDto send(MailDataDto dto) {
        MailDataDto mailDataDto = sendGridRestClient.sendMail(dto);
        return null;
    }
}
