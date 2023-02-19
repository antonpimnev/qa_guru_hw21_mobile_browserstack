package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.WebDriverConfig;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;

import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @SneakyThrows
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", BrowserstackDriver.config.getBrowserstackUser());
        mutableCapabilities.setCapability("browserstack.key", BrowserstackDriver.config.getBrowserstackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", BrowserstackDriver.config.getApp());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", BrowserstackDriver.config.getDeviceName());
        mutableCapabilities.setCapability("os_version", BrowserstackDriver.config.getPlatformVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new RemoteWebDriver(new URL(BrowserstackDriver.config.getUrl()), mutableCapabilities);
    }
}