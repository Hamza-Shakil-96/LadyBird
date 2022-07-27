package com.perfect.Specs.School

import Services.BaseClassManager
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.SchoolPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method

class DisableSchool : BaseClassManager() {
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

    @Test(testName = "Search And Disable School", suiteName = "School", description ="Verify if the user can disable school" )
    fun searchAndDisableSchool(method: Method) {
        startTest(method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName)
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)

        homePageObject!!.navigateToHomeScreen()
        homePageObject!!.clickSchoolLink()
        schoolPageObject!!.searchSchool()
        schoolPageObject!!.disableSchool()
    }
}