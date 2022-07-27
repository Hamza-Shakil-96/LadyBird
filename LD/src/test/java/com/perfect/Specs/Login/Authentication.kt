package com.perfect.Specs.Login

import Services.BaseClassManager
import Services.FileServiceManager
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
    private var props = FileServiceManager.getProps("data")
    private var url = props.getProperty("login_api")

    @BeforeMethod
    fun initializationPageObjects() {
        loginPageObject = LoginPageObject(webDriver)
        homePageObject = AdminHomePageObject(webDriver)
    }

    @Test(
        testName = "Login With Valid Credentials",
        description = "Authentication with Valid Credentials",
        suiteName = "Login",
        priority = 1
    )
    fun loginWithValidCredentials(method: Method) {

        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)
        homePageObject!!.navigateToHomeScreen()
        retrieveApiStatus(url)

    }

    @Test(
        testName = "Login With InValid Credentials", suiteName = "Login", priority = 2
    )
    fun loginWithInValidCredentials(method: Method) {

        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(false, email, password)
        retrieveApiStatus(url, 400)
    }
}
