package Automation.Increasingly;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */

public class AppTest{
	
	
	
	public static WebDriver driver;
	
	 
	//Launch Browser action 
	public static void launchBrowser() throws InterruptedException{
		
		System.setProperty("webdriver.gecko.driver", "C:/Program Files/drivers/geckodriver.exe");
		driver =  new FirefoxDriver();
		
		driver.manage().window().maximize() ;
		
		driver.get("https://imomoko.com");
		
		WebDriverWait wait = new WebDriverWait(driver,50);
		
		WebElement Tab;
		
		
		Tab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://www.imomoko.com/lifestyle-health.html']//span[contains(text(),'LIFESTYLE & HEALTH')]")));
		
		Actions act = new Actions(driver);
		
		act.moveToElement(Tab).build().perform();
		
		System.out.println("Hovering over the tab element!");
		
		Thread.sleep(5000);
		
		List<WebElement> Dropdown = driver.findElements(By.xpath("/html[1]/body[1]/div[10]/div[1]/div[1]/div[1]/div[2]/ul[1]/li[3]/ul/li/a/span"));
		
		
		for(WebElement e: Dropdown){
			
			String optionText = e.getText();
			
			if(optionText.contains("SNACKS")){
				
				e.click();
				
				System.out.println("Clicked on the optionText");
				
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				break;
					
			}
			
		}	
	}
	
	//Bundle Product search from PLP
	public static void searchForBundledProduct() throws InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver,50);
		
		WebElement searchField;
		
		searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));
		
		System.out.println("search field found!");
		
		searchField.clear();
		
		searchField.sendKeys("20747");
		
		WebElement searchIcon;
		
		searchIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='hiddenbutton']")));
		
		System.out.println("search field found!");
		
		searchIcon.click();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		System.out.println("Search Action done");
		
		WebElement ProductName;
		
		ProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[10]/div[1]/div[1]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/div[2]/ul[1]/li[1]/div[2]/div[1]/a[1]")));
        
		ProductName.click();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		 System.out.println("Product page visit made!");
		 
		 //entering the bundle  tab grp validation 
		 
		 locateBundleTabs();
		
	//	Thread.sleep(3000);
		
		
		
	}
	
	
		public static void locateBundleTabs(){
			
			 WebDriverWait wait = new WebDriverWait(driver,70);
	

			  WebElement tabGrpHeader;
				
			  tabGrpHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[1]")));

	        
	        //This will scroll the page till the element is found	
			  
			  JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView();", tabGrpHeader);
	        
	        System.out.println("Scrolled into View!");
	        
	        WebElement tabToBeSelected;
	        
	        tabToBeSelected = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[1]/div[2]/span[1]")));
	        
	        tabToBeSelected.click();
	        
	        
	    //    WebElement addToCart_btn;
			
			//   addToCart_btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='content1']//div[@class='bundle_buy_btn']/button[1]")));
	       
	        
	        //   tabToBeSelected = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[1]/div[1]/span[1]")))
		}
	
		
		public static void addToCartAndProceedToCartPage(){
			
		   WebDriverWait wait = new WebDriverWait(driver,60);
			
		   WebElement addToCart_btn;
		   

		   addToCart_btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[2]/div[1]/div[3]/button")));
			
		   Actions builder = new Actions(driver);
		     builder.moveToElement( addToCart_btn ).click( addToCart_btn );
		     builder.perform();

		   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
		}
	
		
		public static void cartModalValidation() throws InterruptedException{
			
			WebDriverWait wait = new WebDriverWait(driver,70);
			
			WebElement ModalDiv;
			
			ModalDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='inc_minibasket_modal-content']")));
			
			if(ModalDiv.isDisplayed()){
				
				WebElement confirmationMsg;
				
				WebElement modalRecHeader;
				
				confirmationMsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='prodAddedTag']")));
				
				modalRecHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='modalPHead']")));
				
				String confirmationText = confirmationMsg.getText();
				
				String recHeaderText = modalRecHeader.getText();
				
				String ActualConfirmText = "was added to your shopping cart";
				
				String ActualHeaderText = "Other users also bought";
				
				if(confirmationText.contains(ActualConfirmText) && recHeaderText.contains(ActualHeaderText)){
					
					System.out.println("Product addition confirmation message found!");
					System.out.println("Modal recommendation text found!");
	
				}
				
				else{
					
					System.out.println("Product addition confirmation message not found!");
					System.out.println("Modal recommendation text not found!");					
				}
				
				WebElement viewCartBtn;
				
				viewCartBtn  = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='incViewBasketHref']")));
				
				viewCartBtn.click();
				
			   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			   
			   Thread.sleep(2000);
				
			}
			
			else{
				System.out.println("Modal not displayed");
			}
		}

	
	public static void main(String [] args) throws InterruptedException{
		
		launchBrowser();
	
		System.out.println("Moving to search Bundle Product in PLP");
		
		searchForBundledProduct();
		
		addToCartAndProceedToCartPage();
		
		cartModalValidation();
		
		
		System.out.println("All actions done!");
		
	}
}
