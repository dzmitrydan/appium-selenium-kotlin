import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Optional
import org.testng.annotations.Parameters
import org.testng.annotations.Test
import org.example.page.CalculatorPage

class CalculatorSimpleTest : BaseTest() {
    private lateinit var calculatorPage: CalculatorPage

    @Test
    fun checkUrlPage() {
        calculatorPage = CalculatorPage(driver!!)
        calculatorPage.openPage()
        val actualURL = calculatorPage.getPageUrl()
        Assert.assertEquals(actualURL, "https://cloud.google.com/products/calculator/")
    }

    @Test
    @Parameters("pageTitle")
    fun checkGreetingTitle(@Optional("Welcome to Google Cloud's pricing calculator") pageTitle: String) {
        calculatorPage = CalculatorPage(driver!!)
        calculatorPage.openPage()
        val actualGreetingTitleText = calculatorPage.getGreetingTitleText()
        Assert.assertEquals(actualGreetingTitleText, pageTitle);
    }

    @Test(dataProvider = "currencyData")
    fun checkCurrencyDropdownInCostDetails(currency: String) {
        calculatorPage = CalculatorPage(driver!!)
        calculatorPage.openPage()
        calculatorPage.selectDropdownCurrency(currency)
        val actualCurrency = calculatorPage.getSelectedItemInDropdownCurrency()
        Assert.assertEquals(actualCurrency, currency)
    }

    @DataProvider
    fun currencyData(): Array<String> {
        return arrayOf("EUR", "AUD", "CZK")
    }
}