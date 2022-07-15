package ar.com.cdmoraleda.alkemychallenge.security.services;

import ar.com.cdmoraleda.alkemychallenge.security.dto.ApiUserDto;
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


@Service
public class EmailService {
    private String apiKey = "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";
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
        SendGrid sg = new SendGrid(apiKey);
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
