import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class DictionaryApp {
    private static BinaryTree<Association<String, String>> dictionaryTree = new BinaryTree<>();

    public static void main(String[] args) {
        loadDictionary("diccionario.txt");
        translateText("texto.txt");
    }

    private static void loadDictionary(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.replace("(", "").replace(")", "").split(" ");
                if (parts.length == 2) {
                    dictionaryTree.insert(new Association<>(parts[0].toLowerCase(), parts[1].toLowerCase()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    private static void translateText(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : line.split("\\s+")) {
                    Association<String, String> searchResult = dictionaryTree.search(new Association<>(word.toLowerCase(), null));
                    if (searchResult != null) {
                        System.out.print(searchResult.getValue() + " ");
                    } else {
                        System.out.print("*" + word + "* ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error translating text: " + e.getMessage());
        }
    }
}
