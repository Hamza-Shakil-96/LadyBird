package Services


import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest
import com.relevantcodes.extentreports.LogStatus
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.ITestResult
import org.testng.annotations.*
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


open class Driver {

    private var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private var now = LocalDateTime.now()
    private var date = dtf.format(now)
    private var props = FileService.getProps("data")

    @BeforeTest
    @Parameters("browser")
    fun setup(@Optional("Chrome") browser: String) {
        println("initialize")
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe")
        if (browser.equals("chrome", ignoreCase = true)) {
            println("Chrome")
            webDriver = ChromeDriver()
        } else if (browser.equals("firefox", ignoreCase = true)) {
            webDriver = FirefoxDriver()
        }
        webDriver!!.manage().window().maximize()

    }

    @BeforeMethod
    @Parameters("env")
    fun setBaseUrl(@Optional("QA") env: String,result: ITestResult) {
        println(env)
        if (env.equals("QA", ignoreCase = true)) {
            webDriver!![props.getProperty("qa_base_Url")]
        } else if (env.equals("stage", ignoreCase = true)) {
            webDriver!![props.getProperty("stage_base_Url")]
        }
        extent.addSystemInfo("Environment", env);

    }

    @AfterMethod
    fun createReport(result: ITestResult) {
        var dest = File("")
        if (ITestResult.FAILURE == result.status) {
            try {
                val scr = (webDriver as TakesScreenshot?)!!.getScreenshotAs(OutputType.FILE)
                val filename = result.name + ".png"
                dest = File("C:\\temp\\$date\\$filename")
                FileUtils.copyFile(scr, dest)
                println(dtf.format(now))
                println(result.name)
                println("Successfully captured a screenshot")
                test!!.log(LogStatus.FAIL, result.throwable.message.toString())
                test!!.log(LogStatus.FAIL,
                    "ScreenShot below: " + test!!.addScreenCapture(File("C:\\temp\\$date\\$filename").toString()))
                test!!.addScreenCapture(File("C:\\temp\\$date\\$filename").toString())
            } catch (e: Exception) {
                println("Exception while taking screenshot " + e.message)

            }

        } else {
            test!!.log(LogStatus.PASS, "Test case completed")
        }
        extent.endTest(test);
        extent.flush()
    }

    @AfterTest
    fun tearDown() {
        println("tearDown")
        webDriver!!.manage().deleteAllCookies();
        webDriver!!.quit()
    }

    companion object {

        private val yourFile = File("test-output/report.html")

        @JvmStatic
        protected var webDriver: WebDriver? = null

        @JvmStatic
        var extent: ExtentReports = ExtentReports(yourFile.toString(), false)

        @JvmStatic
        var test: ExtentTest? = null
    }
}