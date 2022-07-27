package com.perfect.Specs.School

import Services.BaseClassManager
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import kotlin.test.assertTrue

class ViewSchool : BaseClassManager() {

    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var viewSchoolPageObject: ViewSchoolPageObject? = null

    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        viewSchoolPageObject = ViewSchoolPageObject(webDriver)
    }

    @Test(testName = "View School",
        suiteName = "School",
        description = "Verify if the super admin can select and view school")
    fun selectAndViewSchool(method: Method) {
        startTest(method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName)
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)

        adminHomePageObject!!.navigateToHomeScreen()
        adminHomePageObject!!.clickSchoolLink()
        adminHomePageObject!!.clickViewAsBtn()
        viewSchoolPageObject!!.selectSchoolFromListing()
        assertTrue(viewSchoolPageObject!!.viewSelectedSchool(), "School Name is not matching")
    }
}