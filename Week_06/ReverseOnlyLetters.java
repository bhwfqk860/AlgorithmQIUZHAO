package algorithm.week06;

public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {
        StringBuilder res = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0 ; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                res.append(S.charAt(j--));
            } else {
                res.append(S.charAt(i));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        new ReverseOnlyLetters().reverseOnlyLetters("ab-cd");
    }
}
