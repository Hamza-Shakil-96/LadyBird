package com.perfect.Specs.Staff

import Services.Driver
import com.github.javafaker.Faker
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import com.perfect.PageObjects.Staff.StaffPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.util.*
import kotlin.test.assertTrue

class AddStaff : Driver() {
    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var viewSchoolPageObject: ViewSchoolPageObject? = null
    private var staffPageObjectPageObject: StaffPageObject? = null
    private var data = SchoolData()
    private var faker = Faker(Locale("en_CA"));

    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        viewSchoolPageObject = ViewSchoolPageObject(webDriver)
        staffPageObjectPageObject = StaffPageObject(webDriver)
        data.staff[0].first_name = faker.name().firstName()
        data.staff[0].last_name = faker.name().lastName()
        data.staff[0].phone = faker.phoneNumber().cellPhone()
        data.staff[0].emergency_name = faker.name().firstName() + " " + faker.name().lastName()
        data.staff[0].emergency_phone = faker.phoneNumber().cellPhone()
        data.staff[0].email_Address = faker.name().firstName() + "@mailinator.com"
        data.staff[0].postion_type = "assistant"
        data.staff[0].title = "part-time"
        data.staff[0].superUser = "yes"
    }

    @Test(testName = "Add Staffs")
    fun addNewStaff() {
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickStaffLink()
        staffPageObjectPageObject!!.navigateToAddStaffScreen()
        staffPageObjectPageObject!!.addStaff(data.staff[0])
        val result: String = data.staff.last().first_name +" "+ data.staff.last().last_name
        assertTrue(
            staffPageObjectPageObject!!.viewNewlyAddStaff(result),
            "Newly added staff is not visible in listing"
        )
        //
//        loginPageObject!!.navigateToLoginPage()
//        //Assertion (Login Form)
//        loginPageObject!!.viewLoginModal()
//        loginPageObject!!.loginUser()
//        adminHomePageObject!!.navigateToHomeScreen()
//        adminHomePageObject!!.clickViewAsBtn()
//        viewSchoolPageObject!!.selectSchoolFromListing()
//        assertTrue(viewSchoolPageObject!!.viewSelectedSchool(), "School Name is not matching")
    }
}