package IFS

import (
	"net/http"
)

func init() {
	http.HandleFunc("/", root)
	http.HandleFunc("/farn",handleFarn)
        http.HandleFunc("/baum",handleBaum)
	http.HandleFunc("/sierpinski", handleSierpinski)
	http.HandleFunc("/koch", handleKoch)
}

