package orangehrma;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class TestReport {

	File f=null;
	FileWriter fw=null;
	BufferedWriter bw=null;
	String fp=null,fn=null;
	int tc_pass = 0,tc_fail=0,tc_total=0;
	
	
	public void createReport(String filePath, String fileName) {
		try 
		{
			File f = new File(filePath +"\\"+fileName);
			if(f.exists()) {
				f.delete();
			}
			fw = new FileWriter(filePath +"\\"+fileName,true);
			fp=filePath;
			fn=fileName;
		}
		catch(Exception e){ 
			e.printStackTrace();
		}		
	}
	public void addReportHeader(Map<String,String> headerInfo){		
	
		
		try {
			fw = new FileWriter(fp +"\\"+fn,true);			
			bw = new BufferedWriter(fw);
			fw.append("<STYLE>.firstCell{font-family:verdana;font-size:12;border-style:solid;border-bottom-color:blue;border-left-color:blue;border-width:1;}.normal{font-family:verdana;font-size:12;border-style:solid;border-bottom-color:blue;border-width:1;border-left-color:blue;}.lastCell{font-family:verdana;font-size:12;border-style:solid;border-bottom-color:blue;border-right-color:blue;border-width:1;border-left-color:blue;}.thfirstCell{font-family:verdana;font-size:12;border-style:solid;border-bottom-color:blue;border-top-color:blue;border-left-color:blue;border-width:1;}.thnormal {font-family:verdana;font-size:12;border-style:solid;border-bottom-color:blue;border-top-color:blue;border-left-color:blue;border-width:1;}.thlastCell{font-family:verdana;font-size:12;border-style:solid;border-bottom-color:blue;border-right-color:blue;border-width:1;border-left-color:blue;border-top-color:blue;}</STYLE>");
			for ( Map.Entry<String, String> entry : headerInfo.entrySet() ) {
			  fw.append("<div style='font-family:verdana;font-size:12;'><u>"+entry.getKey()+"</u> <b style='color:blue;font-family:verdana;font-size:12;'> "+entry.getValue()+ "</b></div>");
			    
			}	
				fw.close();
				bw.close();			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	public void addTCHeader(ArrayList al)
	{
		try {
			fw = new FileWriter(fp +"\\"+fn,true);			
			bw = new BufferedWriter(fw);		
			fw.append("<br><table cellspacing=1>");
			for(int i=0;i<al.size();i++)
			{
				String cl=(i==0)? "class=thfirstCell":"class=thnormal";
				fw.append("<th "+cl+">"+al.get(i).toString()+"</th>");			
				System.out.println("test case header: "+al.get(i).toString());
			
			}
			fw.close();
			bw.close();	
		
		}
		catch(Exception e) { e.printStackTrace();}
	
	}
	public void addTestCaseResult(ArrayList al) {
		try {
			fw = new FileWriter(fp +"\\"+fn,true);			
			bw = new BufferedWriter(fw);		
			fw.append("<tr>");
			for(int i=0;i<al.size();i++)
			{
				if(i==4 & al.get(i).toString().equalsIgnoreCase("Passed")) {
					fw.append("<td class=normal style='background-color:green;color:white;'>"+al.get(i).toString()+"</td>");
					tc_pass+=1;
				}
				else if(i==4 && al.get(i).toString().equalsIgnoreCase("Failed")) {
					fw.append("<td class=normal style='background-color:red;color:white;'>"+al.get(i).toString()+"</td>");
					tc_fail+=1;
				}
				else {
					fw.append("<td class=normal>"+al.get(i).toString()+"</td>");
				}					
				
				System.out.println("Test Case Details: "+al.get(i).toString());			
			}
			fw.append("</tr>");
			fw.close();
			bw.close();	
		
		}
		catch(Exception e) { e.printStackTrace();}
		
	}
	public void testCaseResults() {
		tc_total=tc_pass+tc_fail;
		try {
			fw = new FileWriter(fp +"\\"+fn,true);
			bw = new BufferedWriter(fw);
			fw.append("</table><hr><div><span style='color:black;font-face:bold;' ><u>Test Execution Summary</u></span><br>");
			fw.append("<span>Total Test Cases Executed: "+tc_total+"</span></span><br>");
			fw.append("<span>Test Cases Passed: <span style='color:green;font-weight:bold;'>"+tc_pass+"</span></span><br>");
			fw.append("<span>Test Cases Failed: <span style='color:red;font-weight:bold;'>"+tc_fail+"</span></span</div>");
			fw.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
