package screen

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

class SearchScreen(driver: AppiumDriver) : AbstractScreen(driver) {
    private val linkXpath = "//*[@resource-id='org.wikipedia:id/page_list_item_title']"
    private val textLinkXpath = "//*[@resource-id='org.wikipedia:id/page_list_item_title'" +
            " and contains(@text, '%s')]"

    @AndroidFindBy(xpath = "//*[contains(@text, 'Search Wikipedia')]")
    private lateinit var searchField: WebElement

    fun enterWordInTheSearchField(word: String): SearchScreen {
        searchField.sendKeys(word)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linkXpath)))
        return this
    }

    fun openTextLink(text: String): ArticleScreen {
        driver.findElement(By.xpath(String.format(textLinkXpath, text))).click()
        return ArticleScreen(driver)
    }
}