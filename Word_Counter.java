import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringJoiner;

public class Word_Counter {

    public static void main(String[] args) {
        try {
            String UserText = getUserText();
            String[] Words = splitTextIntoWords(UserText);
            int totalWordCount = Words.length;
            Set<String> UniqueWords = CountUniqueWords(Words);
            Map<String, Integer> wordFrequency = CountWordFrequency(Words);

            System.out.println("TOTAL WORDS: " + totalWordCount);
            System.out.println("UNIQUE WORDS " + UniqueWords.size());
            System.out.println("WORD FREQENCIES : ");
            wordFrequency.forEach((word, frequency) -> System.out.println(word + ": " + frequency +
             " times"));
        } catch (IOException e) {
            System.err.println("AN ERROR OCCURED WHILE PROCESSING THE INPUT");
        }
    }

    private static String getUserText() throws IOException {
        System.out.print("ENTER A TEXT OR PROVIDE A FILE PATH : ");
        String Input;
        try (BufferedReader BR = new BufferedReader(new InputStreamReader(System.in))) {
            Input = BR.readLine();
        }
        if (Input.isEmpty()) {
            System.out.println("INPUT IS EMPTY.PLEASE PROVIDE TEXT OR A VALID FILE PATH");
            System.exit(1);
        }

        if (Input.toLowerCase().endsWith(".txt")) {
            return ReadTextFromFile(Input);
        } else {
            return Input;
        }
    }

    private static String ReadTextFromFile(String filePath) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder Text = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                Text.append(line).append(" ");
            }
            return Text.toString();
        }
    }

    private static String[] splitTextIntoWords(String text) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        StringJoiner Words = new StringJoiner(" ");

        while (matcher.find()) {
            Words.add(matcher.group());
        }

        return Words.toString().split(" ");
    }

    private static Set<String> CountUniqueWords(String[] words) {
        Set<String> UniqueWords = new HashSet<>();
        for (String word : words) {
            UniqueWords.add(word);
        }
        return UniqueWords;
    }

    private static Map<String, Integer> CountWordFrequency(String[] words) {
        Map<String, Integer> WordFrequency = new HashMap<>();
        for (String Word : words) {
            WordFrequency.put(Word, WordFrequency.getOrDefault(Word, 0) + 1);
        }
        return WordFrequency;
    }
}



