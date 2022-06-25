package com.perfect.Listeners

import Services.BaseClassManager
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


open class ListenerTest() : BaseClassManager(), ITestListener {
    private var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private var now = LocalDateTime.now()
    private var date = dtf.format(now)

    override fun onFinish(result: ITestContext) {
        extentReports!!.flush()
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
        println(getTestMethodName(Result) + "test is starting.")

    }

    // When Test case get passed, this method is called.
    override fun onTestSuccess(Result: ITestResult) {
        getTest()!!.log(LogStatus.PASS, Result.method.description)
        getTest()!!.log(LogStatus.PASS, Result.method.methodName)
        getTest()!!.log(LogStatus.PASS, Result.method.qualifiedName)
        getTest()!!.log(LogStatus.PASS, Result.method.currentInvocationCount.toString())
        getTest()!!.log(LogStatus.PASS, Result.testName)
    }

    private fun getTestMethodName(iTestResult: ITestResult): String? {
        return iTestResult.method.constructorOrMethod.name
    }
}