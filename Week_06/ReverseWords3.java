package algorithm.week06;

public class ReverseWords3 {

    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String str : arr) {
            res.append(new StringBuffer(str).reverse().toString() + " ");
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        new ReverseWords3().reverseWords("Let's take LeetCode contest");
    }
}
