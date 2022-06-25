package com.perfect.Specs.Login

import Services.BaseClassManager
import com.github.javafaker.Faker
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.util.*


class Authentication : BaseClassManager() {

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

    @Test(
        testName = "Authentication with Valid Credentials",
        description = "Authentication with Valid Credentials",
        priority = 1,
    )
    fun loginWithValidCredentials(method: Method) {
        startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)
        homePageObject!!.navigateToHomeScreen()
    }

    @Test(testName = "Authentication with InValid Credentials", priority = 2)
    fun loginWithInValidCredentials(method: Method) {
        startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(false, email, password)
        loginPageObject!!.viewValidationMsg()
    }
}