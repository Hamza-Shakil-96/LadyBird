package com.perfect.PageObjects.Util

import Services.PageObject
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.time.Duration
import java.util.*


class UtilsPageObject(driver: WebDriver?) : PageObject(driver) {
    private var wait = WebDriverWait(driver, Duration.ofSeconds(30))
    private val js = driver as JavascriptExecutor
    private val loaderClassName = "MuiCircularProgress-svg"

    @FindBy(className = "MuiSnackbarContent-message")
    val toastMsg: WebElement? = null


    fun isElementVisible(element: WebElement?): WebElement {
        return wait.until(ExpectedConditions.visibilityOf(element))
    }

    fun waitForElem(elem: String?, locate: String = "id"): WebElement {
        return when (locate) {
            "class" -> {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elem)))
            }
            "name" -> {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elem)))
            }
            "xpath" -> {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elem)))
            }
            else -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elem)))
        }

    }

    fun isLoaderElementInvisible() {
        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                By
                    .className(loaderClassName)
            )
        )
    }

    fun isLoaderElementVisible() {
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By
                    .className(loaderClassName)
            )
        )
    }

    fun isElementClickable(element: WebElement?): WebElement {
        return wait.until(ExpectedConditions.elementToBeClickable(element))
    }

    fun viewSuccessMessage() {
        isElementVisible(toastMsg)
    }

    fun selectOptionFromDropDown(WebElement: List<WebElement?>, value: String?) {
        val myList: List<WebElement?> = WebElement
        for (element in myList) {
            if (element?.getAttribute("data-value")!!.lowercase() == value!!.lowercase()) {
                element.click()
                break
            }
        }
    }

    fun elementScrollDown(element: WebElement?) {
        js.executeScript("arguments[0].scrollIntoView(true)", element)
    }

    fun getFileFromFolder(dir: File): File {
        val files: Array<out File>? = dir.listFiles()
        val rand = Random()
        return files!![rand.nextInt(files.size)]
    }


}