package com.perfect.Specs.Login

import Services.Driver
import com.aventstack.extentreports.Status
import com.github.javafaker.Faker
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.relevantcodes.extentreports.LogStatus
import org.testng.ITestContext
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.util.*


class Authentication : Driver() {

    private var loginPageObject: LoginPageObject? = null
    private var homePageObject: AdminHomePageObject? = null
    private var faker = Faker(Locale.US)
    private var email = faker.internet().emailAddress()
    private var password = faker.internet().password()


    @BeforeMethod
    fun initializationPageObjects() {
        loginPageObject = LoginPageObject(webDriver)
        homePageObject = AdminHomePageObject(webDriver)

    }

    @Test(testName = "Authentication with Valid Credentials", priority = 1)
    fun loginWithValidCredentials(method: Method, result: ITestContext) {
        startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject!!.viewLoginModal()
        getTest()!!.log(LogStatus.INFO, "View Login Modal")
        //Assertion (View validation msg)
        loginPageObject!!.loginUser(true)
        getTest()!!.log(LogStatus.INFO, "Login successfully")
        homePageObject!!.navigateToHomeScreen()
        getTest()!!.log(LogStatus.INFO, "Navigate to home screen")
    }

    @Test(testName = "Authentication with InValid Credentials", priority = 2)
    fun loginWithInValidCredentials(method: Method) {
        startTest(method.name, method.getAnnotation(Test::class.java).testName)
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        getTest()!!.log(LogStatus.INFO, "View Login Modal")
        loginPageObject!!.loginUser(false, email, password)
        getTest()!!.log(LogStatus.INFO, "Login successfully")
        //Assertion (View validation msg)
        loginPageObject!!.viewValidationMsg()
        getTest()!!.log(LogStatus.INFO, "View error message")
    }
}