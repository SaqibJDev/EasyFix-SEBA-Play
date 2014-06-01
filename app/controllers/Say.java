package controllers;




import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;





import org.joda.time.DateTime;

import models.Manufacturer;
import models.TestTable;
import play.mvc.Controller;

public class Say extends Controller{

	public static void hello(){
		TestTable testing = new TestTable("Saqib");
		testing.create();
		
		List<TestTable> names = TestTable.all().fetch();
		render(names);
	}
	
	public static void manufacturer(){
		Manufacturer company = new Manufacturer("Apple");
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
}
