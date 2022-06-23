package com.perfect.PageObjects.Util

import Services.Driver
import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.LogStatus
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


open class ListenerTest : Driver(), ITestListener {
    private var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private var now = LocalDateTime.now()
    private var date = dtf.format(now)
    var reports: ExtentReports? = null

    override fun onFinish(result: ITestContext) {
        reports!!.endTest(getTest())
        reports!!.flush()
    }

    override fun onStart(Result: ITestContext) {

    }

    override fun onTestFailedButWithinSuccessPercentage(Result: ITestResult) {}

    // When Test case get failed, this method is called.
    override fun onTestFailure(result: ITestResult) {
        try {
            val scr = (webDriver as TakesScreenshot?)!!.getScreenshotAs(OutputType.FILE)
            val filename = result.name + ".png"
            var dest = File("C:\\temp\\$date\\$filename")
            FileUtils.copyFile(scr, dest)
            println(dtf.format(now))
            println(result.name)
            println("Successfully captured a screenshot")
            getTest()!!.log(LogStatus.FAIL, result.throwable.message.toString())
            getTest()!!.log(
                LogStatus.FAIL,
                "ScreenShot below: " + getTest()!!.addScreenCapture(File("C:\\temp\\$date\\$filename").toString())
            )
            getTest()!!.addScreenCapture(File("C:\\temp\\$date\\$filename").toString())
        } catch (e: Exception) {
            println("Exception while taking screenshot " + e.message)

        }
    }

    // When Test case get Skipped, this method is called.
    override fun onTestSkipped(Result: ITestResult) {
        println("The name of the testcase Skipped is :" + Result.name)
    }

    // When Test case get Started, this method is called.
    override fun onTestStart(Result: ITestResult) {
        getTestMethodName(Result)
        reports = createExtentReports()
        println(getTestMethodName(Result) + "test is starting.")

    }

    // When Test case get passed, this method is called.
    override fun onTestSuccess(Result: ITestResult) {
        println(getTestMethodName(Result) + " test is succeed.")
    }

    private fun getTestMethodName(iTestResult: ITestResult): String? {
        return iTestResult.method.constructorOrMethod.name
    }
}