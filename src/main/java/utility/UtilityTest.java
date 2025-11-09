package utility;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class UtilityTest {

    public static final int IMPLICIT_WAIT_TIME = 15;
    public static final int PAGE_WAIT_TIME = 15;

    public static String generateEmailWithTimeStamp()
    {
        Date date = new Date();
        String timestamp= date.toString().replace(" ","_").replace(":","_");
        return "mercyrill"+timestamp+"@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName)
    {
        File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\testdata\\testngdata.xlsx");
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fisExcel = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(fisExcel);
        }catch(Throwable e)
        {e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object [][] data = new Object[rows][cols];

        for(int i=0;i<rows;i++)
        {
            XSSFRow row = sheet.getRow(i+1);

            for(int j=0; j<cols; j++)
            {
                XSSFCell col = row.getCell(j);
                CellType cellType = col.getCellType();

                switch(cellType)
                {
                    case STRING:
                        data[i][j] = col.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int)col.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = col.getBooleanCellValue();
                        break;
                }

            }

        }
        return data;
    }

    public static String captureScreenshot(WebDriver driver, String testName)
    {
        File srcScreenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String testScreenshot = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
        try {
            FileHandler.copy(srcScreenshot, new File(testScreenshot));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return testScreenshot;
    }
}
