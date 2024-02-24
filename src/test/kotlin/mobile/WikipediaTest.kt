package mobile

import driver.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import org.testng.Assert
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import screen.MainScreen

class WikipediaTest {
    private lateinit var driver: AppiumDriver

    @BeforeTest
    fun setUp() {
        driver = MobileDriverFactory.getDriver()
    }

    @Test()
    fun checkSearchingByWord() {
        val text = "Appium"
        val mainScreen = MainScreen(driver)
        val actualTitle = mainScreen
            .clickOnSkipButton()
            .clickOnSearchField()
            .enterWordInTheSearchField(text)
            .openTextLink(text)
            .getTitle()

        Assert.assertEquals(actualTitle, text)
    }

    @AfterTest
    fun tearDown() {
        MobileDriverFactory.quitDriver()
    }
}