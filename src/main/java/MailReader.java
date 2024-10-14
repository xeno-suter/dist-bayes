import java.io.File;
import java.io.IOException;
import java.util.List;

public interface MailReader {
    File[] listFiles(String folderPath);
    void getFileWords(File file, ClassificationType type, Dictionary dictionary) throws IOException;
    List<String> getWords(File file) throws IOException ;
}
