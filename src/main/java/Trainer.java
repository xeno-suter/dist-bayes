import java.io.File;

// Trainer class is responsible for learning the dictionary from the given files
public class Trainer {
    private final Dictionary dictionary;
    private final MailReader mailReader;
    
    public Trainer() {
        this.dictionary = new Dictionary();
        this.mailReader = new TextFileReader();
    }

    // Learn the files in the given folder
    public void learnDirectory(String folderPath, ClassificationType type) {
        File[] files = mailReader.listFiles(folderPath);
        for (File file : files) {
            learnFile(file, type, mailReader);
        }
    }

    // Learn the given file
    public void learnFile(File file, ClassificationType type, MailReader reader) {
        try {
            // fill increments with learning file
            reader.getFileWords(file, type, dictionary);
        } catch (Exception e) {
            throw new RuntimeException("Error reading file");
        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
