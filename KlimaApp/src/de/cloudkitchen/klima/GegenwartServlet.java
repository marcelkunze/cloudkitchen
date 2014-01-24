package de.cloudkitchen.klima;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GegenwartServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Klimasmulation");

		Klima gegenwart = new Klima("Gegenwart",280.,320.,0.30,0.11,0.31,0.53,0.06);
		gegenwart.berechne();
		resp.getWriter().println(gegenwart.name()+":"+gegenwart.temperatur());
	}
}