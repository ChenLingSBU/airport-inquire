package servlets;



import javabeans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Servlet implementation class searchAirportWithRadiusServlet
 */
@WebServlet("/searchAirportWithRadiusServlet")
public class searchAirportWithRadiusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchAirportWithRadiusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		airportInfo [] airports = null;
		cityInfo cityInfo = new cityInfo();
		cityInfo.setCityName(request.getParameter("cityName"));
		cityInfo.setRadiusMiles(Integer.parseInt(request.getParameter("airportCityRadius")));
		
		searchGeoInfo geoInfo = new searchGeoInfo(cityInfo.getCityName());
		try {
			geoInfo.getGeoInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cityInfo.setCityName(geoInfo.getCityName());
		cityInfo.setCityLatitude(geoInfo.getLatitude());
		cityInfo.setCityLongitude(geoInfo.getLongitude());
		cityInfo.setCityWoeid(geoInfo.getWoeid());
		searchAirportWithRadius airportWithRadius = new searchAirportWithRadius(cityInfo.getCityLatitude(), cityInfo.getCityLongitude(), cityInfo.getRadiusMiles());
		try {
			  airports = airportWithRadius.getAirports();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("cityInfo", cityInfo);
		request.getSession().setAttribute("airportInfo", airports);
		request.getRequestDispatcher("airportswithradius.jsp").forward(request, response);
	}
}
