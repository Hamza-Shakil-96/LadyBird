package com.perfect.Specs.Student

import Services.BaseClassManager
import com.github.aistomin.sexist.DefaultDictionary
import com.github.aistomin.sexist.NamesDictionary
import com.github.javafaker.Faker
import com.perfect.Class.Enums
import com.perfect.Class.Enums.Gender
import com.perfect.Class.Enums.Relation
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Student.StudentPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.Method
import java.text.SimpleDateFormat
import java.util.*


class AddStudent : BaseClassManager() {
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
                gender = dictionary.gender(faker.name().firstName()).toString().lowercase()
                if (gender == Gender.Male.toString() || gender == Gender.Female.toString()) {
                    data.student[0].gender = gender
                } else {
                    data.student[0].gender = Gender.values().random().name
                }
            } else {
                data.student[0].gender = Gender.values().random().name
            }
        } catch (e: Exception) {
            data.student[0].gender = Gender.values().random().name
        }
        data.student[0].address = faker.address().fullAddress()
    }

    @Test(
        testName = "Add Student with new parent",
        priority = 1,
        description = "Verify if the school admin can enroll new student",
        suiteName = "Student"
    )
    fun addNewStudentWithNewParent(method: Method) {

        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickStudentLink()
        studentPageObject!!.navigateToAddStudentScreen()
        data.student[0].parent.parentType = Enums.ParentType.New.name.lowercase()
        data.student[0].parent.parentFName = faker.name().firstName()
        data.student[0].parent.parentLName = faker.name().lastName()
        data.student[0].parent.phoneNumber = faker.numerify("+1 (###) ###-####")
        data.student[0].parent.emailAddress = data.student[0].parent.parentFName + "@mailinator.com"
        if (data.student[0].gender == Gender.Male.toString()) {
            data.student[0].parent.relationWithChild = Relation.Father.name.lowercase()
        } else {
            data.student[0].parent.relationWithChild = Relation.Mother.name.lowercase()
        }
        studentPageObject!!.addNewStudent(data.student[0], true)
    }

    @Test(
        testName = "Add Student with existing parent",
        priority = 2,
        description = "Verify if the school admin can enroll student with existing parent",
        suiteName = "Student"
    )
    fun addNewStudentWithExistingParent(method: Method) {
        startTest(
            method.getAnnotation(Test::class.java).testName,
            method.getAnnotation(Test::class.java).description,
            method.getAnnotation(Test::class.java).suiteName
        )
        loginPageObject!!.navigateToLoginPage()
        loginPageObject!!.viewLoginModal()
        loginPageObject!!.loginUser()
        adminHomePageObject!!.clickStudentLink()
        studentPageObject!!.navigateToAddStudentScreen()
        data.student[0].parent.parentType = Enums.ParentType.Existing.name.lowercase()
        studentPageObject!!.addNewStudent(data.student[0], false)
    }
//    @Test(testName = "Add Student with existing parent")
//    fun addNewStudentWithExistingParent() {
//    }
}