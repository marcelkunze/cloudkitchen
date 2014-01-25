package main

import (
	"fmt"
	"math"
)

// Physikalische Konstanten

const Sigma = 5.6703e-8		// Stefan-Boltzmann-K.
const S = 1360.				// Solarkonstante(W/m^2)
const c = 2.6				// Konstante der Konvektion(W/m^2/K)


func main() {
	fmt.Println("Klimasimulation")
	fmt.Println("Gegenwart ", Simulation(280.,320.,0.30,0.11,0.31,0.53,0.06))
	fmt.Println("Komet     ", Simulation(280.,320.,0.36,0.11,0.37,0.43,0.05))
	fmt.Println("Treibhaus ", Simulation(280.,320.,0.30,0.10,0.34,0.53,0.05))
}

func Simulation (tb, ta, r1, r2, r3, t1, t2 float64) float64 {   
	// Simulationsparameter

	// tb Anfangswert fuer Bodentemperatur
	// ta Anfangswert fuer Atmosphaerentemperatur
	// r1 Reflexionskoeff. Atmosphaere im kurzwell.Bereich
	// r2 Reflexionskoeff. Boden im kurzwell.Bereich
	// r3 Reflexionskoeff. Atmosphaere im langwell.Bereich
	// t1 Transmissionskoeff. Atmosphaere im kurzwell.Bereich
	// t2 Transmissionskoeff. Atmosphaere im langwell.Bereich

	var temperatur = 0.
	
	for i:=0; i<100; i++ {
		temperatur = tb;
		tb, ta = iteration(tb, ta, r1, r2, r3, t1, t2)
		if math.Abs(tb - temperatur) < 0.001 { return tb }
	}
	
	return 0.	// Fehler: Keine Konvergenz	 
}


// Newton-Raphson-Iteration
func iteration(tb, ta, r1, r2, r3, t1, t2 float64) (float64, float64) {
		var det,delta1,delta2,ff,f1,f2,gg,g1,g2 float64
		ff = f(tb,ta,r1,r2,r3,t1,t2)
		gg = g(tb,ta,r2,r3,t1)				// Funktionswerte
		f1 = df1(tb,r3,t2)
		f2 = df2(ta)						// Part.Ableitungen nicht
		g1 = dg1(tb,r3)
		g2 = dg2(ta)						// abhaengig von allen Variablen
		det = f1*g2-f2*g1					// Jacobi-Determinante der part.Ableit.
		delta1 = gg*f2 -ff*g2				// Aenderung Tb
		delta2 = ff*g1 -gg*f1				// Aenderung Ta
		tb += delta1/det					// Korrektur Tb
		ta += delta2/det					// Korrektur Ta
		return tb, ta
}

// Energiebilanz der Atmosphaere
func f(tb, ta, r1, r2, r3, t1, t2 float64) float64 {
	return -(1.-r1-t1+r2*t1)*S/4.-c*(tb-ta)-Sigma*math.Pow(tb,4.)*(1.-t2-r3)+2.*Sigma*math.Pow(ta,4.)
}

// Energiebilanz des Bodens
func g(tb, ta, r2, r3, t1 float64) float64 {
	return (-t1)*(1-r2)*S/4.+c*(tb-ta)+Sigma*math.Pow(tb,4.)*(1.-r3)-Sigma*math.Pow(ta,4.)
}

// part.Ableitung nach df/dTb
func df1(tb, r3, t2 float64) float64 {
	return -c-4.*Sigma*math.Pow(tb,3.)*(1.-t2-r3)
}

// part.Ableitung nach df/dTa
func df2(ta float64) float64 {
	return +c+8.*Sigma*math.Pow(ta,3.)
}

// part.Ableitung nach dg/dTb
func dg1(tb, r3 float64) float64 {
	return +c+4.*Sigma*math.Pow(tb,3.)*(1.-r3)
}

// part.Ableitung nach dg/dTa
func dg2(ta float64) float64 {
	  return -c-4.*Sigma*math.Pow(ta,3.)
}
