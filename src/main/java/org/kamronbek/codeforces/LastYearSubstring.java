package org.kamronbek.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class LastYearSubstring {

    public static void main(String[] args) {
        FastScanner fs=new FastScanner();
        int T=fs.nextInt();
        for (int tt=0; tt<T; tt++) {
            int size = fs.nextInt();
            String str = fs.next();
            if (str.startsWith("2020", size - 4))
                System.out.println("YES");
            else if (str.charAt(0) == '2' && str.startsWith("020", size - 3))
                System.out.println("YES");
            else if (str.startsWith("20") && str.startsWith("20", size - 2))
                System.out.println("YES");
            else if (str.startsWith("202") && str.charAt(size - 1) == '0')
                System.out.println("YES");
            else if (str.startsWith("2020"))
                System.out.println("YES");
            else
                System.out.println("NO");

        }

    }

    static void sort(int[] a) {
        ArrayList<Integer> l=new ArrayList<>();
        for (int i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }

    static class FastScanner {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        int[] readArray(int n) {
            int[] a=new int[n];
            for (int i=0; i<n; i++) a[i]=nextInt();
            return a;
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }


}

