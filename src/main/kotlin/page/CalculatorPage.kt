package org.example.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class CalculatorPage(driver: WebDriver) {

    private var driver: WebDriver? = null
    private var weit: WebDriverWait? = null

    @FindBy(xpath = "//h1")
    private var greetingTitle: WebElement? = null

    private var  itemDropdownCurrencyXpath: String? = "//*[contains(text(), '%s')]/ancestor::li[@role='menuitemradio']"

    @FindBy(xpath = "//*[@jsname='WjL7X']//*[@jsname='V67aGc']")
    private var dropdownCurrency: WebElement? = null

    init {
        this.driver = driver
        weit = WebDriverWait(driver, 10)
        PageFactory.initElements(driver, this)
    }

    fun openPage() {
        driver!!.get("https://cloud.google.com/products/calculator/")
    }

    fun getPageUrl(): String? {
        return driver!!.currentUrl
    }

    fun getGreetingTitleText(): String? {
        return greetingTitle!!.text
    }

    fun selectDropdownCurrency(item: String?) {
        selectDropdown(item, itemDropdownCurrencyXpath, dropdownCurrency)
    }

    fun getSelectedItemInDropdownCurrency(): String? {
        return dropdownCurrency!!.text
    }

    private fun selectDropdown(item: String?, itemDropdownXpath: String?, dropdown: WebElement?) {
        dropdown!!.click()
        val itemXpath = String.format(itemDropdownXpath!!, item)
        val element = driver!!.findElement(By.xpath(itemXpath))
        element.click()
        weit!!.until(ExpectedConditions.attributeContains(element, "aria-checked", "true"))
    }
}