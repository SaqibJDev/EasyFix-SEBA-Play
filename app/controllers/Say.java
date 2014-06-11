package controllers;




import groovy.ui.text.FindReplaceUtility;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;













import models.device.DeviceModel;
import models.device.Manufacturer;

import org.joda.time.DateTime;

import play.libs.Time;
import play.mvc.Controller;

/*
 * Test controller for experimenting different functions before implementing them in the real code
 */
public class Say extends Controller{

	public static void hello(){
//		TestTable testing = new TestTable("Saqib");
//		testing.create();
//		
//		List<TestTable> names = TestTable.all().fetch();
//		render(names);
	}
	
	public static void manufacturer(){
		Manufacturer company = new Manufacturer();
		company.name = "Banana";
		company.image = "appleImage.png";
		company.description = "Testing with apple description";
		
		long time = System.currentTimeMillis();
		company.createdOn = company.lastUpdated = new Timestamp(time);//toString("yyyy-MM-dd HH:mm:ss");
		System.out.println(new java.util.Date().toString()+":"+new Timestamp(time));
		company.create();
		
		
		List<Manufacturer> names = Manufacturer.all().fetch();
		render(names);
	}
	
	public static void bye(){
		render();
	}
	
	public static void create_device(){
		DeviceModel device = new DeviceModel();
		device.name = "iPhone 5S";
		device.description = "Testing 5S";
		device.lastUpdated = device.createdOn = new Timestamp(System.currentTimeMillis());
		device.create();
		
		render();
	}
	
	public static void add_device_to_manufacturer(){
		List<DeviceModel> devices = DeviceModel.find("byId", 1L).fetch();
		
		Manufacturer manufacturer = (Manufacturer) Manufacturer.find("byName", "Apple").fetch().get(0);
		manufacturer.deviceModels = devices;
		manufacturer.save();
		
		render();
	}
	
	
}
