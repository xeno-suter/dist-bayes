import java.util.HashMap;
import java.util.Map;

/**
 * A dictionary of words and their counts.
 * Stores the number of times a word has appeared in ham and spam emails.
 */
public class Dictionary {
    private final Map<String, Word> words;
    private int hamCount;
    private int spamCount;
    public static final double ALPHA = 0.07;

    public Dictionary() {
        this.words = new HashMap<>();
    }

    public void addWord(String word, double initialHamCount, double initialSpamCount) {
        words.put(word, new Word(word, initialHamCount, initialSpamCount));
        hamCount += initialHamCount;
        spamCount += initialSpamCount;
    }

    public Word getWord(String word) {
        return words.get(word);
    }

    public void incrementHam(String word) {
        // add word to map and increment count
        words.get(word).incrementHam();
        hamCount++;
    }

    public void incrementSpam(String word) {
        // add word to map and increment count
        words.get(word).incrementSpam();
        spamCount++;
    }

    public double getHamProbabilityOfWord(String word) {
        Word w = getWord(word);
        // if there is no word present or the word is not present in ham mails return alpha
        return w == null || w.getHamCount() == 0 ? ALPHA / (hamCount + ALPHA * words.size()) : w.getHamCount() / (hamCount + ALPHA * words.size());
    }

    public double getSpamProbabilityOfWord(String word) {
        Word w = getWord(word);
        // if there is no word present or the word is not present in spam mails return alpha
        return w == null || w.getSpamCount() == 0  ? ALPHA / (spamCount + ALPHA * words.size()) : w.getSpamCount() / (spamCount + ALPHA * words.size());
    }

    public int size() {
        return words.size();
    }
}
