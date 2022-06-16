package com.perfect.PageObjects.Home

import Services.PageObject
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class AdminHomePageObject(driver: WebDriver?) : PageObject(driver,) {

    private var utilsPageObject = UtilsPageObject(this.driver)

    @FindBy(id = "fuse-navbar")
    val navBarElem: WebElement? = null

    @FindBy(xpath = "//a[@href='/schools']")
    val linkSchoolElem: WebElement? = null

    @FindBy(xpath = "//a[@href='/superadmin']")
    val linkHomeElem: WebElement? = null

    @FindBy(xpath = "//a[@href='/rooms']")
    val linkRoomElem: WebElement? = null

    @FindBy(xpath = "//a[@href='/staff']")
    val linkStaffElem: WebElement? = null

    @FindBy(xpath = "//a[@href='/students']")
    val linkStudentElem: WebElement? = null

    @FindBy(className = "view-as-btn")
    val viewAsBtn: WebElement? = null

    private fun getNavBar(): WebElement? {
        return navBarElem
    }

    fun clickViewAsBtn() {
        utilsPageObject.isElementClickable(getViewAsBtnElem())
        getViewAsBtnElem()!!.click()
    }

    private fun getViewAsBtnElem(): WebElement? {
        utilsPageObject.isElementVisible(viewAsBtn)
        return viewAsBtn
    }

    fun navigateToHomeScreen() {
        utilsPageObject.isElementVisible(getNavBar())
    }

    private fun getSchoolLink(): WebElement? {
        return linkSchoolElem
    }

    private fun getHomeLink(): WebElement? {
        return linkHomeElem
    }
    private fun getRoomLink(): WebElement? {
        return linkRoomElem
    }
    private fun getStaffLink(): WebElement? {
        return linkStaffElem
    }
    private fun getStudentLink(): WebElement? {
        return linkStudentElem
    }

    fun clickSchoolLink() {
        utilsPageObject.isElementClickable(getSchoolLink())
        getSchoolLink()!!.click()
    }
    fun clickRoomLink() {
        utilsPageObject.isElementClickable(getRoomLink())
        getRoomLink()!!.click()
    }
    fun clickStaffLink() {
        utilsPageObject.isElementClickable(getStaffLink())
        getStaffLink()!!.click()
    }
    fun clickStudentLink() {
        utilsPageObject.isElementClickable(getStudentLink())
        getStudentLink()!!.click()
    }


}