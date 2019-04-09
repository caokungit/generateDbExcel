package com.subGrove.templet;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.subGrove.vo.IndexSheetVo;
import com.subGrove.vo.list.IndexSheetVoList;

public class BodySheetTmp {

    private Sheet sheet;

    private int curRow;

    public BodySheetTmp(Sheet sheet) {
        super();
        this.sheet = sheet;
    }

    public Sheet createSheet(IndexSheetVo sheetVo) {

        this.createNorColStyle();
        //首行
        this.createHeadRow();

        //
        this.createBodyRow(sheetVo);

        return this.sheet;
    }

    /**创建通用列样式*/
    private void createNorColStyle() {
        //设置列宽
        sheet.setColumnWidth(0, 256 * 30);
        sheet.setColumnWidth(1, 256 * 40);
        sheet.setColumnWidth(2, 256 * 40);
    }

    /**创建头数据*/
    private void createHeadRow() {
        //首行
        Row firstRow = sheet.createRow(curRow++);
        String firstRowCont[] = { "表名", "表描述", "备注" };

        //列-1
        for (int i = 0; i < firstRowCont.length; i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(firstRowCont[i]);
        }
    }

    /**创建正文 */
    private void createBodyRow(IndexSheetVo sheetVo) {

        List<IndexSheetVoList> list = sheetVo.getList();
        for (IndexSheetVoList unit : list) {

            //创建行
            Row bodyRow = this.sheet.createRow(curRow++);
            String bodyRowCont[] = { unit.getTableName(), unit.getTableCom(), unit.getRemark() };
            //创建列单元
            for (int i = 0; i < bodyRowCont.length; i++) {
                Cell cell = bodyRow.createCell(i);
                cell.setCellValue(bodyRowCont[i]);
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
