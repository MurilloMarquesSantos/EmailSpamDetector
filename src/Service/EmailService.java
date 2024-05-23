package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EmailService {

    public static String readEmail(File file) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(" ");
            }

        } catch (IOException e) {
            throw new RuntimeException("Can´t read or find the file: " + file);
        }
        return sb.toString().trim();
    }

    public static String reshapedEmail(String email) {
        return email.replaceAll("\\W", " ");
    }

    public static int spamCount(String reshapedEmail) {
        Scanner sc = new Scanner(reshapedEmail);
        sc.useDelimiter(" ");

        int spamCount = 0;
        while (sc.hasNext()) {
            String token = sc.next();
            if (token.equalsIgnoreCase("won") || token.equalsIgnoreCase("money")) {
                spamCount++;
                if (spamCount == 2) {
                    break;
                }
            }
        }
        return spamCount;
    }

    public static void displaySpam(String email, int spamCount) {
        System.out.println("Email: " + email);
        switch (spamCount) {
            case 1:
                System.out.println("Just one spam occurrence, not classified as spam.");
                break;
            case 2:
                System.out.println("Spam detected");
                System.out.println("Words: \"won\" or \"money\" are detected in this email");
                break;
            case 0:
                System.out.println("None spam detected.");
                break;
        }
    }


}
