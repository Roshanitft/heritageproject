
package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
static ExtentReports extent;
	public static ExtentReports extentReportGenerator() {
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("TFT Automation Results");
		reporter.config().setDocumentTitle("Heritage");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rishabh Uniyal");
		return extent;
		
		
	}
}
