
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpamFilter {
    private final Dictionary dictionary;
    private final MailReader mailReader;
    public static final double THRESHOLD = 0.5;

    public SpamFilter(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.mailReader = new TextFileReader();
    }

    public double calculateSpamProbability(File email) throws IOException {
        // we use logs because otherwise the number we be to tiny to work with double.
        double spamProbability = Math.log(0.5);
        double hamProbability = Math.log(0.5);

        List<String> words = mailReader.getWords(email);
        // Set<String> words = new HashSet<>(mailReader.getWords(email));

        // multiply (add because of math.log) the probability of each word to the overall probability
        for (String word : words){
            double wordSpamProbability = Math.log(dictionary.getSpamProbabilityOfWord(word));
            double wordHamProbability = Math.log(dictionary.getHamProbabilityOfWord(word));

            spamProbability += wordSpamProbability;
            hamProbability += wordHamProbability;
        }

        // used when not using math.log
//        double totalProbability = spamProbability + hamProbability;
//        return spamProbability / totalProbability; // 0 .. 1
        return 1 / (1 + Math.exp(hamProbability - spamProbability));
    }

    public ClassificationType classifyMail(File email) throws IOException {
        double spamProbability = calculateSpamProbability(email);
        // System.out.println(spamProbability);
        // check if the threshold does pass, higher means more ham, lower means more spam
        return spamProbability <= THRESHOLD ? ClassificationType.HAM : ClassificationType.SPAM;
    }
}
