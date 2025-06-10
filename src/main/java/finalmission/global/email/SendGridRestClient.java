package finalmission.global.email;

import mail.domain.Mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec.ErrorHandler;

@Component
public class SendGridRestClient {

    private final RestClient restClient;

    public SendGridRestClient(RestClient sendGridRestClient) {
        this.restClient = sendGridRestClient;
    }

    public MailDataDto sendMail(MailDataDto requestDto) {
        Mail mail = restClient.post()
                .uri("/v1/payments/confirm")
                .body(requestDto)
                .retrieve()
                .onStatus(this::isError, getErrorHandler())
                .toEntity(Mail.class)
                .getBody();

        return MailDataDto.from(mail);
    }

    public boolean isError(HttpStatusCode httpStatusCode) {
        return httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError();
    }

    private ErrorHandler getErrorHandler() {
        return (req, res) -> {
            HttpStatus status = HttpStatus.valueOf(res.getStatusCode().value());
            throw new InvalidSendGridException("예외 발생", status);
        };
    }
}
