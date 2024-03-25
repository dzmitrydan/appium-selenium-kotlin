package screen

import driver.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class AbstractScreen {
    protected val driver: AppiumDriver = MobileDriverFactory.getDriver();
    protected val wait: WebDriverWait = WebDriverWait(this.driver, Duration.ofSeconds(30))
    init {
        PageFactory.initElements(AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this)
    }

    protected fun isElementVisible(locator: By): Boolean {
        return try {
            driver.findElement(locator).isDisplayed
        } catch (e: Exception) {
            false
        }
    }

    protected fun isElementVisible(element: WebElement): Boolean {
        return try {
            element.isDisplayed
        } catch (e: Exception) {
            false
        }
    }
}