package ubercodechallenge.foodtruck.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ubercodechallenge.foodtruck.pojo.LocationObjects;
import ubercodechallenge.foodtruck.service.SearchFoodTrucks;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		String action=request.getParameter("action");

		System.out.println("Action in SearchServlet:-------------------------->"+action);
		
		if(action.equals("getNearestFoodTrucks"))
		{
			int total = 10;  //Here I am finding 10 near food truck locations
			
			String FoodType = request.getParameter("searchFoodItem");
			//System.out.println("FoodType in Servlet %%%%%%%%%%%%%%%%"+FoodType);

			String latLong = request.getParameter("clickMarker");

			ArrayList<LocationObjects>  listOfNearestTrucks = new ArrayList<>();
			HttpSession session = request.getSession();
			
			//Service Class Object to access the business logic method
			SearchFoodTrucks searchFoodTruckObj = new SearchFoodTrucks();
			
			// If user has clicked on map to mark a point
			if(!latLong.isEmpty())
			{

				System.out.println("event.latLng in servlet %%%%%%%%%%%%"+latLong);   

				String LatLng1 = latLong.replace("(", "");
				String newLatLng =  LatLng1.replace(")", "");
				//System.out.println("!!!!!!!!!!!!!!!"+newLatLng);
				String[] latLngA = newLatLng.split(",");

				//System.out.println("@@@@@@@@@@@@@@@@@@@@@"+Arrays.toString(latLngA)); 
				
				// If the user has not selected any particular food item it will search for "Sandwich"
				if(FoodType.isEmpty())
				{
					FoodType = "Sandwich";
				}
				
				listOfNearestTrucks = searchFoodTruckObj.searchTypeOfTrucks(FoodType,Double.parseDouble(latLngA[0]),Double.parseDouble(latLngA[1]),total);
				session.setAttribute("selectedLat", latLngA[0]);
				session.setAttribute("selectedLng", latLngA[1]);
			}
			else //If user has not selected any location if will roughly take the center of the San Francisco and search for the nearest food trucks
			{
				double sfCenterLat = 37.7833;
				double sfCenterLng = -122.4167;

				// If the user has not selected any particular food item it will search for "Sandwich"
				if(FoodType.isEmpty())
				{
					FoodType = "Sandwich";
					
				}

				listOfNearestTrucks = searchFoodTruckObj.searchTypeOfTrucks(FoodType,sfCenterLat,sfCenterLng,total);
				session.setAttribute("selectedLat", sfCenterLat);
				session.setAttribute("selectedLng", sfCenterLng);
			}

			System.out.println("List of nearest food trucks in SearchServlet.java------------------------->"+listOfNearestTrucks);

			//			String[] foodItems = new String[listOfTrucks.size()];
			//			String[] latList = new String[listOfTrucks.size()];
			//			String[] lngList = new String[listOfTrucks.size()];

			// Using String rather than arrays to access it in .js file
			String foodStr = "";
			String latStr ="";
			String lngStr = "";
			String addressStr = "";

			for(int k=0;k<listOfNearestTrucks.size();k++)
			{
				LocationObjects ObjPojo = listOfNearestTrucks.get(k);
				String[] array = ObjPojo.getObjects();
				/*foodItems[k] = array[1];
				latList[k] = array[2];
				lngList[k] = array[3];*/

				foodStr += array[1] +",";
				latStr += array[2] +",";
				lngStr += array[3] +",";
				addressStr += array[4] + ",";
			}

			//System.out.println("foodItems in Servlet $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+Arrays.toString(foodItems));
			//System.out.println("latList in Servlet $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+Arrays.toString(latList));
			//System.out.println("lngList in Servlet $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+Arrays.toString(lngList));

			System.out.println("foodStr in SearchServlet.java $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+foodStr);
			System.out.println("latStr in SearchServlet.java $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+latStr);
			System.out.println("lngStr in SearchServlet.java $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+lngStr);
			System.out.println("addressStr in SearchServlet.java $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+addressStr);

			session.setAttribute("foodStr", foodStr);
			session.setAttribute("latStr", latStr);
			session.setAttribute("lngStr", lngStr);
			session.setAttribute("addressStr", addressStr);

			//request.setAttribute("listOfTrucks", listOfTrucks);

			if(listOfNearestTrucks.isEmpty())
			{
				request.setAttribute("errMessage", "There is no food truck availble for this food type.");
			}
			
			request.setAttribute("searchedFood",FoodType);
			RequestDispatcher rd=request.getRequestDispatcher("/jsp/IndexPage.jsp");

			rd.forward(request, response);

		}
	}

}
