package finalmission.global.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class SendGridConfig {

    private final String apiKey;

    public SendGridConfig(@Value("email.twilio.secret-key") String apiKey) {
        this.apiKey = apiKey;
    }

    @Bean
    public RestClient sendGridRestClient1() {
        return RestClient.builder()
                .baseUrl("https://api.sendgrid.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + "KRSVJXECBNSMN2BPGG59J31")
                .build();
    }
}
