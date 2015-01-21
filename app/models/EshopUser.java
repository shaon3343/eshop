package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import utils.UserService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dummy.LoginForm;



@Entity
@Table(name="eshop_user")
public class EshopUser extends Model {

		@Id
		@Column(name="id")
		public Long id;
		
		public String name;
		
		public String userName;
				
		public String password;
				
		public String mobileNo;
		
		@Required
		public String email;
				
		private static Finder<Long,EshopUser> finder = new Finder<Long,EshopUser>(Long.class,EshopUser.class);
		
		public static void create(EshopUser reg){
			
			String hashPass = UserService.HashPassword(reg.password);
			reg.password = hashPass;
			reg.save();
			
		}
		
		public static boolean findUser(String email){
			try{
				EshopUser user = finder.where().eq("email",email).findUnique();
				if(user.email.equals(email))
					return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return false;
			
		}
		
		public static boolean findUserByEmailPass(LoginForm loginForm){
			
			try{
				EshopUser user = finder.where().eq("email",loginForm.email).eq("password",loginForm.password).findUnique();
				
				if(user!=null)
					return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return false;
		}
		
}
