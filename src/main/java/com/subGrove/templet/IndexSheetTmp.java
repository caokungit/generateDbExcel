package com.subGrove.templet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.subGrove.vo.IndexSheetVo;
import com.subGrove.vo.list.IndexSheetVoList;

public class IndexSheetTmp {

    private Sheet sheet;

    private int curRowNum;

    private Map<String, CellStyle> styleMap = new HashMap<>();

    public IndexSheetTmp(Sheet sheet) {
        super();
        this.sheet = sheet;
    }

    public Sheet createSheet(IndexSheetVo sheetVo) {

        //初始化样式
        this.initCellStyle();
        //创建目录头
        this.createHeadRow();
        //创建目录正文
        this.createBodyRow(sheetVo);

        return this.sheet;
    }

    /**初始化样式*/
    private void initCellStyle() {

        //设置列宽
        sheet.setColumnWidth(0, 256 * 30);
        sheet.setColumnWidth(1, 256 * 40);
        sheet.setColumnWidth(2, 256 * 40);

    }

    /**创建头数据*/
    private void createHeadRow() {

        //创建合并单元格
        CellRangeAddress headRange = new CellRangeAddress(0, 1, 0, 2);
        sheet.addMergedRegion(headRange);

        int headRowNum = 3;
        int headColNum = 3;

        //初始化数据
        String title = "数据库目录";
        String colName[] = { "表名", "表描述", "备注" };

        for (int i = 0; i < headRowNum; i++) {
            //创建行
            Row curRow = sheet.createRow(curRowNum++);
            //创建列
            for (int j = 0; j < headColNum; j++) {
                Cell curCell = curRow.createCell(j);

                /**===============  数据操作  ===============**/
                if (i == 0 && j == 0) {
                    curCell.setCellValue(title);
                }
                if (i == 2) {
                    curCell.setCellValue(colName[j]);
                }

                /**===============  样式操作  ===============**/
                //最右边粗边框
                if (j == headColNum - 1 && i < headColNum - 1) {
                    curCell.setCellStyle(this.createCellStyle("style1"));
                }

                //最下面粗边框
                if (i == headColNum - 1 && j < headColNum - 1) {
                    curCell.setCellStyle(this.createCellStyle("style2"));
                }

                //右下角边框
                if (i == headColNum - 1 && j == headColNum - 1) {
                    curCell.setCellStyle(this.createCellStyle("style3"));
                }
            }
        }
    }

    /**创建正文 */
    private void createBodyRow(IndexSheetVo sheetVo) {

        List<IndexSheetVoList> list = sheetVo.getList();
        for (IndexSheetVoList unit : list) {

            //创建行
            Row bodyRow = this.sheet.createRow(curRowNum++);
            String bodyRowCont[] = { unit.getTableName(), unit.getTableCom(), unit.getRemark() };
            //创建列单元
            for (int i = 0; i < bodyRowCont.length; i++) {
                Cell cell = bodyRow.createCell(i);
                cell.setCellValue(bodyRowCont[i]);
            }
        }

    }

    private CellStyle createCellStyle(String type) {

        CellStyle style = styleMap.get(type);
        if (style != null) {
            return style;
        } else {
            style = sheet.getWorkbook().createCellStyle();
            this.styleMap.put(type, style);
        }

        switch (type) {
        case "style1":
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            break;
        case "style2":
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            break;
        case "style3":
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            break;
        default:
        }
        return style;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

}
