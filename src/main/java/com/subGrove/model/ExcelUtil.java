package com.subGrove.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    //当前工作表
    private Workbook workbook;
    //当前工作薄
    private Sheet sheet;

    private CreationHelper createHelper;

    //工作表对应的流
    private InputStream workIs;

    public static File createNewExcel() throws IOException {

        File file = new File("DataBaseCom.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }

        //创建Excel文件对象
        Workbook wb = new XSSFWorkbook();
        //输出Excel文件
        try (FileOutputStream output = new FileOutputStream(file)) {
            wb.write(output);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public boolean starttrans(File excel) {

        try {
            workIs = new FileInputStream(excel);
            workbook = new XSSFWorkbook(workIs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**创建新工作薄*/
    public Sheet createNewSheet(String sheetName) {

        sheet = workbook.createSheet(sheetName);

        return sheet;
    }

    public void commit(File file) {
        try {
            workbook.write(new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XSSFHyperlink createLink(String address) {

        if (createHelper == null) {
            createHelper = workbook.getCreationHelper();
        }

        XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
        link.setAddress(address);
        return link;

    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public static void main(String[] args) throws IOException {
        createNewExcel();
    }
}
