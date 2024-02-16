package driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

object DriverFactory {
    val browser: WebDriver?
        get() {
            //val driverType = "chrome"
            val driverType = "firefox"
            //val driverHeadless = true
            val driverHeadless = false

            val driver: WebDriver? = when (driverType) {
                "chrome" -> {
                    var options = ChromeOptions()
                    options.setHeadless(driverHeadless)
                    WebDriverManager.chromedriver().setup()
                    ChromeDriver(options)
                }

                "firefox" -> {
                    var options = FirefoxOptions()
                    options.setHeadless(driverHeadless)
                    WebDriverManager.firefoxdriver().setup()
                    FirefoxDriver(options)
                }

                else -> null
            }

            driver?.manage()?.window()?.maximize()
            return driver
        }
}