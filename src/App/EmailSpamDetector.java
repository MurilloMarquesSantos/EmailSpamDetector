package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EmailSpamDetector {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader("email5.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read or find the file");
        }

        String email = sb.toString().trim();

        String replacedEmail = email.replaceAll("\\W", " ");

        Scanner sc = new Scanner(replacedEmail);
        sc.useDelimiter("\\s");

        int spamCount = 0;
        while (sc.hasNext()) {
            String token = sc.next();
            if (token.equalsIgnoreCase("won") || token.equalsIgnoreCase("money")) {
                spamCount++;
                if (spamCount == 2) {
                    System.out.println("Email: " + email);
                    System.out.println("Spam detected.");
                    System.out.println("Words: \"won\" or \"cash\" are detected in this email.");
                    break;
                }
            }
        }
        switch (spamCount) {
            case 1:
                System.out.println("Email: " + email);
                System.out.println("Just one appearance, not classified as spam.");
                break;
            case 0:
                System.out.println("Email: " + email);
                System.out.println("None spam detected");
                break;
        }


    }
}
