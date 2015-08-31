package edu.ucsc.extension.wtest.support;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaDashboardLibrary {
    private WebDriver driver ;
    
    @FindBy(how = How.ID, id= "view-switch-list")
  	 private WebElement photolist ;
	
    
    public MediaDashboardLibrary(WebDriver driver){
    	this.driver = driver;
    	if(!(driver.getCurrentUrl().endsWith("upload.php"))){
    		throw new IllegalStateException();
    	}	
    }
    	
    public void deletdphoto(){
    	//String handler = driver.getWindowHandle();
    	photolist.click();
    	
    	WebDriverWait wait = new WebDriverWait(driver,10);
    	WebElement allphoto= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cb-select-all-1")));
    	allphoto.click();
        WebElement s = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("action")));
    	
    	Select select = new Select(s);
    	select.selectByVisibleText("Delete Permanently");
    	WebElement apply= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doaction2")));
    	apply.click();
    	
    	Util.wait(2);
   
 	
    	}
    	
       
    }
    
   
    


	

