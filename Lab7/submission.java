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
  public Node link;
  public Node(int data, Node link)//constructor
  {
    this.data = data;
    this.link = link;
  }
  public Node getLink()
  {
    return link;
  }
  public int getData()
  {
    return data;
  }
  public void setLink(Node x)
  {
    link = x;
  }
  public void setdata(int x)
  {
    data = x;
  }
}


class stack
{
	private int size = 0;
	private Node top = null;

	public void push(int data)
	{
		Node n = new Node(data,null);
		n.setLink(top);
		top = n;
		size++;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public int pop()
	{
		if(isEmpty())
		{
			return -1;
		}
		else
		{
			int x = top.getData();
			top = top.getLink();
			size--;
			return x;
		}
	}

	public int peek()
	{
		if(isEmpty())
		{
			return -1;
		}
		else
		{
			return top.getData();
		}
	}
}

public class lab7
{
  //reversing the stack to access elements in the specified order
	public static stack reverse(stack x)
	{
		stack s = new stack();
		while(!x.isEmpty())
		{
			s.push(x.pop());
		}
		return s;
	}

	public static stack add(stack a, stack b)
	{
		stack ans = new stack();
		int carry = 0;
		while(!a.isEmpty() && !b.isEmpty())
		{
			int x = a.pop() + b.pop() + carry;
			carry = x/10;
			ans.push(x%10);
		}
		while(!a.isEmpty())
		{
			int x = a.pop() + carry;
			carry = x/10;
			ans.push(x%10);
		}
		while(!b.isEmpty())
		{
			int x = b.pop() + carry;
			carry = x/10;
			ans.push(x%10);
		}
		if(carry>0)
		{
			ans.push(carry);
		}
		return ans;
	}

	public static stack multiply(stack a, stack b)
	{
		stack ans = new stack();
		stack ans1 = new stack();
		int count = 0;
		while(!b.isEmpty())
		{
			int x = b.pop();
			int carry = 0;
			stack s = new stack();
			while(!a.isEmpty())
			{
				s.push(a.pop());
			}
			stack c = new stack();
			while(!s.isEmpty())
			{
				int temp = s.pop();
				c.push(temp);
				a.push(temp);
			}
			for(int i=0;i<count;i++)
			{
				ans1.push(0);
			}
			while(!c.isEmpty())
			{
				int prod = x*c.pop() + carry;
				carry = prod / 10;
				ans1.push(prod%10);
			}
			if(carry>0)
			{
				ans1.push(carry);
			}
			count++;
			ans1 = reverse(ans1);
			ans = reverse(ans);
			ans = add(ans,ans1);
		}
		return ans;
	}

	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int n = Reader.nextInt();
		stack ans = new stack();
		ans.push(1);
		for(int i=n;i>=2;i--)
		{
			int j = i;
			stack xS = new stack();
			while(j>0)
			{
				xS.push(j%10);
				j = j / 10;
			}
			xS = reverse(xS);
			ans = reverse(ans);
			ans = multiply(ans,xS);
		}
		while(!ans.isEmpty())
		{
			System.out.print(ans.pop());
		}
	}
}
