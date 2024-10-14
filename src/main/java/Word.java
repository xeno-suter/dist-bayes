/**
 * Represents a word in the training data.
 * Contains the word itself, the number of times it appears in ham emails, and the number of times it appears in spam emails.
 */
public class Word {
    private static final double INCREMENT_SIZE = 1.0;

    private final String word;
    private double hamCount;
    private double spamCount;

    public Word(String word, double initialHamCount, double initialSpamCount) {
        this.word = word;
        this.hamCount = initialHamCount;
        this.spamCount = initialSpamCount;
    }

    public String getWord() {
        return word;
    }

    public double getHamCount() {
        return hamCount;
    }

    public double getSpamCount() {
        return spamCount;
    }

    public void incrementHam() {
        this.hamCount += INCREMENT_SIZE;
    }

    public void incrementSpam() {
        this.spamCount += INCREMENT_SIZE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word other = (Word) obj;
        return word.equalsIgnoreCase(other.word);
    }

    @Override
    public int hashCode() {
        return word.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Word: '%s', Ham Count: %.2f, Spam Count: %.2f", word, hamCount, spamCount);
    }
}
