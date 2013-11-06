package orangehrma;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bsh.This;
import orangehrma.TestReport;

public class HrmMain {

	HrmLib l1 = new HrmLib();
	TestData ob1 = new TestData();
	Dictionary<Object,Object> tdtemp=ob1.getTestData();
	String url =tdtemp.get("hosturl").toString(); 
	boolean res = false;
	FileWriter fw = null;
	BufferedWriter bw = null;	
	Date d1=null,d2=null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal=null;
	String reportFileName=tdtemp.get("reportFile").toString();
	String st=null,et=null;
	long diff;
	TestReport tr = new TestReport();
	ArrayList<String> tcdata = new ArrayList<String>();
	
	Logger log=null;
	
	@BeforeTest
	
	public void BeforeTest() { 
		
	}

	@BeforeMethod
	public void BeforeInit() {	
		log = Logger.getLogger(HrmMain.class.getName());		
	}


	@Test(description="Login as admin")
	public void TestMethod1() {
		
		
		try {			 
		    
		    log.error("this is an ERROR sage");
			l1.LaunchBrowser(url);		
			l1.setText(0, "txtUsername",tdtemp.get("username_generic").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_generic").toString());
			l1.clickButton(0,"btnLogin");
			res=l1.isElementVisible(0, "welcome", tdtemp.get("welcome_prefix").toString()+" "+tdtemp.get("username_generic").toString()); 				
			
		} catch (Exception e) {
			
				e.printStackTrace();
				res=false;	
			
		}	

		l1.closeBrowser();	
		Assert.assertTrue(res);
	}


	@Test(description="Add an Employee")
	public void TestMethod2() {

		try {

			l1.LaunchBrowser(url);
			l1.setText(0, "txtUsername",tdtemp.get("username_admin").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_admin").toString());
			l1.clickButton(0,"btnLogin");
			l1.clickLink(0, "menu_pim_viewPimModule");
			l1.clickLink(0, "menu_pim_viewEmployeeList");
			l1.clickButton(0, "btnAdd");
			String fn=tdtemp.get("firstname_random").toString();
			String ln=tdtemp.get("lastname_random").toString();
			l1.setText(0, "firstName",fn );
			l1.setText(0, "lastName",ln );
			l1.selectCheckbox(0, "chkLogin");
			l1.setText(0, "user_name", tdtemp.get("username_random").toString());
			l1.setText(0, "user_password", tdtemp.get("desiredpassword").toString());
			l1.setText(0, "re_password", tdtemp.get("desiredpassword").toString());
			l1.selectComboboxItem(0, "status", "Enabled");
			l1.clickButton(0, "btnSave");
			res = l1.isTextPresentByTag("h1", fn + " " + ln);	
							

		} catch (Exception e) {
			
				e.printStackTrace();
				res=false;	
				Assert.assertTrue(res);
			
		}	 
		
		l1.closeBrowser();	
		Assert.assertTrue(res);
		

	}



	@Test(description="Search an Employee by ID")
	public void TestMethod3() {
		
		try {
				l1.LaunchBrowser(url);
				l1.setText(0, "txtUsername",tdtemp.get("username_admin").toString());
				l1.setText(0, "txtPassword",tdtemp.get("password_admin").toString());
				l1.clickButton(0, "btnLogin");
				l1.clickLink(0, "menu_pim_viewPimModule");
				l1.clickLink(0, "menu_pim_viewEmployeeList");
				l1.setText(0, "empsearch_id", tdtemp.get("empid_for_search").toString());
				l1.clickButton(0,"searchBtn");
				res = l1.isTextPresentByTag("a",tdtemp.get("empid_for_search").toString() );						
		

		}catch (Exception e) {
			res=false;	
			e.printStackTrace();
		}
		l1.closeBrowser();		
		Assert.assertTrue(res);

	}



	@Test(description="Search an Employee by Name")
	public void TestMethod4() {
	
		try {

			l1.LaunchBrowser(url);
			l1.setText(0, "txtUsername",tdtemp.get("username_admin").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_admin").toString());
			l1.clickButton(0, "btnLogin");
			l1.clickLink(0, "menu_pim_viewPimModule");
			l1.clickLink(0, "menu_pim_viewEmployeeList");
			l1.setText(5, "input#empsearch_employee_name_empName.ac_input", tdtemp.get("empname_for_search").toString());
			l1.clickButton(0,"searchBtn");
			res = l1.isTextPresentByTag("a",tdtemp.get("empname_for_search").toString() );		
			
		}catch (Exception e) {							
				res=false;	
				e.printStackTrace();
		}
		l1.closeBrowser();
		Assert.assertTrue(res);
	}


	
	@Test(description="Edit Personal Details")
	public void TestMethod5() {
		
		try {

			l1.LaunchBrowser(url);
			l1.setText(0, "txtUsername",tdtemp.get("username_generic").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_generic").toString());
			l1.clickButton(0, "btnLogin");

			l1.clickLink(0, "menu_pim_viewMyDetails");
			l1.clickButton(0,"btnSave");
			
			l1.selectCheckbox(0,"personal_optGender_1");
			l1.clearTextField(0,"personal_txtLicExpDate");
			l1.setText(0,"personal_txtLicExpDate",tdtemp.get("lic_exp_date").toString());
			l1.clickButton(0,"btnSave");
			do { l1.getText(1,"btnSave");	Thread.sleep(1000);	} while(l1.getText(1, "btnSave").equalsIgnoreCase("Processing") && l1.getText(1, "btnSave").equalsIgnoreCase("Save"));
			String temp=l1.getText(1,"personal_txtLicExpDate");
			if(temp.equalsIgnoreCase(tdtemp.get("lic_exp_date").toString())) {
				res=true; } else { res=false; }	
			
			
		}catch (Exception e) {
			res=false;
			e.printStackTrace();
			
		}
		l1.closeBrowser();
		Assert.assertTrue(res);
	}

	
	@Test(description="Add a Project")
	public void TestMethod6() {
	
		try {

			l1.LaunchBrowser(url);
			l1.setText(0, "txtUsername",tdtemp.get("username_admin").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_admin").toString());
			l1.clickButton(0, "btnLogin");
			
			l1.clickLink(0, "menu_time_viewTimeModule");
			Thread.sleep(1000);
			l1.setFocus(0,"menu_admin_ProjectInfo");
			l1.clickLink(0, "menu_admin_ProjectInfo");
			l1.clickLink(0, "menu_admin_viewProjects");
			
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(10000);
			String pn = String.valueOf(randomInt);
			
			l1.clickButton(0, "btnAdd");
			l1.setText(0, "addProject_customerName",tdtemp.get("customer_name").toString());
			l1.setText(0, "addProject_projectName",tdtemp.get("project_name").toString()+pn);
			l1.setText(0, "addProject_projectAdmin_1",tdtemp.get("project_admin").toString());
			l1.setFocus(0, "addProject_description");
			l1.setText(0, "addProject_description",tdtemp.get("generic_desc").toString());
			l1.clickButton(0, "btnSave");	
			do { l1.getText(1,"btnSave");	Thread.sleep(1000);	} while(l1.getText(1, "btnSave").equalsIgnoreCase("Processing") && l1.getText(1, "btnSave").equalsIgnoreCase("Save"));
			if(l1.getText(1,"btnSave").contains("Edit"))
				res=true;
			else
				res=false;			
					
		}catch (Exception e) {						
			res=false;	
			e.printStackTrace();
		}
		l1.closeBrowser();	
		Assert.assertTrue(res);
	}
	


	@Test(description="Add a Customer")
	public void TestMethod7() {
	
		try {

			l1.LaunchBrowser(url);
			l1.setText(0, "txtUsername",tdtemp.get("username_admin").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_admin").toString());
			l1.clickButton(0, "btnLogin");
		
			l1.clickLink(0, "menu_time_viewTimeModule");
			Thread.sleep(1000);
			l1.setFocus(0,"menu_admin_ProjectInfo");
			l1.clickLink(0, "menu_admin_ProjectInfo");
			l1.clickLink(0, "menu_admin_viewCustomers");	
				
			l1.clickButton(0, "btnAdd");
			String cn=tdtemp.get("customer_name_random").toString();
			l1.setText(0, "addCustomer_customerName",cn);
			l1.setText(0, "addCustomer_description",tdtemp.get("generic_desc").toString());
			l1.clickButton(0, "btnSave");			
			res = l1.isTextPresentByTag("a",cn);			
				
		}catch (Exception e) {
			
				res=false;	
				e.printStackTrace();
				
			
		}
		l1.closeBrowser();		
		Assert.assertTrue(res);
	}
	
	
	
	

	@Test(description="Delete a Customer")
	public void TestMethod8() {
		try {
			log.error("this is an ERROR message");
			log.info("In Test Method 8 - Delete a Customer");
			l1.LaunchBrowser(url);
			l1.setText(0, "txtUsername",tdtemp.get("username_admin").toString());
			l1.setText(0, "txtPassword",tdtemp.get("password_admin").toString());
			l1.clickButton(0, "btnLogin");
			String cn=tdtemp.get("customer_name").toString();
			l1.clickLink(0, "menu_time_viewTimeModule");
			Thread.sleep(1000);
			l1.setFocus(0,"menu_admin_ProjectInfo");
			l1.clickLink(0, "menu_admin_ProjectInfo");
			l1.clickLink(0, "menu_admin_viewCustomers");
			Thread.sleep(5000);
			String cs=l1.getAttribute("a", "href",cn);
			System.out.println("temp is: "+cs);	
			String temp=cs.substring(cs.lastIndexOf("/") + 1);
			System.out.println("temp is: "+temp);	
			String[] data = temp.split("\\=");
	        String checkboxid="ohrmList_chkSelectRecord_"+data[1].toString();
	        l1.selectCheckbox(0,checkboxid);
	        Thread.sleep(3000);
	        l1.clickButton(0, "btnDelete");
	        Thread.sleep(3000);
	        l1.clickButton(5,"input#dialogDeleteBtn.btn");
	        res = l1.isTextPresentByTag("a",cn );  
	        System.out.println("Checkbox id is : "+checkboxid);
	        System.out.println("Result is : "+res);
			
		}catch (Exception e) {
				
			//log.error("this is an ERROR message from the catch block");
			log.error("Message",e);
			//log.error("message"+Thread.currentThread().getStackTrace()[0].getMethodName().toString()+": Error:"+e.getMessage().toString());
			res=false;			
			e.printStackTrace();
			Assert.assertTrue(res,"Element could not be found, Employee name entry should exist inorder to delete it");
			l1.closeBrowser();
			
		}
		
	
		
		l1.closeBrowser();
		res=true;
		Assert.assertTrue(res);
	
	}		
	
}



