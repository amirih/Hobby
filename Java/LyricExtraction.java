import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LyricExtraction {
    private PrintWriter createFileWriter(String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        FileWriter appendableFileWriter = new FileWriter(path.toFile(), true);
        PrintWriter writer = new PrintWriter(appendableFileWriter);
        return writer;
    }

    public static void main(String[] args) throws IOException {
        /**
         * English sentence should be ended with a dot. when translating to persian
         * using google translate.
         */
        String englishFilePath = "./temp/english.txt";
        String persianFilePath = "./temp/persian.txt";
        String outpuString = "./temp/output.txt";
        PrintWriter writer = new LyricExtraction().createFileWriter(outpuString);
        try {
            List<String> englishSentenceList = Files.readAllLines(Paths.get(englishFilePath));
            List<String> persianSentenceList = Files.readAllLines(Paths.get(persianFilePath));
            for (String englishSentence : englishSentenceList) {
                String persianSentence = persianSentenceList.get(englishSentenceList.indexOf(englishSentence));
                writer.printf("%s \n %s\n", englishSentence, persianSentence);

            }

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
