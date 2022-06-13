package com.perfect.Specs.School

import Services.Driver
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.SchoolPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class DisableSchool : Driver() {
    private var homePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var viewSchoolPageObject: ViewSchoolPageObject? = null
    private var schoolPageObject: SchoolPageObject? = null

    @BeforeMethod
    fun initializationPageObjects() {
        loginPageObject = LoginPageObject(webDriver)
        viewSchoolPageObject = ViewSchoolPageObject(webDriver)
        homePageObject = AdminHomePageObject(webDriver)
        schoolPageObject = SchoolPageObject(webDriver)
    }

    @Test(testName = "Disable School")
    fun searchAndDisableSchool() {

        loginPageObject!!.navigateToLoginPage()
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)

        homePageObject!!.navigateToHomeScreen()
        homePageObject!!.clickSchoolLink()
        schoolPageObject!!.searchSchool()
        schoolPageObject!!.disableSchool()
    }
}