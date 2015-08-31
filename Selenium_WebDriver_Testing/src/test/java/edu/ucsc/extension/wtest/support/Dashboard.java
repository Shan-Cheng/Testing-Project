package edu.ucsc.extension.wtest.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {
	
	private WebDriver driver;
	
	@FindBy(how = How.CSS, css = "#menu-posts a")
	private WebElement postsMenu;
	
	@FindBy(how = How.ID, id="menu-media")
	private WebElement mediaMenu;
	
	public Dashboard(WebDriver driver) {
		this.driver = driver;
		
		String currentUrl = driver.getCurrentUrl();
		if(!currentUrl.contains("wp-admin"))
			throw new IllegalStateException("Not on the dashboard");
	}
	
	public PostDashboard showPostDashboard() {
		postsMenu.click();
		
		// Wait for the title to change
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Posts"));
		
		// Wait for few items 
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add New")));
		
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.posts")));
		
		// Create a new post dashboard object and return it
		return PageFactory.initElements(driver, PostDashboard.class);
		
	}
	
	public MediaDashboardAddNew showMediaDashboardAddNew(){
		mediaMenu.click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement addNew = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add New")));
		addNew.click();
		
		return PageFactory.initElements(driver, MediaDashboardAddNew.class); 
	}
   
	public MediaDashboardLibrary showMediaDashboardLibrary(){
		mediaMenu.click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement library = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Library")));
		library.click();
		//(new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Media Library "));
		return PageFactory.initElements(driver, MediaDashboardLibrary.class); 
	}


}
