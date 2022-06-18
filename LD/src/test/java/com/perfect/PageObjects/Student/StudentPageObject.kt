package com.perfect.PageObjects.Student

import Services.PageObject
import com.github.javafaker.Faker
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.Select
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


class StudentPageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)

    private val faker = Faker()

    @FindBy(id = "enroll-student-btn")
    val enrollAddBtnElem: WebElement? = null

    @FindBy(id = "enroll-form-container")
    val formElem: WebElement? = null

    @FindBy(id = "search")
    val searchTxtElem: WebElement? = null

    @FindBy(id = "filter")
    val filterDropDownElem: WebElement? = null

    @FindBy(id = "fname-student")
    val fNameTxtElem: WebElement? = null

    @FindBy(id = "lname-student")
    val lNameTxtElem: WebElement? = null

    @FindBy(id = "date-of-birth")
    val dOBTxtElem: WebElement? = null

    @FindBy(className = " MuiSelect-icon")
    val homeRoomDropDownElem: WebElement? = null

    @FindBy(id = "homeroomLabel")
    val homeRoomLblDropDownElem: WebElement? = null

    @FindBy(id = "gender")
    val genderDropDownElem: WebElement? = null

    @FindBy(id = "address1")
    val addressTxtElem: WebElement? = null

    @FindBy(id = "isNew")
    val parentTypeDropdownElem: WebElement? = null

    @FindBy(id = "new")
    val newParentTypeDropdownElem: WebElement? = null

    @FindBy(id = "existing")
    val existParentTypeDropdownElem: WebElement? = null

    @FindBy(id = "fname-parent")
    val fnameParentTxtElem: WebElement? = null

    @FindBy(id = "lname-parent")
    val lnameParentTxtElem: WebElement? = null

    @FindBy(id = "childRelation")
    val kidRelationDropdownElem: WebElement? = null

    @FindBy(id = "contact-parent-label")
    val phoneNumberTxtElem: WebElement? = null

    @FindBy(id = "email-parent")
    val emailAddressTxtElem: WebElement? = null

    @FindBy(id = "parent-autocpmplete")
    val parentTxtElem: WebElement? = null

    private fun getAddBtn(): WebElement? {
        return enrollAddBtnElem
    }

    private fun getSearchTxtField(): WebElement? {
        return searchTxtElem
    }

    private fun getFilterDropDownField(): WebElement? {
        return filterDropDownElem
    }

    private fun getFNameTxtField(): WebElement? {
        utilsPageObject.isElementVisible(fNameTxtElem)
        return fNameTxtElem
    }

    private fun setFNameTxtField(name: String) {
        getFNameTxtField()!!.sendKeys(name)
    }

    private fun getLNameTxtField(): WebElement? {
        return lNameTxtElem
    }

    private fun setLNameTxtField(name: String) {
        getLNameTxtField()!!.sendKeys(name)
    }

    private fun getDOBTxtField(): WebElement? {
        return dOBTxtElem
    }
    private fun getHomeRoomDropdownField(): WebElement? {
        return homeRoomDropDownElem
    }
    private fun getHomeRoomLblDropdownField(): WebElement? {
        return homeRoomLblDropDownElem
    }
    private fun selectHomeRoomField(room:String){
        val select = Select(driver!!.findElement(By.id("homeroom")))
        select.selectByValue(room)
////        utilsPageObject.isElementClickable(getHomeRoomLblDropdownField()).click()
//        utilsPageObject.isElementClickable(getHomeRoomDropdownField()).click()
////        this.driver!!.findElement(By.id(room)).click()
    }

    private fun setDOBField(dob: String) {
        val stringSelection = StringSelection(dob)
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)
        getDOBTxtField()!!.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getDOBTxtField()!!.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    }

    private fun getForm(): WebElement? {
        return formElem
    }

    fun navigateToAddStudentScreen() {
        clickAddBtn()
        getForm()
    }

    private fun clickAddBtn() {
        utilsPageObject.isElementClickable(getAddBtn()).click()
    }

    fun addNewStudent() {
        setFNameTxtField(faker.name().firstName())
        setLNameTxtField(faker.name().lastName())
        selectHomeRoomField("tan")
//        setDOBField(sdf.format(faker.date().birthday(3, 5)))

    }


}