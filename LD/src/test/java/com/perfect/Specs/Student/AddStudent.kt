package com.perfect.Specs.Student

import Services.Driver
import com.github.aistomin.sexist.DefaultDictionary
import com.github.aistomin.sexist.NamesDictionary
import com.github.javafaker.Faker
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Student.StudentPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*


class AddStudent : Driver() {
    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var studentPageObject: StudentPageObject? = null
    private var data = SchoolData()
    private var faker = Faker(Locale("en_US"));

    private val dictionary: NamesDictionary = DefaultDictionary()
    private val sdf = SimpleDateFormat("MM/dd/yyyy")
    private var gender = ""

    @BeforeMethod
    fun initializationPageObjects() {

        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        studentPageObject = StudentPageObject(webDriver)
        data.student[0].first_name = faker.name().firstName()
        data.student[0].last_name = faker.name().lastName()
        data.student[0].dateOfBirth = sdf.format(faker.date().birthday(1, 5))
        data.student[0].homeRoom = faker.color().name()
        try {
            if (dictionary.gender(faker.name().firstName()) != null) {
                gender = dictionary.gender(faker.name().firstName()).toString()
                if (gender == "MALE" || gender == "FEMALE") {
                    data.student[0].gender = gender
                } else {
                    data.student[0].gender = "FEMALE"
                }
            } else {
                data.student[0].gender = "male"
            }
        } catch (e: Exception) {
            data.student[0].gender = "male"
        }
        data.student[0].address = faker.address().fullAddress()
        data.student[0].parentType = "new"
        data.student[0].parentFName = faker.name().firstName()
        data.student[0].parentLName = faker.name().lastName()
        data.student[0].phoneNumber = faker.numerify("+1 (###) ###-####")
        data.student[0].emailAddress = data.student[0].parentFName + "@mailinator.com"
        if (data.student[0].gender == "male") {
            data.student[0].relationWithChild = "father"
        } else {
            data.student[0].relationWithChild = "mother"
        }
        data.student[0].gender!!.lowercase()

    }

    @Test(testName = "Add Student with new parent", priority = 1)
    fun addNewStudentWithNewParent() {
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickStudentLink()
        studentPageObject!!.navigateToAddStudentScreen()
        studentPageObject!!.addNewStudent(data.student[0])
    }
//    @Test(testName = "Add Student with existing parent")
//    fun addNewStudentWithExistingParent() {
//    }
}