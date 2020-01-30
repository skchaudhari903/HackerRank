import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       // String n = scanner.nextLine();
        Scanner scan = new Scanner(System.in);
        BigInteger n = scan.nextBigInteger();
       System.out.println(n.isProbablePrime(1) ? "prime" :"not prime");
    }
}