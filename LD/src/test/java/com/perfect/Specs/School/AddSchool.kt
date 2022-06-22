package com.perfect.Specs.School

import Services.Driver
import com.github.javafaker.Faker
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.SchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.util.*
import kotlin.test.assertTrue


class AddSchool : Driver() {

    private var loginPageObject: LoginPageObject? = null
    private var schoolPageObject: SchoolPageObject? = null
    private var homePageObject: AdminHomePageObject? = null
    private var data = SchoolData()
    private var faker = Faker(Locale("en-US"));

    @BeforeMethod
    fun initializationPageObjects() {
        loginPageObject = LoginPageObject(webDriver)
        schoolPageObject = SchoolPageObject(webDriver)
        homePageObject = AdminHomePageObject(webDriver)
        data.admins[0].first_name = faker.name().firstName()
        data.admins[0].last_name = faker.name().lastName()
        data.admins[0].phone = faker.numerify("+1 (###) ###-####")
        data.admins[0].designation = "Admin"
        data.admins[0].email_Address = data.admins[0].first_name + "@mailinator.com"
        data.name = faker.company().name()
        data.phone = faker.numerify("+1(###) ###-####")
        data.address = faker.address().fullAddress()
        data.street = faker.address().streetAddress()
        data.country = "US"
        data.state = "6"
        data.city = faker.address().city()
        data.zipcode = faker.address().zipCodeByState("VA")
        data.status = "1"
    }

    @Test(testName = "Add new school with single admin")
    fun addNewSchoolWithSingleAdmin(method: Method) {
        test = extent.startTest(method.name, method.getAnnotation(Test::class.java).testName)
        test = extent.startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject!!.navigateToLoginPage()
        //Assertion (Login Form)
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser(true)

        homePageObject!!.navigateToHomeScreen()
        homePageObject!!.clickSchoolLink()

        schoolPageObject!!.clickAddBtn()
        schoolPageObject!!.navigateToAddSchoolScreen()
        schoolPageObject!!.addSchoolInfo(data)
        schoolPageObject!!.clickAddSchoolBtn()
//        Assertion (Success Toast)
        schoolPageObject!!.viewSuccessMessage()
        // assertTrue(schoolPageObject!!.viewNewlyAddedSchoolInListing(), "Newly added school is not visible in listing")
    }

    @Test(testName = "Login as School Admin")
    fun loginAsNewlyAddedSchoolAdmin(method: Method) {
        test = extent.startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        homePageObject!!.navigateToHomeScreen()
    }
}