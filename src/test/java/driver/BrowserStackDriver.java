package driver;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {
    static BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutCaps = new MutableCapabilities();
        mutCaps.setCapability("browserstack.user", config.getUser());
        mutCaps.setCapability("browserstack.key", config.getKey());
        mutCaps.setCapability("app", config.getApp());
        mutCaps.setCapability("device", config.getDevice());
        mutCaps.setCapability("os_version", config.getOsVersion());
        mutCaps.setCapability("project", config.getProject());
        mutCaps.setCapability("build", config.getBuild());
        mutCaps.setCapability("name", config.getName());
        try {
            return new RemoteWebDriver(
                    new URL(config.getBaseUrl()), mutCaps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
