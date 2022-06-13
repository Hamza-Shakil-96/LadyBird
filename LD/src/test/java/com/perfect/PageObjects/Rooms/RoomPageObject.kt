package com.perfect.PageObjects.Rooms

import Services.FileService
import Services.PageObject
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.perfect.Class.SchoolData
import com.perfect.Class.SchoolData.Rooms
import com.perfect.PageObjects.Util.UtilsPageObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import java.io.File


class RoomPageObject(driver: WebDriver?) : PageObject(driver) {

    private var utilsPageObject: UtilsPageObject = UtilsPageObject(this.driver)
    private var props = FileService.getProps("data")
    val objectMapper = ObjectMapper()
    val node: JsonNode = FileService.convertJsonToJavaObjects()//objectMapper.readTree(File(props.getProperty("json-File_Url")))
    val school: SchoolData = objectMapper.treeToValue(node[0], SchoolData::class.java)
    var roomList = ArrayList<Rooms>()
    var dataList = ArrayList<Any>()

    @FindBy(xpath = "//*[@id=\"Scrollable-table\"]/table/tbody/tr[1]/th")
    val roomTableFirstRow: WebElement? = null

    @FindBy(id = "go-to-add-room")
    val addRoomBtnElem: WebElement? = null

    @FindBy(id = "search-input")
    val searchTxtElem: WebElement? = null

    @FindBy(id = "room")
    val roomTxtElem: WebElement? = null

    @FindBy(id = "image")
    val uploadImgTxtElem: WebElement? = null

    @FindBy(id = "add-room")
    val submitBtnElem: WebElement? = null

    @FindBy(id = "cancel")
    val cancelBtnElem: WebElement? = null

    @FindBy(className = "main-div")
    val mainDivElem: WebElement? = null

    private fun getRoomBtn(): WebElement? {
        return addRoomBtnElem
    }

    private fun getSearchTxt(): WebElement? {
        return searchTxtElem
    }

    private fun getRoomTxt(): WebElement? {
        return roomTxtElem
    }

    private fun getUploadImgTxt(): WebElement? {
        return uploadImgTxtElem
    }

    private fun getSubmitBtn(): WebElement? {
        return submitBtnElem
    }

    private fun getCancelBtn(): WebElement? {
        return cancelBtnElem

    }

    fun setSearchTxt(value: String?) {
        getSearchTxt()!!.clear()
        getSearchTxt()!!.sendKeys(value)
    }

    private fun getRoomListingFirstRow(): WebElement? {
        return roomTableFirstRow
    }

    private fun setRoomTxt(value: String?) {
        utilsPageObject.isElementVisible(getRoomTxt())
        getRoomTxt()!!.clear()
        getRoomTxt()!!.sendKeys(value)
    }

    private fun setUploadTxt(value: String?) {
        getUploadImgTxt()!!.sendKeys(java.io.File(value).absolutePath) //Uploading the file using sendKeys
    }

    private fun clickSubmitBtn() {
        utilsPageObject.isElementClickable(getSubmitBtn())
        getSubmitBtn()!!.click()
    }

     private fun clickAddRoomBtn() {
        utilsPageObject.isElementClickable(getRoomBtn()).click()
    }
    fun navigateToAddRoomScreen() {
        clickAddRoomBtn()
    }
    fun addRoom(data: Rooms, isUpload: Boolean = true, fileFolder: String? = props.getProperty("room_Folder_Url")) {
        setRoomTxt(data.name)
        if (isUpload) {
            var url = utilsPageObject.getFileFromFolder(File(fileFolder))
            setUploadTxt(url.toString())
            data.url = url.toString()
        }
        clickSubmitBtn()
        for (element in school.rooms) {
            if (element.name != null) {
                roomList.add(element)
            }
        }
        roomList.add(data)
        school.rooms = roomList
        dataList.add(school)
        FileService.convertJavaObjectToJson(dataList)
    }

    fun viewNewlyAddedRoom(name: String): Boolean {
        utilsPageObject.isElementVisible(getRoomListingFirstRow())
        utilsPageObject.isLoaderElementInvisible()
        setSearchTxt(name)
        utilsPageObject.isLoaderElementVisible()
        utilsPageObject.isLoaderElementInvisible()
        val firstRowTxt = getRoomListingFirstRow()!!.text
        println("Expected: $name Actual: $firstRowTxt")
        return name.lowercase() == firstRowTxt.lowercase()
    }
}