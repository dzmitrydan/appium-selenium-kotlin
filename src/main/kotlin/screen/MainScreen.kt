package screen

import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import utils.ScrollDirection
import utils.scrollVerticalToElement

class MainScreen : AbstractScreen() {
    private val skipButtonXpath = "//*[contains(@text, 'Skip')]"
    private val articleLink = "//*[@resource-id='org.wikipedia:id/footerActionButton' and contains(@text, '%s')]"

    @AndroidFindBy(xpath = "//*[contains(@text, 'Search Wikipedia')]")
    private lateinit var searchField: WebElement

    @AndroidFindBy(id = "org.wikipedia:id/footerActionButton")
    private lateinit var moreOnThisDayLink  : WebElement

    fun clickOnSkipButton(): MainScreen {
        if (isElementVisible(By.xpath(skipButtonXpath))) {
            driver.findElement(By.xpath(skipButtonXpath)).click()
        }
        return this
    }

    fun clickOnSearchField(): SearchScreen {
        wait.until(ExpectedConditions.visibilityOf(searchField)).click()
        return SearchScreen()
    }

    fun scrollToArticle(article: String): MainScreen {
        scrollVerticalToElement(ScrollDirection.DOWN, By.xpath(String.format(articleLink, article)))
    return this
    }

    fun clickMoreOnThisDayLink(article: String): OnThisDayScreen {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(articleLink, article)))).click()
        return OnThisDayScreen()
    }
}