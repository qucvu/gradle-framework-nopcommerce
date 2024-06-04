package commons;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface Environment extends Config {
    String endUserUrl();

    String adminUrl();

    String adminEmail();

    String adminPassword();

}
