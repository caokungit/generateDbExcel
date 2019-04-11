package com.subGrove.templet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import com.subGrove.vo.TableInfoSheetVo;
import com.subGrove.vo.list.FieldInfoList;

public class BodySheetTmp {

    private Sheet sheet;

    private int curRowNum;

    private Map<String, CellStyle> styleMap = new HashMap<>();

    //字段头
    private static final String title[] = { "字段", "字段名", "数据类型", "长度", "必输项", "默认值", "备注" };

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
        //表字段信息
        this.createBody(sheetVo);

        return this.sheet;
    }

    /**初始化样式*/
    private void initCellStyle() {
        //设置列宽
        int[] width = { 20, 30, 10, 5, 5, 10, 20 };
        for (int i = 0; i < width.length; i++) {
            sheet.setColumnWidth(i, width[i] * 256);
        }
    }

    /**创建表信息  */
    private void createTableInfo(TableInfoSheetVo sheetVo) {

        //合并开始列
        int startMer = 1;
        //合并结束列
        int endMer = title.length - 1;

        //表名
        CellRangeAddress tableNameMer = new CellRangeAddress(0, 0, startMer, endMer);
        sheet.addMergedRegion(tableNameMer);
        Row curRow = sheet.createRow(curRowNum++);
        Cell cell1 = curRow.createCell(0);
        cell1.setCellValue("表名");
        cell1.setCellStyle(this.createCellStyle("style1"));
        curRow.createCell(1).setCellValue(sheetVo.getTableName());
        //     表名的样式
        this.createMerCellStyle(tableNameMer, "style1");

        //表描述
        CellRangeAddress tableColMer = new CellRangeAddress(1, 1, startMer, endMer);
        sheet.addMergedRegion(tableColMer);
        curRow = sheet.createRow(curRowNum++);
        Cell cell2 = curRow.createCell(0);
        cell2.setCellValue("表描述");
        cell2.setCellStyle(this.createCellStyle("style1"));
        curRow.createCell(1).setCellValue(sheetVo.getTableCom());
        //      表描述的样式
        this.createMerCellStyle(tableColMer, "style1");
    }

    /**创建索引*/
    private void createIndex(TableInfoSheetVo sheetVo) {

        int priNum = sheetVo.getPrimaryKey().size();
        int uniNum = sheetVo.getUniqueKey().size();
        int norNum = sheetVo.getNorKey().size();

        //合并开始列
        int startMer = 1;
        //合并结束列
        int endMer = startMer + (int) Math.floor((title.length - 1) / 2);
        //合并开始列
        int startMer2 = endMer + 1;
        //合并结束列
        int endMer2 = startMer2 + (int) Math.round((title.length - 1) / 2);

        //主键
        if (priNum > 0) {
            Map<String, String> priMap = sheetVo.getPrimaryKey();
            Set<Entry<String, String>> entrySet = priMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                sheet.addMergedRegion(
                        new CellRangeAddress(curRowNum + 1, curRowNum + 1, startMer, endMer));
                sheet.addMergedRegion(
                        new CellRangeAddress(curRowNum + 1, curRowNum + 1, startMer2, endMer2));
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
                sheet.addMergedRegion(
                        new CellRangeAddress(curRowNum + 1, curRowNum + 1, startMer, endMer));
                sheet.addMergedRegion(
                        new CellRangeAddress(curRowNum + 1, curRowNum + 1, startMer2, endMer2));
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
                sheet.addMergedRegion(
                        new CellRangeAddress(curRowNum + 1, curRowNum + 1, startMer, endMer));
                sheet.addMergedRegion(
                        new CellRangeAddress(curRowNum + 1, curRowNum + 1, startMer2, endMer2));
                Row curRow = sheet.createRow(curRowNum++);
                curRow.createCell(0).setCellValue("索引");
                curRow.createCell(1).setCellValue(entry.getKey());
                curRow.createCell(3).setCellValue(entry.getValue());
            }
        }
    }

    /**表字段信息*/
    private void createBody(TableInfoSheetVo sheetVo) {
        //字段信息
        Map<String, FieldInfoList> fieldMap = sheetVo.getFieldInfo();

        //标题
        CellRangeAddress titleMer = new CellRangeAddress(curRowNum, curRowNum, 0, title.length - 1);
        this.sheet.addMergedRegion(titleMer);
        Row titleRow = this.sheet.createRow(curRowNum++);
        titleRow.createCell(0).setCellValue("字段描述");
        //  标题样式
        this.createMerCellStyle(titleMer, "style3");

        //表头
        Row headRow = this.sheet.createRow(curRowNum++);
        for (int i = 0, length = title.length; i < length; i++) {
            Cell curCell = headRow.createCell(i);
            curCell.setCellValue(title[i]);
            if (i < length - 1) {
                curCell.setCellStyle(this.createCellStyle("style3"));
            } else {
                curCell.setCellStyle(this.createCellStyle("style4"));
            }
        }

        //表数据
        int startDataNo = curRowNum;
        int endDataNo = curRowNum + fieldMap.size();

        Set<Entry<String, FieldInfoList>> entrySet = fieldMap.entrySet();
        for (Entry<String, FieldInfoList> entry : entrySet) {
            Row curRow = this.sheet.createRow(curRowNum++);
            FieldInfoList fieldInfo = entry.getValue();
            String content[] = { fieldInfo.getColName(), fieldInfo.getColCom(),
                    fieldInfo.getDataType(), fieldInfo.getLength(), fieldInfo.getMust(),
                    fieldInfo.getDataDef(), fieldInfo.getRemark() };
            for (int i = 0; i < content.length; i++) {
                Cell curCell = curRow.createCell(i);
                curCell.setCellValue(this.trim(content[i]));
            }
        }

    }

    //单单元格样式
    private CellStyle createCellStyle(String type) {

        CellStyle style = styleMap.get(type);
        if (style != null && styleMap.size() > 1000000) {
            return style;
        } else {
            style = sheet.getWorkbook().createCellStyle();
            this.styleMap.put(type, style);
        }

        switch (type) {
        case "style1"://四周细边框
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_THIN);
            break;
        case "style2"://四周细边框，右边粗边框
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            break;
        case "style3"://上下细边框
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            break;
        case "style4"://上下细边框,右粗边框
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            break;
        default:
        }
        return style;
    }

    //合并单元格样式
    private void createMerCellStyle(CellRangeAddress cell, String type) {

        switch (type) {
        case "style1"://又粗边框其余细边框
            RegionUtil.setBorderTop(CellStyle.BORDER_THIN, cell, sheet, sheet.getWorkbook());
            RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, cell, sheet, sheet.getWorkbook());
            RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, cell, sheet, sheet.getWorkbook());
            RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, cell, sheet, sheet.getWorkbook());
            break;
        case "style3"://上右粗边框其余细边框
            RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, cell, sheet, sheet.getWorkbook());
            RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, cell, sheet, sheet.getWorkbook());
            RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, cell, sheet, sheet.getWorkbook());
            RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, cell, sheet, sheet.getWorkbook());
            break;
        default:
            break;
        }

    }

    private String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

}
