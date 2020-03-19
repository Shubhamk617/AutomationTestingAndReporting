package createPdf;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

public class SeleniumPDFReportWithIReporter extends CreatePDFReport implements IReporter {
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		for (ISuite ist : suites) {
			try {
				openPdfPath();
				addMetaData("SHUBHAM KUMAR", "AUTOMATION PRACTICE USING SELENIUM AND TESTNG",
						"CHECKS FUNCTIONALITIES ON A SHOPPING WEBSIT");
				Map<String, ISuiteResult> resultSuiteMap = ist.getResults();
				Set<String> key = resultSuiteMap.keySet();
				for (String k : key) {
					ITestContext cntx = resultSuiteMap.get(k).getTestContext();
					System.out.println("Suite Name- " + cntx.getName() + "\n Report Directory- "
							+ cntx.getOutputDirectory() + "\n Test Suite Name- " + cntx.getSuite().getName()
							+ "\n Start Date and Time of Execution- " + cntx.getStartDate()
							+ "\n End Date and Time of Execution- " + cntx.getEndDate()
							+ "\n=====================================================================");
					addParagraph("Suite Name- " + cntx.getName() + "\n Report Directory- " + cntx.getOutputDirectory()
							+ "\n Test Suite Name- " + cntx.getSuite().getName()
							+ "\n Start Date and Time of Execution- " + cntx.getStartDate()
							+ "\n End Date and Time of Execution- " + cntx.getEndDate()
							+ "\n=====================================================================");

					IResultMap failedTest = cntx.getFailedTests();
					Collection<ITestNGMethod> failedMethods = failedTest.getAllMethods();
					System.out.println("------Failed Test Case-----");
					for (ITestNGMethod imd : failedMethods) {

						System.out.println(
								"Test Case Name- " + imd.getMethodName() + "\n Description- " + imd.getDescription()
										+ "\n Priority- " + imd.getPriority() + "\n Date- " + new Date(imd.getDate())
										+ "\n=====================================================================");

						addParagraph(
								"Test Case Name- " + imd.getMethodName() + "\n Description- " + imd.getDescription()
										+ "\n Priority- " + imd.getPriority() + "\n Date- " + new Date(imd.getDate())
										+ "\n=====================================================================");
					}

					IResultMap passedTest = cntx.getPassedTests();
					Collection<ITestNGMethod> passedMethods = passedTest.getAllMethods();
					System.out.println("------Passed Test Case-----");
					for (ITestNGMethod imd1 : passedMethods) {

						System.out.println(
								"Test Case Name- " + imd1.getMethodName() + "\n Description- " + imd1.getDescription()
										+ "\n Priority- " + imd1.getPriority() + "\n Date- " + new Date(imd1.getDate())
										+ "\n=====================================================================");

						addParagraph(
								"Test Case Name- " + imd1.getMethodName() + "\n Description- " + imd1.getDescription()
										+ "\n Priority- " + imd1.getPriority() + "\n Date- " + new Date(imd1.getDate())
										+ "\n=====================================================================");
					}
				}
				closePdf();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
