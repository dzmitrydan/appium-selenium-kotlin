package driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

object DriverFactory {
    private var driver: WebDriver? = null

    val browser: WebDriver?
        get() {
            if (driver == null) {
                val driverType = "firefox"
                val driverHeadless = false

                driver = when (driverType) {
                    "chrome" -> {
                        val options = ChromeOptions()
                        options.setHeadless(driverHeadless)
                        WebDriverManager.chromedriver().setup()
                        ChromeDriver(options)
                    }

                    "firefox" -> {
                        val options = FirefoxOptions()
                        options.setHeadless(driverHeadless)
                        WebDriverManager.firefoxdriver().setup()
                        FirefoxDriver(options)
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