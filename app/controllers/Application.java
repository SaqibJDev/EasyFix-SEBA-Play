package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import controllers.Secure.Security;
import models.*;

public class Application extends Controller {

	@Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            Actor user = Actor.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.firstName+" "+user.lastName);
        }
    }
	/*
	 * Render Home page
	 */
    public static void index() {
        render();
    }

    public static boolean abc(){
    	return true;
    }
}