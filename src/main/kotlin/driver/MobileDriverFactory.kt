package driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException
import java.net.URL

object MobileDriverFactory {
    private var driver: AppiumDriver? = null

    fun getDriver(): AppiumDriver {
        if (driver == null) {
            initializeDriver()
        }
        return driver!!
    }

    private fun initializeDriver() {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("platformVersion", "13")
        capabilities.setCapability("deviceName", "Pixel_5_API_33")
        capabilities.setCapability("appPackage", "org.wikipedia")
        capabilities.setCapability("appActivity", ".main.MainActivity")
        capabilities.setCapability("app", File("apps/org.wikipedia.apk").absolutePath)
        capabilities.setCapability("udid", "emulator-5554")
        capabilities.setCapability("automationName", "UiAutomator2")

        val appiumServerURL: URL
        try {
            appiumServerURL = URL("http://127.0.0.1:4723/wd/hub")
        } catch (e: MalformedURLException) {
            throw RuntimeException(e)
        }
        driver = AndroidDriver(appiumServerURL, capabilities)
    }

    fun quitDriver() {
        if (driver != null) {
            driver!!.quit()
            driver = null
        }
    }
}