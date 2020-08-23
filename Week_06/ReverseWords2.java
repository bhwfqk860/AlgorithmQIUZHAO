package algorithm.week06;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWords2 {

    public String reverseWords(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public static void main(String[] args) {
        new ReverseWords2().reverseWords("the sky is blue");
    }
}
