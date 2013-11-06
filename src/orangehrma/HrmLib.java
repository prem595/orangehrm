package orangehrma;
import java.util.Hashtable;
import java.util.List;
import java.util.Dictionary;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;




import java.awt.RenderingHints.Key;
import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HrmLib {

	String browser=null;
	String platform="windows";
	WebDriver driver;
	String testCaseResult="Failed";
	String userAgent;
	TestData ob1 = new TestData();
	Dictionary<Object,Object> co=ob1.getTestData();
	public void LaunchBrowser(String url) {
		browser=co.get("browser").toString();
		try {

			if(browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("InternetExplorer")) {
				driver = new InternetExplorerDriver();	
				driver.get(url);
			}
			else if(browser.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();	
				driver.get(url);
			}
			else if(browser.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();	
				driver.get(url);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setText(int locateBy,String val,String txt) {

		try {
			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				if(browser.equalsIgnoreCase("IE") || browser.equalsIgnoreCase("InternetExplorer"))
				{
					
					oelement.click();
					oelement.clear();
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("return document.all."+val+".value='"+txt+"'");
									
				}
				else { 
					oelement.sendKeys(txt);
					driver.switchTo().activeElement().sendKeys(Keys.TAB);
					}
				System.out.println("Value is: "+txt);
				break;		

			case 5:
				wait = new WebDriverWait(driver, 10);
				oelement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(val)));
				oelement.sendKeys(txt);
				System.out.println("Value is: "+txt);
				break;	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public String getAttribute(String tag,String attr,String val) {
		String temp=null;
		try {		
				List<WebElement> tags = driver.findElements(By.tagName(tag));
				for (int i = 0; i < tags.size(); i++) {
					
					if(tags.get(i).getAttribute("innerHTML").equals(val)) {
						temp=tags.get(i).getAttribute(attr);	
						System.out.println("Val of href is: "+temp);
						break;
					}					
				}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	
	}
	
	public void clickButton(int locateBy,String val) {

		try {

			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				oelement.click();
				System.out.println("Value is: "+val);
				break;
			case 1:
				oelement=driver.findElement(By.name(val));
				oelement.click();			
				System.out.println("Value is: "+val);
			case 2:
				Actions action =new Actions(driver);
				wait = new WebDriverWait(driver, 10);
				oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				action.click(oelement);
				System.out.println("Value is: "+val);
				break;				
			case 5:
				wait = new WebDriverWait(driver, 10);
				oelement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(val)));
				oelement.click();
				System.out.println("Value is: "+val);
				break;	
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUserAgent() {
		String ua;		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ua=(String) js.executeScript("return navigator.userAgent");
		System.out.println("User Agent is: "+ua);		
		return ua;


	}
	public void clearTextField(int locateBy,String val) {
		try {

			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				oelement.clear();			
				System.out.println("Value is: "+val);
				break;
			case 1:
				oelement=driver.findElement(By.name(val));
				oelement.clear();			
				System.out.println("Value is: "+val);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void selectCheckbox(int locateBy,String val) {
		try {
			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				oelement.click();			
				System.out.println("Value is: "+val);
				break;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectComboboxItem(int locateBy,String val,String txt) {
		try {

			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				List<WebElement> allOptions = oelement.findElements(By.tagName("option"));
				for (WebElement option : allOptions) {
					System.out.println(String.format("Value is: %s", option.getAttribute("value")));
					if(option.getAttribute(("value")).equals(txt)) {
						option.click();
						break;	    	  
					}				   
				}						
				break;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void wait(String time) {

		try {
			System.out.println("Waiting For: "+time);
			Thread.sleep(Long.parseLong(time));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
	public String getText(int locateBy,String val) {
		String temp=null;
		try {
			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(val)));
				temp=oelement.getText();
				System.out.println("Value is: "+temp);
				break;		
			case 1:
				wait = new WebDriverWait(driver, 10);
				oelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(val)));
				temp=oelement.getAttribute("value");
				System.out.println("Value is: "+temp);
				break;	
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return temp.trim();
	}
	

	public boolean isElementVisible(int locateBy,String val,String txt) {
		boolean res=false;
		try {
			Thread.sleep(1000);
			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.elementToBeClickable(By.id(val)));
				System.out.println("Value for element is: "+oelement.getText());
				if(oelement.isDisplayed() && oelement.getText().equalsIgnoreCase(txt)) {
					res=true;				
				}
				else {					
					res=false;		
				}
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return res;	
	}


	public void setResult(int tcResult) {

		try {
			Thread.sleep(1000);
			switch(tcResult) {
			case 1:		
				System.out.println("TestCase Result: Passed");
				testCaseResult="Passed";
				break;
			case 0:
				System.out.println("TestCase Result: Failed");
				testCaseResult="Failed";
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setFocus(int locateBy,String val) {

		try {

			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(val)));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("return document.all."+val+".focus()");
				System.out.println("In setFocus()");
				Thread.sleep(1000);
				break;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clickLink(int locateBy,String val) {

		try {

			switch(locateBy) {
			case 0:
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement oelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(val)));
				oelement.click();			
				System.out.println("Value is: "+val);
				break;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isTextPresentByTag(String tag,String val) {
		boolean res=false;
		List<WebElement> tags = driver.findElements(By.tagName(tag));

		for (int i = 0; i < tags.size(); i++) {
			//System.out.println(tags.get(i).getAttribute("innerHTML"));
			if(tags.get(i).getAttribute("innerHTML").equals(val)) {
				res=true;	
				break;
			}
			else {
				res=false;
			}
		}

		return res;	
	}

	public void closeBrowser() {
		try {
			System.out.println("Closing the browser");
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}

