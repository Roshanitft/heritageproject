package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageClassVPN  {

    WebDriver driver;

   @FindBy(xpath ="//*[text()='Agree']")
public
   WebElement agree_Btn;

    @FindBy(xpath ="//div[@class='select_location']/descendant::div[@class='cross_button']")
 public    WebElement  cross_Btn;

    @FindBy(xpath =" //*[contains(@class,'location_flag')]")
    public   WebElement locationFlag;

   public PageClassVPN(WebDriver driver){

        this.driver=driver;
        PageFactory.initElements(driver,this);
   }

}



