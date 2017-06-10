import java.io.*;
import java.util.*;

class Reader 
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) 
    {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException 
    {
        while ( ! tokenizer.hasMoreTokens() ) 
        {
          //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException 
    {
        return Integer.parseInt( next() );
    }

    static double nextDouble() 
    throws IOException 
    {
        return Double.parseDouble( next() );
    }

    static long nextLong() 
    throws IOException 
    {
        return Long.parseLong( next() );
    }
}

class Node
{
    public int data;
    public Node left;
    public Node right;
    public Node(int data, Node left, Node right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public Node getleft()
    {
        return left;
    }
    public Node getright()
    {
        return right;
    }
    public int getData()
    {
        return data;
    }
    public void setleft(Node x)
    {
        left = x;
    }
    public void setright(Node x)
    {
        right = x;
    }
    public void setdata(int x)
    {
        data = x;
    }

}

class VisitedLevels
{
    int visitedLevels;
    VisitedLevels()
    {
        visitedLevels = -1;
    }
}

public class lab8
{

    public Node insert (Node root, int value) 
    {
        if (root == null)
        {
            root = new Node (value, null, null);
        }
        else 
        {
            if (value < root.data)
                root.left = insert (root.left, value);
            else
                root.right = insert (root.right, value);
        }
        return root;
    }

    public void inorder(Node root) 
    {
        if (root == null) 
        {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public Node minNode (Node root) 
    {
       if (root.left == null)
           return root;
       else
           return minNode (root.left);
    }


    public Node delete (Node root, int x)
    {
        if (root == null)
            return null;
        if (root.data > x)
            root.left = delete (root.left, x);
        else if (root.data < x)
            root.right = delete (root.right, x);
        else
        {
            if (root.left != null && root.right != null)
            {
                //  Node temp = root;
                 Node minRight = minNode (root.right);
                 root.data = minRight.data;
                 root.right = delete (root.right, minRight.data);
            }

            else if (root.left != null)
                root = root.left;
            else if (root.right != null)
                root = root.right;
            else
                root = null;
        }
        return root;
    }
    
    
    static int level = 0;
    // static int VisitedLevels = -1;
    public static void rightprofile(Node root, VisitedLevels maxLevel)
    {
        if (root == null)
            return;
        else
        {
            if (level > maxLevel.visitedLevels)
            {
                System.out.print(root.data + " ");
                maxLevel.visitedLevels++;
            }
            level++;
            rightprofile(root.right, maxLevel);
            rightprofile(root.left, maxLevel);

            level--;
        }
    }


    public static void main(String[] args)
    throws IOException 
    {
        lab8 obj = new lab8();
        Reader.init(System.in);
        int N = Reader.nextInt();
        int Q = Reader.nextInt(); 
        Node root = new Node(Reader.nextInt(), null, null);
        for (int i=0;i<N-1;i++)
        {
            obj.insert(root, Reader.nextInt());
        }

        // obj.inorder(root);

        for (int i = 0; i < Q; i++) 
        {
            int a = Reader.nextInt();
            if (a == 1) 
            {
                int b = Reader.nextInt();
                root = obj.delete(root, b);
            } 
            else if (a == 2) 
            {
                VisitedLevels maxLevel = new VisitedLevels();
                obj.rightprofile(root, maxLevel);
                System.out.println();
            }
        }
        
        
        
    }
}

