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
import java.util.*


class StudentPageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)
    private val studentList = ArrayList<SchoolData.Student>()
    private val dataList = ArrayList<SchoolData>()
    private val r = Random()

    @FindBy(className = "student-name") //  "//*[@id=\"Scrollable-table\"]/table/tbody/tr/td[1]")
    val studentTableFirstRow: WebElement? = null

    @FindBy(id = "search")
    val studentSearch: WebElement? = null

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

    @FindBy(id = "parent-autocpmplete")
    val existingParentDropDown: WebElement? = null

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

    private fun getExsistingTypeDropDownElem(): WebElement? {
        return existParentTypeDropdownElem
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
        } else {
            utilsPageObject.isElementClickable(getExsistingTypeDropDownElem()).click()
        }

    }

    private fun getStudentListingFirstRow(): WebElement? {
        return studentTableFirstRow
    }

    private fun getExistingParentTxtField(): WebElement? {
        return existingParentDropDown
    }

    private fun setExistingParentTxt(parent: SchoolData.Parent) {
        utilsPageObject.isElementVisible(getExistingParentTxtField())
        getExistingParentTxtField()!!.sendKeys(parent.parentFName + " " + parent.parentLName)
        getExistingParentTxtField()!!.sendKeys(Keys.chord(Keys.ARROW_DOWN));
        getExistingParentTxtField()!!.sendKeys(Keys.chord(Keys.ENTER));
    }

    private fun getSchoolSearchTxt(): WebElement? {
        return studentSearch
    }

    private fun setSchoolSearchTxtField(value: String?) {
//        getSchoolSearchTxt()!!.clear()
        getSchoolSearchTxt()!!.sendKeys(value)
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

    fun addNewStudent(data: SchoolData.Student, isNew: Boolean = true) {
        val objectMapper = ObjectMapper()
        val node: JsonNode =
            FileServiceManager.convertJsonToJavaObjects()//objectMapper.readTree(File(props.getProperty("json-File_Url")))
        val schoolData: SchoolData = objectMapper.treeToValue(node[0], SchoolData::class.java)
        setFNameTxtField(data.first_name)
        setLNameTxtField(data.last_name)
        if (schoolData.rooms.size != 0) {
            val randomNumber = r.nextInt(schoolData.rooms.size)
            selectHomeRoomField(schoolData.rooms[randomNumber].name)
            data.homeRoom = schoolData.rooms[randomNumber].name.toString()
        } else {
            selectHomeRoomField(data.homeRoom)
        }
        setDOBField(data.dateOfBirth)
        setGenderDropDownField(data.gender!!.lowercase())
        setAddressTxtField(data.address)
        utilsPageObject.elementScrollDown(getParentTypeDropDownField())
        selectParentTypeField(data.parent.parentType)
        if (isNew) {
            setParentFNameTxtField(data.parent.parentFName)
            setParentLNameTxtField(data.parent.parentLName)
            selectKidRelationField(data.parent.relationWithChild)
            utilsPageObject.elementScrollDown(getKidRelationDropDownLbl())
            setPhoneNoTxtField(data.parent.phoneNumber)
            setEmailAddressTxtField(data.parent.emailAddress)
        } else {
            val randomNumber = r.nextInt(schoolData.student.size)
            var parent = schoolData.student[randomNumber].parent
            data.parent.parentFName = parent.parentFName
            data.parent.parentLName = parent.parentLName
            data.parent.emailAddress = parent.emailAddress
            data.parent.phoneNumber = parent.phoneNumber
            data.parent.relationWithChild = parent.relationWithChild
            setExistingParentTxt(parent)
        }
        clickStudentAddBtn()
        utilsPageObject.viewSuccessMessage()
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

    private fun searchStudent(): String? {
        var studentName: String? = null
        var data: SchoolData
        if (dataList.size == 0) {
            val objectMapper = ObjectMapper()
            var node = FileServiceManager.convertJsonToJavaObjects()
            data = objectMapper.treeToValue(node[0], SchoolData::class.java)
            studentName = data.student.last().first_name
        } else {
            studentName = studentList.last().first_name
            utilsPageObject.isLoaderElementVisible()
            utilsPageObject.isLoaderElementInvisible()
            setSchoolSearchTxtField(studentName)
            utilsPageObject.isLoaderElementVisible()
            utilsPageObject.isLoaderElementInvisible()
            utilsPageObject.isElementVisible(getStudentListingFirstRow())
        }
        return studentName
    }
}