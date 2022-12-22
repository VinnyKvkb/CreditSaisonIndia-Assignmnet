package com.example.FoodTruckService;

import com.example.FoodTruckService.Models.FoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/FoodTruck")
@CrossOrigin
public class FoodTruckController {

@Autowired
FoodTruckService foodTruckService;

	  @RequestMapping(value = "/searchFoodTruck/{string}", method = RequestMethod.GET,produces =  "application/json")
		public ResponseEntity<Object> searchFoodTruck(@PathVariable(value = "string") String query) {
			log.info("==============searchFoodTruck==========" + query);
			ResponseEntity<Object> resEntity = foodTruckService.searchFoodTruck(query);
			return resEntity;
		}
	@RequestMapping(value = "/searchFoodTruckByDate/{string}", method = RequestMethod.GET,produces =  "application/json")
	public ResponseEntity<Object> findFoodTruckByRegexStr(@PathVariable(value = "string") String query, @RequestParam(value = "pageNo") Integer pageNo,
														  @RequestParam(value = "pageSize") Integer pageSize) {
		log.info("==============searchFoodTruckByDate==========" + query);
		ResponseEntity<Object> resEntity = foodTruckService.findFoodTruckByRegexStr(query,pageNo,pageSize);
		return resEntity;
	}
	@RequestMapping(value = "/insertFoodTruck", method = RequestMethod.POST,produces =  "application/json")
	public ResponseEntity<Object> insertFoodTruck(@Valid @RequestBody FoodTruck req) {
		log.info("==============insertFoodTruck==========" );

		ResponseEntity<Object> resEntity = foodTruckService.insertFoodTruck(req);
		return resEntity;
	}
	@RequestMapping(value = "/findNearestFoodTruck", method = RequestMethod.POST,produces =  "application/json")
	public ResponseEntity<Object> findNearestFoodTruck(@RequestBody FoodTruck req) {
		log.info("==============findNearestFoodTruck==========" );

		ResponseEntity<Object> resEntity = foodTruckService.findNearestFoodTruck(req);
		return resEntity;
	}
}
