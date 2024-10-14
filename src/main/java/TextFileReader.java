import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFileReader implements MailReader {
    @Override
    public File[] listFiles(String folderPath) {
        File folder = new File(folderPath);

        if(!folder.exists()) {
            throw new RuntimeException("There is no folder with that path");
        }

        if(!folder.isDirectory()) {
            throw new RuntimeException("Path is not a folder");
        }

        return folder.listFiles();
    }

    @Override
    public void getFileWords(File file, ClassificationType type, Dictionary dictionary) throws IOException {
        List<String> words = this.getWords(file);

        for (String word : words) {
            Word dictionaryWord = dictionary.getWord(word);

            // add the word with 1 in ham or spam
            if (dictionaryWord == null) {
                int initialHamCount = type == ClassificationType.HAM ? 1 : 0;
                int initialSpamCount = type == ClassificationType.SPAM ? 1 : 0;
                dictionary.addWord(word, initialHamCount, initialSpamCount);
                continue;
            }

            // increment the ham or spam when the word does exist
            if (type == ClassificationType.HAM) {
                dictionaryWord.incrementHam();
            } else {
                dictionaryWord.incrementSpam();
            }
        }
    }

    @Override
    public List<String> getWords(File file) throws IOException {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Collections.addAll(words, line.split(" "));
            }
        }

        return words;
    }
}
