package Services


import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.*


open class Driver {

    private var props = FileService.getProps("data")
    private var environment: String? = null
    var extent: ExtentReports? = null
    private val extentTestMap: HashMap<Int, ExtentTest> = HashMap<Int, ExtentTest>() //define empty hashmap

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
        environment = env;
        if (env.equals("QA", ignoreCase = true)) {
            webDriver!![props.getProperty("qa_base_Url")]
        } else if (env.equals("stage", ignoreCase = true)) {
            webDriver!![props.getProperty("stage_base_Url")]
        }
    }

    @AfterTest
    fun tearDown() {
        println("tearDown")
        webDriver!!.manage().deleteAllCookies();
        webDriver!!.quit()
    }

    fun createExtentReports(): ExtentReports? {
        extent = ExtentReports("test-output/report.html", false)
        return extent
    }

    @Synchronized
    fun startTest(testName: String?, desc: String?): ExtentTest? {
        println(testName)
        val test: ExtentTest = extent!!.startTest(testName, desc)
        extentTestMap[Thread.currentThread().id.toInt()] = test
        return test
    }

    @Synchronized
    open fun getTest(): ExtentTest? {
        println(extentTestMap[0])
        return extentTestMap[Thread.currentThread().id.toInt()]
    }

    companion object {

        @JvmStatic
        protected var webDriver: WebDriver? = null

    }
}