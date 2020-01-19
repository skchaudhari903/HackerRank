import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        BigInteger first = new BigInteger(scan.next());
        BigInteger second = new BigInteger(scan.next());

        System.out.println(first.add(second));
        System.out.println(first.multiply(second));

    }
}