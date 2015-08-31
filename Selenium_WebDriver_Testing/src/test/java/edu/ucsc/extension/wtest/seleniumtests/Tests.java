package edu.ucsc.extension.wtest.seleniumtests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.ucsc.extension.wtest.support.Dashboard;
import edu.ucsc.extension.wtest.support.LoginPage;
import edu.ucsc.extension.wtest.support.NewPostForm;
import edu.ucsc.extension.wtest.support.PostDashboard;
import edu.ucsc.extension.wtest.support.MediaDashboardAddNew;
import edu.ucsc.extension.wtest.support.MediaDashboardLibrary;


public class Tests {
	
	private static final String username = "shan123456";
	private static final String password = "yi12345678";
	

	@Test
	@Ignore
	public void testPostCreation() {
		WebDriver driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		
		// You may want to add an implicit wait here
	
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Dashboard dashboard = loginPage.login(username, password);
		PostDashboard postdashboard = dashboard.showPostDashboard();
		int postCount = postdashboard.getPostCount();
		System.out.println("There are " + postCount + " posts.");
		NewPostForm newPostform = postdashboard.openNewPostForm();
		
		List<String> content = new ArrayList<String>();
		content.add("*shan123");
		content.add("*shan456");
		content.add("*shan789");
		newPostform.publish("New Title",content);
	
		Util.wait(3);
		int postCountafter = postdashboard.getPostCount();
     	if(postCountafter == (postCount+1)){System.out.println("post successed!");}
		
		Util.wait(3);
		driver.quit();
	}
	@Test
	@Ignore
	public void testDeletAllPost() {
		WebDriver driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		
		// You may want to add an implicit wait here
	
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Dashboard dashboard = loginPage.login(username, password);
		PostDashboard postdashboard = dashboard.showPostDashboard();
		int postCount = postdashboard.getPostCount();
		System.out.println("There are " + postCount + " posts.");
		for(int i=0 ; i <=4; i++){
		NewPostForm newPostform = postdashboard.openNewPostForm();	
		List<String> content = new ArrayList<String>();
		content.add("*shan123");
		content.add("*shan456");
		content.add("*shan789");
		newPostform.publish("New Title",content);	
		}

		postdashboard.deleteAllPosts();
		
		Util.wait(3);
		driver.quit();
	}

	@Test
	@Ignore
	public void testQuickEdit() {
		WebDriver driver = new FirefoxDriver();
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Dashboard dashboard = loginPage.login(username, password);
		PostDashboard postdashboard = dashboard.showPostDashboard();
		
		NewPostForm newPostform = postdashboard.openNewPostForm();
		List<String> content = new ArrayList<String>();
		content.add("*shan123");
		content.add("*shan456");
		content.add("*shan789");
		newPostform.publish("New Title",content);
		Util.wait(3);
		WebElement post_table = driver.findElement(By.tagName("tbody"));	
		List<WebElement> posts = post_table.findElements(By.tagName("strong"));
		WebElement last_post =posts.get(0);
		
		Actions build = new Actions(driver);
		build.moveToElement(last_post).perform();

		WebElement edit = post_table.findElement(By.partialLinkText("Quick"));
		build.moveToElement(edit).click().perform();
		
		
		Util.wait(3);
		WebElement title = driver.findElement(By.name("post_title"));
		title.clear();
	
		Actions build2 = new Actions(driver);
		build2
		.moveToElement(title)
		.sendKeys("title changed")
		.perform();
		Util.wait(2);
		
		WebElement save = driver.findElement(By.partialLinkText("Update"));
		build2.moveToElement(save).click().perform();
		Util.wait(3);
		
		//above works
		
		WebElement tbody = driver.findElement(By.tagName("tbody"));
	    List<WebElement> lastone = tbody.findElements(By.tagName("strong"));
		Actions build3 = new Actions(driver);
		build3.moveToElement(lastone.get(0)).perform();
		
		WebElement delet = tbody.findElement(By.partialLinkText("Trash"));
		build3.moveToElement(delet).click().perform();
		


		Util.wait(3);
		driver.quit();
	}
	
    @Test
    public void testDeleteById(){
    	WebDriver driver = new FirefoxDriver();	
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Dashboard dashboard = loginPage.login(username, password);
		PostDashboard postdashboard = dashboard.showPostDashboard();	
		for(int i=0 ; i<=2; i++){
			NewPostForm newPostform = postdashboard.openNewPostForm();	
			List<String> content = new ArrayList<String>();
			content.add("*shan123");
			content.add("*shan456");
			content.add("*shan789");
			newPostform.publish("New Title",content);	
				
		}

		
		WebElement tbody = driver.findElement(By.id("the-list"));
		List<WebElement> allpost = tbody.findElements(By.tagName("input"));
		int[] first3item = new int[allpost.size()];
		for(int i=0; i<allpost.size();i++){
			String value= (allpost.get(i).getAttribute("value"));
			int id = Integer.parseInt(value);
		     first3item[i]=id;
			
		}
		
	    for(int i=0 ; i <=2;i++){
		 postdashboard.deletePostById(first3item[i]);	
		  }
		
        Util.wait(3);
		driver.quit();
    }
	
	@Test
	@Ignore
	public void testUploadAndDeletePhoto() {
		WebDriver driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		// You may want to add an implicit wait here
	
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Dashboard dashboard = loginPage.login(username, password);
		
		for (int i=0; i<=2;i++){
		MediaDashboardAddNew addnewphoto = dashboard.showMediaDashboardAddNew();
		String photoUrl= "/Users/hsuanyicheng/Desktop/unnamed.png";
		addnewphoto.uploadphoto(photoUrl);}
		
		MediaDashboardLibrary library = dashboard.showMediaDashboardLibrary();
		library.deletdphoto();
		
		Util.wait(5);
		driver.quit();
	}
	
	
}
