package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;

import models.DeviceModel;
import models.Manufacturer;
import play.mvc.Controller;

public class Maker extends Controller {

	public static void index() {
		List<Manufacturer> manufacturers = Manufacturer.findAll();
		Collections.sort(manufacturers, new Comparator<Manufacturer>() {

			public int compare(Manufacturer o1, Manufacturer o2) {
				return o1.name.compareTo(o2.name);
			}
		});

		render(manufacturers);
	}

	public static void manufacturer(String maker) {
		// DeviceModel device = new DeviceModel("iPhone 4");
		Manufacturer manufacturer = (Manufacturer) Manufacturer
				.find("byName", maker).fetch().get(0);
		if (manufacturer != null) {
			System.out.println("Manufacturer exists : " + maker);
			List<DeviceModel> devices = manufacturer.deviceModels;
			Collections.sort(devices, new Comparator<DeviceModel>() {

				public int compare(DeviceModel o1, DeviceModel o2) {
					return o1.name.compareTo(o2.name);
				}
			}); 
			render(devices);
		} else {
			render();
		}

	}

}
