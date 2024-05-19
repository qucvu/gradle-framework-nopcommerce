package factoryBrowser;

public class BrowserNotSupportedException extends IllegalStateException {
    public BrowserNotSupportedException(String browserName) {
        super(String.format("The browser: %s is not supported", browserName));

    }

}
