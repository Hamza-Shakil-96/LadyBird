package com.perfect.Specs.Login

import Services.BaseClassManager
import Services.FileServiceManager
import com.perfect.PageObjects.Login.ForgotPasswordPageObject
import com.perfect.PageObjects.Login.LoginPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method


class ForgotPassword : BaseClassManager() {
    private var loginPageObject: LoginPageObject? = null
    private var forgotPasswordPageObject: ForgotPasswordPageObject? = null
    private var props = FileServiceManager.getProps("data")
    private var forgotPasswdUrl = props.getProperty("forgot_passwd_api")
    private var resetPasswdUrl = props.getProperty("reset_passwd_api")
    private var otpUrl = props.getProperty("otp_api")

    @BeforeMethod
    fun initializationPageObjects() {
        loginPageObject = LoginPageObject(webDriver)
        forgotPasswordPageObject = ForgotPasswordPageObject(webDriver)
    }

    @Test(
        testName = "Forgot Password",
        description = "Forgot Password",
        priority = 1,
        suiteName = "Login"
    )
    fun forgotPassword(method: Method) {

        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.viewLoginModal()
        forgotPasswordPageObject!!.sendForgotPasswordEmail()
        retrieveApiStatus(forgotPasswdUrl)
        forgotPasswordPageObject!!.verifyOtpCode()
        retrieveApiStatus(otpUrl)
        forgotPasswordPageObject!!.resetPassword()
        retrieveApiStatus(resetPasswdUrl)
        loginPageObject!!.viewLoginModal()
    }
}