package com.perfect.PageObjects.Staff

import Services.FileService
import Services.PageObject
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy


class StaffPageObject(driver: WebDriver?) : PageObject(driver) {
    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)

    @FindBy(className = "MuiList-root")
    val dropDownElem: WebElement? = null

    @FindBy(className = "staff-name")
    val staffTableFirstRow: WebElement? = null

    @FindBy(className = "add-btn")
    val addBtn: WebElement? = null

    @FindBy(className = "add-btn")
    val staffAddBtn: WebElement? = null

    @FindBy(id = "search")
    val staffSearch: WebElement? = null

    @FindBy(name = "first_name")
    val fNameTxtField: WebElement? = null

    @FindBy(name = "last_name")
    val lNameTxtField: WebElement? = null

    @FindBy(name = "email")
    val emailTxtField: WebElement? = null

    @FindBy(name = "phone")
    val phoneTxtField: WebElement? = null

    @FindBy(name = "emergency_name")
    val emergencyNameTxtField: WebElement? = null

    @FindBy(name = "emergency_phone")
    val emergencyPhoneTxtField: WebElement? = null

    @FindBy(id = "title")
    val titleDropDown: WebElement? = null

    @FindBy(id = "yes")
    val adminFirstDropElem: WebElement? = null

    @FindBy(id = "no")
    val adminSecondDropElem: WebElement? = null

    @FindBy(id = "full-time")
    val titleFirstDropElem: WebElement? = null

    @FindBy(id = "part-time")
    val titleSecondDropElem: WebElement? = null

    @FindBy(id = "admin")
    val adminDropDown: WebElement? = null

    @FindBy(className = "edit-staff-form-heading")
    val addStaffFormElem: WebElement? = null

    @FindBy(id = "position_type")
    val positionDropDown: WebElement? = null

    private fun getDropDown(): WebElement? {
        return dropDownElem
    }

    private fun getPositionTypeOptions(type: String?): WebElement? {
        return this.driver!!.findElement(By.id(type))
    }

    private fun getTitleTypeOptions(title: String?): WebElement? {
        return if (title.equals("part-time")) {
            titleSecondDropElem
        } else {
            titleFirstDropElem
        }
    }

    private fun getAdminTypeOptions(isAdmin: String?): WebElement? {
        return if (isAdmin.equals("no")) {
            adminSecondDropElem
        } else {
            adminFirstDropElem
        }
    }

    private fun getAddStaffForm(): WebElement? {
        utilsPageObject.isElementVisible(addStaffFormElem)
        return addStaffFormElem
    }

    private fun getPositionDropDownElem(): WebElement? {
        return positionDropDown
    }


    private fun getBtnAdd(): WebElement? {
        return addBtn
    }

    private fun getStaffBtnAdd(): WebElement? {
        return staffAddBtn
    }

    private fun getSearchTxtField(): WebElement? {
        return staffSearch
    }

    private fun getFnameTxtField(): WebElement? {
        return fNameTxtField
    }

    private fun getLnameTxtField(): WebElement? {
        return lNameTxtField
    }

    private fun getEmailAddressTxtField(): WebElement? {
        return emailTxtField
    }

    private fun getNumberTxtField(): WebElement? {
        return phoneTxtField
    }

    private fun `getEmergency-nameTxtField`(): WebElement? {
        return emergencyNameTxtField
    }

    private fun `getEmergency-phoneTxtField`(): WebElement? {
        return emergencyPhoneTxtField
    }

    private fun getTitleDropdownElem(): WebElement? {
        return titleDropDown
    }

    private fun getAdminDropdownElem(): WebElement? {
        return adminDropDown
    }

    private fun setFnameTxtField(fName: String?) {
        getFnameTxtField()!!.clear()
        getFnameTxtField()!!.sendKeys(fName)
    }

    private fun setSearchTxtField(name: String?) {
        getSearchTxtField()!!.clear()
        getSearchTxtField()!!.sendKeys(name)
    }

    private fun setLnameTxtField(lName: String?) {
        getLnameTxtField()!!.clear()
        getLnameTxtField()!!.sendKeys(lName)
    }

    private fun setEmailTxtField(email: String?) {
        getEmailAddressTxtField()!!.clear()
        getEmailAddressTxtField()!!.sendKeys(email)
    }

    private fun setPhoneTxtField(number: String?) {
        getNumberTxtField()!!.clear()
        getNumberTxtField()!!.sendKeys(number)
    }

    private fun setEmergencyNameTxtField(ename: String?) {
        `getEmergency-nameTxtField`()!!.clear()
        `getEmergency-nameTxtField`()!!.sendKeys(ename)
    }

    private fun setEmergencyNumberTxtField(enumber: String?) {
        `getEmergency-phoneTxtField`()!!.clear()
        `getEmergency-phoneTxtField`()!!.sendKeys(enumber)
    }

    private fun setPositionTypeField(type: String?) {
        getPositionDropDownElem()!!.click()
        utilsPageObject.isElementVisible(getDropDown())
        utilsPageObject.isElementClickable(getPositionTypeOptions(type))!!.click()
    }

    private fun setTitleField(title: String?) {
        //utilsPageObject.isElementClickable(getTitleDropdownElem()).click()
        getTitleDropdownElem()!!.click()
        utilsPageObject.isElementVisible(getDropDown())
        utilsPageObject.isElementClickable(getTitleTypeOptions(title))!!.click()
    }

    private fun setAdminField(isAdmin: String?) {
//        utilsPageObject.isElementClickable(getAdminDropdownElem()).click()
        getAdminDropdownElem()!!.click()
        utilsPageObject.isElementVisible(getDropDown())
        utilsPageObject.isElementClickable(getAdminTypeOptions(isAdmin))!!.click()
    }

    private fun getStaffListingFirstRow(): WebElement? {
        return staffTableFirstRow
    }

    fun navigateToAddStaffScreen() {
        clickAddBtn()
        getAddStaffForm()
    }

    fun addStaff(data: SchoolData.Staff) {
        val objectMapper = ObjectMapper()
        val node: JsonNode =
            FileService.convertJsonToJavaObjects()//objectMapper.readTree(File(props.getProperty("json-File_Url")))
        val schoolData: SchoolData = objectMapper.treeToValue(node[0], SchoolData::class.java)
        val staffList = ArrayList<SchoolData.Staff>()
        val dataList = ArrayList<SchoolData>()

        setFnameTxtField(data.first_name)
        setLnameTxtField(data.last_name)
        setEmailTxtField(data.email_Address)
        setPhoneTxtField(data.phone)
        setEmergencyNameTxtField(data.emergency_name)
        setEmergencyNumberTxtField(data.emergency_phone)
        setPositionTypeField(data.postion_type)
        setTitleField(data.title)
        setAdminField(data.superUser)
        for (element in schoolData.staff) {
            if (element.first_name != null) {
                staffList.add(element)
            }
        }
        staffList.add(data)
        schoolData.staff = staffList
        dataList.add(schoolData)
        FileService.convertJavaObjectToJson(dataList)
        clickStaffAddBtn()
    }

    private fun clickAddBtn() {
        utilsPageObject.isElementClickable(getBtnAdd()).click()
    }

    private fun clickStaffAddBtn() {
        utilsPageObject.isElementClickable(getStaffBtnAdd()).click()
    }

    fun viewNewlyAddStaff(name: String): Boolean {
        utilsPageObject.isElementVisible(getSearchTxtField())
        utilsPageObject.isLoaderElementInvisible()
        setSearchTxtField(name)
        utilsPageObject.isLoaderElementVisible()
        utilsPageObject.isLoaderElementInvisible()
        val firstRowTxt = getStaffListingFirstRow()!!.text
        println("Expected: $name Actual: $firstRowTxt")
        return name.lowercase() == firstRowTxt.lowercase()
    }
}