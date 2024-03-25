package utils

import driver.MobileDriverFactory
import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.TouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.PointerInput.Origin.viewport
import java.time.Duration.ofMillis
import java.util.List
import org.openqa.selenium.interactions.PointerInput.Kind.TOUCH
import org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT


private val POINTER_INPUT = PointerInput(TOUCH, "finger")

fun swipeFromCenter(scrollDirection: ScrollDirection?) {
    val swipeArea: Dimension = MobileDriverFactory.getDriver().manage().window().size
    val swipeHeight: Int = (swipeArea.height * 0.5).toInt()
    val width: Int = swipeArea.width
    val start = Point(width / 2, swipeArea.height / 2)
    var end: Point? = null

    when (scrollDirection) {
        ScrollDirection.UP -> end = Point(start.x, start.y - swipeHeight)
        ScrollDirection.DOWN -> end = Point(start.x, start.y + swipeHeight)
        else -> {}
    }
    val swipe = org.openqa.selenium.interactions.Sequence(POINTER_INPUT, 1)
        .addAction(POINTER_INPUT.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
        .addAction(POINTER_INPUT.createPointerDown(LEFT.asArg()))
        .addAction(POINTER_INPUT.createPointerMove(ofMillis(1000), viewport(), end!!.getX(), end.getY()))
        .addAction(POINTER_INPUT.createPointerMove(ofMillis(500), viewport(), end.getX(), end.getY() - 1))
        .addAction(POINTER_INPUT.createPointerUp(LEFT.asArg()))

    MobileDriverFactory.getDriver().perform(List.of(swipe))
}

fun scrollVerticalToElement(direction: ScrollDirection, elementLocator: By?) {
    var maxScrolls = 10
    var found = false
    while (maxScrolls > 0 && !found) {
        if (isElementVisible(elementLocator)) {
            found = true
            break
        }
        val size: Dimension = MobileDriverFactory.getDriver().manage().window().size
        val startX: Int = size.width / 2
        var startY: Int
        var endY: Int
        if (direction === ScrollDirection.DOWN) {
            startY = (size.height * 0.8).toInt()
            endY = (size.height * 0.2).toInt()
        } else {
            startY = (size.height * 0.2).toInt()
            endY = (size.height * 0.8).toInt()
        }
        val touchAction = TouchAction(MobileDriverFactory.getDriver() as PerformsTouchActions)
        touchAction.press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(ofMillis(500)))
            .moveTo(PointOption.point(startX, endY))
            .release()
            .perform()
        maxScrolls--
    }
    if (!found) {
        throw NoSuchElementException("Element not found after scrolling")
    }
}

fun isElementVisible(locator: By?): Boolean {
    return try {
        MobileDriverFactory.getDriver().findElement(locator).isDisplayed()
    } catch (e: Exception) {
        false
    }
}

fun isElementVisible(element: WebElement): Boolean {
    return try {
        element.isDisplayed
    } catch (e: Exception) {
        false
    }
}
