package screen

import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class ArticleScreen : AbstractScreen() {

    @AndroidFindBy(xpath = "//*[@resource-id='pcs']/*/*")
    private lateinit var title: WebElement

    fun getTitle(): String {
        return title.text
    }
}