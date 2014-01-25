package klima;
public class Klima
{
	Simulation s;
	double temperatur = 0.;
	String name;

	Klima(String Name,double TB,double TA,double R1,double R2,double R3,double T1,double T2)
	{
		name = Name;
		s = new Simulation(TB,TA,R1,R2,R3,T1,T2);
	}
	 
	public void berechne()
	{
		double t;
		do
		{
			t = temperatur;
			temperatur = s.iteration();
		}
		while (Math.abs(t - temperatur) > 0.001); 
	}

	double temperatur()
	{
		return temperatur;
	}

	String name()
	{
		return name;
	}


	public static void main(String[] argv)
	{
		Klima gegenwart = new Klima("Gegenwart",280.,320.,0.30,0.11,0.31,0.53,0.06);
		gegenwart.berechne();
		System.out.println(gegenwart.name()+":"+gegenwart.temperatur());

		Klima komet = new Klima("Komet   ",280.,320.,0.36,0.11,0.37,0.43,0.05);
		komet.berechne();
		System.out.println(komet.name()+":"+komet.temperatur());

		Klima treibhaus = new Klima("Treibhauseffekt",280.,320.,0.30,0.10,0.34,0.53,0.05);
		treibhaus.berechne();
		System.out.println(treibhaus.name()+":"+treibhaus.temperatur());
	}
}

class Simulation
{   
	// Physikalische Konstanten

	final static double sigma = 5.6703e-8;		// Stefan-Boltzmann-K.
	final static double S = 1360.;				// Solarkonstante(W/m^2)
	final static double c = 2.6;				// Konstante der Konvektion(W/m^2/K)

	// Simulationsparameter

	double Tb; // Anfangswert fuer Bodentemperatur
	double Ta; // Anfangswert fuer Atmosphaerentemperatur
	double r1; // Reflexionskoeff. Atmosphaere im kurzwell.Bereich
	double r2; // Reflexionskoeff. Boden im kurzwell.Bereich
	double r3; // Reflexionskoeff. Atmosphaere im langwell.Bereich
	double t1; // Transmissionskoeff. Atmosphaere im kurzwell.Bereich
	double t2; // Transmissionskoeff. Atmosphaere im langwell.Bereich

	Simulation(double TB,double TA,double R1,double R2,double R3,double T1,double T2)
	{
		Tb = TB; Ta = TA; r1 = R1; r2 = R2; r3 = R3; t1 = T1; t2 = T2;
	}

	// Newton-Raphson-Iteration
	double iteration() 
	{
		double det,delta1,delta2,ff,f1,f2,gg,g1,g2;
		ff = f(Tb,Ta,r1,r2,r3,t1,t2);
		gg = g(Tb,Ta,r2,r3,t1);				// Funktionswerte
		f1 = df1(Tb,r3,t2); f2 = df2(Ta);	// Part.Ableitungen nicht
		g1 = dg1(Tb,r3); g2 = dg2(Ta);		// abhaengig von allen Variablen
		det = f1*g2-f2*g1;					// Jacobi-Determinante der part.Ableit.
		delta1 = gg*f2 -ff*g2;				// Aenderung Tb
		delta2 = ff*g1 -gg*f1;				// Aenderung Ta
		Tb += delta1/det;					// Korrektur Tb
		Ta += delta2/det;					// Korrektur Ta
		return Tb;
	}

	// Energiebilanz der Atmosphaere
	double f(double Tb,double Ta,double r1,double r2,double r3,double t1,double t2)
	{
	  return -(1.-r1-t1+r2*t1)*S/4.-c*(Tb-Ta)-sigma*Math.pow(Tb,4.)*(1.-t2-r3)+2.*sigma*Math.pow(Ta,4.);
	}

	// Energiebilanz des Bodens
	double g(double Tb,double Ta,double r2,double r3,double t1)
	{
	  return (-t1)*(1-r2)*S/4.+c*(Tb-Ta)+sigma*Math.pow(Tb,4.)*(1.-r3)-sigma*Math.pow(Ta,4.);
	}

	// part.Ableitung nach df/dTb
	double df1(double Tb,double r3,double t2)
	{
	  return -c-4.*sigma*Math.pow(Tb,3.)*(1.-t2-r3);
	}

	// part.Ableitung nach df/dTa
	double df2(double Ta) 
	{
	  return +c+8.*sigma*Math.pow(Ta,3.);
	}

	// part.Ableitung nach dg/dTb
	double dg1(double Tb,double r3) 
	{
	  return +c+4.*sigma*Math.pow(Tb,3.)*(1.-r3);
	}

	// part.Ableitung nach dg/dTa
	double dg2(double Ta) 
	{
	  return -c-4.*sigma*Math.pow(Ta,3.);
	}
}

