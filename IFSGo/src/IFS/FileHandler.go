package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

// Hier werden die Requests an den Server verarbeitet
func handleFileRequest(c http.ResponseWriter, r *http.Request) {
	// Pfad aus der Url holen
	pfad := r.URL.Path[0:]
	// gemäß der üblichen Konvention mappen wir / auf index.html
	if pfad == "/" {
		pfad = "/index.html"
	}
	log("verarbeite request an [" + pfad + "]")

	// versuche die Datei zu laden
	content, err := ioutil.ReadFile(pfad[1:])
	if err != nil {
		// Fehler ausgeben und loggen
		e := fmt.Sprintf("404: page not found at %s : Error (%s)",
			pfad, err)
		log(fmt.Sprintf("GET [%s] code 404 error %s",
			pfad, err))
		http.Error(c, e, http.StatusNotFound)
	} else {
		// Seite ausgeben und Erfolg loggen
		fmt.Fprint(c, string(content))
		log(fmt.Sprintf("GET [%s] code 200 size %d Bytes",
			pfad, len(content)))
	}
}
