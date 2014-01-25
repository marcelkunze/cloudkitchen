package klima;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class KometServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Klimasmulation");

		Klima komet = new Klima("Komet   ",280.,320.,0.36,0.11,0.37,0.43,0.05);
		komet.berechne();
		komet.temperatur();
		resp.getWriter().println(komet.name()+":"+komet.temperatur());
	}
}
