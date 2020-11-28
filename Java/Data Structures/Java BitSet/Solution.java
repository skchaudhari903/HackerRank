import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        BitSet A = new BitSet(n);
        BitSet B = new BitSet(n);
        for (int i = 0; i < m; i++) {
            String query = scan.next();
            int x = scan.nextInt();
            int y = scan.nextInt();

            if (query.equals("AND")) {
                if (x == 1) {
                    A.and(B);
                } else {
                    B.and(A);
                }
            }

            if (query.equals("OR")) {
                if (x == 1) {
                    A.or(B);
                } else {
                    B.or(A);
                }
            }

            if (query.equals("XOR")) {
                if (x == 1) {
                    A.xor(B);
                } else {
                    B.xor(A);
                }
            }

            if (query.equals("FLIP")) {
                if (x == 1) {
                    A.flip(y);
                } else {
                    B.flip(y);
                }
            }

            if (query.equals("SET")) {
                if (x == 1) {
                    A.set(y);
                } else {
                    B.set(y);
                }
            }

            System.out.println(A.cardinality() + " " + B.cardinality());
        }
    }
}
