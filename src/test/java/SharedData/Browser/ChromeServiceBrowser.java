package SharedData.Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeServiceBrowser implements IBrowserServices{

    private WebDriver driver;

    @Override
    public void openBrowser() {
        ChromeOptions options = (ChromeOptions) browserOptions();
        driver = new ChromeDriver(options);

    }

    @Override
    public Object browserOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.addArguments("no-sandbox");

        options.setExperimentalOption("prefs", java.util.Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));

        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
