package com.perfect.Specs.Login

import Services.BaseClassManager
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.ITestContext
import org.testng.annotations.Test
import java.lang.reflect.Method

class Logout : BaseClassManager() {

    private var loginPageObject: LoginPageObject? = null

    @Test(testName = "User Logout",
        suiteName = "Login",
        description = "Verify the logout functionality for super admin")
    fun userLogout(method: Method) {
        startTest(method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName)
        loginPageObject = LoginPageObject(webDriver)
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)
        loginPageObject!!.logoutUser()
        loginPageObject!!.viewLoginModal()
    }
}