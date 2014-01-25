package klima;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class TreibhausServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Klimasimulation");

		Klima treibhaus = new Klima("Treibhauseffekt",280.,320.,0.30,0.10,0.34,0.53,0.05);
		treibhaus.berechne();
		treibhaus.temperatur();
		resp.getWriter().println(treibhaus.name()+":"+treibhaus.temperatur());
	}
}
