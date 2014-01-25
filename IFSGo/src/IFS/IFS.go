package main

import (
	"fmt"
	"net/http"
	"os"
	"time"
)

// ein globaler logchannel
var logchannel chan string

func main() {
	//defer handleErrors("main",true)	
	// der Port auf dem unser Server laufen soll
	port := "8080"
	// das Basisverzeichnis (rootdir) unseres Servers
	dir := "."

	// Kommandozeile pruefen und Werte übernehmen
	if len(os.Args) > 1 {
		port = os.Args[1]
	}
	if len(os.Args) > 2 {
		dir = os.Args[2]
	}

	// logkanal gepuffert eröffnen
	logchannel = make(chan string, 2000)

	// logging nebenläufig starten
	go loggerThread(logchannel)

	// und los gehts
	log("Der Go IFS Server startet auf Port " + port + " im Verzeichnis " + dir)

	// Server initialisieren
	os.Chdir(dir)
	http.HandleFunc("/farn", handleFarn)
	http.HandleFunc("/baum", handleBaum)
	http.HandleFunc("/sierpinski", handleSierpinski)
	http.HandleFunc("/koch", handleKoch)
	http.HandleFunc("/", handleFileRequest)
	http.ListenAndServe(fmt.Sprintf(":%s", port), nil)
	
	log("Der Go IFS Server ist beendet ")
}

// Logging

func loggerThread(c chan string) {
	// endlos die strings aus dem channel holen und 
	// auf der Konsole ausgeben
	for {
		s := <-c
		fmt.Println(s)
	}
}

func log(l string) {
	// die funktion stellt die logzeie in die queue
	// erst mit der Zeit prefixen
	t := time.Now()
	ts := fmt.Sprintf("%02d.%02d.%04d %02d:%02d:%02d: ",
		t.Day(), t.Month(), t.Year(), t.Hour(), t.Minute(), t.Second())
	logchannel <- ts + l
}

// Error Handling

func handleErrors(loc string, catchErrors bool) {
	if err := recover(); err != nil {
		log(fmt.Sprintf("%s > Es ist ein Fehler aufgetreten: %s", loc, err))
		if !catchErrors {
			log("Leite Fehler weiter")
			panic(err)
		}
		log("Fahre mit Programm fort")
	}
}
