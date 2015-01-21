package controllers;

import dummy.LoginForm;
import models.EshopUser;
import play.*;
import play.data.Form;
import play.mvc.*;
import utils.UserService;
import views.html.*;

public class Application extends Controller {
	
	static Form<EshopUser> registrationForm = Form.form(EshopUser.class);
	static Form<LoginForm> loginForm = Form.form(LoginForm.class);
	
    public static Result index() {
        return ok(login.render());
    }
    
    public static Result registerUser(){
    	
    	Form<EshopUser> registerForm = registrationForm.bindFromRequest();
    	
    	if(registerForm.hasErrors()){
    		
    		System.out.println("ERROR ON FILLED FORM");
    		flash("error","Fillup the registration form correctly");
        	return redirect(routes.Application.index());
    	}else{
    		
    		EshopUser registerUser = registerForm.get();
    		
    		if(UserService.checkUserExist(registerUser.email)==false)
    			EshopUser.create(registerUser);
    		else
    		{
    			flash("error","Email Already Exist");
    	    	return redirect(routes.Application.index());
    		}
    	}
    	
    	flash("msg","SUCCESSFULLY REGISTERED");
    	return redirect(routes.Application.index());
    	
    }
    
    
    public static Result userLogin(){
    	
    	Form<LoginForm> loginUserForm = loginForm.bindFromRequest();
    	
    	if(loginUserForm.hasErrors()){
    		
    		System.out.println("ERROR ON FILLED FORM");
    		flash("errorlogin","Fillup the login form correctly");
        	return redirect(routes.Application.index());
    	}else{
    		
    		LoginForm login = loginUserForm.get();
    		String pass = UserService.HashPassword(login.password);
    		login.password = pass;
    		System.out.println("########################"+login.email+"###########################"+login.password);
    		if(UserService.authUser(login))
    		{
    			return ok("SUCCESSFULLY LOGGED IN");
    			
    		}
    		else{
    			
    			System.out.println("ERROR ON Login");
        		flash("errorlogin","USER EMAIL AND / OR PASSWORD DOES NOT MATCH");
            	return redirect(routes.Application.index());
    		}
    		
    	}
    }

}
