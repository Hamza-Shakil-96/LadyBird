package com.perfect.Specs.Login

import Services.BaseClassManager
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.annotations.Test
import java.lang.reflect.Method

class Logout : BaseClassManager() {

    private var loginPageObject: LoginPageObject? = null

    @Test(
        testName = "User Logout from logged as super admin",
        suiteName = "Login",
        description = "Verify the logout functionality for super admin",
        priority = 1
    )
    fun userLogoutAsSuperAdmin(method: Method) {
        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject = LoginPageObject(webDriver)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)
        loginPageObject!!.logoutUser()
        loginPageObject!!.viewLoginModal()
    }

    @Test(
        testName = "User Logout from logged as school admin",
        suiteName = "Login",
        description = "Verify the logout functionality for school admin",
        priority = 2
    )
    fun userLogoutAsSchoolAdmin(method: Method) {
        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject = LoginPageObject(webDriver)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        loginPageObject!!.logoutUser()
        loginPageObject!!.viewLoginModal()
    }
}