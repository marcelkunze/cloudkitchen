package IFS

import (
	"net/http"
	"fmt"
)

func root(w http.ResponseWriter, r *http.Request) {
    fmt.Fprint(w, IFSForm)
}

const IFSForm = `
<html>
  <head>
    <title>Iterierte Funkionssysteme </title>
  </head>
  <body>
  <H1>Programmierung in Go!</h1>
    <br/>
    Waehlen Sie das IFS<br/>
    <ul>
      <li><a href="farn">Farn</a></li>
      <li><a href="baum">Baum</a></li>
      <li><a href="sierpinski">Sierpinski</a></li>
      <li><a href="koch">Koch</a></li>
    </ul>
  </body>
</html> `

func handleFarn(w http.ResponseWriter, r *http.Request) {

    maxIter := 50000
    
	// Farn
	a := [4]float64{0,0.197,-0.15,0.849}
	b := [4]float64{0,-0.226,0.283,0.037}
	c := [4]float64{0,0.226,0.26,-0.037}
	d := [4]float64{0.16,0.197,0.237,0.849}
	e := [4]float64{0,0,0,0}
	f := [4]float64{0,1.6,0.44,1.6}
	p := [4]float64{0.03,0.16,0.27,1.0}
	
	drawIFS(w, maxIter, a,b,c,d,e,f,p)
}

func handleBaum(w http.ResponseWriter, r *http.Request) {

    maxIter := 50000
    
	// Baum
	a := [4]float64{-0.04,0.61,0.65,0.64}
	b := [4]float64{0,0,0.18,-0.2}
	c := [4]float64{-0.23,0,-0.3,0.32}
	d := [4]float64{-0.65,0.31,0.48,0.56}
	e := [4]float64{-0.08,0.07,0.74,-0.66}
	f := [4]float64{0.26,3.5,0.39,0.9}
	p := [4]float64{0.15,0.37,0.67,1}
	
	drawIFS(w, maxIter, a,b,c,d,e,f,p)
}

func handleSierpinski(w http.ResponseWriter, r *http.Request) {

    maxIter := 5000
    
	// Sierpinski-Kurve
	a := [4]float64{0.5,0.5,0.5,0}
	b := [4]float64{0,0,0,0}
	c := [4]float64{0,0,0,0}
	d := [4]float64{0.5,0.5,0.5,0}
	e := [4]float64{0,1,0.5,0}
	f := [4]float64{0,0,0.5,0}
	p := [4]float64{0.33,0.66,1,1}
	
	drawIFS(w, maxIter, a,b,c,d,e,f,p)
}

func handleKoch(w http.ResponseWriter, r *http.Request) {

    maxIter := 1000
    
	// Kochsches Dreieck
	a := [4]float64{0.3333,0.3333,0.1667,-0.1667}
	b := [4]float64{0,0,-0.28867,0.28867}
	c := [4]float64{0,0,0.28867,0.28867}
	d := [4]float64{0.3333,0.3333,0.1667,0.1667}
	e := [4]float64{0,0.6667,0.3333,0.6667}
	f := [4]float64{0,0,0,0}
	p := [4]float64{0.25,0.5,0.75,1}
	
	drawIFS(w, maxIter, a,b,c,d,e,f,p)
}

