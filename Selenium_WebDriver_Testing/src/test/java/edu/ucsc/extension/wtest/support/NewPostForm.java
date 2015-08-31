package edu.ucsc.extension.wtest.support;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewPostForm {
	
	private WebDriver driver;
	
	@FindBy(how = How.NAME, name = "post_title")
	private WebElement titleBox;
	
	
	@FindBy(how = How.ID, id = "publish")
	private WebElement publishButton;
	
	public NewPostForm(WebDriver driver) {
		this.driver = driver;
	}
	
	public void publish(String title, List<String> contenBullets) {
		titleBox.sendKeys(title);
		// fix question1 
		
		Util.wait(1);
		String basehandler = driver.getWindowHandle();
		driver.switchTo().frame("content_ifr");
		Util.wait(1);
	    WebElement body = driver.findElement(By.tagName("body"));
	    Util.wait(1);
	    for (String each : contenBullets){
	    	body.sendKeys("\n"+each);}
	    Util.wait(1);
	    driver.switchTo().window(basehandler);
	    Util.wait(1);
	    publishButton.click();
		Util.wait(3);
		driver.get("https://shan123456.wordpress.com/wp-admin/edit.php");
		
		Util.wait(3);
		
	}

}
