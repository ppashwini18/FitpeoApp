package Automation_project.Automation_project;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;



public class AppTest
{
	public static void main(String[] args)
	{
		ChromeDriver driver= new ChromeDriver();
		try
		{
		
		driver.get("https://www.fitpeo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://fitpeo.com/revenue-calculator");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Sucessfully navigate to revenue calculator page:");
		//Thread.sleep(500);
		//driver.close();
		}
		catch(Exception e)
		{
			System.out.println("Unable to launch FitPeo Homepage");
		}
		try
		{
				WebElement revenueCalSlider =driver.findElement(By.xpath("//div[contains(@class,'MuiBox-root')]/child::h4[text()='Medicare Eligible Patients']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", revenueCalSlider);
				Thread.sleep(500);
				System.out.println("Sucessffully scroll down to revenue calculator slider");
				
				WebElement sliderSourecePt=driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb MuiSlider-thumbSizeMedium')]"));
				Thread.sleep(2000);
				Actions act= new Actions(driver);
				act.dragAndDropBy(sliderSourecePt,94,0).build().perform();
				Thread.sleep(500);
				System.out.println("Sucessfully Adjust the Slider and updated to 820");
				
				//Validate Slider Value
				WebElement sliderValueTxt=driver.findElement(By.xpath("//div[contains(@class,'MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-129j43u')]//input[@value='823']"));
				Thread.sleep(500);

				if(sliderValueTxt.isDisplayed())
				{
					
					//Actions action = new Actions(driver);
					//action.moveToElement(sliderValueTxt).build().perform();
					//Thread.sleep(500);

					//sliderValueTxt.click();

					Thread.sleep(2000);
					sliderValueTxt.clear();
					
					
					// Select CPT Codes:
					Thread.sleep(2000);
					WebElement ctpScroll =driver.findElement(By.xpath("//p[normalize-space()='CPT-99091']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ctpScroll);
					List<WebElement> checkBoxCtp= driver.findElements(By.xpath("//span[@class='MuiTypography-root MuiTypography-body1 MuiFormControlLabel-label css-1s3unkt']"));
					for(WebElement ele:checkBoxCtp)
					{
						String getTextValue=ele.getText();
						if(getTextValue.contains("57") || getTextValue.contains("19.19") || getTextValue.contains("63"))
						{
							ele.click();
							System.out.println(ele.getText());
							Thread.sleep(2000);	
							driver.manage().window().maximize();
							WebElement ctpScroll1 =driver.findElement(By.xpath("//p[normalize-space()='CPT-99091']"));
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ctpScroll1);
							
						}
						
						
					}
					
					
				}
				else
				{

					System.out.println("Not Displayed");
				
				}
				
				// validate Total Recurring Reimbursement:
				try
				{
					WebElement totalRemTxt= driver.findElement(By.xpath("//p[contains(text(),'Total Recurring Reimbursement for all Patients Per')]"));
					if(totalRemTxt.isDisplayed())
					{
						// Verify that the header displaying Total Recurring Reimbursement
						System.out.println("Header displaying for total recurring");
						WebElement totalValueReim= driver.findElement(By.xpath("//div[@class='MuiBox-root css-m1khva']/child::p[2]"));
						if(totalValueReim.getText().contains("98760"))
						{
							System.out.println("Verify sucessfully header displaying total recurring Reimbursement for patient");
						}
				
						
					}
				}
				catch(Exception e)
				{
					System.out.println("Header not displaying for total recurring");
				}
					
		}
		
		catch(Exception e)
		{
			System.out.println("revenue calculator slider not visible");
		}
		driver.close();
	}
}