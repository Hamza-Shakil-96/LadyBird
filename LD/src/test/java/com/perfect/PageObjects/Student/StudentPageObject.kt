package com.perfect.PageObjects.Student

import Services.FileServiceManager
import Services.PageObject
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


class StudentPageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)

    @FindBy(className = "MuiSnackbarContent-message")
    val toastMsg: WebElement? = null

    @FindBy(className = "MuiList-root")
    val dropDownElem: WebElement? = null

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

    @FindBy(id = "homeroom")
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

    @FindBy(id = "relationLabel")
    val kidRelationDropdownLbl: WebElement? = null

    @FindBy(id = "childRelation")
    val kidRelationDropdownElem: WebElement? = null

    @FindBy(id = "contact-parent")
    val phoneNumberTxtElem: WebElement? = null

    @FindBy(id = "email-parent")
    val emailAddressTxtElem: WebElement? = null

    @FindBy(id = "parent-autocpmplete")
    val parentTxtElem: WebElement? = null

    @FindBy(id = "submit")
    val addStudentBtn: WebElement? = null

    private fun getAddBtn(): WebElement? {
        return enrollAddBtnElem
    }

    private fun getStudentAddBtn(): WebElement? {
        return addStudentBtn
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

    private fun getParentFNameTxtField(): WebElement? {
        return fnameParentTxtElem
    }

    private fun getKidRelationDropDownLbl(): WebElement? {
        return kidRelationDropdownLbl
    }

    private fun getKidRelationDropDownField(): WebElement? {
        return kidRelationDropdownElem
    }

    private fun getKidRelation(relation: String?): WebElement? {
        return utilsPageObject.waitForElem(relation)
    }

    private fun getParentLNameTxtField(): WebElement? {
        return lnameParentTxtElem
    }

    private fun getPhoneTxtField(): WebElement? {
        return phoneNumberTxtElem
    }

    private fun getEmailAddressTxtField(): WebElement? {
        return emailAddressTxtElem
    }

    private fun setPhoneNoTxtField(name: String?) {
        getPhoneTxtField()!!.sendKeys(name)
    }

    private fun setEmailAddressTxtField(name: String?) {
        getEmailAddressTxtField()!!.sendKeys(name)
    }

    private fun setParentFNameTxtField(name: String?) {
        getParentFNameTxtField()!!.sendKeys(name)
    }

    private fun setParentLNameTxtField(name: String?) {
        getParentLNameTxtField()!!.sendKeys(name)
    }

    private fun getAddressField(): WebElement? {
        return addressTxtElem
    }

    private fun setAddressTxtField(address: String?) {
        getAddressField()!!.sendKeys(address)
    }

    private fun setFNameTxtField(name: String?) {
        getFNameTxtField()!!.sendKeys(name)
    }

    private fun getLNameTxtField(): WebElement? {
        return lNameTxtElem
    }

    private fun setLNameTxtField(name: String?) {
        getLNameTxtField()!!.sendKeys(name)
    }

    private fun getDOBTxtField(): WebElement? {
        return dOBTxtElem
    }

    private fun getHomeRoomDropdownField(): WebElement? {
        return homeRoomDropDownElem
    }

    private fun getParentTypeDropDownField(): WebElement? {
        return parentTypeDropdownElem
    }

    private fun getNewParentTypeDropDownElem(): WebElement? {
        return newParentTypeDropdownElem
    }

    private fun getHomeRoomLblDropdownField(): WebElement? {
        return homeRoomLblDropDownElem
    }

    private fun getGenderDropdownField(): WebElement? {
        return genderDropDownElem
    }

    private fun getDropDown(): WebElement? {
        return dropDownElem
    }

    private fun setGenderDropDownField(gender: String?) {
        utilsPageObject.isElementClickable(getGenderDropdownField()).click()
        utilsPageObject.waitForElem(gender).click()
    }

    private fun getHomeRoom(room: String?): WebElement? {
        return utilsPageObject.waitForElem(room)
    }

    private fun selectHomeRoomField(room: String?) {
        utilsPageObject.isElementClickable(getHomeRoomLblDropdownField()).click()
        getHomeRoomDropdownField()!!.sendKeys(Keys.chord(Keys.ENTER));
        getHomeRoom(room)!!.click()
    }

    private fun selectKidRelationField(relation: String?) {
        utilsPageObject.isElementClickable(getKidRelationDropDownLbl()).click()
        getKidRelationDropDownField()!!.sendKeys(Keys.chord(Keys.ENTER));
        getKidRelation(relation)!!.click()
    }

    private fun selectParentTypeField(type: String?) {
        utilsPageObject.isElementClickable(getParentTypeDropDownField()).click()
        if (type == "new") {
            utilsPageObject.isElementClickable(getNewParentTypeDropDownElem()).click()
        }

    }

    private fun setDOBField(dob: String?) {
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

    private fun clickStudentAddBtn() {
        utilsPageObject.isElementClickable(getStudentAddBtn()).click()
    }

    fun addNewStudent(data: SchoolData.Student) {
        val objectMapper = ObjectMapper()
        val node: JsonNode =
            FileServiceManager.convertJsonToJavaObjects()//objectMapper.readTree(File(props.getProperty("json-File_Url")))
        val schoolData: SchoolData = objectMapper.treeToValue(node[0], SchoolData::class.java)
        val studentList = ArrayList<SchoolData.Student>()

        setFNameTxtField(data.first_name)
        setLNameTxtField(data.last_name)
        selectHomeRoomField(schoolData.rooms[0].name)
        setDOBField(data.dateOfBirth)
        setGenderDropDownField(data.gender!!.lowercase())
        setAddressTxtField(data.address)
        utilsPageObject.elementScrollDown(getParentTypeDropDownField())
        selectParentTypeField(data.parentType)
        setParentFNameTxtField(data.parentFName)
        setParentLNameTxtField(data.parentLName)
        selectKidRelationField(data.relationWithChild)
        utilsPageObject.elementScrollDown(getKidRelationDropDownLbl())
        setPhoneNoTxtField(data.phoneNumber)
        setEmailAddressTxtField(data.emailAddress)
        clickStudentAddBtn()
        utilsPageObject.viewSuccessMessage()

        val dataList = ArrayList<SchoolData>()
        for (element in schoolData.student) {
            if (element.first_name != null) {
                studentList.add(element)
            }
        }
        studentList.add(data)
        schoolData.student = studentList
        dataList.add(schoolData)
        FileServiceManager.convertJavaObjectToJson(dataList)

    }


}