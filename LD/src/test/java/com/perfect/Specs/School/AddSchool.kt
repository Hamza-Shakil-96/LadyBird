package com.perfect.Specs.School

import Services.BaseClassManager
import com.github.javafaker.Faker
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.SchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.util.*


class AddSchool : BaseClassManager() {

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

    @Test(testName = "Add New School With Single Admin",
        suiteName = "School",
        description = "Verify the add school functionality")
    fun addNewSchoolWithSingleAdmin(method: Method) {
        startTest(method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName)
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

    @Test(testName = "Login as School Admin",
        suiteName = "School",
        description = "Verify the school admin login functionality")
    fun loginAsNewlyAddedSchoolAdmin(method: Method) {
        startTest(method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName)
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        homePageObject!!.navigateToHomeScreen()
    }
}