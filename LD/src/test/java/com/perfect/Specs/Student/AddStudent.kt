package com.perfect.Specs.Student

import Services.Driver
import com.perfect.PageObjects.Home.AdminHomePageObject
import com.perfect.PageObjects.Login.LoginPageObject
import com.perfect.PageObjects.Student.StudentPageObject
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class AddStudent: Driver()  {
    private var adminHomePageObject: AdminHomePageObject? = null
    private var loginPageObject: LoginPageObject? = null
    private var studentPageObject: StudentPageObject? = null

    @BeforeMethod
    fun initializationPageObjects() {
        adminHomePageObject = AdminHomePageObject(webDriver)
        loginPageObject = LoginPageObject(webDriver)
        studentPageObject = StudentPageObject(webDriver)
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