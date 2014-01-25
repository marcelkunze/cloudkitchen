import java.applet.*;
import java.awt.*;

public class IFS extends Applet {
	 	private double[] a,b,c,d,e,f,p;
	  	private static IFS currentIFS;
	 	private static String selection="Farn";
	 	private static int maxIter = 30000;

	 	public IFS() {}

	 	public IFS(double[] a,double[] b,double[] c,double[] d,
	 				double[] e,double[] f,double[] p)
	 	{
	 		this.a = a;
	 		this.b = b;
	 		this.c = c;
	 		this.d = d;
	 		this.e = e;
	 		this.f = f;
	 		this.p = p;
	 	}

	 	void draw(Graphics g)
	 	{
	 		double x,x1,y,pk, xs=40., ys=40.;
	 		int xd,yd;
	 		x = y = 0;
	 		for (int i=1; i<=maxIter; i++)
	 		{
	 		  int k;
	 		  pk = Math.random();
	 		  if (pk<= p[0]) k = 0;
	 		  else if (pk<=p[1]) k = 1;
	 		  else if (pk<=p[2]) k = 2;
	 		  else k = 3;
	 		  x1 = a[k]*x + b[k]*y + e[k];
	 		  y = c[k]*x + d[k]*y + f[k];
	 		  x = x1;
	 		  xd = (int)(x*xs+200.);
	 		  yd = (int)(400.-y*ys);
	 		  g.drawLine(xd,yd,xd,yd);
	 		}
	 	}

	 	public String getAppletInfo()
	 	{
	 		return "Name : IFS, Iterierte Funktionssysteme\r\n" +
	 		       "Autor: Marcel Kunze\r\n";
	 	}

	 	public String[][] getParameterInfo()
	 	{
	 		String[][] info =
	 		{
	 			{ "IFS", "String", "[Farn,Baum,Sierpinski,Koch]" },
	 			{ "MAX", "Int",    "Max. Zahl der Iterationen" },
	 		};
	 		return info;		
	 	}

	 	public void init()
	 	{
	 		// Auswerten der Parameter aus der HTML-Seite
	 		if (getParameter("IFS")!=null) IFS.selection = getParameter("IFS");
	 		if (getParameter("MAX")!=null) IFS.maxIter = Integer.parseInt(getParameter("MAX"));

	 		// Aufsetzen der Parameter zur Berechnung des IFS
	 		if (IFS.selection.equals("Farn"))
	 		{
	 		  double[] a = {0,0.197,-0.15,0.849};
	 		  double[] b = {0,-0.226,0.283,0.037};
	 		  double[] c = {0,0.226,0.26,-0.037};
	 		  double[] d = {0.16,0.197,0.237,0.849};
	 		  double[] e = {0,0,0,0};
	 		  double[] f = {0,1.6,0.44,1.6};
	 		  double[] p = {0.03,0.16,0.27,1.0};
	  		  currentIFS = new IFS(a,b,c,d,e,f,p);
	 		}
	 		else if (IFS.selection.equals("Baum"))
	 		{
	 		  double[] a = {-0.04,0.61,0.65,0.64};
	 		  double[] b = {0,0,0.18,-0.2};
	 		  double[] c = {-0.23,0,-0.3,0.32};
	 		  double[] d = {-0.65,0.31,0.48,0.56};
	 		  double[] e = {-0.08,0.07,0.74,-0.66};
	 		  double[] f = {0.26,3.5,0.39,0.9};
	 		  double[] p = {0.15,0.37,0.67,1};
	  		  currentIFS = new IFS(a,b,c,d,e,f,p);
	 		}
	 		else if (IFS.selection.equals("Sierpinski"))
	 		{
	 		  double[] a = {0.5,0.5,0.5,0};
	 		  double[] b = {0,0,0,0};
	 		  double[] c = {0,0,0,0};
	 		  double[] d = {0.5,0.5,0.5,0};
	 		  double[] e = {0,1,0.5,0};
	 		  double[] f = {0,0,0.5,0};
	 		  double[] p = {0.33,0.66,1,1};
	  		  currentIFS = new IFS(a,b,c,d,e,f,p);
	 		}
	 		else if (IFS.selection.equals("Koch"))
	 		{
	 		  double[] a = {0.3333,0.3333,0.1667,-0.1667};
	 		  double[] b = {0,0,-0.28867,0.28867};
	 		  double[] c = {0,0,0.28867,0.28867};
	 		  double[] d = {0.3333,0.3333,0.1667,0.1667};
	 		  double[] e = {0,0.6667,0.3333,0.6667};
	 		  double[] f = {0,0,0,0};
	 		  double[] p = {0.25,0.5,0.75,1};
	  		  currentIFS = new IFS(a,b,c,d,e,f,p);
	 		}
	 		else 
	 		{
	 			System.out.println(IFS.selection+" existiert nicht.");
	 		}

	 		resize(800, 480);
	 	}

	 	public void destroy()
	 	{
	 	}

	 	public void paint(Graphics g)
	 	{
	 		g.drawString("Iterierte Funktionssysteme: "+selection,0,20);
	 		if (currentIFS != null) currentIFS.draw(g);
	 	}

	 	public void start()
	 	{
	 	}
	 	
	 	public void stop()
	 	{
	 	}
}


