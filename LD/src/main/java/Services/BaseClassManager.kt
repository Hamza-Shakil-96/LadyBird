package Services

import com.relevantcodes.extentreports.LogStatus
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.devtools.DevTools
import org.openqa.selenium.devtools.v103.network.Network
import org.openqa.selenium.devtools.v103.network.model.ResponseReceived
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.Assert
import org.testng.Assert.fail
import org.testng.annotations.*
import java.io.File
import java.util.Optional as Optional1

open class BaseClassManager() : ExtendReportManager() {

    private var props = FileServiceManager.getProps("data")

    @BeforeTest
    @Parameters("browser", "env")
    fun setup(@Optional("Chrome") browser: String, @Optional("QA") env: String) {
        println("initialize")
        extentReports!!.addSystemInfo("Environment", env)
        extentReports!!.loadConfig(File("extent-config.xml"))
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe")
        if (browser.equals("chrome", ignoreCase = true)) {
            println("Chrome")
            webDriver = ChromeDriver()
            devTools = (webDriver as ChromeDriver).devTools
        } else if (browser.equals("firefox", ignoreCase = true)) {
            webDriver = FirefoxDriver()
        }
        webDriver!!.manage().window().maximize()

    }

    @BeforeMethod
    @Parameters("env")
    fun setBaseUrl(@Optional("QA") env: String) {
        if (env.equals("QA", ignoreCase = true)) {
            webDriver!![props.getProperty("qa_base_Url")]
            devTools!!.createSession()
            println("Dev tool session created")
            devTools!!.send(
                Network.enable(
                    Optional1.of(100000000),
                    Optional1.empty(),
                    Optional1.empty()
                )
            )
            devTools!!.addListener(Network.responseReceived()) { responseReceived: ResponseReceived ->
                if (responseReceived.type.toJson().contains("XHR")) {
                    responseList.add(responseReceived)
                    println(responseReceived.response.url)
                }
            }
        } else if (env.equals("stage", ignoreCase = true)) {
            webDriver!![props.getProperty("stage_base_Url")]
        }
    }

    @AfterMethod
    fun afterEachMethod() {
        println("after method")
        devTools!!.clearListeners()
        println("Cleared Listeners")
        devTools!!.disconnectSession()
        println("Dev tool session disconnected")
        responseList.clear()
        webDriver!!.manage().deleteAllCookies()
    }

    fun retrieveApiStatus(url: String, expected: Int? = 200) {
        getTest()!!.log(LogStatus.INFO, url)
        var status = 0
        var time: Long = 0
        var responseBody = ""
        for (responses in responseList) {
            //"https://backend.perfectdaylive.com/qa/api/v1/login"
            if (responses.response.url.contains(url)) {
                status = responses.response.status
                time = responses.response.responseTime.get().toJson().toLong()
                responseBody = devTools!!.send(
                    Network.getResponseBody(
                        responses.requestId
                    )
                ).body
                println(responseBody)
            }
        }
        try {
            Assert.assertEquals(status, expected)
            getTest()!!.log(LogStatus.PASS, "Response: ok")
            getTest()!!.log(LogStatus.PASS, "Response Body:$responseBody")
        } catch (e: AssertionError) {
            getTest()!!.log(
                LogStatus.FAIL,
                "Response: " + e.message.toString()
            )
            getTest()!!.log(
                LogStatus.FAIL,
                "Response: $responseBody"
            )
            println(e.message.toString())
            fail("Test Failed")
        }
        getTest()!!.log(LogStatus.INFO, time.toString())
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

        @JvmStatic
        protected var responseList = ArrayList<ResponseReceived>()

        @JvmStatic
        protected var devTools: DevTools? = null


    }
}