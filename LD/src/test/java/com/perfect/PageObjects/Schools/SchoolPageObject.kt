package com.perfect.PageObjects.Schools

import Services.FileServiceManager
import Services.PageObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfect.Class.SchoolData
import com.perfect.PageObjects.Util.UtilsPageObject
import com.relevantcodes.extentreports.LogStatus
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy


class SchoolPageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)
    private val dataList = ArrayList<SchoolData>()

    @FindBy(xpath = "//*[@id=\"Scrollable-table\"]/table/tbody/tr/td[1]")
    val schoolTableFirstRow: WebElement? = null

    @FindBy(id = "go-to-add-school")
    val addBtn: WebElement? = null

    @FindBy(id = "fname-0")
    val fNameTxtField: WebElement? = null

    @FindBy(id = "lname-0")
    val lNameTxtField: WebElement? = null

    @FindBy(id = "email-0")
    val emailTxtField: WebElement? = null

    @FindBy(id = "phone-0")
    val adminNumberTxtField: WebElement? = null

    @FindBy(id = "designation-0")
    val designationTxtField: WebElement? = null

    @FindBy(id = "school-name")
    val schoolNameTxtField: WebElement? = null

    @FindBy(id = "school-phone")
    val schoolPhoneTxtField: WebElement? = null

    @FindBy(id = "school-address")
    val schoolAddressTxtField: WebElement? = null

    @FindBy(id = "school-street")
    val schoolStreetTxtField: WebElement? = null

    @FindBy(id = "country_code")
    val schoolCountryTxtField: WebElement? = null

    @FindBy(id = "state_id")
    val schoolStateTxtField: WebElement? = null

    @FindBy(id = "city")
    val schoolCityTxtField: WebElement? = null

    @FindBy(id = "menu-country_code")
    val countryCodeDropDownElem: WebElement? = null

    @FindBy(className = "MuiButtonBase-root")
    val dropDownElem: WebElement? = null

    @FindBy(id = "menu-state_id")
    val stateCodeDropDownElem: WebElement? = null

    @FindBy(id = "add-school-form")
    val addSchoolForm: WebElement? = null

    @FindBy(id = "school-zipcode")
    val schoolZipCodeTxtField: WebElement? = null

    @FindBy(id = "school-status")
    val schoolStatusTxtField: WebElement? = null

    @FindBy(id = "add-school-button")
    val schoolBtnAdd: WebElement? = null

    @FindBy(id = "search-input")
    val schoolSearch: WebElement? = null

    @FindBy(className = "MuiSnackbarContent-message")
    val toastMsg: WebElement? = null

    @FindBy(className = "switch-btn")
    val disableSchoolSwitch: WebElement? = null

    @FindBy(className = "add-disable-btn")
    val disableYesBtn: WebElement? = null

    @FindBy(className = "MuiSelect-icon")
    val dropDownIcon: WebElement? = null

    private fun getBtnAdd(): WebElement? {
        return addBtn
    }

    private fun getSchoolSwitch(): WebElement? {
        return disableSchoolSwitch
    }

    private fun getDisableYesBtnElem(): WebElement? {
        return disableYesBtn
    }

    private fun getSchoolCityDropDown(): WebElement? {
        return schoolCityTxtField
    }

    private fun getDropDown(): WebElement? {
        return dropDownElem
    }

    private fun getStateCodeDropDown(): WebElement? {
        return stateCodeDropDownElem
    }

    private fun getSchoolListingFirstRow(): WebElement? {
        return schoolTableFirstRow
    }

    private fun getSchoolSearchTxt(): WebElement? {
        return schoolSearch
    }

    private fun getSchoolForm(): WebElement? {
        utilsPageObject.isElementVisible(addSchoolForm)
        return addSchoolForm
    }

    private fun getFnameTxt(): WebElement? {
        return fNameTxtField
    }

    private fun getLnameTxt(): WebElement? {
        return lNameTxtField
    }

    private fun getemailTxt(): WebElement? {
        return emailTxtField
    }

    private fun getAdminNumberTxt(): WebElement? {
        return adminNumberTxtField
    }

    private fun getDesignationTxt(): WebElement? {
        return designationTxtField
    }

    private fun getSchoolNameTxt(): WebElement? {
        return schoolNameTxtField
    }

    private fun getSchoolPhoneTxt(): WebElement? {
        return schoolPhoneTxtField
    }

    private fun getSchoolAddressTxt(): WebElement? {
        return schoolAddressTxtField
    }

    private fun getSchoolStreetTxt(): WebElement? {
        return schoolStreetTxtField
    }

    private fun getSchoolCountryTxt(): WebElement? {
        return schoolCountryTxtField
    }

    private fun getSchoolCountryCode(countryName: String?): WebElement? {
        //return utilsPageObject.waitForElem("//li[@data-value='$countryCode']", "xpath")
        return utilsPageObject.waitForElem("//*[contains(text(),'$countryName')]", "xpath")
    }

    private fun getSchoolStateCode(state: String?): WebElement? {
        //return utilsPageObject.waitForElem("//li[@data-value='$stateCode']", "xpath")
        return utilsPageObject.waitForElem("//*[contains(text(),'$state')]", "xpath")
    }

    private fun getSchoolStatusCode(): List<WebElement?> {
        return this.driver!!.findElements(By.xpath("//*[@id=\"menu-status\"]/div[3]/ul/li"))
    }

    private fun getSchoolStateTxt(): WebElement? {
        return schoolStateTxtField
    }

    private fun getSchoolCityTxt(city: String?): WebElement? {
        return utilsPageObject.waitForElem("//*[contains(text(),'$city')]", "xpath")
    }

    private fun getSchoolZipcodeTxt(): WebElement? {
        return schoolZipCodeTxtField
    }

    private fun getSchoolStatusTxt(): WebElement? {
        return schoolStatusTxtField
    }

    private fun getBtnSchoolAdd(): WebElement? {
        return schoolBtnAdd
    }

    private fun setFnameTxtField(fName: String?) {
//        getFnameTxt()!!.clear()
        getFnameTxt()!!.sendKeys(fName)
    }

    private fun setLnameTxtField(lName: String?) {
//        getLnameTxt()!!.clear()
        getLnameTxt()!!.sendKeys(lName)
    }

    private fun setemailTxtField(email: String?) {
//        getemailTxt()!!.clear()
        getemailTxt()!!.sendKeys(email)
    }

    private fun setAdminNumberTxtField(number: String?) {
//        getAdminNumberTxt()!!.clear()
        getAdminNumberTxt()!!.sendKeys(number)
    }

    private fun setDesignationTxtField(number: String?) {
//        getDesignationTxt()!!.clear()
        getDesignationTxt()!!.sendKeys(number)
    }

    private fun setSchoolNameTxtField(schoolName: String?) {
//        getSchoolNameTxt()!!.clear()
        getSchoolNameTxt()!!.sendKeys(schoolName)
    }

    private fun setSchoolPhoneTxtField(number: String?) {
//        getSchoolPhoneTxt()!!.clear()
        getSchoolPhoneTxt()!!.sendKeys(number)
    }

    private fun setSchoolAddressTxtField(address: String?) {
//        getSchoolAddressTxt()!!.clear()
        getSchoolAddressTxt()!!.sendKeys(address)
    }

    private fun setSchoolStreetTxtField(street: String?) {
//        getSchoolStreetTxt()!!.clear()
        getSchoolStreetTxt()!!.sendKeys(street)
    }

    private fun setSchoolSearchTxtField(value: String?) {
//        getSchoolSearchTxt()!!.clear()
        getSchoolSearchTxt()!!.sendKeys(value)
    }

    private fun selectSchoolCountry(value: String? = "US") {
        utilsPageObject.isElementClickable(getSchoolCountryTxt()).click()
        utilsPageObject.isElementVisible(getDropDown())
        utilsPageObject.isElementClickable(getSchoolCountryCode(value)).click()
    }

    private fun selectSchoolState(value: String? = "116") {
        getSchoolStateTxt()!!.click()
        utilsPageObject.isElementVisible(getDropDown())
        utilsPageObject.isElementClickable(getSchoolStateCode(value)).click()
    }

    private fun selectSchoolStatus(value: String? = "1") {
        utilsPageObject.isElementClickable(getSchoolStatusTxt()).click()
        utilsPageObject.selectOptionFromDropDown(getSchoolStatusCode(), value)
    }

    private fun selectSchoolCity(city: String?) {
        getSchoolCityDropDown()!!.click()
        utilsPageObject.isElementVisible(getDropDown())
        utilsPageObject.isElementClickable(getSchoolCityTxt(city)).click()
//        getSchoolCityTxt()!!.clear()
//        getSchoolCityTxt()!!.sendKeys(city)
    }

    private fun selectSchoolZipCode(zipCode: String?) {
        getSchoolZipcodeTxt()!!.clear()
        getSchoolZipcodeTxt()!!.sendKeys(zipCode)
    }

    fun clickAddSchoolBtn() {
        utilsPageObject.isElementClickable(getBtnSchoolAdd()).click()
    }

    fun addSchoolInfo(data: SchoolData) {
        dataList.add(data)
        getTest()!!.log(LogStatus.INFO, "Filling school data")
        setFnameTxtField(data.admins[0].first_name)
        setLnameTxtField(data.admins[0].last_name)
        setemailTxtField(data.admins[0].email_Address)
        setAdminNumberTxtField(data.admins[0].phone)
        setDesignationTxtField(data.admins[0].designation)
        utilsPageObject.elementScrollDown(schoolNameTxtField)
        setSchoolNameTxtField(data.name)
        setSchoolPhoneTxtField(data.phone)
        setSchoolAddressTxtField(data.address)
        setSchoolStreetTxtField(data.street)
        selectSchoolCountry(data.country)
        selectSchoolState(data.state)
        selectSchoolCity(data.city)
        selectSchoolZipCode(data.zipcode)
        selectSchoolStatus()
        clickAddSchoolBtn()
        FileServiceManager.convertJavaObjectToJson(dataList)
    }

    fun viewSuccessMessage() {
        utilsPageObject.isElementVisible(toastMsg)
        getTest()!!.log(LogStatus.INFO, "View success Toast")
    }

    fun clickAddBtn() {
        utilsPageObject.isElementClickable(getBtnAdd())
        getBtnAdd()!!.click()
    }

    fun searchSchool(): String? {
        var schoolName: String? = null
        var data: SchoolData
        if (dataList.size == 0) {
            val objectMapper = ObjectMapper()
            var node = FileServiceManager.convertJsonToJavaObjects()
            data = objectMapper.treeToValue(node[0], SchoolData::class.java)
            schoolName = data.name
        } else {
            schoolName = dataList[0].name
        }
        getTest()!!.log(LogStatus.INFO, "Search school using Keyword: $schoolName")
        utilsPageObject.isLoaderElementVisible()
        utilsPageObject.isLoaderElementInvisible()
        setSchoolSearchTxtField(schoolName)
        utilsPageObject.isLoaderElementVisible()
        utilsPageObject.isLoaderElementInvisible()
        utilsPageObject.isElementVisible(getSchoolListingFirstRow())
        return schoolName
    }

    fun disableSchool() {
        getTest()!!.log(LogStatus.INFO, "Disabling School")
        utilsPageObject.isElementVisible(getSchoolSwitch())
        getSchoolSwitch()!!.click()
        utilsPageObject.isElementVisible(getDisableYesBtnElem())
        getDisableYesBtnElem()!!.click()
        utilsPageObject.viewSuccessMessage()
    }

    fun viewNewlyAddedSchoolInListing(): Boolean {
        val schoolName = searchSchool()
        val firstRowTxt = getSchoolListingFirstRow()!!.text
        println("Expected: $schoolName Actual: $firstRowTxt")
        return if (schoolName!!.lowercase() == firstRowTxt.lowercase()) {
            FileServiceManager.convertJavaObjectToJson(dataList)
            true
        } else {
            false
        }
    }

    fun navigateToAddSchoolScreen() {
        getSchoolForm()
    }
}

