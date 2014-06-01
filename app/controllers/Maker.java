package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;










import org.joda.time.DateTime;

import models.DeviceModel;
import models.Manufacturer;
import models.TestTable;
import play.mvc.Controller;

public class Maker extends Controller{

	public static void index(){
		List<Manufacturer> manufacturers = Manufacturer.findAll();
		Collections.sort(manufacturers, new Comparator<Manufacturer>() {

			public int compare(Manufacturer o1, Manufacturer o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		render(manufacturers);
	}
	
	public static void manufacturer(String maker){
//		DeviceModel device = new DeviceModel("iPhone 4");
//		List<Manufacturer> manufacturers = Manufacturer.findAll();
//		device.manufacturer = manufacturers.get(0);
//		device.create();
//		List<DeviceModel> devices = (manufacturers.get(0)).deviceModels;//  DeviceModel.findAll();
//		Collections.sort(manufacturers, new Comparator<Manufacturer>() {
//
//			public int compare(Manufacturer o1, Manufacturer o2) {
//				return o1.getName().compareTo(o2.getName());
//			}
//		});
		System.out.println(maker);
		render(/*devices*/);
	}
	
}
