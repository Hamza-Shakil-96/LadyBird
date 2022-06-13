package com.perfect.Specs.Login

import Services.Driver
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.annotations.Test

class Logout : Driver() {

    private var loginPageObject: LoginPageObject? = null

    @Test(testName = "Logout")
    fun userLogout() {
        loginPageObject = LoginPageObject(webDriver)
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)
        loginPageObject!!.logoutUser()
        loginPageObject!!.viewLoginModal()
    }
}