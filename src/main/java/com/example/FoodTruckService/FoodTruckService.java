package com.example.FoodTruckService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.FoodTruckService.Models.FoodTruck;
import com.uber.h3core.H3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodTruckService {
	@Autowired
	FoodTruckRepo foodTruckRepo;

	H3Service h3Service=new H3Service();

	public ResponseEntity<Object> searchFoodTruck(String query) {
		List<FoodTruck> truckList=new ArrayList<>();
		try {
			truckList = foodTruckRepo.findFoodTruckByRegexString(query);
			log.info("truckList->" + truckList);
		}catch (Exception e){
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(truckList, HttpStatus.OK);
	}
	public ResponseEntity<Object> findFoodTruckByRegexStr(String query, Integer pageNo, Integer pageSize) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<FoodTruck> truckList = foodTruckRepo.findFoodTruckByRegexStr(query,paging);
			log.info("truckList->" + truckList);

			response.put("truckList", truckList.getContent());
			response.put("currentPage", truckList.getNumber());
			response.put("totalItems", truckList.getTotalElements());
			response.put("totalPages", truckList.getTotalPages());
		}catch (Exception e){
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	public ResponseEntity<Object> insertFoodTruck(FoodTruck req) {
		FoodTruck res=new FoodTruck();
		try {
			 res = foodTruckRepo.save(req);
			log.info("FoodTruck->" + res);
		}catch (Exception e){
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(res, HttpStatus.OK);
	}

	public ResponseEntity<Object> findNearestFoodTruck(FoodTruck deliveryLocation) {
		List list=new ArrayList();
		try {
			int maxLevel=8;
			List<FoodTruck> allTrucksLoc = foodTruckRepo.findAll();
			for (int i = maxLevel; i >= 1; --i) {
				if (list.size() >= 1) {
					break;
				} else {
					log.info("maxLevel--else-->" + i);
					list = h3Service.getNearestLocations(i, deliveryLocation, allTrucksLoc);
				}
			}
		}catch (Exception e){
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
}
