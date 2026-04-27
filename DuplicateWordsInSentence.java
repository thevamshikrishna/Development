import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateWordsInSentence {
    public static void main(String[] args) {
        String input="Java is great and Java is powerful";



        Set<String> seen =new HashSet<>();

        String result= Arrays.stream(input.split("\\s+")).filter(word->!seen.add(word)).distinct().collect(Collectors.joining(", "));

        System.out.println(result);
    }
}
