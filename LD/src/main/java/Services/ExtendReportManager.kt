package Services

import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod


open class ExtendReportManager() {
    fun startTest(testName: String?, desc: String?, cat: String?): ExtentTest? {
        val test: ExtentTest = extentReports!!.startTest(testName, desc)
        extentTestMap[Thread.currentThread().id.toInt()] = test
        test.assignCategory(cat)
        return test
    }


    @AfterMethod
    fun endTest() {
        extentReports!!.endTest(getTest())
    }

    fun abc(){

    }
    fun getTest(): ExtentTest? {
        return extentTestMap[Thread.currentThread().id.toInt()]
    }

    companion object {
        @JvmStatic
        protected var driver: WebDriver? = null

        @JvmStatic
        val extentTestMap: HashMap<Int, ExtentTest> = HashMap<Int, ExtentTest>()

        @JvmStatic
        var extentReports: ExtentReports? = ExtentReports("output/extent-report.html", false)
    }

}