package com.subGrove.templet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;

import com.subGrove.inter.GetIgnoreImpl;
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
                    curCell.setCellStyle(this.createCellStyle("style1", ""));
                }

                //最下面粗边框
                if (i == headColNum - 1 && j < headColNum - 1) {
                    curCell.setCellStyle(this.createCellStyle("style2", ""));
                }

                //右下角边框
                if (i == headColNum - 1 && j == headColNum - 1) {
                    curCell.setCellStyle(this.createCellStyle("style3", ""));
                }
            }
        }
    }

    /**创建正文 */
    private void createBodyRow(IndexSheetVo sheetVo) {

        Font font = this.sheet.getWorkbook().createFont();
        font.setColor(IndexedColors.BLUE.index);
        font.setUnderline(Font.U_SINGLE);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        List<IndexSheetVoList> list = sheetVo.getList();

        int lastBodyNo = list.size() + curRowNum;

        for (IndexSheetVoList unit : list) {

            //创建行
            Row bodyRow = this.sheet.createRow(curRowNum++);
            //  隐藏行
            if (GetIgnoreImpl.Entire_Ignore.contains(unit.getTableName().toUpperCase())) {
                bodyRow.setZeroHeight(true);
            }

            String bodyRowCont[] = { unit.getTableName(), unit.getTableCom(), unit.getRemark() };
            //创建列单元
            for (int i = 0; i < bodyRowCont.length; i++) {
                Cell cell = bodyRow.createCell(i);
                /**===============  数据操作  ===============**/
                cell.setCellValue(bodyRowCont[i]);
                /**===============  样式操作  ===============**/
                if (i < bodyRowCont.length - 1 && curRowNum < lastBodyNo) {
                    if (i == 0) {
                        cell.setCellStyle(this.createCellStyle("style4", "hypeLink"));
                    } else {
                        cell.setCellStyle(this.createCellStyle("style4", ""));
                    }
                }
                if (i == bodyRowCont.length - 1 && curRowNum < lastBodyNo) {
                    cell.setCellStyle(this.createCellStyle("style5", ""));
                }
                if (i < bodyRowCont.length - 1 && curRowNum == lastBodyNo) {
                    if (i == 0) {
                        cell.setCellStyle(this.createCellStyle("style2", "hypeLink"));
                    } else {
                        cell.setCellStyle(this.createCellStyle("style2", ""));
                    }
                }
                if (i == bodyRowCont.length - 1 && curRowNum == lastBodyNo) {
                    cell.setCellStyle(this.createCellStyle("style3", ""));
                }
                /**===============  创建超链接  ===============**/
                if (i == 0) {
                    CreationHelper createHelper = this.sheet.getWorkbook().getCreationHelper();
                    XSSFHyperlink link = (XSSFHyperlink) createHelper
                            .createHyperlink(Hyperlink.LINK_DOCUMENT);
                    link.setAddress("#" + unit.getTableName() + "!A1");
                    cell.setHyperlink(link);
                }
            }
        }
    }

    private CellStyle createCellStyle(String type, String other) {

        String styleType = type + other;

        CellStyle style = styleMap.get(styleType);
        if (style != null) {
            return style;
        } else {
            style = sheet.getWorkbook().createCellStyle();
            this.styleMap.put(styleType, style);
        }

        this.createCellBorderStyle(style, type);
        this.createCellOtherStyle(style, other);

        return style;
    }

    /**单元格边框*/
    private void createCellBorderStyle(CellStyle style, String type) {

        switch (type) {
        case "style1"://右边粗边框
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            break;
        case "style2"://下边粗边框
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            break;
        case "style3"://右下角粗边框
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            style.setBorderBottom(CellStyle.BORDER_MEDIUM);
            break;
        case "style4"://下边虚边框
            style.setBorderBottom(CellStyle.BORDER_HAIR);
            break;
        case "style5"://下边虚边框,右边粗边框
            style.setBorderBottom(CellStyle.BORDER_HAIR);
            style.setBorderRight(CellStyle.BORDER_MEDIUM);
            break;
        default:
        }
    }

    private void createCellOtherStyle(CellStyle style, String type) {

        switch (type) {
        case "hypeLink":
            Font font = this.sheet.getWorkbook().createFont();
            font.setColor(IndexedColors.BLUE.index);
            font.setUnderline(Font.U_SINGLE);
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style.setFont(font);
            break;
        default:
            break;
        }
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

}
