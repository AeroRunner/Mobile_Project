package config;

import org.aeonbits.owner.Config;

@Config.Sources(
        {"classpath:browserStack.properties"}
)
public interface BrowserStackConfig extends Config {
    @Key("user")
    String getUser();

    @Key("key")
    String getKey();

    @Key("app")
    String getApp();

    @Key("device")
    String getDevice();

    @Key("osVersion")
    String getOsVersion();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();

}
