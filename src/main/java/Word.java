/**
 * Represents a word in the training data.
 * Contains the word itself, the number of times it appears in ham emails, and the number of times it appears in spam emails.
 */
public class Word {
    private static final int INCREMENT_SIZE = 1;

    private final String word;
    private int hamCount;
    private int spamCount;

    public Word(String word, int initialHamCount, int initialSpamCount) {
        this.word = word;
        this.hamCount = initialHamCount;
        this.spamCount = initialSpamCount;
    }

    public String getWord() {
        return word;
    }

    public int getHamCount() {
        return hamCount;
    }

    public int getSpamCount() {
        return spamCount;
    }

    public void incrementHam() {
        this.hamCount += INCREMENT_SIZE;
    }

    public void incrementSpam() {
        this.spamCount += INCREMENT_SIZE;
    }

    @Override
    public String toString() {
        return String.format("Word: '%s', Ham Count: %d, Spam Count: %d", word, hamCount, spamCount);
    }
}
