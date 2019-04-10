package com.subGrove.templet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.subGrove.vo.TableInfoSheetVo;

public class BodySheetTmp {

    private Sheet sheet;

    private int curRowNum;

    public BodySheetTmp(Sheet sheet) {
        super();
        this.sheet = sheet;
    }

    public Sheet createSheet(TableInfoSheetVo sheetVo) {

        //初始化样式
        this.initCellStyle();
        //表信息
        this.createTableInfo(sheetVo);
        //表索引
        this.createIndex(sheetVo);

        return this.sheet;
    }

    /**初始化样式*/
    private void initCellStyle() {
        //设置列宽
    }

    /**创建表信息  */
    private void createTableInfo(TableInfoSheetVo sheetVo) {
        //表名
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 5));
        Row curRow = sheet.createRow(curRowNum++);
        curRow.createCell(0).setCellValue("表名");
        curRow.createCell(1).setCellValue(sheetVo.getTableName());

        //表描述
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));
        curRow = sheet.createRow(curRowNum++);
        curRow.createCell(0).setCellValue("表描述");
        curRow.createCell(1).setCellValue(sheetVo.getTableCom());
    }

    /**创建索引*/
    private void createIndex(TableInfoSheetVo sheetVo) {

        int priNum = sheetVo.getPrimaryKey().size();
        int uniNum = sheetVo.getUniqueKey().size();
        int norNum = sheetVo.getNorKey().size();

        //主键
        if (priNum > 0) {
            Map<String, String> priMap = sheetVo.getPrimaryKey();
            Set<Entry<String, String>> entrySet = priMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                sheet.addMergedRegion(new CellRangeAddress(curRowNum + 1, curRowNum + 1, 1, 3));
                sheet.addMergedRegion(new CellRangeAddress(curRowNum + 1, curRowNum + 1, 4, 5));
                Row curRow = sheet.createRow(curRowNum++);
                curRow.createCell(0).setCellValue("主键");
                curRow.createCell(1).setCellValue(entry.getKey());
                curRow.createCell(3).setCellValue(entry.getValue());
            }
        }

        //唯一索引
        if (uniNum > 0) {
            Map<String, String> priMap = sheetVo.getUniqueKey();
            Set<Entry<String, String>> entrySet = priMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                sheet.addMergedRegion(new CellRangeAddress(curRowNum + 1, curRowNum + 1, 1, 3));
                sheet.addMergedRegion(new CellRangeAddress(curRowNum + 1, curRowNum + 1, 4, 5));
                Row curRow = sheet.createRow(curRowNum++);
                curRow.createCell(0).setCellValue("唯一索引");
                curRow.createCell(1).setCellValue(entry.getKey());
                curRow.createCell(3).setCellValue(entry.getValue());
            }
        }

        //索引
        if (norNum > 0) {
            Map<String, String> priMap = sheetVo.getNorKey();
            Set<Entry<String, String>> entrySet = priMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                sheet.addMergedRegion(new CellRangeAddress(curRowNum + 1, curRowNum + 1, 1, 3));
                sheet.addMergedRegion(new CellRangeAddress(curRowNum + 1, curRowNum + 1, 4, 5));
                Row curRow = sheet.createRow(curRowNum++);
                curRow.createCell(0).setCellValue("索引");
                curRow.createCell(1).setCellValue(entry.getKey());
                curRow.createCell(3).setCellValue(entry.getValue());
            }
        }
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

}
