package com.perfect.PageObjects.Login

import Services.PageObject
import com.perfect.PageObjects.Util.UtilsPageObject
import org.jsoup.Jsoup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import java.util.*
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.MimeMultipart
import kotlin.test.fail


class ForgotPasswordPageObject(driver: WebDriver?) : PageObject(driver) {
    private var utilsPageObject = UtilsPageObject(this.driver)

    private var hostName = "smtp.gmail.com"
    private var username = "hamza.appiskey@gmail.com"
    private var password = "dzydvcyezrbxdbcr"

    @FindBy(xpath = "//a[@href='/forgot-password']")
    val linkForgotPasswordElem: WebElement? = null

    @FindBy(id = "forgot-password-email")
    val forgotPasswordTxtElem: WebElement? = null

    @FindBy(id = "pswd")
    val passwordTxtElem: WebElement? = null

    @FindBy(id = "cnfrm_pswd")
    val confirmPasswordTxtElem: WebElement? = null

    @FindBy(className = "forget-password-submit")
    val submitBtnElem: WebElement? = null

    @FindBy(name = "otp1")
    val otp1TxtElem: WebElement? = null

    @FindBy(name = "otp2")
    val otp2TxtElem: WebElement? = null

    @FindBy(name = "otp3")
    val otp3TxtElem: WebElement? = null

    @FindBy(name = "otp4")
    val otp4TxtElem: WebElement? = null

    @FindBy(className = "otpbtn")
    val otpBtnElem: WebElement? = null

    @FindBy(className = "resetsubmit")
    val resetBtnElem: WebElement? = null

    @FindBy(xpath = "//*[contains(text(),Resend OTP)]")
    val resendOtpBtnElem: WebElement? = null

    private fun getForgotPasswordLink(): WebElement? {
        return linkForgotPasswordElem
    }

    private fun viewOtpForm() {
        utilsPageObject.waitForElem("otpContainer", "class")
    }

    private fun getPasswordTxtField(): WebElement? {
        return passwordTxtElem
    }

    private fun getResetBtn(): WebElement? {
        return resetBtnElem
    }

    private fun getConfirmPasswordTxtField(): WebElement? {
        return confirmPasswordTxtElem
    }

    private fun clickForgotPasswordLink() {
        utilsPageObject.isElementClickable(getForgotPasswordLink()).click()
    }

    private fun getForgotPasswordTxtField(): WebElement? {
        return forgotPasswordTxtElem
    }

    private fun setForgotPasswordTxtField(email: String?) {
        utilsPageObject.isElementVisible(getForgotPasswordTxtField()).sendKeys(email)
    }

    private fun setPasswordTxtField(passwd: String?) {
        utilsPageObject.isElementVisible(getPasswordTxtField()).sendKeys(passwd)
    }

    private fun setConfirmPasswordTxtField(passwd: String?) {
        utilsPageObject.isElementVisible(getConfirmPasswordTxtField()).sendKeys(passwd)
    }

    private fun getSubmitBtn(): WebElement? {
        return submitBtnElem
    }

    private fun clickSubmitBtn() {
        utilsPageObject.isElementClickable(getSubmitBtn()).click()
    }

    private fun clickResetBtn() {
        utilsPageObject.isElementClickable(getResetBtn()).click()
    }

    private fun getOtP1TxtField(): WebElement? {
        return otp1TxtElem
    }

    private fun setOtP1TxtField(otp: String) {
        getOtP1TxtField()!!.sendKeys(otp)
    }

    private fun getOtP2TxtField(): WebElement? {
        return otp2TxtElem
    }

    fun setOtP2TxtField(otp: String) {
        getOtP2TxtField()!!.sendKeys(otp)
    }

    private fun getOtP3TxtField(): WebElement? {
        return otp3TxtElem
    }

    fun setOtP3TxtField(otp: String) {
        getOtP3TxtField()!!.sendKeys(otp)
    }

    fun getOtP4TxtField(): WebElement? {
        return otp4TxtElem
    }

    fun setOtP4TxtField(otp: String) {
        getOtP2TxtField()!!.sendKeys(otp)
    }

    private fun getOtPBtnElem(): WebElement? {
        return otpBtnElem
    }

    private fun clickOtpBtnElem() {
        utilsPageObject.isElementClickable(getOtPBtnElem()).click()
    }

    fun getResendOtPBtnElem(): WebElement? {
        return resendOtpBtnElem
    }

    fun sendForgotPasswordEmail(email: String? = "hamza.appiskey@gmail.com") {
        clickForgotPasswordLink()
        setForgotPasswordTxtField(email)
        clickSubmitBtn()
        utilsPageObject.viewSuccessMessage()
        Thread.sleep(2000)
    }

    fun verifyOtpCode() {
        viewOtpForm()
        val otp = retrieveOTPFromEmail()
        if (otp != "") {
            utilsPageObject.isElementVisible(getOtP1TxtField())
            setOtP1TxtField(otp!!.substring(0, 4))
            clickOtpBtnElem()
            Thread.sleep(2000)
        } else {
            fail("No OTP Found")
        }
    }

    fun resetPassword() {
        setPasswordTxtField("Hamza@321")
        setConfirmPasswordTxtField("Hamza@321")
        clickResetBtn()
        Thread.sleep(2000)
    }

    @Throws(Throwable::class)
    private fun retrieveOTPFromEmail(): String? {
        Thread.sleep(2000)
        val props = Properties()
        props.setProperty("mail.store.protocol", "imaps")
        props.setProperty("mail.imaps.ssl.enable", "true")
        props["mail.imaps.ssl.protocols"] = "TLSv1.2"
        props.setProperty("mail.imaps.port", "993")
        props.setProperty("mail.imaps.timeout", "10000")
        props.setProperty("mail.imaps.connectiontimeout", "10000")
        val session = Session.getInstance(props, null)
        val store = session.store
        store.connect(hostName, username, password) //"imap.gmail.com"
        val inbox = store.getFolder("Inbox")
        inbox.open(Folder.READ_ONLY)
        println("Connected!")
        var message: Message? = null
        val messages = inbox.messages
        for (i in messages.size - 1 downTo messages.size - 2) {
            message = messages[i]
            val d1 = messages[messages.size - 1].receivedDate
            val d2 = messages[messages.size - 2].receivedDate
            println("$d1--------$d2")
            if (message.subject.equals("Password reset for Perfect Day Care", ignoreCase = true)) {
                if (d1.after(d2)) {
                    message = messages[messages.size - 1]
                    if (message.isMimeType("text/plain")) {
                        println(message.content.toString())
                    } else if (message.isMimeType("multipart/*")) {
                        var result = ""
                        val mimeMultipart = message.content as MimeMultipart
                        val count = mimeMultipart.count
                        for (j in 0 until count) {
                            val bodyPart = mimeMultipart.getBodyPart(j)
                            if (bodyPart.isMimeType("text/plain")) {
                                result = """
                                $result
                                ${bodyPart.content}
                                """.trimIndent()
                                break //without break same text appears twice in my tests
                            } else if (bodyPart.isMimeType("text/html")) {
                                val html = bodyPart.content as String
                                result = """
                                $result
                                ${Jsoup.parse(html).text()}
                                """.trimIndent()
                            }
                        }
                        val s1 = result.replace("[^0-9]+".toRegex(), "")
                        println("------- result ----------$s1")
                        return s1
                    }
                    println("-")
                }
            }
        }
        return ""
    }


}