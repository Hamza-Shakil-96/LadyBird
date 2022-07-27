package com.perfect.PageObjects.Login

import Services.FileServiceManager
import Services.PageObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Util.UtilsPageObject
import com.relevantcodes.extentreports.LogStatus
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy


class LoginPageObject(driver: WebDriver?) : PageObject(driver) {
    private var utilsPageObject = UtilsPageObject(this.driver)
    private var props = FileServiceManager.getProps("data")
    private val dataList = ArrayList<SchoolData>()


    @FindBy(id = "email")
    val userEmail: WebElement? = null

    @FindBy(id = "password")
    private val passwordTxtField: WebElement? = null

    @FindBy(id = "pswd")
    private val updatePasswordTxtField: WebElement? = null

    @FindBy(id = "cnfrm_pswd")
    private val confirmPasswordTxtField: WebElement? = null

    @FindBy(className = "loginsub")
    val loginBtn: WebElement? = null

    @FindBy(className = "resetsubmit")
    val resetBtn: WebElement? = null

    @FindBy(id = "user-menu-btn")//"//*[@id=\"fuse-toolbar\"]/div/div[3]/button/span[1]")
    val profileAvatar: WebElement? = null

    @FindBy(className = "MuiSnackbarContent-message")
    val invalidCredentialMsg: WebElement? = null

    @FindBy(id = "logout-btn") //"/html/body/div[2]/div[3]/li/div[1]/span")
    val logoutSpan: WebElement? = null

    @FindBy(name = "loginForm")
    val loginFormElem: WebElement? = null

    fun viewLoginModal() {
        getTest()!!.log(LogStatus.INFO, "View Login Modal")
        utilsPageObject.isElementVisible(getLoginForm())
    }

    private fun getPassword(): WebElement? {
        return passwordTxtField
    }

    private fun getEmail(): WebElement? {
        return userEmail
    }

    private fun getUpdatePassword(): WebElement? {
        utilsPageObject.isElementVisible(updatePasswordTxtField)
        return updatePasswordTxtField
    }

    private fun getConfirmPassword(): WebElement? {
        return confirmPasswordTxtField
    }

    private fun getLoginForm(): WebElement? {
        return loginFormElem
    }

    private fun getBtnLogin(): WebElement? {
        return loginBtn
    }

    private fun getBtnReset(): WebElement? {
        return resetBtn
    }

    private fun setUserEmail(email: String?) {
        getEmail()!!.sendKeys(Keys.chord(Keys.CONTROL, "a"))
        getEmail()!!.sendKeys(Keys.DELETE)
        getEmail()!!.sendKeys(email)
    }

    private fun setPassword(password: String?) {
        getPassword()!!.sendKeys(Keys.chord(Keys.CONTROL, "a"))
        getPassword()!!.sendKeys(Keys.DELETE)
        getPassword()!!.sendKeys(password)
    }

    private fun setUpdatePassword(password: String?) {
        getUpdatePassword()!!.sendKeys(Keys.chord(Keys.CONTROL, "a"))
        getUpdatePassword()!!.sendKeys(Keys.DELETE)
        getUpdatePassword()!!.sendKeys(password)
    }

    private fun setConfirmPassword(password: String?) {
        getConfirmPassword()!!.sendKeys(Keys.chord(Keys.CONTROL, "a"))
        getConfirmPassword()!!.sendKeys(Keys.DELETE)
        getConfirmPassword()!!.sendKeys(password)
    }

    private fun clickLoginBtn() {
        utilsPageObject.isElementClickable(getBtnLogin())
        getBtnLogin()!!.click()
    }

    private fun clickResetBtn() {
        utilsPageObject.isElementClickable(getBtnReset())
        getBtnReset()!!.click()
    }

    private fun clickProfileBtn() {
        utilsPageObject.isElementClickable(profileAvatar)
        profileAvatar!!.click()
    }

    private fun clickLogoutBtn() {
        utilsPageObject.isElementClickable(logoutSpan)
        logoutSpan!!.click()
    }


    fun viewValidationMsg() {
        getTest()!!.log(LogStatus.INFO, "View error message")
        utilsPageObject.isElementVisible(invalidCredentialMsg)
    }

    fun loginUser(
        isAdmin: Boolean = false, email: String? = null, password: String? = null,
    ) {
        getTest()!!.log(LogStatus.INFO, "Login user")
        var currentEmailAddress: String? = ""
        var currentPassword: String? = ""
        var updatePassword: String? = ""
        var data = SchoolData()
        if (isAdmin) {
            currentEmailAddress = props.getProperty("email")
            currentPassword = props.getProperty("password")
        } else if (email != null && password != null) {
            currentEmailAddress = email
            currentPassword = password

        } else if (!isAdmin) {
            val objectMapper = ObjectMapper()
            var node = FileServiceManager.convertJsonToJavaObjects()
            data = objectMapper.treeToValue(node[0], SchoolData::class.java)
            currentEmailAddress = data.admins[0].email_Address
            currentPassword = data.admins[0].password
            updatePassword = data.admins[0].first_name + "@123"
        }
        setUserEmail(currentEmailAddress);
        setPassword(currentPassword);
        clickLoginBtn()

        if (!data.admins[0].isPasswordReset && !isAdmin && email == null && password == null) {
            setUpdatePassword(updatePassword);
            setConfirmPassword(updatePassword);
            clickResetBtn()
            getTest()!!.log(LogStatus.INFO, "Password Reset Successfully")
            utilsPageObject.isLoaderElementVisible()
            utilsPageObject.isLoaderElementInvisible()
            utilsPageObject.isElementVisible(getEmail())
            setUserEmail(currentEmailAddress);
            setPassword(updatePassword);
            clickLoginBtn()
            data.admins[0].password = updatePassword
            data.admins[0].isPasswordReset = true
            dataList.add(data)
            FileServiceManager.convertJavaObjectToJson(dataList)
        }
        getTest()!!.log(LogStatus.INFO, "Login successfully")
    }

    fun logoutUser() {
        clickProfileBtn()
        clickLogoutBtn()
    }
}