package edu.ucsc.extension.wtest.support;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaDashboardAddNew {
    private WebDriver driver ;
    
    @FindBy(how = How.ID, id = "async-upload")
  	 private WebElement add ;
	
    @FindBy(how = How.ID, id = "html-upload")
 	 private WebElement upload ;
    
    public MediaDashboardAddNew(WebDriver driver){
    	this.driver = driver;
    	if(!(driver.getCurrentUrl().endsWith("media-new.php"))){
    		throw new IllegalStateException();
    	}	
    }
    	
    public void uploadphoto(String photourl){
    	add.sendKeys(photourl);
    	upload.click();
       //(new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Posts"));
    	
    }
    
    
    }


	

