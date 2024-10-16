import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Train the classifier with HAM and SPAM data from the training directories
        Trainer train = new Trainer();
        train.learnDirectory("src/main/resources/ham-anlern", ClassificationType.HAM);
        train.learnDirectory("src/main/resources/spam-anlern", ClassificationType.SPAM);

        // Create a spam filter using the trained dictionary
        SpamFilter filter = new SpamFilter(train.getDictionary());

        // Perform the classification for calibration and testing
        classifyAndPrintResults(filter, "src/main/resources/spam-kallibrierung", "src/main/resources/ham-kallibrierung", "kallibrierung");
        classifyAndPrintResults(filter, "src/main/resources/spam-test", "src/main/resources/ham-test", "Test");

        // Output additional configuration values
        System.out.println("Alpha: " + Dictionary.ALPHA);
        System.out.println("Threshold: " + SpamFilter.THRESHOLD);
    }

    private static void classifyAndPrintResults(SpamFilter filter, String spamDir, String hamDir, String phase) throws IOException {
        int[] spamResults = classifyFiles(filter, spamDir, ClassificationType.SPAM);
        int[] hamResults = classifyFiles(filter, hamDir, ClassificationType.HAM);

        double spamPrecision = calculatePrecision(spamResults[0], spamResults[1]);
        double hamPrecision = calculatePrecision(hamResults[0], hamResults[1]);

        System.out.println(phase + " Spam Precision: " + spamPrecision + " " + spamResults[0] + "/" + spamResults[1]);
        System.out.println(phase + " Ham Precision: " + hamPrecision + " " + hamResults[0] + "/" + hamResults[1]);
    }

    private static int[] classifyFiles(SpamFilter filter, String dirPath, ClassificationType expectedType) throws IOException {
        int countCorrect = 0;
        int total = 0;

        File[] files = new TextFileReader().listFiles(dirPath);

        for (File file : files) {
            ClassificationType classification = filter.classifyMail(file);
            if (classification == expectedType) {
                countCorrect++;
            }
            total++;
        }

        return new int[]{countCorrect, total};
    }

    private static double calculatePrecision(int correct, int total) {
        return (double) correct / total;
    }
}