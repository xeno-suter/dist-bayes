import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Trainer train = new Trainer();
        train.learnDirectory("src/main/resources/ham-anlern", ClassificationType.HAM);
        train.learnDirectory("src/main/resources/spam-anlern", ClassificationType.SPAM);

        SpamFilter filter = new SpamFilter(train.getDictionary());

        int countActualSpamMail = 0;
        int countActualHamMail = 0;

        int totalSpamMail = 0;
        int totalHamMail = 0;

        File[] spamFiles = new TextFileReader().listFiles("src/main/resources/spam-kallibrierung");
        for (File file : spamFiles) {
            ClassificationType classification = filter.classifyMail(file);
            if (classification == ClassificationType.SPAM) {
                countActualSpamMail++;
            } else {
                countActualHamMail++;
            }
            totalSpamMail++;
        }

        File[] hamFiles = new TextFileReader().listFiles("src/main/resources/ham-kallibrierung");
        for (File file : hamFiles) {
            ClassificationType classification = filter.classifyMail(file);
            if (classification == ClassificationType.HAM) {
                countActualHamMail++;
            } else {
                countActualSpamMail++;
            }
            totalHamMail++;
        }

        double spamPrecision = (double) countActualSpamMail / (countActualSpamMail + countActualHamMail);
        double hamPrecision = (double) countActualHamMail / (countActualHamMail + countActualSpamMail);

        System.out.println("kallibrierung Spam Precision: " + spamPrecision + " " + countActualSpamMail + "/" + totalSpamMail);
        System.out.println("kallibrierung Ham Precision: " + hamPrecision + " " + countActualHamMail + "/" + totalHamMail);

        countActualSpamMail = 0;
        countActualHamMail = 0;

        totalSpamMail = 0;
        totalHamMail = 0;

        File[] spamFilesTest = new TextFileReader().listFiles("src/main/resources/spam-test");
        for (File file : spamFilesTest) {
            ClassificationType classification = filter.classifyMail(file);
            if (classification == ClassificationType.SPAM) {
                countActualSpamMail++;
            } else {
                countActualHamMail++;
            }
            totalSpamMail++;
        }

        File[] hamFilesTest = new TextFileReader().listFiles("src/main/resources/ham-test");
        for (File file : hamFilesTest) {
            ClassificationType classification = filter.classifyMail(file);
            if (classification == ClassificationType.HAM) {
                countActualHamMail++;
            } else {
                countActualSpamMail++;
            }
            totalHamMail++;
        }

        spamPrecision = (double) countActualSpamMail / (countActualSpamMail + countActualHamMail);
        hamPrecision = (double) countActualHamMail / (countActualHamMail + countActualSpamMail);

        System.out.println("Test Spam Precision: " + spamPrecision + " " + countActualSpamMail + "/" + totalSpamMail);
        System.out.println("Test Ham Precision: " + hamPrecision + " " + countActualHamMail + "/" + totalHamMail);
        System.out.println("Alpha: " + Dictionary.ALPHA);
        System.out.println("Threshold: " + SpamFilter.THRESHOLD);
    }
}