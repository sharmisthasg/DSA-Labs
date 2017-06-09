import java.io.*;
class lab3
{
	public static void main(String[] arg)
	throws Exception
	{
		BufferedReader n = new BufferedReader(new InputStreamReader(System.in));
		double k = Double.parseDouble(n.readLine());
		double startTime = System.currentTimeMillis();
		IFib(k);
		double endTime = System.currentTimeMillis();
		double TimeElapsed= (endTime-startTime)/1000.0; //finds time in seconds
		System.out.println(TimeElapsed);

		double startTime_1 = System.currentTimeMillis();
		RFib(k);
		double endTime_1 = System.currentTimeMillis();
		double TimeElapsed_1= (endTime_1-startTime_1)/1000.0; //finds time in seconds
		System.out.println(TimeElapsed_1);
		

		double startTime_2 = System.currentTimeMillis();
		CleverAlgoFib(k);
		double endTime_2 = System.currentTimeMillis();
		double TimeElapsed_2= (endTime_2-startTime_2)/1000.0; //finds time in seconds
		System.out.println(TimeElapsed_2);

	}
	public static double IFib(double n)
	{
		double temp;
		double a = 1;
		double b = 1;
		if (n==0)
		{
			b = 0;
		}
		else if (n==1)
		{
			b = 1;
		}
		else
		{
			
			for (double i=2;i<n;i++)
			{
				temp = b;
				b = a + b ;
				a = temp;
			}
		}
		return b % 2014;
	}


	public static double RFib(double n)
	{
		double b;
		if (n==0)
		{
			return 0;
		}
		else if (n==1)
		{
			return 1;
		}
		else
		{
			b = RFib(n-1) + RFib(n-2);
		}
		return b % 2014;
	}

	public static double[][] multiply(double[][] x,double[][] y)
	{
		double a = (x[0][0] * y[0][0]) + (x[0][1] * y[1][0]);
		double b = (x[0][0] * y[0][1]) + (x[0][1] * y[1][1]);
		double c = (x[1][0] * y[0][0]) + (x[1][1] * y[1][0]);
		double d = (x[1][0] * y[0][1]) + (x[1][1] * y[1][1]);
		x[0][0] = a;
		x[0][1] = b;
		x[1][0] = c;
		x[1][1] = d;
		return x;
	}

	public static double[][] power(double n)
	{
		double[][] x = {{1,1},{1,0}};
		double[][] z = new double[2][2];
		if (n==-1)
		{
			z[0][0] = 0;
			z[0][1] = 0;
			z[1][0] = 0;
			z[1][1] = 0;
			return z;
		}
		else if (n==0)
		{
			z[0][0] = 1;
			z[0][1] = 0;
			z[1][0] = 0;
			z[1][1] = 1;
			return z;
		}
		else if (n % 2==0)
		{
			double[][] k = power(n/2);
			z = multiply(k,k);
		}
		else if (n % 2!=0)
		{
			double[][] k = power(n/2);
			double[][] t = multiply(k,k);
			z = multiply(x,t);
		}
		return z;
	}

	public static double CleverAlgoFib(double n)
	{
		double[][] x = {{1,1},{1,0}};
		double[][] y = power(n-1);
		double b;
		b = y[0][0];
		return b % 2014;
	}
}
