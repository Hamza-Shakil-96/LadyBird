package com.perfect.Specs.School

import Services.Driver
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import kotlin.test.assertTrue

class ViewSchool : Driver() {

    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var viewSchoolPageObject: ViewSchoolPageObject? = null

    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        viewSchoolPageObject = ViewSchoolPageObject(webDriver)
    }

    @Test(testName = "View School")
    fun selectAndViewSchool() {

        loginPageObject!!.navigateToLoginPage()
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)

        adminHomePageObject!!.navigateToHomeScreen()
        adminHomePageObject!!.clickViewAsBtn()
        viewSchoolPageObject!!.selectSchoolFromListing()
        assertTrue(viewSchoolPageObject!!.viewSelectedSchool(), "School Name is not matching")

    }
}