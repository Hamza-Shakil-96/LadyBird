package com.perfect.Specs.Rooms

import Services.BaseClassManager
import Services.FileServiceManager
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
    private var props = FileServiceManager.getProps("data")
    private var url = props.getProperty("room_api")


    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        viewSchoolPageObject = ViewSchoolPageObject(webDriver)
        roomPageObject = RoomPageObject(webDriver)
        data.rooms[0].name = faker.color().name()
    }

    @Test(
        testName = "Add room in newly added school",
        suiteName = "Room",
        description = "Verify if the school admin can add new room against school"
    )
    fun addRoomAgainstTheSchool(method: Method) {
        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickRoomLink()
        roomPageObject!!.navigateToAddRoomScreen()
        roomPageObject!!.addRoom(data.rooms[0])
        retrieveApiStatus(url)
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