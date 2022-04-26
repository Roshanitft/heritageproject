package testpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import base.PageClassVPN;
import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Listeners(base.Listener.class)
public class TestVPN_top10domain2 {


    JavascriptExecutor js;
    Actions action;
    WebDriverWait webDriverWait;
    public WebDriver driver;
    

    @DataProvider(name="countries") public Object[][] data(){
        return new Object[][]

                {{"United States"}, {"United Kingdom"}, {"Canada"}, {"Australia"}, {"Germany"}, {"Netherlands"}, {"Switzerland"}, {"Sweden"}};

    }


    @BeforeSuite
    public void setup()  {
     
        System.setProperty( "webdriver.chrome.driver", "/home/runner/work/heritageproject/heritageproject/Heritage/chromedriver");
	String extension_Path = System.getProperty("user.dir");
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("--headless");
 	//chromeOptions.addExtensions(new File( "/home/runner/work/heritageproject/heritageproject/Heritage/extensions/Urban-Free-VPN-proxy-Unblocker---Best-VPN.crx"));
    
      driver = new ChromeDriver(chromeOptions);

     

	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        
   
        

    }

    
    @Test(invocationCount=1,threadPoolSize=1)
    public void vpnInit() throws InterruptedException, AWTException {

        
            driver.get("https://top10.com/dna-testing/comparison");
            Thread.sleep(2000);
           WebElement mostVisitedDNASitesSection= driver.findElement(By.xpath("//div[@class='css-unb0xl']/a"));
            System.out.println(mostVisitedDNASitesSection.getSize());
          Dimension actualSize= mostVisitedDNASitesSection.getSize();
           int height=actualSize.height;
           int width=actualSize.width;
           System.out.println(height);
           System.out.println(width);
         //   System.out.println(mostVisitedDNASitesSection.getText());

            if(mostVisitedDNASitesSection.getText().contains("Our Top Choice")) {
            	if(mostVisitedDNASitesSection.getText().contains("MyHeritage")) {
            		System.out.println("Heritage is present as Our Top Choice");
            		
            		 if(mostVisitedDNASitesSection.getText().contains("Exceptional")) {
                     	System.out.println("Heritage product score is exceptional");
                     }
            		 else {
            			 System.out.println("Heritage product score is not mentioned as exceptional");
            			 //Screenshot
            		 }
            		 
            		 if(mostVisitedDNASitesSection.getText().contains("9.9")) {
            			 System.out.println("Heritage rating is 9.9 as expected");
                     }
            		 else {
            			 System.out.println("Heritage rating is not 9.9 as expected");
            			 //Screenshot
            		 }
						
            	}
            	
            	else {
            		System.out.println("Heritage is not present as Our Top Choice");
            		//Screenshot
            	}
            	
            }
    }
            
            
            
            WebElement firstSection = driver.findElement(By.xpath(" //*[@class='index-counter__value'  and text()='1']/following::img"));
            if(firstSection.getAttribute("alt").contains("MyHeritage DNA")) {
            	System.out.println("MyHeritage DNA is ranked first");
            }
            else {
            	System.out.println(firstSection.getAttribute("alt")+"is ranked first");
            }
            
            
            
           
            
           
            
           


    public void vpnCountrySelector(String country) throws InterruptedException {

        Boolean status;

        if(driver.findElements(By.xpath("//h1[text()='Like using Urban VPN?']")).size()>0){
            if(driver.findElements(By.xpath("//*[@class='cross_button simple_layout__close']")).size()>0){
                driver.findElement(By.xpath("//*[@class='cross_button simple_layout__close']")).click();
                if(driver.findElements(By.xpath("//div[@class='select_location']/descendant::div[@class='cross_button']")).size()>0) {
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='select_location']/descendant::div[@class='cross_button']"))).click();
                }
            }
        }

        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='search location']")))).sendKeys(country);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(@class,'location_flag')]")))).click();
        Thread.sleep(5000);
        status =webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@class='loader__spin']")))).booleanValue();
        System.out.println(status);
        if(!status){
            Thread.sleep(5000);
            status =webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@class='loader__spin']")))).booleanValue();
            if(!status){
                Thread.sleep(10000);
            }
        }



    }

    @AfterSuite
    public void teardown(){
        driver.close();
        driver.quit();
    }


}






