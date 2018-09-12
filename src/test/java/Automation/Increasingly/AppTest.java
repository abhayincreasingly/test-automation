package Automation.Increasingly;

import java.text.DecimalFormat;
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
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		
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
		 
		 locateBundleTabs();
		
	
		
		
		
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
	        
	      //price validation for PDP bundle 
	        
	         priceCalculationPDP();
	        
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        
		}
	
		
		public static void addToCartAndProceedToCartPage() throws InterruptedException{
			
		   WebDriverWait wait = new WebDriverWait(driver,60);
			
		   WebElement addToCart_btn;
		  
		   
		
		  addToCart_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='content2']//button[@id='btnAddtobasket-product-bundle-1 bundlebutton1']")));
			
		
		   Thread.sleep(5000);
	
		  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart_btn);
		  
		  cartModalValidation();

		   
			
		}
	
		
		public static void mainAddToCart() throws InterruptedException{
			
			WebDriverWait wait = new WebDriverWait(driver,60);
			  WebElement addToCartButton;
				
			  addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/button[1]")));

	        
	        //This will scroll the page till the element is found	
			  
			  JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView();", addToCartButton);
	        
	        System.out.println("Scrolled into view!!!");
	        
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
	        
			WebElement ModalDiv;
			
			ModalDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inc_minibasket_modal-content']")));
			
			if(ModalDiv.isDisplayed()){
				
				Thread.sleep(3000);
				
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
	        
	        WebElement addToCart_button;
	        
	        addToCart_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[10]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[3]/button[1]")));
 
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart_button);
	        
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        
			}
		}
		
		public static void cartModalValidation() throws InterruptedException{
			
			WebDriverWait wait = new WebDriverWait(driver,60);
			
			WebElement ModalDiv;
			
			ModalDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inc_minibasket_modal-content']")));
			
			if(ModalDiv.isDisplayed()){
				
				Thread.sleep(3000);
				
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
		
			
		public static void priceCalculationPDP(){
			
			WebDriverWait wait = new WebDriverWait(driver,60);
			
			DecimalFormat decimalFormat = new DecimalFormat("##.00");
	      
			//decimalFormat.setMaximumFractionDigits(2);
	      //  double value = 234.9300;
	      //  System.out.println("The formatted value is = " + decimalFormat.format(value));

			WebElement ProductA_WasPrice;
			
			WebElement ProductB_WasPrice;
			
			WebElement ProductA_NowPrice;
			
			WebElement ProductB_NowPrice;
			
			WebElement Was_TotalPrice;
			
			WebElement Now_TotalPrice;
			
			ProductA_WasPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/div[2]/span[1]")));
			
			ProductB_WasPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/div[3]/div[2]/div[2]/span[1]")));
		
			ProductA_NowPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/div[2]/span[2]")));
					
			ProductB_NowPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/div[3]/div[2]/div[2]/span[2]")));

			Was_TotalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/span[2]")));
			
			Now_TotalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[12]/div[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/span[3]")));
			
			 
			 String PrevPrice_A = ProductA_WasPrice.getText();
			 
			 String PrevPrice_B = ProductB_WasPrice.getText();
			 
			 String Prev_TotalPrice = Was_TotalPrice.getText();
			 
			 String NewPrice_A = ProductA_NowPrice.getText();
			 
			 String NewPrice_B = ProductB_NowPrice.getText();
			 
			 String New_TotalPrice = Now_TotalPrice.getText();
			 
			 //START - Was prices and the Was price total validation
			 String str1 = PrevPrice_A.replace("$", "");
			 
			 System.out.println("VALUE OF THE PREVIOUS PRICE OF A IS: " +str1);
			 
			 String str2 = PrevPrice_B.replace("$", "");
			 
			 System.out.println("VALUE OF THE PREVIOUS PRICE OF B IS: " +str2);
			 
			 String str3 = Prev_TotalPrice.replace("$", "");
			 
			 System.out.println("VALUE OF THE PREVIOUS PRICE OF SUM TOTAL IS: " +str3);

			 float value1 = Float.parseFloat(str1);
			 
			 System.out.println("Converted float value of the ProductA was price"  +value1);
			 
			 float value2 = Float.parseFloat(str2);
			 
			 System.out.println("Converted float value of the ProductA was price"  +value2);
			 
			 float value3 = Float.parseFloat(str3);
			 
			 String c = decimalFormat.format(value3);
			 
			 System.out.println("Value of c: " +c);
			 
			 float value_final = Float.parseFloat(c);
			 
			 System.out.println("FINAL VALUE OF C: " +value_final);
			 
			 float sum1 = value1+value2;
			 
			 String sum1_decimalEdit = decimalFormat.format(sum1);
			 
			 float sum1_final = Float.parseFloat(sum1_decimalEdit);

			 if(sum1_final == value_final){
				 
				 System.out.println("Value of the total Was price is correct!!");
				 
			 }
			 
			 
		
			 
			 //END - Was prices and the Was price total validation
			 
			 
			//START - Now prices and the Now price total validation
			 String str4 = NewPrice_A.replace("$", "");
			 
			 System.out.println("VALUE OF THE NEW PRICE OF A IS: " +str4);
			 
			 String str5 = NewPrice_B.replace("$", "");
			 
			 System.out.println("VALUE OF THE NEW PRICE OF B IS: " +str5);
			 
			 String str6 = New_TotalPrice.replace("$", "");
			 
			 System.out.println("VALUE OF THE PREVIOUS PRICE OF SUM TOTAL IS: " +str6);
			 
			 
			 float value4 = Float.parseFloat(str4);
			 
			 System.out.println("Converted float value of the ProductA was price"  +value4);
			 
			 float value5 = Float.parseFloat(str5);
			 
			 System.out.println("Converted float value of the ProductA was price"  +value5);
			 
			 float value6 = Float.parseFloat(str6);
			 
			 System.out.println("Converted float value of the ProductA was price"  +value6);
			 
			 
			 String c1 = decimalFormat.format(value6);
			 
			 System.out.println("Value of c1: " +c1);
			 
			 float value_final1 = Float.parseFloat(c1);
			 
			 System.out.println("FINAL VALUE OF C1: " +value_final1);
			 
			 
			 
			 float sum2 = value4+value5;
			 
			 String sum2_decimalEdit = decimalFormat.format(sum2);
			 
			 float sum2_final = Float.parseFloat(sum2_decimalEdit);
			 
			 if(sum2_final == value_final1){
				 
				 System.out.println("Value of the total Now price is correct!!");
				 
			 }
			 
			 //END - Now prices and the Now price total validation
			 
			 
			 
			 
			

		}
	

	
	public static void main(String [] args) throws InterruptedException{
		
		launchBrowser();
	
		System.out.println("Moving to search Bundle Product in PLP");
		
		searchForBundledProduct();
		
		addToCartAndProceedToCartPage();
		
		driver.navigate().back();
		
		mainAddToCart();

		System.out.println("All actions done!");
		
	}
}
