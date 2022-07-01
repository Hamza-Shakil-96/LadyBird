package com.perfect.Specs.Staff

import Services.BaseClassManager
import com.github.javafaker.Faker
import com.perfect.Class.Enums.Staff_Title
import com.perfect.Class.Enums.Staff_Type
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import com.perfect.PageObjects.Staff.StaffPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.util.*

class AddStaff : BaseClassManager() {
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
        data.staff[0].phone = faker.numerify("+1 (###) ###-####")
        data.staff[0].emergency_name = faker.name().firstName() + " " + faker.name().lastName()
        data.staff[0].emergency_phone = faker.numerify("+1 (###) ###-####")
        data.staff[0].email_Address = faker.name().firstName() + "@mailinator.com"
        data.staff[0].postion_type = Staff_Type.values().random().name.lowercase()
        data.staff[0].title = Staff_Title.values().random().name.lowercase()
        data.staff[0].superUser = "yes"
    }

    @Test(testName = "Add Staff", suiteName = "Staff")
    fun addNewStaff(method: Method) {
        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickStaffLink()
        staffPageObjectPageObject!!.navigateToAddStaffScreen()
        staffPageObjectPageObject!!.addStaff(data.staff[0])
        val result: String = data.staff.last().first_name + " " + data.staff.last().last_name
//        assertTrue(
//            staffPageObjectPageObject!!.viewNewlyAddStaff(result),
//            "Newly added staff is not visible in listing"
//        )
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