import java.io.*;
import java.util.*;



public class lab10
{

    public static void main(String[] args)
    throws IOException
    {

       Reader.init(System.in);
       int x = Reader.nextInt();
       int y = Reader.nextInt();

        graph graph1 = new graph(x);


        for (int i = 1; i<=x; i++)
        {
            graph1.addVertex(i);
        }

        for (int i = 0; i<y; i++)
        {
            int a = Reader.nextInt();
            int b = Reader.nextInt();
            graph1.addEdge(a,b);
        }

        int count = 0;
        graph1.dfs(1,x);
        count++;
        //System.out.print(graph1.vertexlist[0].visited);

        for (int i = 1; i<=x; i++)
        {
            if (graph1.vertexlist[i-1].visited == false)
            {
        
                graph1.dfs(i,x);
                count++;
            }
        }


        System.out.println(count);
        int[] array1 = new int[count];
        for (int i = 0; i<count; i++)
        {
            array1[i] = graph1.array[i];
        }
        Arrays.sort(array1);
        for (int i = 0; i<count; i++)
        {
            System.out.print(array1[i] + " ");
        }
    }
}


class stack
{
	private int[] st;
    private int top;

    public stack(int x)
    {
        st = new int[x];
        top = -1;
    }

	public void push(int j)
	{
		st[++top] = j;
	}

	public boolean isEmpty()
	{
		return top == -1;
	}

	public int pop()
	{
		if(isEmpty())
		{
			return -1;
		}
		else
		{
			return st[top--];
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
			return st[top];
		}
	}
}

class vertex
{
    public int number;
    public boolean visited;

    public vertex(int number)
    {
        this.number = number;
        visited = false;
    }
}

class graph
{
    public vertex vertexlist[];
    public int matrix[][];
    public int no_of_vertices;
    public stack st;
    public stack st1;
    public int index = 0;
    int[] array = new int[500];
    int cnt;

    public graph(int x)
    {
        vertexlist = new vertex[x+2];
        matrix = new int[x+1][x+1];
        no_of_vertices = 0;
        for (int i = 1; i<=x; i++)
        {
            for (int j = 1; j<=x; j++)
            {
                matrix[i][j] = 0;
            }
        }
        st = new stack(x);
        st1 = new stack(x);
    }

    public void addVertex(int k)
    {
        vertexlist[no_of_vertices] = new vertex(k);
        no_of_vertices++;
    }

    public void addEdge(int i, int j)
    {
        matrix[i][j] = 1;
        matrix[j][i] = 1;
    }

    public void displayVertex(int k)
    {
        System.out.print(vertexlist[k-1].number);
        System.out.println();
    }

    public void dfs(int k, int x)
    {
        //dfs starts at vertex k
        vertexlist[k-1].visited = true;
        //displayVertex(k);
        st.push(k);
        cnt=1;
        while(! st.isEmpty())
        {
            int v = unvisitedVertex(st.peek());
            if (v==-1)
            {
                st.pop();
            }
            else
            {
                vertexlist[v-1].visited = true;
                //displayVertex(v);
                st.push(v);
                cnt++;
            }
        }


        array[index] = cnt;
        index++;
        

    }

    public int unvisitedVertex(int k)
    {
        for (int i = 1; i<=no_of_vertices; i++)
        {
            if (matrix[k][i] == 1 && vertexlist[i-1].visited == false)
            {
                return i;
            }
        }
        return -1;
    }
}

class Reader
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static void init(InputStream input)
    {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException
    {
        while ( ! tokenizer.hasMoreTokens() )
        {
            tokenizer = new StringTokenizer(reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException
    {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException
    {
        return Double.parseDouble( next() );
    }
}

