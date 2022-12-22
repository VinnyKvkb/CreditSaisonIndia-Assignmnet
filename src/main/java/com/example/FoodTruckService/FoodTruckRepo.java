package com.example.FoodTruckService;

import java.util.List;

import com.example.FoodTruckService.Models.FoodTruck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTruckRepo extends MongoRepository<FoodTruck, Integer>{
	@Query("{$or : [{'applicant': { $regex: ?0, $options:'i' }}, {'locationdesc': { $regex: ?0, $options:'i' }}]}")
	List<FoodTruck> findFoodTruckByRegexString(final String regexString);
	@Query("{'expirationdate':{$gte:?0}}")
	Page<FoodTruck> findFoodTruckByRegexStr(final String date, Pageable page);

}
