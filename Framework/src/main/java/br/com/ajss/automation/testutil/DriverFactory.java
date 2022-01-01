package la.foton.sisag.automation.testutil;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory
{
	private static WebDriver driver;
	private DesiredCapabilities capabilities;
    private String remoteDebuggingAddress = "localhost:9222";
    private ChromeOptions options = new ChromeOptions();
    private ChromeDriverService service;

	public  WebDriver getDriver()
	{
		if (driver==null) {
			service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("src/test/resources/drivers/chromedriver.exe")).usingAnyFreePort().build();
			try {
				service.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			capabilities = new DesiredCapabilities();
			options.addArguments("--disable-print-preview");
			options.setExperimentalOption("debuggerAddress", remoteDebuggingAddress);
			//options.addArguments("--headless");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new RemoteWebDriver(service.getUrl(), capabilities);
		}
		return driver;
	}
}
