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
  public int data;
  public Node link;

  public Node(int data,Node link)
  {
    this.data = data;
    this.link = link;
  }

  public int getData()
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

public class lab5
{
  int N;
  Node front = null;
  Node end = null;
  public static void main(String[] args) 
  throws IOException
  {
    //lab5 list1 = new lab5();
    Reader.init(System.in);
    int T = Reader.nextInt();
    for (int j=0;j<T;j++)
    {
      lab5 list1 = new lab5();
      list1.N = Reader.nextInt();
      for (int i=list1.N;i>0;i--)
	  {
	    Node ptr=new Node(i,null);
	    if (list1.front == null)
	    {
	       list1.front = ptr;
	       list1.end = ptr;
	    }
	    else
	    {
	       ptr.setLink(list1.front);
	    }
	    list1.front = ptr;
	  }
	  list1.end.setLink(list1.front);
	  //sSystem.out.print(list1.end.getLink().getData());
	  //list1.display();
	  //double startTime = System.currentTimeMillis();
	  list1.delete(list1);
	  //double endTime = System.currentTimeMillis();
	  //double TimeElapsed= (endTime-startTime)/1000.0; //finds time in seconds
	  //System.out.println(TimeElapsed);

	  
	}
  }

  public void delete(lab5 list1)
  {
  	Node ptr = front;
  	Node cur = front.getLink();
  	int count=0;
 	while (list1.N!=1)
  	{
        ptr.setLink(cur.getLink());
        ptr = cur.getLink();
        cur=cur.getLink().getLink();
        list1.N--;
        count ++;
  	}
  	//System.out.print(ptr.getData());
  	//System.out.print(cur.getData());
  	//System.out.print(count);
  	System.out.println(ptr.getData());

  }

  /*public void display()
  {
    //System.out.print("Hi");
    Node ptr = front;
    for (int i=0;i<N;i++)
    {
      System.out.print(ptr.getData() + " ");
      ptr=ptr.getLink();
    }
    System.out.println();
  }
  */

}

      
  
