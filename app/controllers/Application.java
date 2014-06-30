package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import controllers.Secure.Security;
import models.*;

public class Application extends Controller {

	/*
	 * For user login check
	 * If user is logged in then header shows his name and logout
	 * otherwise sign in / signup buttons
	 * 
	 * All controller are inherited from this to replicate this functionality
	 */
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

}