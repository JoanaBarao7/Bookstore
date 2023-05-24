package uniquewords;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueWordsFinder {
    public static void main(String[] args) {

        File sourceFile = new File("src/main/resources/UniqueWordFinder.txt");
        File targetFile = new File("src/main/resources/target.txt");

        try {
            String inputText = FileUtils.readFileToString(sourceFile, StandardCharsets.UTF_8);
            List<String> words = List.of(StringUtils.split(inputText));
            Map<String, Integer> frequencyMap = new HashMap<>();

            for (String word : words) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }

            StringBuilder outputTextBuilder = new StringBuilder();
            outputTextBuilder.append("Number of unique words: ").append(frequencyMap.keySet().size()).append("\n");

            for (String word : frequencyMap.keySet()) {
                int frequency = frequencyMap.get(word);
                outputTextBuilder.append(word).append(": ").append(frequency).append("\n");
            }

            FileUtils.writeStringToFile(targetFile, outputTextBuilder.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
