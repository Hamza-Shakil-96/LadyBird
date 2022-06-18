package com.perfect.Specs.Student

import Services.Driver
import com.github.aistomin.sexist.DefaultDictionary
import com.github.javafaker.Faker
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Student.StudentPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.text.SimpleDateFormat
import java.util.*

class AddStudent: Driver()  {
    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var studentPageObject: StudentPageObject? = null
    private var data = SchoolData()
    private var faker = Faker(Locale("en_CA"));
    private var namesDictionary = DefaultDictionary()
    private val sdf = SimpleDateFormat("MM/dd/yyyy")
    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        studentPageObject = StudentPageObject(webDriver)
        data.student[0].first_name= faker.name().firstName()
        data.student[0].last_name= faker.name().lastName()
        data.student[0].dateOfBirth= sdf.format(faker.date().birthday(3, 5))
        data.student[0].homeRoom= faker.color().name()
    }
    @Test(testName = "Add Student with new parent", priority = 1)
    fun addNewStudentWithNewParent() {
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickStudentLink()
        studentPageObject!!.navigateToAddStudentScreen()
        studentPageObject!!. addNewStudent()
    }
//    @Test(testName = "Add Student with existing parent")
//    fun addNewStudentWithExistingParent() {
//    }
}