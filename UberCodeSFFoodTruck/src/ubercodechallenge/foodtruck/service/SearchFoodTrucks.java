package ubercodechallenge.foodtruck.service;

/*
 * @Author: Bhavi Joshi
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ubercodechallenge.foodtruck.pojo.LocationObjects;

public class SearchFoodTrucks {

	// This Method Search for the food trucks according the food type provided by the user
	public ArrayList<LocationObjects> searchTypeOfTrucks(String foodType,double latParameter,double LngParameter,int numOfResults)
	{
		ArrayList<String[]> allTrucksByFoodType = new ArrayList<>();
		ArrayList<LocationObjects> closetsNLocationObjList = new ArrayList<>();
		
		String csvFile ="/ubercodechellenge/foodtruck/csv/Mobile_Food_Facility_Permit.csv";
		BufferedReader br = null;
		String line ="";
		String splitByComma =",";

		

		try
		{ 
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(csvFile)));
			System.out.println("+++++++++++++++++Started reading file+++++++++++++++++++");
			 int countCheck = 0;
			while((line = br.readLine()) != null)
			{
				
				String[] tempArr = line.split(splitByComma);
				
				if((tempArr[11].contains(foodType) || tempArr[11].contains(foodType.toLowerCase()) || tempArr[11].contains(foodType.toUpperCase())) && foodType.length()>2)
				{
					String[] truckObjArr = new String[5];
					truckObjArr[0] = tempArr[0];    //Location Id
					truckObjArr[1] = tempArr[11];   // Food TRuck Type 
					truckObjArr[2] = tempArr[14];   // Latitude
					truckObjArr[3] = tempArr[15];   // Longitude
					truckObjArr[4] = tempArr[5];    // Address

					allTrucksByFoodType.add(truckObjArr);
				}
				
				countCheck++;
			}
			System.out.println("List of all specific trucks in SearchFoodTruck.java------------------------->"+allTrucksByFoodType);

			
			ArrayList<LocationObjects> sortedLocationObjList = findClosestN(latParameter,LngParameter,allTrucksByFoodType);

			//This loop will select top nearest N[numOfResults] locations from the sorted list.
			if(sortedLocationObjList.size()> numOfResults)
			{
				for(int i =0;i< numOfResults;i++)
					closetsNLocationObjList.add(sortedLocationObjList.get(i));
			}
			else
			{
				for(int i =0;i< sortedLocationObjList.size();i++)
					closetsNLocationObjList.add(sortedLocationObjList.get(i));
			}
			System.out.println("List of nearerSpecificTrucks in SearchFoodTruck.java------------------------->"+sortedLocationObjList);

			System.out.println("+++++++++++++++++Finished reading file+++++++++++++++++++");

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return closetsNLocationObjList;
	}

	
	/*This method will sort the whole list to find the nearest locations using the HashMap. It will first create a map from given location coordinates of selected location, 
	 * in which the key is the location object and the value is the distance from the given location point. 
	 */
	public static ArrayList<LocationObjects> findClosestN(double latitide, double longitude,ArrayList<String[]> allTrucksByFoodType)
	{
		ArrayList<LocationObjects> sortedLocationObjList = new ArrayList<LocationObjects>();

		LocationObjects[] locationObjArr = new LocationObjects[allTrucksByFoodType.size()];
		HashMap<LocationObjects,Double> locationMap = new  HashMap<LocationObjects,Double>();

		for(int i=0;i<allTrucksByFoodType.size();i++) 
		{
			String[] locationObj = allTrucksByFoodType.get(i);
			
			// If latitude & longitude are not null [Some of the locations have empty latitude -longitude, that's why it is necessary to check this
			if(!locationObj[3].isEmpty() && !locationObj[2].isEmpty() && !locationObj[3].equals("Longitude") && !locationObj[2].equals("Latitude"))
			{
				locationObjArr[i] = new LocationObjects();
				locationObjArr[i].setObjects(locationObj);

				double theta = longitude - Double.parseDouble(locationObj[3]);
				double dist = Math.sin(deg2rad(latitide)) * Math.sin(deg2rad(Double.parseDouble(locationObj[2]))) + Math.cos(deg2rad(latitide)) * Math.cos(deg2rad(Double.parseDouble(locationObj[2]))) * Math.cos(deg2rad(theta));
				dist = Math.acos(dist);
				dist = rad2deg(dist);
				dist = dist * 60 * 1.1515;

				locationMap.put(locationObjArr[i], dist);
			}
		}


		// Sort the list using the HashMap according to the distance it has from the given point
		Set<Entry<LocationObjects, Double>> set = locationMap.entrySet();
		List<Entry<LocationObjects, Double>> list = new ArrayList<Entry<LocationObjects, Double>>(set);
		Collections.sort( list, new Comparator<Map.Entry<LocationObjects, Double>>()
				{
			public int compare( Map.Entry<LocationObjects, Double> o1, Map.Entry<LocationObjects, Double> o2 )
			{
				return (o1.getValue()).compareTo( o2.getValue() );
			}
				} );
		
		for(Entry<LocationObjects, Double> entry:list){
			//System.out.println(entry.getKey()+" ==== "+entry.getValue());
			sortedLocationObjList.add( entry.getKey());
		}

		return sortedLocationObjList;
	}


	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}



