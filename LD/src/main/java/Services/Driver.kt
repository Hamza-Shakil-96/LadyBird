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

    fun startTest(testName: String?, desc: String?): ExtentTest? {
        val test = extentReports!!.startTest(testName, desc)
        hashMap[Thread.currentThread().id.toInt()] = test
        return test
    }

    @AfterMethod
    fun endTest() {
        extentReports!!.endTest(getTest())
    }

    open fun getTest(): ExtentTest? {
        return hashMap[Thread.currentThread().id.toInt()]
    }

    companion object {

        @JvmStatic
        protected var webDriver: WebDriver? = null

        @JvmStatic
        val hashMap: HashMap<Int, ExtentTest> = HashMap<Int, ExtentTest>()

        @JvmStatic
        var extentReports: ExtentReports? = ExtentReports("output/extent-report.html", false)

    }
}