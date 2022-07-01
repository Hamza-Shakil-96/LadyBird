package com.perfect.PageObjects.Schools

import Services.FileServiceManager
import Services.PageObject
import com.perfect.PageObjects.Home.SchoolHomePageObject
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy


class ViewSchoolPageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)
    private var schoolHomePageObject: SchoolHomePageObject = SchoolHomePageObject(this.driver)

    @FindBy(id = "scrollable-list")
    private val schoolListingElem: WebElement? = null

    @FindBy(className = "view-school-btn")
    val viewSchoolBtnElem: WebElement? = null

    @FindBy(className = "hd-school")
    val checkElem: WebElement? = null

    @FindBy(xpath = "//*[@id=\"fuse-navbar\"]/div/div/div[1]/p")
    val schoolNameElem: WebElement? = null

    @FindBy(xpath = "//*[@id=\"scrollable-list\"]/div[last()]/div/div[2]/i")
    val lastElem: WebElement? = null

    @FindBy(xpath = "//*[@id=\"scrollable-list\"]/div[last()]")
    val schoolsListing: WebElement? = null

    @FindBy(className = "MuiCircularProgress-svg")
    val loader: WebElement? = null

    @FindBy(className = "mx-auto")
    val loaderClass: WebElement? = null

    private fun getViewSchoolBtn(): WebElement? {
        utilsPageObject.isElementVisible(viewSchoolBtnElem)
        return viewSchoolBtnElem
    }

    private fun getSchoolListingElem(): WebElement? {
        utilsPageObject.isElementVisible(schoolListingElem)
        return schoolListingElem
    }

    private fun getSchoolName(): WebElement? {
        return schoolNameElem
    }

    private fun getSchoolListing(): WebElement? {
        return this.driver!!.findElement(By.cssSelector("#scrollable-list > div:last-child"))
    }

    private fun getLastSchoolFromListing(): WebElement? {
        return lastElem
    }
    private fun viewCheckBox(): WebElement? {
        return checkElem
    }

    fun selectSchoolFromListing() {
        utilsPageObject.isLoaderElementVisible()
        utilsPageObject.isLoaderElementInvisible()
        utilsPageObject.isElementVisible(viewCheckBox())
        val schoolObject = FileServiceManager.convertJsonToJavaObjects().last()
        val schoolName = schoolObject.get("name").asText()
        val js = driver as JavascriptExecutor

        var flag = true
        var lastHeight: Long = 0
        var newHeight: Long = 0

        while (flag) {

            lastHeight = js.executeScript("return document.querySelector('#scrollable-list').scrollHeight") as Long
            js.executeScript("arguments[0].scrollIntoView(true)", getSchoolListing());
            utilsPageObject.isLoaderElementInvisible()

            println(lastHeight)
            // Do some waiting
            (js.executeScript("return document.querySelector('#scrollable-list').scrollHeight") as Long).also { newHeight = it }

            if (lastHeight == newHeight) {
                flag = false
            }
            lastHeight = newHeight
        }

        val schoolNameList = this.driver!!.findElements(By.xpath("//*[@id=\"scrollable-list\"]/div/div/div"))
        for (elem in schoolNameList) {
            if (elem.text == schoolName) {
                utilsPageObject.isElementClickable(elem).click()
                break;
            }
        }
        utilsPageObject.isElementClickable(getViewSchoolBtn()).click()
    }

    fun viewSelectedSchool(): Boolean {
        schoolHomePageObject.getNavBar()
        utilsPageObject.isElementVisible(getSchoolName())
        val name = getSchoolName()!!.text
        val schoolObject = FileServiceManager.convertJsonToJavaObjects().last()
        val schoolName = schoolObject.get("name").asText()
        println("Expected: $schoolName Actual: $name")
        return schoolName.lowercase() == name.lowercase()
    }
}