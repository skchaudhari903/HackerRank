import java.util.Scanner;

public class Solution {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        // a -> 97
        // z -> 122
        // A -> 65
        // Z -> 90
        int[] cache = new int[26];
        if (a.length() != b.length()) { return false; }
        for (int i = 0; i < a.length(); i++) {
            int index = (int) a.charAt(i);
            if (65 <= index && index <= 90) { index += 32; }
            index -= 97;
            cache[index]++;
        }

        for (int i = 0; i < b.length(); i++) {
            int index = (int) b.charAt(i);
            if (65 <= index && index <= 90) { index += 32; }
            index -= 97;
            cache[index]--;
        }

        for (int i = 0; i < 26; i++) {
            if (cache[i] != 0) { return false; }
        }

        return true;
    }

  public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
