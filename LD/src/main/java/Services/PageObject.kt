package Services

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

open class PageObject(protected var driver: WebDriver?) : ExtendReportManager() {
    init {
        PageFactory.initElements(driver, this)
    }
}