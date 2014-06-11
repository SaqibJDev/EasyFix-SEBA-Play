package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;
import models.technician.Technician;

import org.joda.time.DateTime;

import play.mvc.Controller;
import utility.QueryUtil;

public class Repair extends Controller {

	/*
	 * Shows list of all manufacturer from database
	 */
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
            List<DeviceModel> devices = manufacturer.deviceModels;
            Collections.sort(devices, new Comparator<DeviceModel>() {

                public int compare(DeviceModel o1, DeviceModel o2) {
                    return o1.name.compareTo(o2.name);
                }
            });
            render(maker, devices);
        } else {
            render();
        }

    }

    public static void deviceModelRepairList(String maker, String deviceModel) {

        Manufacturer manufacturer = (Manufacturer) Manufacturer
                .find("byName", maker).fetch().get(0);
        List<DeviceRepair> deviceRepairList = new ArrayList<DeviceRepair>();
        if (manufacturer != null) {

            List<DeviceModel> devices = manufacturer.deviceModels;
            for (DeviceModel deviceModelLocal : devices) {
                if (deviceModelLocal.name.equals(deviceModel)) {
                    deviceRepairList = deviceModelLocal.deviceRepairList;
                }
            }
            Collections.sort(deviceRepairList, new Comparator<DeviceRepair>() {

                public int compare(DeviceRepair arg0, DeviceRepair arg1) {
                    return arg0.name.compareTo(arg1.name);
                }
            });
            render(maker, deviceModel, deviceRepairList);
        } else {
            render();
        }

    }

    public static void deviceModelRepairDetails(String maker,
            String deviceModel, String repair) {
        DeviceRepair deviceRepair = (DeviceRepair) DeviceRepair
                .find("byName", repair).fetch(1).get(0);
        if (deviceRepair != null) {
            render(maker, deviceModel, deviceRepair);
        } else {
            render();
        }

    }


}
