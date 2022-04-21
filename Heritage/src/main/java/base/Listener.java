
package base;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listener  implements ITestListener {
	 
	ExtentReports extent = ExtentReporterNG.extentReportGenerator();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		 test=extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
		 extentTest.get().log(Status.INFO,result.getMethod().getMethodName()+" started ");
		 extentTest.get().log(Status.INFO,"Thread started with id---"+Thread.currentThread().getId());
	}

	public void onTestSuccess(ITestResult result) {
		
		

	
		
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName()+" is successful");
	
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	
}