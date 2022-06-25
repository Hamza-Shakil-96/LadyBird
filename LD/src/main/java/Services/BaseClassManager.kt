package Services


import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.*


open class BaseClassManager() : ExtendReportManager() {

    private var props = FileServiceManager.getProps("data")
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
        environment = env
        if (env.equals("QA", ignoreCase = true)) {
            webDriver!![props.getProperty("qa_base_Url")]
        } else if (env.equals("stage", ignoreCase = true)) {
            webDriver!![props.getProperty("stage_base_Url")]
        }
    }

    @AfterTest
    fun tearDown() {
        println("tearDown")
        webDriver!!.manage().deleteAllCookies()
        webDriver!!.quit()
    }

    companion object {
        @JvmStatic
        protected var webDriver: WebDriver? = null


    }
}