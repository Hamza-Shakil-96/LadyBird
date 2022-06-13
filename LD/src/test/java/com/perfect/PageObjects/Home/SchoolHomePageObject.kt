package com.perfect.PageObjects.Home

import Services.PageObject
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SchoolHomePageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject = UtilsPageObject(this.driver)

    @FindBy(xpath = "//a[@href='/messaging']")
    val linkMessagingElem: WebElement? = null

    @FindBy(id = "fuse-navbar")
    val navBarElem: WebElement? = null

    @FindBy(xpath = "//*[@id=\"fuse-navbar\"]/div/div/div[2]/div[1]/p")
    private val viewBackToAdminBtnElem: WebElement? = null

    fun getMessagingLink(): WebElement? {
        utilsPageObject.isElementVisible(linkMessagingElem)
        return linkMessagingElem
    }

    fun getBackToAdminBtn(): WebElement? {
        utilsPageObject.isElementVisible(viewBackToAdminBtnElem)
        return viewBackToAdminBtnElem
    }

    fun getNavBar(): WebElement? {
        utilsPageObject.isElementVisible(navBarElem)
        return navBarElem
    }
}