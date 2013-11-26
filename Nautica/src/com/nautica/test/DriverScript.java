package com.nautica.test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import com.nautica.xls.read.XLSReader;
public class DriverScript {
    //declare global variables
    public static Logger APP_LOGS;
    //properties
        public static Properties CONFIG;
        public static Properties OR;
       
    //For Suite.xls
   
    public XLSReader suiteXLS;
    public int currentsuiteID;
    public String currentTestSuite;
   
    //For current test suite
    public XLSReader currentTestSuiteXLS;
    public int currentTestCaseID;
    public String currentTestCaseName;
    public int currentTestStepID;
    public String currentKeyword;
    public Method method[];//returns an array of all methods in the class
    public int currentTestDataSetID;
    public static Method capturescreenShot_method;
   
    public static Keywords keywords;
    public String keyword_execution_result;
    public ArrayList<String> resultSet;
    public String data;
    public String object;
   
   
   
    //constructor at global level
    public DriverScript()
    {
        keywords=new Keywords();
        method=keywords.getClass().getMethods();//extract the methods once
    }
   
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
        //main shuld be in driver script.java
        // TODO Auto-generated method stub
        //FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"/src/com/nautica/config/config.properties");
        FileInputStream fs=new FileInputStream("/Users/igonzalez/Desktop/WORKING_WITH_ECLIPSE/Nautica/bin/com/nautica/config/config.properties");
        CONFIG=new Properties();
        CONFIG.load(fs);
       
        //fs=new FileInputStream(System.getProperty("user.dir")+"/src/com/nautica/config/OR.properties");
       // fs=new FileInputStream("/Users/vpeter/Desktop/eclipse/TestScripts/CoreHybrid/src/com/nautica/config/OR.properties");
        
        fs=new FileInputStream("/Users/igonzalez/Desktop/WORKING_WITH_ECLIPSE/Nautica/bin/com/nautica/config/OR.properties");
        OR=new Properties();
        OR.load(fs);
       
       
        /*System.out.println(CONFIG.getProperty("testSiteURL"));
        System.out.println(CONFIG.getProperty("browserType"));
        System.out.println(OR.getProperty("name"));*/
       
        DriverScript test=new DriverScript();
        test.start();

    }
   
    public void start() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        //initialize the application logs
       
        APP_LOGS= Logger.getLogger("myLogger");
        APP_LOGS.debug("Testing logger: pass");
        APP_LOGS.debug("Properties loaded, starting testing");
       
        //check run mode of test suite
        //check run mode of the test case in suite
        //execute keywords of the test case
        //execute keywords for each data set with run mode "yes"
        APP_LOGS.debug("Initializing Suite");
        suiteXLS= new XLSReader(System.getProperty("user.dir")+"/src/com/nautica/xls/Suite.xlsx");
        //suiteXLS= new XLSReader("/Users/vpeter/Desktop/eclipse/TestScripts/CoreHybrid/src/com/nautica/xls/Suite.xlsx");
       
        //read and print from test suite.xls
        for (currentsuiteID=2;currentsuiteID<=suiteXLS.getRowCount(Constants.TEST_SUITE_SHEET);currentsuiteID++)
        {
           
            APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentsuiteID)+"--"+suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RUNMODE, currentsuiteID));
            //test suite name=test suite xls file containing the test cases
            currentTestSuite=suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentsuiteID);
            //    String runmode=suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, "Runmode", currentsuiteID);
            //check run mode is equal to Y
            if(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RUNMODE, currentsuiteID).equals(Constants.RUNMODE_YES))
            {
                //execute the test cases in the suite with runmode Y
                APP_LOGS.debug("**Executing the  test suite***"+suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentsuiteID));
                //Get the corresponding test suite's xcel file
                currentTestSuiteXLS=new XLSReader(System.getProperty("user.dir")+"/src/com/nautica/xls/"+currentTestSuite+".xlsx");
               
                //iterate through all test cases in the suite
                for(currentTestCaseID=2;currentTestCaseID<=currentTestSuiteXLS.getRowCount("Test Cases"); currentTestCaseID++)
                {
                    APP_LOGS.debug(currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID)+"--"+currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.RUNMODE, currentTestCaseID));
                    currentTestCaseName=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID);
                   
                    resultSet=new ArrayList<String>();
                    if (currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET,Constants.RUNMODE, currentTestCaseID).equals(Constants.RUNMODE_YES))
                    {
                        APP_LOGS.debug("executing the test case ->"+currentTestCaseName);
                       
                        //check if the test data sheet exists for the current test case
                        if (currentTestSuiteXLS.isSheetExist(currentTestCaseName))
                        {
                       
                        //run for each test data set with run mode Y
                                for(currentTestDataSetID=2;currentTestDataSetID<=currentTestSuiteXLS.getRowCount(currentTestCaseName);currentTestDataSetID++)
                                {
                                    APP_LOGS.debug("Iteration number  ->"+(currentTestDataSetID-1));
                                    //check run mode for current data set row
                                    if(currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.RUNMODE, currentTestDataSetID).equals(Constants.RUNMODE_YES))
                                    {//executing keywords of each test case serially
                                        executeKeywords();
                                        createXLSReport();
                                    }
                           }
                        }
                        else
                        {
                            //simply execute the keywords
                            //iterating
                            executeKeywords(); //no data set for the test case
                            createXLSReport();
                           
                        }
                       
                       
                       
                       
                    }
                }
               
            }
           
        }
    }
   
   
    public void executeKeywords() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        //iterating through all keywords
         for (currentTestStepID=2; currentTestStepID <= currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET); currentTestStepID++)
         {
       
            if (currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID, currentTestStepID)))
            {
            //checking tcid
            	data=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.DATA, currentTestStepID);
                object=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.OBJECT, currentTestStepID);
                currentKeyword=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.KEYWORD, currentTestStepID);
                APP_LOGS.debug(currentKeyword);
                //code to execute the keywords
                //reflection API
               
               
               
               
                for (int i=0;i<method.length;i++)
                {
                    //System.out.println(method[i].getName());
                    if(method[i].getName().equals(currentKeyword))
                    {
                        keyword_execution_result=(String) method[i].invoke(keywords,object,data);
                   
                        APP_LOGS.debug(keyword_execution_result);
                        //after executing all keywords, print results for that test case
                        resultSet.add(keyword_execution_result);
                        
                      //capture screen shot according to config file
                        /*
                        
                        if(CONFIG.getProperty("screenshot_everystep").equals("Y"))
                             {
                      //capture screenshots
                            keywords.captureScreenShot(currentTestSuite+"_"+currentTestCaseName+"_TS"+(currentTestStepID-1)+"_"+(currentTestDataSetID-1));
                   
                            }
                        else if(keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y"))
                            {
                      //capturescreenshots
                              keywords.captureScreenShot(currentTestSuite+"_"+currentTestCaseName+"_TS"+(currentTestStepID-1)+"_"+(currentTestDataSetID-1));
                             }
                        */
                    }
                }
               
               
            }
       
         }
    }
   
    public void createXLSReport()
    {
        String colName=Constants.RESULT+(currentTestDataSetID-1); //name the column
        //currentTestSuiteXLS.getCellData(sheetName, colNum, rowNum)
        boolean isColExist=false;
        for(int c=0;c<currentTestSuiteXLS.getColumnCount(Constants.TEST_STEPS_SHEET);c++)
        {
            System.out.println(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, c, 2));
            if(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, c, 1).equals(colName))
            {
                isColExist=true;
                break;
            }
           
           
        }
       
        if(!isColExist)
        	currentTestSuiteXLS.addColumn(Constants.TEST_STEPS_SHEET, colName);
        int index = 0;
        for (int i = 2; i <= currentTestSuiteXLS
                .getRowCount(Constants.TEST_STEPS_SHEET); i++) {

            if (currentTestCaseName.equals(currentTestSuiteXLS.getCellData(
                    Constants.TEST_STEPS_SHEET, Constants.TCID, i))) {
                if (resultSet.size() == 0)
                    currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET,
                            colName, i, Constants.KEYWORD_SKIP);
                else
                    currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET,
                            colName, i, resultSet.get(index));
                index++;
                }
            }
        
           
        if (resultSet.size()==0)//keywords have not been executed if resultset size is zero, then mark result as "Skipped"
        {
        	// skip
            currentTestSuiteXLS.setCellData(currentTestCaseName,
                    Constants.RESULT, currentTestDataSetID,
                    Constants.KEYWORD_SKIP);
            return;
        } else {
            for (int i = 0; i < resultSet.size(); i++) {
                if (!resultSet.get(i).equals(Constants.KEYWORD_PASS)) {
                    currentTestSuiteXLS.setCellData(currentTestCaseName,
                            Constants.RESULT, currentTestDataSetID,
                            resultSet.get(i));
                    return;
                }
            }
        }
        currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT,
                currentTestDataSetID, Constants.KEYWORD_PASS);
    }
 }
