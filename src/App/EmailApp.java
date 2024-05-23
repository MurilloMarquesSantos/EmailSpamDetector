package App;

import Service.EmailService;

import java.io.File;

public class EmailApp {
    public static void main(String[] args) {
        String email = EmailService.readEmail(new File("email1.txt"));
        String reshapedEmail = EmailService.reshapedEmail(email);
        int spamCount = EmailService.spamCount(reshapedEmail);
        EmailService.displaySpam(email, spamCount);
    }
}
