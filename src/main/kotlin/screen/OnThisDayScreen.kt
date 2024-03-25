package screen

import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

class OnThisDayScreen : AbstractScreen() {

    @AndroidFindBy(id = "org.wikipedia:id/day")
    private lateinit var currentDate: WebElement

    fun getDateFromArticle(): String? {
        return wait.until(ExpectedConditions.visibilityOf(currentDate)).text
    }
}