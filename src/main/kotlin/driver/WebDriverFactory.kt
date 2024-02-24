package driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

object WebDriverFactory {
    private var driver: WebDriver? = null

    val browser: WebDriver?
        get() {
            if (driver == null) {
                val driverType = "firefox"
                val driverHeadless = false

                driver = when (driverType) {
                    "chrome" -> {
                        WebDriverManager.chromedriver().setup()
                        ChromeDriver()
                    }

                    "firefox" -> {
                        FirefoxDriver()
                    }

                    else -> null
                }

                driver?.manage()?.window()?.maximize()
            }

            return driver
        }

    fun quitDriver() {
        driver?.quit()
        driver = null
    }
}