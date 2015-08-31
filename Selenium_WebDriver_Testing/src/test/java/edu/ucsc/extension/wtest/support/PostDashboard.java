package edu.ucsc.extension.wtest.support;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostDashboard {
	
	private WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, linkText = "Add New")
	private WebElement newPostButton;
	
	public PostDashboard(WebDriver driver) {
		this.driver = driver;
		if(!driver.getCurrentUrl().endsWith("edit.php"))
			throw new IllegalStateException();
	}
	
	public NewPostForm openNewPostForm() {
		newPostButton.click();
		
		// Wait for the title to change
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Add New Post"));
		
		// Wait for few more elements
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("submitdiv")));
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("publish")));
		
		return PageFactory.initElements(driver, NewPostForm.class);
	}
	
	public int getPostCount() {
		// fix question 2 
		WebElement post_table = driver.findElement(By.tagName("tbody"));	
		List<WebElement> posts = post_table.findElements(By.tagName("strong"));
		return posts.size();
	}
 
	public PostDashboard deletePostById(int postId){
	     WebElement tbody = driver.findElement(By.tagName("tbody"));
		 List<WebElement> item = tbody.findElements(By.tagName("input"));
	     for(WebElement each :item){
	    	 String value = each.getAttribute("value");
	    	 if(Integer.parseInt(value) == postId){
	    		 WebDriverWait wait = new WebDriverWait(driver,10);
	    		 Actions build = new Actions(driver);
	    		 build.moveToElement(each).click().perform();
	    		 WebElement trash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Trash")));
	    		 build.moveToElement(trash).click().perform();
	    		 break;
	    	 }

	     }
         
	     Util.wait(5); 
	     driver.get("https://shan123456.wordpress.com/wp-admin/edit.php");
	     
	     return PageFactory.initElements(driver, PostDashboard.class);		
		
		
		
	}
   
	
	public PostDashboard deleteAllPosts(){
        
    	int posts = getPostCount();
    	WebElement clickall = driver.findElement(By.id("cb-select-all-1"));
    	WebElement sel = driver.findElement(By.name("action"));
    	Select select = new Select(sel);
    	WebElement apply = driver.findElement(By.id("doaction"));
    	
    	if(posts !=0){
    		clickall.click();
    		Util.wait(1);
    		select.selectByValue("trash");
    		Util.wait(1);
    		apply.click();
    	}
    	
    	
    	return this;  	
    }

}
