package listeners;

import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import base.TestBase;
import emailReporter.ReportCreator;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestListener implements IExecutionListener, ITestListener, ISuiteListener {
	
	private long startTime;
    private int suiteTotalTests;
    private int suitePassedTests;
    private int suiteFailedTests;
    private int suiteSkippedTests;
    private static int totalTests;
    private static int totalPassedTests;
    private static int totalFailedTests;
    private static int totalSkippedTests;
    private static List<String[]> suiteDetails;
    private static List<String[]> suiteFailureDetails;
    private static List<String[]> projectDetails;
    WebDriver driver;
    
    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
        suiteDetails = new ArrayList<String[]>();
        projectDetails = new ArrayList<String[]>();
        suiteFailureDetails = new ArrayList<String[]>();
    }
    
    @Override
    public void onStart(ISuite iSuite) {
        suiteTotalTests = 0;
        suitePassedTests = 0;
        suiteFailedTests = 0;
        suiteSkippedTests = 0;
    }
    
    @Override
    public void onStart(ITestContext context) {
    }
    
    @Override
    public void onTestStart(ITestResult result) {
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getName().trim();
        takeScreenShot(methodName);
        addToTotalTestsInSuite();
        addToSuccessTestsInSuite();
        addToTotalTestsInProject();
        addToSuccessTestsInProject();
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
    	this.driver = TestBase.driver;
    	driver.quit();
        addToTotalTestsInSuite();
        addToSkippedTestsInSuite();
        addToSkippedTestsInProject();
        addToTotalTestsInProject();
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Taking screenshot of failure!");
        String methodName = result.getName().trim();
        takeScreenShot(methodName);
        addToTotalTestsInSuite();
        addToTotalTestsInProject();
        addToFailedTestsInSuite();
        addToFailedTestsInProject();
        suiteFailureDetails.add(addSuiteFailureDetails(result));
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    
    @Override
    public void onFinish(ITestContext context) {
    }
    
    @Override
    public void onFinish(ISuite iSuite) {
        if (suiteTotalTests > 0)
            suiteDetails.add(addSuiteDetails(iSuite));
    }
    
    @Override
    public void onExecutionFinish() {
        String totalBuildTime = time(System.currentTimeMillis() - startTime);
        projectDetails.add(addProjectDetails());
        ReportCreator rc = new ReportCreator(totalBuildTime, suiteDetails, projectDetails, suiteFailureDetails);
        String reportSummary = rc.generateSummary();
        String passPercent = Integer.toString(rc.emailSubject());
        String completeReport;
		try {
			completeReport = rc.generateCompleteReport(reportSummary);
			rc.publishReport(completeReport, "target/summary.html");
			String emailSubject = rc.generateMailSubject(passPercent);
			rc.publishReport(emailSubject, "target/mailSubject.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenShot(String methodName) {
        driver = TestBase.driver;
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    
    private String[] addProjectDetails() {
        String[] projectDetails = new String[5];
        projectDetails[0] = "Total";
        projectDetails[1] = Integer.toString(totalTests);
        projectDetails[2] = Integer.toString(totalPassedTests);
        projectDetails[3] = Integer.toString(totalFailedTests);
        projectDetails[4] = Integer.toString(totalSkippedTests);
        return projectDetails;
    }

    

    private String time(long millis) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis - TimeUnit.MINUTES.toMillis(minutes));
        return (minutes + " minutes " + seconds + " seconds");
    }

    

    private String[] addSuiteDetails(ISuite arg0) {
        String[] suiteDetailsArray = new String[5];
        suiteDetailsArray[0] = arg0.getName();
        suiteDetailsArray[1] = Integer.toString(suiteTotalTests);
        suiteDetailsArray[2] = Integer.toString(suitePassedTests);
        suiteDetailsArray[3] = Integer.toString(suiteFailedTests);
        suiteDetailsArray[4] = Integer.toString(suiteSkippedTests);
        return suiteDetailsArray;
    }

    private String[] addSuiteFailureDetails(ITestResult arg0) {
        String[] suiteFailureDetailsArray = new String[2];
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        arg0.getThrowable().printStackTrace(pw);
        suiteFailureDetailsArray[0] = arg0.getTestClass().getName() + "." + arg0.getName();
        suiteFailureDetailsArray[1] = sw.toString();
        return suiteFailureDetailsArray;
    }

    private void addToTotalTestsInSuite() {
        suiteTotalTests = suiteTotalTests + 1;
    }

    private void addToSuccessTestsInSuite() {
        suitePassedTests = suitePassedTests + 1;
    }

    private void addToSkippedTestsInSuite() {
        suiteSkippedTests = suiteSkippedTests + 1;
    }

    private void addToFailedTestsInSuite() {
        suiteFailedTests = suiteFailedTests + 1;
    }

    private void addToSkippedTestsInProject() {
        totalSkippedTests = totalSkippedTests + 1;
    }

    private void addToTotalTestsInProject() {
        totalTests = totalTests + 1;
    }

    private void addToSuccessTestsInProject() {
        totalPassedTests = totalPassedTests + 1;
    }

    private void addToFailedTestsInProject() {
        totalFailedTests = totalFailedTests + 1;
    }
}
