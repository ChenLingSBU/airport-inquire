package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javabeans.*;
import org.json.JSONException;

/**
 * Servlet implementation class airportInfoDetailServlet
 */
@WebServlet("/airportInfoDetailServlet")
public class airportInfoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public airportInfoDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		airportInfo[] airports = null;
		int airportIndex = Integer.parseInt(request.getParameter("airportIndex"));
		airports = (airportInfo[])request.getSession().getAttribute("airportInfo");
		cityInfo cityInfo = (cityInfo)request.getSession().getAttribute("cityInfo");
		weatherInfo weatherInfo = new weatherInfo(cityInfo.getCityWoeid());
		try {
			weatherInfo.getWeatherInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("weatherInfo",weatherInfo );
		request.getSession().setAttribute("airport", airports[airportIndex]);
		
		request.getRequestDispatcher("airportInfoDetail.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
