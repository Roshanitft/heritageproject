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
        chromeOptions.addExtensions(new File( "/home/runner/work/heritageproject/heritageproject/Heritage/extensions/Urban-Free-VPN-proxy-Unblocker---Best-VPN.crx"));
   	options.addArgument("headless");
	    
     	driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


}


        
   
        

    }

    
    @Test(invocationCount=1,threadPoolSize=1)
    public void vpnInit() throws InterruptedException, AWTException {

        String arr[] = {"Sweden"};
        //"United States", "United Kingdom", "Canada", "Australia", "Germany", "Netherlands", "Switzerland", 

        for (int i = 0; i < arr.length; i++) {
            driver.get("chrome-extension:\\eppiocemhmnlbhjplcgkofciiegomcon\\popup\\index.html");
            String parent = driver.getWindowHandle();
            Thread.sleep(10000);

            Set<String> winHandles = driver.getWindowHandles();
            System.out.println("Windows:" + winHandles.size());
            Iterator<String> it = winHandles.iterator();
            if (winHandles.size() > 1) {
                while (it.hasNext()) {
                    String childWindow = it.next();
                    if (!parent.equals(childWindow)) {
                        driver.switchTo().window(childWindow);
                        driver.close();
                        driver.switchTo().window(parent);
                    }
                }
            }

            webDriverWait = new WebDriverWait(driver, 40);
            PageClassVPN vpnPage = new PageClassVPN(driver);
            if (driver.findElements(By.xpath("//*[text()='Agree']")).size() > 0) {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(vpnPage.agree_Btn)).click();
            }
            if (driver.findElements(By.xpath("//div[@class='select_location']/descendant::div[@class='cross_button']")).size() > 0) {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(vpnPage.cross_Btn)).click();
            }

            js = (JavascriptExecutor) driver;
            action = new Actions(driver);
            // String ele = "//ul[@class='locations']/li";
            vpnCountrySelector(arr[i]);
            //  Thread.sleep(5000);
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
						/*
						 * if(mostVisitedDNASitesSection.getText().contains("96 million users globally"
						 * )) { System.out.println("Heritage has 96 million users globally"); } else {
						 * System.out.println("Heritage does not have 96 million users globally");
						 * //Screenshot }
						 * 
						 * if(mostVisitedDNASitesSection.getText().contains("Get Started")) {
						 * System.out.println("Get Started link is present for Heritage"); } else {
						 * System.out.println("Get Started link is not present for Heritage");
						 * //Screenshot }
						 */
            	}
            	
            	else {
            		System.out.println("Heritage is not present as Our Top Choice");
            		//Screenshot
            	}
            	
            }
            
            
            
            WebElement firstSection = driver.findElement(By.xpath(" //*[@class='index-counter__value'  and text()='1']/following::img"));
            if(firstSection.getAttribute("alt").contains("MyHeritage DNA")) {
            	System.out.println("MyHeritage DNA is ranked first");
            }
            else {
            	System.out.println(firstSection.getAttribute("alt")+"is ranked first");
            }
            
            
            
           
            
           
            
           /* WebElement firstSection = driver.findElement(By.xpath("//div[contains(text(),'Our #1 Choice')]/following-sibling::div[@class='row']/descendant::div[@class='vendor-title']"));
            String firstSection_text = firstSection.getText();

            if (firstSection_text.equals("MyHeritageDNA")) {
                System.out.println("MyHeritageDNA is present in Our #1 Choice section at the starting of the page");
            } else {
                Assert.assertTrue(false, firstSection_text + "is present in Our #1 Choice section at the starting of the page");
            }


            WebElement PopularDNAKits = driver.findElement(By.xpath("//h2[contains(text(),'Popular DNA Kits')]"));

            WebElement PopularDNAKitsSection = driver.findElement(By.xpath("//div[contains(text(),'Our #1 Choice')]/following::div[@class='rank'][1]/following-sibling::div[@class='holder-body']/descendant::div[@class='vendor-title']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", PopularDNAKits);
            String PopularDNAKitsSection_text = PopularDNAKitsSection.getText();

            if (PopularDNAKitsSection_text.equals("MyHeritageDNA")) {
                System.out.println("MyHeritageDNA is present in Our #1 Choice section under Popular DNA Kits");
            } else {
                Assert.assertTrue(false, PopularDNAKitsSection_text + "is present in Our #1 Choice section under Popular DNA Kits");
            }


            WebElement getThisKit = driver.findElement(By.xpath("//div[@class='vendors-list']/child::div[@data-title='MyHeritageDNA']/descendant::div[@class='visit-btn-holder']/child::a"));
            getThisKit.getCssValue("background-color");
            System.out.println(getThisKit.getCssValue("background-color"));
            WebElement popupInvokerReferencePoint = driver.findElement(By.xpath("//a[text()='Best DNA Kits for']"));


            if (driver.findElements(By.xpath("//div[@id='exit-popup']")).size() > 0) {
                String popup = driver.findElement(By.xpath("//div[@id='exit-popup']")).getAttribute("data-vendor");
                System.out.println(popup);
                Thread.sleep(2000);
                String div_text;


            }*/
        }

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






