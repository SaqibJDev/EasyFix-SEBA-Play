package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import notifiers.Mails;
import models.Actor;
import models.device.DeviceModel;
import models.device.DeviceRepair;
import models.device.Manufacturer;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;


public class Registration extends Application {

    public static void index() {

        render();
    }

    /*
     * Save user if all validation checks verified 
     */
    public static void register(String email, String password, String name, String confpassword){
    	validation.required(name);
    	validation.required(email);
    	validation.required(password);
    	validation.equals("password",password,"confpassword",confpassword);
        
        if(validation.hasErrors()) {
            for(play.data.validation.Error error : validation.errors()) {
                System.out.println(error.message());
            }
        }else{
        	Actor actor = new Actor(name, null, email, password, null);
        	if(actor.create()){
        		Mails.welcome(actor);
        		redirect("/registerConfirm");
        	};
        }
        
        render();
    }
    
    public static void registerConfirm(){
    	render();
    }


}
