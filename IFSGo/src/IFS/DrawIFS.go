package main

import (
	"net/http"
	svg "github.com/ajstarks/svgo"
	"math/rand"
)

func drawIFS(w http.ResponseWriter, maxIter int, a,b,c,d,e,f,p [4]float64) {
	
	canvas := svg.New(w)
	canvas.Start(400, 800)

	var x,x1,y,pk float64 = 0.,0.,0.,0.
	var xs, ys float64 = 40., 40.
	var xd,yd,i,k int
	for i=1; i<=maxIter; i++ {
	 	pk = rand.Float64()
	 	if pk <= p[0] {	
	 		k = 0
	 	} else if pk <= p[1] {	
	 		k = 1
	 	} else if pk <=p [2] {	
	 		k = 2
	 	} else {	
	 		k = 3
	 	}
	 	
		x1 = a[k]*x + b[k]*y + e[k]
		y  = c[k]*x + d[k]*y + f[k]
		x  = x1
		xd = int (x*xs+200.)
		yd = int (400.-y*ys)
		canvas.Line(xd,yd,xd+1,yd+1, "width:1;stroke:green")
	}
	
	canvas.End()
}