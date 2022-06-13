package com.perfect.PageObjects.Util

import Services.PageObject
import com.github.javafaker.Faker
import com.intellij.util.Function.ID
import org.openqa.selenium.*
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class UtilsPageObject(driver: WebDriver?) : PageObject(driver) {
    private var faker = Faker()
    private var wait = WebDriverWait(driver, 30)
    private val js = driver as JavascriptExecutor
    private val loaderClassName = "MuiCircularProgress-svg"

    @FindBy(className = "MuiSnackbarContent-message")
    val toastMsg: WebElement? = null


    fun isElementVisible(element: WebElement?): WebElement {
        return wait.until(ExpectedConditions.visibilityOf(element))
    }

    fun isLoaderElementInvisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By
            .className(loaderClassName)))
    }

    fun isLoaderElementVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .className(loaderClassName)))
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
                element?.click();
                break;
            }
        }
    }

    fun elementScrollDown(element: WebElement?) {
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    fun getRandomOfBirth(): String {
        val date = faker.date().birthday(1, 5);
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        //val parts: List<String> = strDate.split("-")
        return dateFormat.format(date);
    }

    fun getRandomEmailAddress(localPart: String?): String {
        return "$localPart@mailinator.com"
    }

    fun getFileFromFolder(dir: File): File {
        val files: Array<out java.io.File>? = dir.listFiles()
        val rand = Random()
        return files!![rand.nextInt(files.size)]
    }
}