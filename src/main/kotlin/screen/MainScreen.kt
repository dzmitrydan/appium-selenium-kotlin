package screen

import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import java.awt.Dimension

class MainScreen(driver: AppiumDriver) : AbstractScreen(driver) {
    private val skipButtonXpath = "//*[contains(@text, 'Skip')]"

    @AndroidFindBy(xpath = "//*[contains(@text, 'Search Wikipedia')]")
    private lateinit var searchField: WebElement

    @AndroidFindBy(id = "voice_search_button")
    private lateinit var voiceSearchButton: WebElement

    fun clickOnSkipButton(): MainScreen {
        if (isElementVisible(By.xpath(skipButtonXpath))) {
            driver.findElement(By.xpath(skipButtonXpath)).click()
        }
        return this
    }

    fun clickOnSearchField(): SearchScreen {
        wait.until(ExpectedConditions.visibilityOf(searchField)).click()
        return SearchScreen(driver)
    }
}