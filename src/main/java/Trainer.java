import org.w3c.dom.Text;

import java.io.File;

public class Trainer {
    private final Dictionary dictionary;
    private final MailReader mailReader;
    
    public Trainer() {
        this.dictionary = new Dictionary();
        this.mailReader = new TextFileReader();
    }
    
    public void learnDirectory(String folderPath, ClassificationType type) {
        File[] files = mailReader.listFiles(folderPath);
        for (File file : files) {
            learnFile(file, type, mailReader);
        }
    }
    
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
