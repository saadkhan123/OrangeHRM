package Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class DelayedListener implements WebDriverListener {
    private void delay() {
        try {
            Thread.sleep(1000); // delay 1 second after each step
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void afterClick(WebElement element) {
        delay();
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        delay();
    }

    public void afterGetText(WebElement element, String result) {
        delay();
    }
}
