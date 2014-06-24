package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;

import play.mvc.Controller;

public class StaticPages extends Application {

	/*
	 * Shows Q&A page
	 */
	public static void QandA() {
		render();
	}
	
	/*
	 * Shows About Us page
	 */
	public static void AboutUs() {
		render();
	}
	
}
