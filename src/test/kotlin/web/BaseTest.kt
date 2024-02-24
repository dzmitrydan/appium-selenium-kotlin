package web

import driver.WebDriverFactory
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest

abstract class BaseTest {
    protected var driver: WebDriver? = null

    @BeforeTest
    fun setUp() {
        driver = WebDriverFactory.browser
    }

    @AfterTest
    fun tearDown() {
        WebDriverFactory.quitDriver()
    }
}