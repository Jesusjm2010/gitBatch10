package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    public void onTestSuccess(ITestResult result){

        CommonMethods.takeScreenShot("passed/"+result.getName());

    }
    public void onTestFailure(ITestResult result){

        CommonMethods.takeScreenShot("failed/"+result.getName());

    }

}
