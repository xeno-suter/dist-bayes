import java.io.File;
import java.io.IOException;

public class SpamFilter {
    private final Dictionary dictionary;
    private final MailReader mailReader;

    public static final double THRESHOLD = 0.8; // 0 .. 1 threshold for spam classification

    public SpamFilter(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.mailReader = new TextFileReader();
    }

    // Calculate the spam probability of the given email
    public double calculateSpamProbability(File email) throws IOException {
        double n = 0;
        for (String word : mailReader.getWords(email)) {
            double p = dictionary.getSpamProbabilityOfWord(word);
            double q = dictionary.getHamProbabilityOfWord(word);

            n += Math.log((1 - p) / (1 - q)) - Math.log(p / q);
        }

        return 1 / (1 + Math.exp(n));
    }

    // Classify the email as HAM or SPAM based on the threshold
    public ClassificationType classifyMail(File email) throws IOException {
        double spamProbability = calculateSpamProbability(email);
        // System.out.println(spamProbability);
        // check if the threshold does pass, higher means more ham, lower means more spam
        return spamProbability <= THRESHOLD ? ClassificationType.HAM : ClassificationType.SPAM;
    }
}
