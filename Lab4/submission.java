//Sharmistha Swasti Gupta
//2016193
import java.io.*;
import java.util.*;

class Reader {
  static BufferedReader reader;
  static StringTokenizer tokenizer;

  /** call this method to initialize reader for InputStream */
  static void init(InputStream input) {
      reader = new BufferedReader(
                   new InputStreamReader(input) );
      tokenizer = new StringTokenizer("");
  }

  /** get next word */
  static String next() throws IOException {
      while ( ! tokenizer.hasMoreTokens() ) {
          //TODO add check for eof if necessary
          tokenizer = new StringTokenizer(
                 reader.readLine() );
      }
      return tokenizer.nextToken();
  }

  static int nextInt() throws IOException {
      return Integer.parseInt( next() );
  }

  static double nextDouble() throws IOException {
      return Double.parseDouble( next() );
  }
  
  static long nextLong() throws IOException {
      return Long.parseLong( next() );
  }
}

class Node
{
    String data;
    Node link;

    public Node (String d, Node l)
    {
        data = d;
        link = l;
    }

    public String getData()
    {
        return data;
    }
    
    public Node getLink()
    {
        return link;
    }

    public void setLink(Node k)
    {
        link = k;
    }

}

public class lab4
{
    int M;
    Node front;
    Node front1;
    public static void main(String[] args)
    throws IOException 
    {
        lab4 obj1 = new lab4();
        Reader.init(System.in);
        obj1.M = Reader.nextInt();
        int Q = Reader.nextInt();
        String a  = "";
        String[] arr = new String[obj1.M];
        for (int i=0;i<obj1.M;i++)
            {
                a = Reader.next();
                arr[i] = a;
            }
        String s;
        for (int i = arr.length - 1; i >= 0 ; i--) //creating the list
        {
            s = arr[i];
            Node nptr = new Node (s, null);
            if (obj1.front == null)
            {
                obj1.front = nptr;
            }
            else
            {
                nptr.setLink(obj1.front);
            }
            obj1.front = nptr;
        }
        int s1;
        String v;
        int p;
        int k;
        for (int i=0;i<Q;i++)
        {
            s1 = Reader.nextInt();
            if (s1 == 1)
            {
                v = Reader.next();
                p = Reader.nextInt();
                obj1.insert(v,p,obj1.M);
                obj1.M = obj1.M+1;
                //System.out.println(obj1.M);
            }
            else if (s1 == 2) 
            {
                p = Reader.nextInt();
                obj1.delete(p,obj1.M);
                obj1.M = obj1.M-1;
            }
            else if (s1 == 3)
            {
                obj1.display();
            }
            else if (s1 == 4) 
            {
                k = Reader.nextInt();
                obj1.compare(k,obj1.M);
            }
        }
    }

    public void insert(String v , int p,  int M)       
    {
        Node nptr = new Node(v, null);                
        Node ptr = front;
        if (p==1) // inserting at the beginnning of the list
        {
            if (front == null)
                front = nptr;
            else
                nptr.setLink(front);
                front = nptr;
        }
        else if (p==M+1) //inserting at the end position
        {
            ptr = front;
            while (ptr.getLink( )  != null)
            {
                ptr = ptr.getLink( );
            }
            ptr.setLink(nptr);
        }
        else
        {
            p = p - 1 ;
            for (int i = 1; i < M; i++)             
            {
                if (i == p)                 
                {
                    Node tmp = ptr.getLink() ;
                    ptr.setLink(nptr);
                    nptr.setLink(tmp);
                    break;
                }
                ptr = ptr.getLink();
            }
        }
    }

    public void delete(int p, int M)       
    {
        Node prev = front;
        Node cur = front.getLink();
        if(p==1) // deleting the first element
        {
            Node ptr = front.getLink();
            front = ptr;
        }
        else if(p==M) // deleting the last element
        {
            while ( cur.getLink() != null) 
            {
                prev = cur; 
                cur = cur.getLink();
            }
            prev.setLink(null);
        }
        else
        {
            for (int i = 1; i < M; i++)
            {
                if (i + 1!= p)
                {
                    prev = cur; 
                    cur = cur.getLink();  
                
                }
                else
                {
                    prev.setLink(cur.getLink());
                    cur=cur.getLink();
                    break;
                }
            }
        }
    }

    public void display()
    {
        //System.out.print("Hi");
        Node ptr = front;
        while (ptr != null)
        {
            System.out.print(ptr.getData() + " ");
            ptr=ptr.getLink();
        }
        System.out.println();
    }

    public void compare(int k, int M)
    throws IOException
    {
        String c;
        String[] arr2 = new String[k];
        for (int i=0;i<k;i++)
        {
            c = Reader.next();
            arr2[i] = c;
        } 
        for (int i=arr2.length - 1; i >= 0 ; i--) //creating the list
        {
            String n = arr2[i];
            Node nptr = new Node (n, null);
            if (front1 == null)
            {
                front1 = nptr;
            }
            else
            {
                nptr.setLink(front1);
            }
            front1 = nptr;
        }
        //System.out.print(M);
        //System.out.print(k);
        if (M != k) //lengths of lists are different
        {
            System.out.print(0);
            System.out.println();
        }
        
        else
        {
            Node ptr1 = front;
            Node ptr2 = front1;
            int count = 0;
            while (ptr1.getLink() != null)
            {
                if (ptr1.getData() .equals(ptr2.getData()))// compares every element of both lists
                {
                    count++;
                }
                ptr1 = ptr1.getLink();
                ptr2 = ptr2.getLink();
            }
            if (ptr1.getLink() == null)
            {
                if (ptr1.getData() .equals(ptr2.getData()))
                {
                    count++;
                }
            }
            //System.out.print(count);
            if (count == M)
            {
                System.out.print(1);
                System.out.println();
            }
            
            else
            {
                System.out.print(0);
                System.out.println();
            }
        }
    }
}




