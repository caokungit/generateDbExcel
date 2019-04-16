package com.subGrove;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.subGrove.inter.GetBodyData;
import com.subGrove.inter.GetIndexData;
import com.subGrove.model.ExcelUtil;
import com.subGrove.templet.BodySheetTmp;
import com.subGrove.templet.IndexSheetTmp;
import com.subGrove.vo.IndexSheetVo;
import com.subGrove.vo.TableInfoSheetVo;

public class GenerateDb {

    public static void main(String[] args) throws Exception {

        //获取目录数据
        GetIndexData getIndexData = new GetIndexData();
        IndexSheetVo indexSheetVo = getIndexData.getIndexData();

        //获取表数据
        GetBodyData getBodyData = new GetBodyData();
        List<TableInfoSheetVo> sheetVos = getBodyData.getBodyData();

        createView(indexSheetVo, sheetVos);
    }

    public static void createView(IndexSheetVo indexSheetVo, List<TableInfoSheetVo> sheetVos) {

        ExcelUtil excelUtil = new ExcelUtil();
        try {
            File excel = ExcelUtil.createNewExcel();
            excelUtil.starttrans(excel);
            //创建目录
            Sheet indexSheet = excelUtil.createNewSheet("目录");
            IndexSheetTmp indexSheetTmp = new IndexSheetTmp(indexSheet);
            indexSheetTmp.createSheet(indexSheetVo);

            //创建每个表详情
            int i = 3;
            for (TableInfoSheetVo tableInfo : sheetVos) {

                Sheet bodySheet = excelUtil.createNewSheet(tableInfo.getTableName());
                BodySheetTmp bodySheetTmp = new BodySheetTmp(bodySheet, ++i);
                bodySheetTmp.createSheet(tableInfo);
            }

            excelUtil.commit(excel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
