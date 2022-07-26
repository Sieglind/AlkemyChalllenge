package ar.com.cdmoraleda.alkemychallenge.services;

import ar.com.cdmoraleda.alkemychallenge.dto.ApiUserDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;


@Service
public class EmailService {
    @Value("${api-key}")
    private String apiKey;
    public void sendConfirmation(ApiUserDto apiUserDto) throws IOException {
        Mail mail = new Mail(
                new Email("alkemyapicdm@gmail.com"),
                "Welcome to my API",
                new Email(apiUserDto.getEmail()),
                new Content(
                        "text/plain",
                        "Thanks for register to use my Alkemy Challenge API"
                )
        );
        SendGrid sg = new SendGrid(new String(Base64.getDecoder().decode(apiKey)));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
