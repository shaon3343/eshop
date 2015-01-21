package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dummy.LoginForm;
import models.EshopUser;



public class UserService {

	public static String HashPassword(String plainPassword) {
		 try {
			        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			        byte[] array = md.digest(plainPassword.getBytes());
			        StringBuffer sb = new StringBuffer();
			        for (int i = 0; i < array.length; ++i) {
			          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			       }
			        return sb.toString();
			  } catch (java.security.NoSuchAlgorithmException e) {
			  
			  }
		    return null;
		}
	
	public static boolean checkUserExist(String email){
		
		
		if(EshopUser.findUser(email) == true)
			return true;
		else 
			return false;
	}

	public static boolean authUser(LoginForm login) {
		
		if(EshopUser.findUserByEmailPass(login))
			return true;
		
		return false;
	}
}
