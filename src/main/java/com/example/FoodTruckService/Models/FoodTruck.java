package com.example.FoodTruckService.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "foodtruck")
public class FoodTruck {
	@Id
	private String id;
	@NotNull(message="dayorder Field Should not be Empty")
	private String dayorder;

	private String dayofweekstr;
	private String starttime;
	private String endtime;
	@NotNull(message="permit Field Should not be Empty")
	private String permit;
	private String location;
	private String locationdesc;
	private String optionaltext;
	@NotNull(message="locationid Field Should not be Empty")
	private String locationid;
	private String start24;
	private String end24;
	private String cnn;
	@NotNull(message="ExpirationDate Field Should not be Empty")
	private String expirationdate;
	private String addr_date_modified;
	private String block;
	private String lot;
	private String coldtruck;
	@NotNull(message="Applicant Field Should not be Empty")
	private String applicant;
	private String x;
	private String y;
	@NotNull(message="latitude Field Should not be Empty")
	private String latitude;
	@NotNull(message="longitude Field Should not be Empty")
	private String longitude;
	private Location2 location_2;

}
