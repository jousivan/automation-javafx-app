package br.com.ajss.automation.testutil;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class ScreenshotUtil
{
	public static void takeScreenshot(WebDriver driver, String nomeTeste) throws Exception {
		File screenShotName;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/target/surefire-reports/screnshots/";
		screenShotName = new File(path + nomeTeste + ".png");
		FileUtils.copyFile(scrFile, screenShotName);
		String filePath = screenShotName.toString();
		Reporter.log("<br /><td><a href='" + filePath + "'><img src='" + filePath + "' height='100' width='100' /></a></td><br />");
	}
}
