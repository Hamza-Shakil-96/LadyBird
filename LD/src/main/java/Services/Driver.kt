package Services

import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.ITestResult
import org.testng.annotations.*
import java.io.File
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
    fun setBaseUrl(@Optional("QA") env: String) {
        println(env)
        if (env.equals("QA", ignoreCase = true)) {
            webDriver!![props.getProperty("qa_base_Url")]
        } else if (env.equals("stage", ignoreCase = true)) {
            webDriver!![props.getProperty("stage_base_Url")]
        }
    }

    @AfterMethod
    fun screenShot(result: ITestResult) {
        if (ITestResult.FAILURE == result.status) {
            try {
                val scr = (webDriver as TakesScreenshot?)!!.getScreenshotAs(OutputType.FILE)
                val filename = result.name + ".png"
                val dest = File("C:\\temp\\$date\\$filename")
                FileUtils.copyFile(scr, dest)
                println(dtf.format(now))
                println(result.name)
                println("Successfully captured a screenshot")
            } catch (e: Exception) {
                println("Exception while taking screenshot " + e.message)
            }
        }
    }

    @AfterTest
    fun tearDown() {
        println("tearDown")
        webDriver!!.manage().deleteAllCookies();
        webDriver!!.quit()
    }
    companion object {
        @JvmStatic
        protected var webDriver: WebDriver? = null
    }
}