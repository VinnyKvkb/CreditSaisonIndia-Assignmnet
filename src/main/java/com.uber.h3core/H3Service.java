package com.uber.h3core;


import com.example.FoodTruckService.Models.FoodTruck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
/**
 * @author Vinod Kumar Bhavani
 *
 */

public class H3Service {
	private static final Logger log = Logger.getLogger(H3Service.class.getCanonicalName());
	static H3Core h3 = null;
	public static NativeMethods h3Api;

	public List<FoodTruck> getNearestLocations(int res, FoodTruck deliveryLoc, List<FoodTruck> listofLocations) {

		List<FoodTruck> listInsidePoints = new ArrayList<FoodTruck>();
		try {
			h3 = H3Core.newInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double latitude1 = Double.parseDouble(deliveryLoc.getLatitude());
		double longitude2 = Double.parseDouble(deliveryLoc.getLongitude());
		String hexAddr = h3.geoToH3Address(latitude1, longitude2, res);
		List<String> list = Arrays.asList(hexAddr);
		List<String> compactIndex = h3.compactAddress(list);
		List<String> arr = new ArrayList<String>();
		arr.addAll(compactIndex);
		for (FoodTruck data : listofLocations) {
			double latitude8 = Double.parseDouble(data.getLatitude());
			double longitude9 = Double.parseDouble(data.getLongitude());
			String hexAddr1 = h3.geoToH3Address(latitude8, longitude9, res);
			if (arr.contains(hexAddr1) == true) {
				listInsidePoints.add(data);
			}
		}
		log.info("listofInsidePoints-->" + listInsidePoints.size());
		return listInsidePoints;
	}
//	public String getIndexAddres(DataAnalysisServiceDTO data) {
//		double latitude8 = Double.parseDouble(data.getIncidentLat());
//		double longitude9 = Double.parseDouble(data.getIncidentLng());
//		for(int i=6;i<=2;--i) {
//
//			switch(i) {
//			case 6:
//				String hexAddr6 = h3.geoToH3Address(latitude8, longitude9, i);
//				data.setIndexAtRes6(hexAddr6);
//			case 5:
//				String hexAddr5 = h3.geoToH3Address(latitude8, longitude9, i);
//				data.setIndexAtRes5(hexAddr5);
//			case 4:
//				String hexAddr4 = h3.geoToH3Address(latitude8, longitude9, i);
//				data.setIndexAtRes4(hexAddr4);
//			case 3:
//				String hexAddr3 = h3.geoToH3Address(latitude8, longitude9, i);
//				data.setIndexAtRes3(hexAddr3);
//			case 2:
//				String hexAddr2 = h3.geoToH3Address(latitude8, longitude9, i);
//				data.setIndexAtRes2(hexAddr2);
//			break;
//			}
//		}
//
//		return null;
//	}
}
