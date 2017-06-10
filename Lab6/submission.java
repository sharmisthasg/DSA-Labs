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

public class lab6
{
  int size;// size of input array
  int stack_size = 0;// size of the stack
  int output_size = 0;// size of the output array
  public static void main(String[] args)
  throws IOException 
  {
    Reader.init(System.in);
    lab6 obj = new lab6();
    int N = Reader.nextInt();
    obj.size = N;
    int M = N*2;
    char[] input = new char[M];// input array initialised
    for (int i = 0; i < N; i++)
    {
      String x = Reader.next();
      char character = x.charAt(0);
      input[i] = character;
      //System.out.print(input[i]);
    }
    //obj.display(input);
    char[] stack = new char[M];// stack initialised
    char[] output = new char[M];// output array initialised
    

    for (int i = 0;i < obj.size; i++)
      {
        if (input[i] == '0' || input[i] == '1' || input[i] == '2' || input[i] == '3' || input[i] == '4' || input[i] == '5' || input[i] == '6' || input[i] == '7' || input[i] == '8' ||  input[i] == '9')
        {
          obj.add(output,input[i]);// if digit is encountered, it is directly pushed to the output
        }
        
        if (input[i] == '(')
        {
          obj.push(stack,'(');// if opening bracket is encountered, it is directly pushed into the stack
        }
        
        if (input[i] == '-')  
        {
          if (obj.stack_size != 0)// checking stack size
            if (stack[obj.stack_size - 1] == '~' || stack[obj.stack_size - 1] == '+' || stack[obj.stack_size - 1] == '*' || stack[obj.stack_size - 1] == '-' || stack[obj.stack_size - 1] == '/')
            {
              while (stack[obj.stack_size - 1] != '(')
              {
                char tmp = obj.pop(stack);
                obj.add(output,tmp);
                if (obj.stack_size == 0)
                {
                  break;
                }
              }
              obj.push(stack,input[i]);
            }
            else
              obj.push(stack,input[i]); 
          else
            obj.push(stack,input[i]); 
        }

        if (input[i] == '/')
        {
          if (obj.stack_size != 0)
          {
            if (input[i - 1] == '(')//checking if it is a unary operator
            {
              obj.push(stack,'~');
            }

            else if (stack[obj.stack_size - 1] == '~' || stack[obj.stack_size - 1] == '+' || stack[obj.stack_size - 1] == '*' || stack[obj.stack_size - 1] == '-' || stack[obj.stack_size - 1] == '/')
            {
              while (stack[obj.stack_size - 1] != '(')
              {
                char tmp = obj.pop(stack);
                obj.add(output,tmp);
                if (obj.stack_size == 0)
                {
                  break;
                }
              }
              obj.push(stack,'/');
            }
            else
              obj.push(stack,'/');
          } 
          else
            obj.push(stack,'/'); 
        }

        if (input[i] == '+')
        {
          if (obj.stack_size != 0)
          {
            if (stack[obj.stack_size - 1] == '~' || stack[obj.stack_size - 1] == '+' || stack[obj.stack_size - 1] == '*')
            {
              char tmp1 = obj.pop(stack);
              obj.add(output,tmp1);
              while (obj.stack_size !=0 && (stack[obj.stack_size - 1] != '(' && stack[obj.stack_size - 1] != '-' && stack[obj.stack_size - 1] != '/' ))
              {
                char tmp = obj.pop(stack);
                obj.add(output,tmp);
                if (obj.stack_size == 0)
                {
                  break;
                }
              }
              obj.push(stack,'+');
            }
            else
              obj.push(stack,'+');
          }
          else
            obj.push(stack,'+');
        }

        if (input[i] == '*')
        {
          if (obj.stack_size != 0)
          { 
            if (stack[obj.stack_size - 1] == '~' || stack[obj.stack_size - 1] == '+' || stack[obj.stack_size - 1] == '*')
            {
              char tmp1 = obj.pop(stack);
              obj.add(output,tmp1);
              while (obj.stack_size !=0 && (stack[obj.stack_size - 1] != '(' && stack[obj.stack_size - 1] != '-' && stack[obj.stack_size - 1] != '/'))
              {
                char tmp = obj.pop(stack);
                obj.add(output,tmp);
                if (obj.stack_size == 0)
                {
                  break;
                }
              }
              obj.push(stack,'*');
            }
            else
              obj.push(stack,'*');
          }
          else
            obj.push(stack,'*');  
        }

        if (input[i] == ')')
        {
          while (stack[obj.stack_size - 1] != '(')
          {
            char tmp = obj.pop(stack);
            obj.add(output,tmp);
          }
          obj.pop(stack);
        }  
      }
      while (obj.stack_size > 0)// to check if any elements are left in the stack
      {
        char tmp = obj.pop(stack);
        obj.add(output,tmp);
      }    
    obj.display(output);// displays elements of the output array
  } 


  public void push(char[] stack,char x)
  {
    stack[stack_size] = x;
    stack_size++;
  }

  public char pop(char[] stack)
  {
    char cur = stack[stack_size - 1];
    stack_size--;
    return cur;
  }

  public void add(char[] output,char x)
  {
    output[output_size] = x;
    output_size++;
  }

  public void display(char[] output)
  {
    for ( int i = 0; i < output_size; i++ ) 
    {
      System.out.print(output[i] + " ");  
    }
  }
}
