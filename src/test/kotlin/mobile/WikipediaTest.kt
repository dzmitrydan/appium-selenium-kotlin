package mobile

import driver.MobileDriverFactory
import org.testng.Assert
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import screen.MainScreen
import utils.getCurrentDate

class WikipediaTest {

    @BeforeTest
    fun setUp() {
        MobileDriverFactory.getDriver()
    }

    @Test
    fun checkSearchingByWord() {
        val text = "Appium"
        val mainScreen = MainScreen()
        val actualTitle = mainScreen
            .clickOnSkipButton()
            .clickOnSearchField()
            .enterWordInTheSearchField(text)
            .openTextLink(text)
            .getTitle()

        Assert.assertEquals(actualTitle, text)
    }

    @Test
    fun checkCurrentDateOnThisDayScreen() {
        val article = "More on this day "
        val dateFromArticle = MainScreen().clickOnSkipButton()
            .scrollToArticle(article)
            .clickMoreOnThisDayLink(article)
            .getDateFromArticle()
        Assert.assertEquals(dateFromArticle, getCurrentDate())
    }

    @AfterTest
    fun tearDown() {
        MobileDriverFactory.quitDriver()
    }
}