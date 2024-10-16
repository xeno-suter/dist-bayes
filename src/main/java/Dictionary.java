import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private final Map<String, Word> words;
    private int hamCount;
    private int spamCount;
    public static final double ALPHA = 0.05;

    public Dictionary() {
        this.words = new HashMap<>();
    }

    public void addWord(String word, int initialHamCount, int initialSpamCount) {
        words.put(word, new Word(word, initialHamCount, initialSpamCount));
        hamCount += initialHamCount;
        spamCount += initialSpamCount;
    }

    public Word getWord(String word) {
        return words.get(word);
    }

    public void incrementHam(String word) {
        words.get(word).incrementHam();
        hamCount++;
    }

    public void incrementSpam(String word) {
        words.get(word).incrementSpam();
        spamCount++;
    }

    public double getHamProbabilityOfWord(String word) {
        Word w = getWord(word);
        int hamWordCount = (w != null) ? w.getHamCount() : 0;
        return (hamWordCount + ALPHA) / (hamCount + ALPHA * (words.size() + 1));
    }

    public double getSpamProbabilityOfWord(String word) {
        Word w = getWord(word);
        int spamWordCount = (w != null) ? w.getSpamCount() : 0;
        return (spamWordCount + ALPHA) / (spamCount + ALPHA * (words.size() + 1));
    }

    public int size() {
        return words.size();
    }
}
