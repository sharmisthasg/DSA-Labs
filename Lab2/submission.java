import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class lab2 {
    int MAXN;
    int n;
    int number_of_elements;

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int MAXN = Reader.nextInt();
        int n = Reader.nextInt();
        int q = Reader.nextInt();
        int[] arr = new int[MAXN];
        lab2 obj = new lab2();
        obj.number_of_elements = n;
        for (int i = 0; i < n; i++) {
            arr[i] = Reader.nextInt();
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < q; i++) {
            a = Reader.nextInt();
            if (a == 1) {
                b = Reader.nextInt();
                obj.insert(arr, b, obj.number_of_elements, MAXN);
            } else if (a == 2) {
                b = Reader.nextInt();
                obj.delete(arr, b, obj.number_of_elements, MAXN);
            } else if (a == 3) {
                obj.display(arr, obj.number_of_elements);
            }
        }
    }


    public void insert(int[] arr, int x, int n, int MAXN)
            throws IOException {
        int number_of_operations = 0;
        if (n == MAXN) {
            number_of_elements = n;
        } else if (n < MAXN) {
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i] > x) {
                    arr[i + 1] = arr[i];
                    number_of_operations++;
                }
            }
            arr[n - number_of_operations] = x;
            number_of_elements++;
        }
        System.out.println(number_of_elements + " " + number_of_operations);
        
    }


    public void delete(int[] arr, int x, int n, int MAXN)
    throws IOException 
    {
        int number_of_operations = 0;
        for (int i = 0; i < n - 1; i++) 
        {
            if (arr[i] >= x) 
            {
                arr[i] = arr[i + 1];
                number_of_operations++;
            }
        }
        number_of_elements--;
        System.out.println(number_of_elements + " " + number_of_operations);
        
    }


    public void display(int[] arr, int n) throws IOException 
    {
        if (number_of_elements == 0) 
        {
            System.out.println();
        } 
        else 
        {
            for (int i = 0; i < number_of_elements; i++) 
            {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

    }


}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /**
     * call this method to initialize reader for InputStream
     */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /**
     * get next word
     */
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
