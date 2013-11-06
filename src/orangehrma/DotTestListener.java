package orangehrma;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class DotTestListener implements ITestListener{
	TestReport tr = new TestReport();
	TestData ob1 = new TestData();
	DateFormat dateFormat = 
	        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	ArrayList<String> tcdata = new ArrayList<String>();
	HrmLib l1 = new HrmLib();	
	Date dt1,dt2 = null;
	long diff;
	int i=0;
	@Override
	 public void onTestSuccess(ITestResult result) {
	
		if(result.isSuccess()) {
			dt2 = new Date();
			diff = Math.abs(dt2.getTime() - dt1.getTime())/(1000);
			tcdata.add(dateFormat.format(dt2));
			tcdata.add(String.valueOf(diff));
			tcdata.add("Passed");
			tcdata.add("NA");					
			tr.addTestCaseResult(tcdata);
			
		}
	  }

	private void log(String string) {
		// TODO Auto-generated method stub
		System.out.println("log using listener...");		
	} 

	//Called when the test in xml suite finishes
	  @Override
	  public void onFinish(ITestContext context) {
		 
		  tr.testCaseResults();
	    
	  }
	  
	  //Returns the current time when the method is called
	  public String getCurrentTime(){
	    DateFormat dateFormat = 
	        new SimpleDateFormat("HH:mm:ss:SSS");
	    Date dt = new Date();
	    return dateFormat.format(dt);    
	  }

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		tcdata.clear();
		i+=1;
		//tcdata.add(l1.getTestCaseTitle(i));
		tcdata.add(result.getMethod().getDescription().toString());
		//tcdata.add(result.getMethod().getMethodName());
		dt1 = new Date();
		tcdata.add(dateFormat.format(dt1));
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("failure is also getting called");
		
		if(!result.isSuccess()) {
			dt2 = new Date();
			tcdata.add(dateFormat.format(dt2));
			diff = Math.abs(dt2.getTime() - dt1.getTime())/(1000);			
			tcdata.add(String.valueOf(diff));
			tcdata.add("Failed");
			tcdata.add("<a href='logging.log'>View Log</a>");	
			tr.addTestCaseResult(tcdata);			
		} 		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		Dictionary<Object,Object> tdtemp=ob1.getTestData();
		String url =tdtemp.get("hosturl").toString(); 
	    tr.createReport(".\\src\\orangehrma",tdtemp.get("reportFile").toString());						
		Map<String, String> hd = new HashMap<String, String>();			
		dt1 = new Date();
		hd.put("Browser", tdtemp.get("browser").toString());
		hd.put("Report Generated Time",dateFormat.format(dt1));
		hd.put("Application: ", tdtemp.get("application").toString());
		l1.LaunchBrowser(url);
		hd.put("User Agent",l1.getUserAgent());
		l1.closeBrowser();
		tr.addReportHeader(hd);		
		ArrayList<String> th = new ArrayList<String>();
		th.add("Title");th.add("Start Time");th.add("End Time");th.add("Duration");
		th.add("Results");th.add("Comments");
	
		tr.addTCHeader(th);		
		
	}


}
