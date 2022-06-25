package com.perfect.PageObjects.Login

import Services.FileServiceManager
import Services.PageObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Util.UtilsPageObject
import com.relevantcodes.extentreports.LogStatus
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

    @FindBy(xpath = "//*[@id=\"fuse-toolbar\"]/div/div[2]/button/span[1]")
    val profileAvatar: WebElement? = null

    @FindBy(className = "MuiSnackbarContent-message")
    val invalidCredentialMsg: WebElement? = null

    @FindBy(xpath = "/html/body/div[2]/div[3]/li/div[1]/span")
    val logoutSpan: WebElement? = null

    @FindBy(name = "loginForm")
    val loginFormElem: WebElement? = null


    fun navigateToLoginPage() {
//        driver[props.getProperty("base_Url")]
    }

    fun viewLoginModal() {
        getTest()!!.log(LogStatus.INFO, "View Login Modal")
        utilsPageObject.isElementVisible(getLoginForm())
    }

    fun getPassword(): WebElement? {
        return passwordTxtField
    }

    fun getEmail(): WebElement? {
        return userEmail
    }

    fun getUpdatePassword(): WebElement? {
        utilsPageObject.isElementVisible(updatePasswordTxtField)
        return updatePasswordTxtField
    }

    fun getConfirmPassword(): WebElement? {
        return confirmPasswordTxtField
    }

    fun getLoginForm(): WebElement? {
        return loginFormElem
    }




    fun getBtnLogin(): WebElement? {
        return loginBtn
    }

    fun getBtnReset(): WebElement? {
        return resetBtn
    }

    fun setUserEmail(email: String?) {
        getEmail()!!.clear()
        getEmail()!!.sendKeys(email)
    }

    fun setPassword(password: String?) {
        getPassword()!!.clear()
        getPassword()!!.sendKeys(password)
    }

    fun setUpdatePassword(password: String?) {
        getUpdatePassword()!!.clear()
        getUpdatePassword()!!.sendKeys(password)
    }

    fun setConfirmPassword(password: String?) {
        getConfirmPassword()!!.clear()
        getConfirmPassword()!!.sendKeys(password)
    }

    fun clickLoginBtn() {
        utilsPageObject.isElementClickable(getBtnLogin())
        getBtnLogin()!!.click()
    }

    fun clickResetBtn() {
        utilsPageObject.isElementClickable(getBtnReset())
        getBtnReset()!!.click()
    }

    fun clickProfileBtn() {
        utilsPageObject.isElementClickable(profileAvatar)
        profileAvatar!!.click()
    }

    fun clickLogoutBtn() {
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
        getTest()!!.log(LogStatus.INFO, "Login uer")
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