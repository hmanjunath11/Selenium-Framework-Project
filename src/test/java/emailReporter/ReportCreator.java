package emailReporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ReportCreator {

    private String totalBuildTime;
    private List<String[]> suiteDetails;
    private List<String[]> suiteFailureDetails;
    private List<String[]> projectDetails;

    public ReportCreator(String totalBuildTime, List<String[]> suiteDetails, List<String[]> projectDetails,
       List<String[]> suiteFailureDetails) {
        this.totalBuildTime = totalBuildTime;
        this.suiteDetails = suiteDetails;
        this.suiteFailureDetails = suiteFailureDetails;
        this.projectDetails = projectDetails;
    }

    public String generateSummary() {
        TableCreator tb = new TableCreator();
        String summarryData = tb.generateFeatureTable(suiteDetails, projectDetails);
        if(suiteFailureDetails.size() != 0)
            summarryData = summarryData + tb.generateFailureTable(suiteFailureDetails);
        return summarryData;
    }

    public String generateCompleteReport(String reportSummary) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/test/resources/build-summary.txt"), StandardCharsets.UTF_8.name());
        String summarry = scanner.useDelimiter("\\A").next();
        scanner.close();
        String buildURL = System.getenv().containsKey("BUILD_URL") ? System.getenv("BUILD_URL") : "LOCAL_BUILD";
        summarry = summarry.replace("{{BUILD_URL}}", buildURL);
        summarry = summarry.replace("{{DURATION}}", totalBuildTime);
        summarry = summarry.replace("{{SUMMARY}}",reportSummary);
        return summarry;
    }

    public String generateMailSubject(String subject) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/test/resources/mail-subject.txt"), StandardCharsets.UTF_8.name());
        String summary = scanner.useDelimiter("\\A").next();
        scanner.close();
        summary = summary.replace("{{SUBJECT}}",subject);
        return summary;
    }

    public void publishReport(String reportSummary, String fileDetails) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileDetails, "UTF-8");
        writer.println(reportSummary);
        writer.close();
    }

    public int emailSubject(){
        int totalTest= Integer.parseInt(projectDetails.get(0)[1]);
        int passedTest= Integer.parseInt(projectDetails.get(0)[2]);
        int passPercentage = ((passedTest*100)/totalTest);
        return passPercentage;
    }
}