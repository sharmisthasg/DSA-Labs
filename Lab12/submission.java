import java.io.*;
import java.util.*;
import java.lang.*;

public class lab12
{
    public static void main(String[] args)
    throws IOException
    {
        Reader.init(System.in);
        int X = Reader.nextInt();
        int src = Reader.nextInt(); // source city number
        int des = Reader.nextInt(); // destination city number
        int t1 = 0; // minimum start time
        int t2 = 0; // maximun end time
        String temp1 = Reader.next();
        String temp2 = Reader.next();
        // System.out.println(Character.getNumericValue(temp1.charAt(0)));
        if (temp1.length() == 4 && temp2.length() == 4)
        {
            t1 = (60* ((10*(Character.getNumericValue(temp1.charAt(0)))) + (Character.getNumericValue(temp1.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp1.charAt(2)))) + (Character.getNumericValue(temp1.charAt(3))); 
            t2 = (60* ((10*(Character.getNumericValue(temp2.charAt(0)))) + (Character.getNumericValue(temp2.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp2.charAt(2)))) + (Character.getNumericValue(temp2.charAt(3)));
        }

        else if (temp1.length() == 4 && temp2.length() == 3)
        {
            t1 = (60* ((10*(Character.getNumericValue(temp1.charAt(0)))) + (Character.getNumericValue(temp1.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp1.charAt(2)))) + (Character.getNumericValue(temp1.charAt(3))); 
            t2 = 60* ((Character.getNumericValue(temp2.charAt(0))) - 5) + (10*(Character.getNumericValue(temp2.charAt(1)))) + (Character.getNumericValue(temp2.charAt(2)));
        }

        else if (temp1.length() == 3 && temp2.length() == 4)
        {
            t1 = 60* ((Character.getNumericValue(temp1.charAt(0))) - 5) + (10*(Character.getNumericValue(temp1.charAt(1)))) + (Character.getNumericValue(temp1.charAt(2))); 
            t2 = (60* ((10*(Character.getNumericValue(temp2.charAt(0)))) + (Character.getNumericValue(temp2.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp2.charAt(2)))) + (Character.getNumericValue(temp2.charAt(3)));
        }

        else if (temp1.length() == 3 && temp2.length() == 3)
        {
            t1 = 60* ((Character.getNumericValue(temp1.charAt(0))) - 5) + (10*(Character.getNumericValue(temp1.charAt(1)))) + (Character.getNumericValue(temp1.charAt(2)));
            t2 = 60* ((Character.getNumericValue(temp2.charAt(0))) - 5) + (10*(Character.getNumericValue(temp2.charAt(1)))) + (Character.getNumericValue(temp2.charAt(2)));
        }
        
        // System.out.println(t1);
        // System.out.println(t2);
        int N = Reader.nextInt(); //number of flights

        

        Node[] array = new Node[N + X];
        for (int i = 0; i< N; i++)
        {
            Node temp = new Node();
            array[i] = temp;
            temp.setc1(Reader.nextInt());
            temp.setc2(Reader.nextInt());
            temp.setf(Reader.next());
            temp1 = Reader.next();
            temp2 = Reader.next();

            int d = 0;
            int a = 0;

            if (temp1.length() == 4 && temp2.length() == 4)
            {
                d = (60* ((10*(Character.getNumericValue(temp1.charAt(0)))) + (Character.getNumericValue(temp1.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp1.charAt(2)))) + (Character.getNumericValue(temp1.charAt(3))); 
                a = (60* ((10*(Character.getNumericValue(temp2.charAt(0)))) + (Character.getNumericValue(temp2.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp2.charAt(2)))) + (Character.getNumericValue(temp2.charAt(3)));
            }

            else if (temp1.length() == 4 && temp2.length() == 3)
            {
                d = (60* ((10*(Character.getNumericValue(temp1.charAt(0)))) + (Character.getNumericValue(temp1.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp1.charAt(2)))) + (Character.getNumericValue(temp1.charAt(3))); 
                a = 60* ((Character.getNumericValue(temp2.charAt(0))) - 5) + (10*(Character.getNumericValue(temp2.charAt(1)))) + (Character.getNumericValue(temp2.charAt(2)));
            }

            else if (temp1.length() == 3 && temp2.length() == 4)
            {
                d = 60* ((Character.getNumericValue(temp1.charAt(0))) - 5) + (10*(Character.getNumericValue(temp1.charAt(1)))) + (Character.getNumericValue(temp1.charAt(2))); 
                a = (60* ((10*(Character.getNumericValue(temp2.charAt(0)))) + (Character.getNumericValue(temp2.charAt(1))) - 5)) + (10*(Character.getNumericValue(temp2.charAt(2)))) + (Character.getNumericValue(temp2.charAt(3)));
            }

            else if (temp1.length() == 3 && temp2.length() == 3)
            {
                d = 60* ((Character.getNumericValue(temp1.charAt(0))) - 5) + (10*(Character.getNumericValue(temp1.charAt(1)))) + (Character.getNumericValue(temp1.charAt(2)));
                a = 60* ((Character.getNumericValue(temp2.charAt(0))) - 5) + (10*(Character.getNumericValue(temp2.charAt(1)))) + (Character.getNumericValue(temp2.charAt(2)));
            }

            
            temp.setd(d);
            temp.seta(a);
            temp.setduration();
        }

        //System.out.println(array[7].getduration());

        graph theGraph = new graph(N+X);

        for (int i = 1; i<=X; i++)
        {
            Node nodezero = new Node();
            if (i == src || i == des)
            {
                nodezero = new Node(i,i,"",0, 0); //creating nodes (1,1) , (2,2) and so on..
            }
            else
            {
                nodezero = new Node(i,i,"",0, 6000); //creating nodes (1,1) , (2,2) and so on..
            }
            
            array[N + i - 1] = nodezero; // added them to my array
        }

        //System.out.println(array.length);

        //System.out.println(array[N+X-1].getc2());

        //System.out.println(array.length);

        for (int i =0; i<N+X; i++)
        {
            theGraph.addNode(array[i]);
        }

        for (int i=0; i<N+X; i++)
        {
            for (int j=0; j<N+X; j++)
            {
                // System.out.print(array[i].getc1());
                // System.out.print(array[i].getc2());
                // System.out.print(array[j].getc1());
                // System.out.print(array[j].getc2());
                // System.out.println();
                if (array[i].getc1() == array[i].getc2() && array[i].getc2() == array[j].getc1() && i != j)
                {
                    theGraph.addEdge(i, j, 0);//adding edges with weight 0
                }
                else if (array[i].getc1() != array[i].getc2() && array[i].getc2() == array[j].getc1() && i != j && array[j].getc1()==array[j].getc2())
                {
                    theGraph.addEdge(i, j,0); //adding weight 0
                }
                else if (array[i].getc1() != array[i].getc2() && array[i].getc2() == array[j].getc1() && i != j && array[j].getd() >= array[i].geta()) //departure time and arrival time should be suitable
                {
                    theGraph.addEdge(i, j, array[j].getd() - array[i].geta()); //adding remaining edges
                }
                
            }
        }
        // for(int i =0;i<N+X;i++)
        // {
        //     for(int j=0;j<N+X;j++)
        //     {
        //         System.out.print(theGraph.matrix[i][j]+" ");
        //     }
        //     System.out.println();
        // }


        //theGraph.dfs(1, N+X);

        // for (int i = 1; i<=N+X; i++)
        // {
        //     if (theGraph.Nodelist[i-1].visited == false)
        //     {
        
        //         theGraph.dfs(i,N+X);
        //     }
        // }


        Dijkstra obj = new Dijkstra();

        src = N + src - 1;
        //des = N + des - 1;

        int[] temparray = obj.dijkstra(theGraph, src, des, t1, t2, N);
        
        des = N + des - 1;
        if(temparray[des]!=-1)
        obj.printPath(theGraph, temparray, src, des);
        else
        System.out.println(-1);
        //System.out.println(theGraph.isConnected(1,3));

    }

}



class Node
{
    public int c1;
    public int c2;
    public String f;
    public int d;
    public int a;
    public int duration;
    public boolean visited;

    public Node()
    {
        this.c1 = 0;
        this.c2 = 0;
        this.f = "";
        this.d = 0;
        this.a = 0;
        this.duration = 0;
        visited = false;
    }

    public Node(int c1, int c2, String f, int d, int a)
    {
        this.c1 = c1;
        this.c2 = c2;
        this.f = f;
        this.d = d;
        this.a = a;
        this.duration = a - d;
        visited = false;
    }

    public int getc1()
        {return c1;}
    public void setc1(int x)
        {c1 = x;}

    public int getc2()
        {return c2;}
    public void setc2(int x)
        {c2 = x;}

    public String getf()
        {return f;}
    public void setf(String x)
        {f = x;}

    public int getd()
        {return d;}
    public void setd(int x)
        {d = x;}

    public int geta()
        {return a;}
    public void seta(int x)
        {a = x;}

    public int getduration()
        {return duration;}
    public void setduration()
        {duration = a - d;}
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

class graph
{
    public Node Nodelist[];
    public int matrix[][];
    public int no_of_nodes;
    public stack st;
    public stack st1;

    public graph(int x)
    {
        Nodelist = new Node[x+2];
        matrix = new int[x][x];
        no_of_nodes = 0;
        for (int i = 0; i<x; i++)
        {
            for (int j = 0; j<x; j++)
            {
                matrix[i][j] = 5000; //earlier 0 stood for no edge, now it stands for an edge with weight 0. 5000 stands for no edge.
            }
        }
        st = new stack(x);
    }

    public void addNode(Node node)
    {
        Nodelist[no_of_nodes] = node;
        no_of_nodes++;
    }

    public void addEdge (int start, int end, int weight)
    {
        matrix[start][end] = weight;
        //matrix[end][start] = weight;
    }

    public void displayNode(int k)
    {
        System.out.println(Nodelist[k-1].getf());
    }

    public void dfs(int k, int x)
    {
        //dfs starts at Node k
        Nodelist[k-1].visited = true;
        displayNode(k);
        st.push(k);
        while(! st.isEmpty())
        {
            int v = unvisitedNode(st.peek());
            if (v==-1)
            {
                st.pop();
            }
            else
            {
                Nodelist[v-1].visited = true;
                displayNode(v);
                st.push(v);
            }
        }
    
    }

    public int unvisitedNode(int k)
    {
        for (int i = 1; i<=no_of_nodes; i++)
        {
            if (matrix[k-1][i-1] != 5000 && Nodelist[i-1].visited == false) //unvisitedNode
            {
                return i;
            }
        }
        return -1;
    }

    public boolean isConnected(int i, int j) //index of Node in the array Nodelist 
    {
        if (matrix[i][j] != 5000)
        {
            return true;
        }
        return false;
    }

    public int[] neighbors(int index)
    {
        int[] myneighbours = new int[no_of_nodes];
        int no_of_neighbours = 0;
        for (int i = 0; i<no_of_nodes; i++)
        {
            if (isConnected(index, i))
            {
                myneighbours[no_of_neighbours++] = i;
            }
        }
        for (int i = 0;i<no_of_neighbours;i++){
        //System.out.println("Myneighbours" + myneighbours[i]);
        }
        return myneighbours;
    }

    public int getWeight(int i, int j)
    {
        return matrix[i][j]; //do i need another condition?
    }

}

class Dijkstra 
{

      // Dijkstra's algorithm to find shortest path from s to all other nodes
      public static int [] dijkstra (graph theGraph, int s, int des, int t1, int t2,int N) 
      {
         final int [] dist = new int [theGraph.no_of_nodes];  // shortest known distance from "s"
         final int [] pred = new int [theGraph.no_of_nodes];  // preceeding node in path
         final boolean [] visited = new boolean [theGraph.no_of_nodes]; // all false initially
   
         for (int i=0; i<dist.length; i++) 
         {
           //dist[i] = Integer.MAX_VALUE;
           dist[i]=5000;
         }
         dist[s] = 0;
  
         for (int i=0; i<dist.length; i++) 
         {
           int mv = minVertex(dist, visited, theGraph); 
           if(mv==-1)
           {
               pred[des+N-1]=-1;
               break;
           }
           if (theGraph.Nodelist[mv].getc1() == des && theGraph.Nodelist[mv].getc1() == des) 
           {
               break;
           }

           if (mv != -1)
           {  
            final int next = mv;
            //System.out.println("next"+next);
            visited[next] = true;
    
            // The shortest path to next is dist[next] and via pred[next].
    
            final int [] n = theGraph.neighbors (next);
            for (int j=0; j<n.length; j++) 
            {
                final int v = n[j];
                final int d = dist[next] + theGraph.getWeight(next,v) + theGraph.Nodelist[v].getduration();
                //if(next==5)
                //System.out.println(theGraph.getWeight(next,10)+"  "+ theGraph.Nodelist[10].getduration()+"predest");
                if(v!=des+N-1)
                {
                    if (theGraph.Nodelist[v].getd()>=t1 && theGraph.Nodelist[v].geta()<=t2 && dist[v] > d && d >=0) 
                    {
                        dist[v] = d;
                        pred[v] = next;
                    }
                }
                else
                {
                    if (dist[v] > d && d >=0) 
                    {
                        dist[v] = d;
                        pred[v] = next;
                    }
                }
            }
            //System.out.println(dist[10]+"dest");
           }
         }
        //  for (int i=0; i<pred.length; i++)
        //  {
        //      System.out.println(pred[i]);
        //  }
        return pred;  // (ignore pred[s]==0!)
     }
  
     private static int minVertex (int [] dist, boolean [] v, graph theGraph) 
     {
        //int x = Integer.MAX_VALUE;
        int x = 5000;
        int y = -1;   // graph not connected, or no unvisited vertices
        //System.out.println("dist[i]"+dist[3]+"i"+3);
        //System.out.println("dist[i]"+dist[5]+"i"+5);
        for (int i=0; i<dist.length; i++) 
        { //System.out.println("dist[i]"+dist[i]+"i"+i);
            //System.out.println("i"+i);
           if (!v[i] && dist[i]<x && dist[i] >= 0) 
           {
               y=i; 
               //System.out.println("y"+y);
               x=dist[i];
               //System.out.println("x"+x);
           }

           else if(y!=-1&&!v[i] && dist[i] == x && dist[i] >= 0)
           {
               if(theGraph.Nodelist[i].getd() < theGraph.Nodelist[y].getd())
               {
                   y=i;
                   x = dist[i];
               }
           }
        }
        //System.out.println("boo");
        return y;
     }
  

    public static void printPath (graph theGraph, int [] pred, int s, int e) 
    {
        final java.util.ArrayList<String> path = new java.util.ArrayList<String>();
        int x = e;
        while (x!=s) 
        {
           path.add(0, theGraph.Nodelist[x].getf());
           x = pred[x];
        }
        path.add(0, theGraph.Nodelist[s].getf());

        int count = 0;
        for(int i = 0; i < path.size(); i++) 
        {
            if ((path.get(i)).toString() != "")
            {
                count++;
            }
            
        } 

        System.out.println(count);

        System.out.print((path.get(0)).toString());

        for(int i = 1; i < path.size(); i++) 
        {
            System.out.print((path.get(i)).toString() + " ");
        } 
        System.out.println(); 
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
