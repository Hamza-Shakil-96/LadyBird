package com.perfect.Specs.Rooms

import Services.BaseClassManager
import com.github.javafaker.Faker
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Rooms.RoomPageObject
import com.perfect.PageObjects.Schools.ViewSchoolPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.util.*

class AddRoom : BaseClassManager() {

    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var viewSchoolPageObject: ViewSchoolPageObject? = null
    private var roomPageObject: RoomPageObject? = null
    private var faker = Faker(Locale("en-US"));
    private var data = SchoolData()


    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        viewSchoolPageObject = ViewSchoolPageObject(webDriver)
        roomPageObject = RoomPageObject(webDriver)
        data.rooms[0].name = faker.color().name()
    }

    @Test(testName = "Add room in newly added school")
    fun addRoomAgainstTheSchool(method:Method) {
//        test = extent.startTest(method.name, method.getAnnotation(Test::class.java).testName)
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickRoomLink()
        roomPageObject!!.navigateToAddRoomScreen()
        roomPageObject!!.addRoom(data.rooms[0])
//        assertTrue(
//            roomPageObject!!.viewNewlyAddedRoom(data.rooms.last().name.toString()),
//            "Newly added room is not visible in listing"
//        )

//        adminHomePageObject!!.navigateToHomeScreen()
//        adminHomePageObject!!.clickViewAsBtn()
//        viewSchoolPageObject!!.selectSchoolFromListing()
//        assertTrue(viewSchoolPageObject!!.viewSelectedSchool(), "School Name is not matching")

    }
}