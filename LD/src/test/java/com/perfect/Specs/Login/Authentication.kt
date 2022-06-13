package com.perfect.Specs.Login

import Services.Driver
import com.github.javafaker.Faker
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
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

    @Test(testName = "Authentication with Valid Credentials")
    fun loginWithValidCredentials() {
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        //Assertion (View validation msg)
        loginPageObject!!.loginUser(true)
        homePageObject!!.navigateToHomeScreen()
    }

    @Test(testName = "Authentication with InValid Credentials")
    fun loginWithInValidCredentials() {
        loginPageObject!!.navigateToLoginPage()
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(false, email, password)
        //Assertion (View validation msg)
        loginPageObject!!.viewValidationMsg()
    }
}