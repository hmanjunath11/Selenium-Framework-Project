package emailReporter;

import java.util.List;

public class TableCreator {

    private String fillUpHeadDataForSummarryTable() {
        String tableData = "<table style=\"font-size: 12px;width: 100%;border-spacing: 2px;border-color:grey\">\r\n" +
        "				<thead style=\"width: 100%;border-spacing: 2px;border-color:grey\">\r\n" +
        "					<tr>\r\n" +
        "						<th colspan=\"5\" style=\"font-size: 14px;border: 1px #6ea1cc !important;text-align: center; padding: 8px;background-color: #508abb;color: #fff;\">SCENARIOS</th>\r\n" +
        "					</tr>\r\n" +
        "					<tr>";
        for(int i = 0; i < getHeadValuesForSummarryTable().length; i++) {
            tableData = tableData + "<th style=\"font-size: 14px;border: 1px #6ea1cc !important;text-align: left; padding: 8px;background-color: #508abb;color: #fff;\">"+getHeadValuesForSummarryTable()[i]+"</th>";
        }
        tableData = tableData + "</tr></thead><tbody style=\"font-size: 12px;\">";
        return tableData;
    }

    private String[] getHeadValuesForSummarryTable() {
        String[] headValues = new String[5];
        headValues[0] = "Features";
        headValues[1] = "Total";
        headValues[2] = "Passed";
        headValues[3] = "Failed";
        headValues[4] = "Skipped";
        return headValues;
    }

    private String fillUpSuiteData(List<String[]> suiteDetails) {
        String tableData = new String();
        for(int i = 0; i < suiteDetails.size(); i++) {
            tableData = tableData + "<tr style=\"width: 100%;border-bottom:1px solid #efefef;border-top:1px solid #ececec;background-color:#f4fbff;\">";
            for(int j = 0; j < suiteDetails.get(i).length; j++) {
                tableData = tableData + "<td style=\"border-collapse:collapse;text-align: left; padding: 8px\">" + suiteDetails.get(i)[j] + "</td>";
            }
            tableData = tableData + "</tr>";
        }
        tableData = tableData + "</tbody>";
        return tableData;
    }

    private String fillUpFootData(List<String[]> projectDetails) {
        String footData = "<tfoot><tr style=\"width: 100%;border-spacing: 2px;background-color:#fcffc9 !important\">";
        for(int i = 0; i < projectDetails.get(0).length; i++) {
            footData = footData + "<td style=\"border-collapse:collapse;text-align: left; padding: 8px\">" + projectDetails.get(0)[i] + "</td>";
        }
        footData = footData + "</tr></tfoot></table>";
        return footData;
    }

    public String generateFeatureTable(List<String[]> suiteDetails, List<String[]> projectDetails) {
        return (fillUpHeadDataForSummarryTable() + fillUpSuiteData(suiteDetails) + fillUpFootData(projectDetails));
    }

    private String fillUpHeadDataForFailureTable() {
        String tableData = "</blockquote><h4>FAILURE SUMMARRY</h4><blockquote><table style=\"font-size: 12px;width: 100%;border-spacing: 2px;border-color:grey\">\r\n" +
        "				<thead style=\"width: 100%;border-spacing: 2px;border-color:grey\">\r\n" +
        "					<tr>\r\n" +
        "						<th colspan=\"5\" style=\"font-size: 14px;border: 1px #6ea1cc !important;text-align: center; padding: 8px;background-color: #508abb;color: #fff;\">SCENARIOS</th>\r\n" +
        "					</tr>\r\n" +
        "					<tr>";
        for(int i = 0; i < getHeadValuesForFailureTable().length; i++) {
            tableData = tableData + "<th style=\"font-size: 14px;border: 1px #6ea1cc !important;text-align: left; padding: 8px;background-color: #508abb;color: #fff;\">"+getHeadValuesForFailureTable()[i]+"</th>";
        }
        tableData = tableData + "</tr></thead><tbody style=\"font-size: 12px;\">";
        return tableData;
    }

    private String[] getHeadValuesForFailureTable() {
        String[] headValues = new String[2];
        headValues[0] = "Method";
        headValues[1] = "Failure Information";
        return headValues;
    }

    private String fillUpFailureData(List<String[]> suiteFailureDetails) {
        String tableData = new String();
        for(int i = 0; i < suiteFailureDetails.size(); i++) {
            tableData = tableData + "<tr style=\"width: 100%;border-bottom:1px solid #efefef;border-top:1px solid #ececec;background-color:#f4fbff;\">";
            for(int j = 0; j < suiteFailureDetails.get(i).length; j++) {
                tableData = tableData + "<td style=\"border-collapse:collapse;text-align: left; padding: 8px\">" + suiteFailureDetails.get(i)[j] + "</td>";
            }
            tableData = tableData + "</tr>";
        }
        tableData = tableData + "</tbody></table>";
        return tableData;
    }

    public String generateFailureTable(List<String[]> suiteFailureDetails) {
        String tableData = fillUpHeadDataForFailureTable() + fillUpFailureData(suiteFailureDetails);
        return tableData;
    }
}