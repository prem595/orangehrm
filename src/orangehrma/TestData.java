package orangehrma;

import java.util.Hashtable;
import java.util.Dictionary;
import java.util.Random;

public class TestData {
	
	public Dictionary<Object,Object> getTestData()  {				
		Dictionary<Object, Object> td = new Hashtable<Object, Object>();
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);	
		
		/*  Application specific params */
		td.put("hosturl", "http://santhoshp-lt/orangehrm-3.0.1");
		td.put("application", "ORANGEHRM Web Application");
		td.put("reportFile", "FinalSummary.html");
		td.put("browser","firefox");
		
		/*  Test data goes here... */
		td.put("username_generic", "user23");
		td.put("password_generic", "password"); 
		td.put("username_admin", "admin");
		td.put("password_admin", "admin");
		td.put("firstname_random", "John"+String.valueOf(randomInt));
		td.put("lastname_random", "John"+String.valueOf(randomInt));
		td.put("username_random", "john_"+String.valueOf(randomInt));
		td.put("desiredpassword", "password");
		td.put("welcome_prefix", "Welcome");	
		td.put("empid_for_search", "0014");
		td.put("empname_for_search", "user23");
		td.put("lic_exp_date", "2013-10-09");	
		td.put("customer_name", "c1");	
		td.put("customer_name_random", "c1"+String.valueOf(randomInt));	
		td.put("project_name", "Test Project");	
		td.put("project_admin", "user23 user23");	
		td.put("generic_desc", "zyxwvutsrqponmlkjihgfbdcba");	
		return td; 
	}
}
