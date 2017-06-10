//Sharmistha S. Gupta
//2016193

import java.io.*;
import java.util.*;


public class lab9
{
    public static void main(String[] args)
    throws IOException
    {
        lab9 obj = new lab9();
        priorityqueue pq = new priorityqueue();
        Reader.init(System.in);
        int x = Reader.nextInt();
        int y = Reader.nextInt();
        int[] arr = new int[256];
        TreeNode[] array = new TreeNode[256];
        Tree tree = new Tree();
        for (int i = 0; i < x * y; i++)
        {
            int temp = Reader.nextInt();
            arr[temp]++;
        }
        if(x==1 && y==1)
        {
        	System.out.println(8.0);
        }
        else
        {

            int ptr =0;
            for (int i = 0; i<arr.length; i++)
            {   
                if (arr[i] > 0)
                {   
                    array[ptr] = new TreeNode(arr[i]);
                    pq.insert(array[ptr]);
                    ptr++;
                }
            }

            if(pq.nItems==1)
            {
                System.out.println(8.0);
            }
            else
            {
                tree.insert_tree(pq);
                tree.countbits(pq.arr[0], 0);

                float compression_ratio = (float)((x*y*8.0)/(tree.total));
                System.out.println((float)(Math.round(compression_ratio*10))/10);
            }
        }

        //beginning of part 2

        priorityqueue pq1 = new priorityqueue();
        Tree tree1 = new Tree();
        int count;
        for(int i = 0; i<=24; i+=1)
        {
            count =0;
            if(i!=24)
            {
                for (int j = i*10; j<=i*10+9; j++)
                {
                    if(j!=(i*10+2)&&arr[j]>0)
                    {
                        arr[i*10 + 2]+=arr[j];
                        arr[j]=0;
                    }
                }
            }
            else
            {

                for (int j =i*10; j<=i*10+15; j++)
                {
                    if(j!=(i*10+2)&&arr[j]>0)
                    {
                        arr[i*10 + 2]+=arr[j];
                        arr[j]=0;
                    }
                }
            }
        }

        if(x==1 && y==1)
        {
        	System.out.println(8.0);
        }
        else
        {

            int ptr1 =0;
            for (int i = 0; i<arr.length; i++)
            {   
                if (arr[i] > 0)
                {   
                    array[ptr1] = new TreeNode(arr[i]);
                    pq1.insert(array[ptr1]);
                    ptr1++;
                }
            }

            if(pq1.nItems==1)
            {
                System.out.println(8.0);
            }
            else
            {
                tree1.insert_tree(pq1);
                tree1.countbits(pq1.arr[0], 0);

                float compression_ratio = (float)((x*y*8.0)/(tree1.total));
                System.out.println((float)(Math.round(compression_ratio*10))/10);
            }
        }


    }
}

class priorityqueue
{
    public TreeNode[] arr;
    public int nItems;

    priorityqueue()
    {
        arr = new TreeNode[256];
        for(int i =0; i<256;i++)
        {
            arr[i] = new TreeNode();
        }
        nItems = 0;
    }

    public void insert(TreeNode node)
    {
        int j=0;
        if (nItems == 0)
        {   
            arr[nItems] = node;
            nItems++;
        }
        else
        {
            arr[nItems] = node;
            nItems++;
            for (j = nItems - 1; j>0; j--)
            {
                if(arr[j].data > arr[j-1].data)
                {
                    TreeNode temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                else
                {
                    break;
                }
            }
            // System.out.println(j+1);
        }
        // System.out.println(j);
        // System.out.println(nItems);
    }

    public TreeNode remove()
    {
    
        return arr[--nItems];
    }

    public void display()
    {
        for (int i = 0; i<nItems; i++)
        {
            System.out.println(arr[i].data + " ");
        }
    }

}

class TreeNode
{
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode()
    {
        this.data = 0;
        left = null;
        right = null;
    }

    public TreeNode(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }

    public void inOrder(TreeNode root)
    {
        if (root!=null) 
        {
            inOrder(root.left);
            System.out.print (" " + root.data); 
            inOrder(root.right);
        } 
    } 
}

class Tree
{
    int total;
    public void insert_tree(priorityqueue pq)
    {
        while (pq.nItems > 1)
        {
            TreeNode min_a = pq.remove();
            TreeNode min_b = pq.remove();
            TreeNode tmp = new TreeNode(min_a.data + min_b.data);
            tmp.right = min_a;
            tmp.left = min_b;
            pq.insert(tmp);
        }
    }

    public void countbits(TreeNode root, int count)
    {
        if (root.left == null && root.right == null)
        {
            total += count * root.data;
        }
        else
        {
            countbits(root.left, count+1);
            countbits(root.right, count+1);
        }
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
