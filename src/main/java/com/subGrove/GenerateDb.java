package com.subGrove;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.alibaba.fastjson.JSONObject;
import com.subGrove.dto.FieldInfoDto;
import com.subGrove.dto.TableInfoDto;
import com.subGrove.model.ExcelUtil;
import com.subGrove.model.GetDataBase;

public class GenerateDb {

    public static void main(String[] args) throws Exception {

        JSONObject allDataBase = GetDataBase.getAllFieldInfo();

        File excel = ExcelUtil.createNewExcel();
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.starttrans(excel);
        //目录表
        Sheet indexSheet = excelUtil.createNewSheet("表目录");

        /*设置目录表样式*/
        {
            //设置列宽
            indexSheet.setColumnWidth(0, 256 * 30);
            indexSheet.setColumnWidth(1, 256 * 40);
        }

        int rowNum = 1;
        //根据表创建目录
        Set<Entry<String, Object>> entrySet = allDataBase.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            //获取表信息
            String tableName = entry.getKey();
            TableInfoDto tableInfo = (TableInfoDto) entry.getValue();

            //写入目录内容
            Row curRow = indexSheet.createRow(rowNum++);
            String content[] = { tableName, tableInfo.getTableCom() };
            for (int i = 0; i < content.length; i++) {
                Cell curCell = curRow.createCell(i);
                curCell.setCellValue(content[i]);
            }

            //创建期对应的详细信息
            createDetail(tableInfo, excelUtil);

            //创建超链接
            curRow.getCell(0).setHyperlink(excelUtil.createLink("#" + tableName + "!A1"));
        }

        excelUtil.commit(excel);
    }

    private static void createDetail(TableInfoDto tableInfo, ExcelUtil excelUtil) {
        //创建表字段 信息工作簿
        Sheet sheet = excelUtil.createNewSheet(tableInfo.getTableName());
        int rowNum = 1;
        /*设置目录表样式*/
        {
            //设置列宽
            sheet.setColumnWidth(0, 256 * 30);
            sheet.setColumnWidth(1, 256 * 25);
            sheet.setColumnWidth(2, 256 * 16);
            sheet.setColumnWidth(3, 256 * 5);
        }

        //遍历字段信息
        Map<String, FieldInfoDto> fieldMap = tableInfo.getFieldInfo();
        Set<Entry<String, FieldInfoDto>> entrySet = fieldMap.entrySet();
        for (Entry<String, FieldInfoDto> entry : entrySet) {
            //获取字段信息
            FieldInfoDto fieldInfo = entry.getValue();
            Row row = sheet.createRow(rowNum++);

            //初始化需要写入信息
            String content[] = { fieldInfo.getColName(), fieldInfo.getColCom(),
                    fieldInfo.getDataType(), fieldInfo.getNullAble() };
            //循环写入
            for (int i = 0; i < content.length; i++) {
                Cell curCell = row.createCell(i);
                curCell.setCellValue(content[i]);
            }
        }

    }
}
