package com.perfect.Specs.Login

import Services.BaseClassManager
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.annotations.Test
import java.lang.reflect.Method

class Logout : BaseClassManager() {

    private var loginPageObject: LoginPageObject? = null

    @Test(testName = "Logout")
    fun userLogout(method: Method) {
//        test = extent.startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject = LoginPageObject(webDriver)
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)
        loginPageObject!!.logoutUser()
        loginPageObject!!.viewLoginModal()
    }
}