package com.subGrove;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;

import com.subGrove.inter.GetIndexData;
import com.subGrove.model.ExcelUtil;
import com.subGrove.templet.IndexSheetTmp;
import com.subGrove.vo.IndexSheetVo;

public class GenerateDb {

    public static void main(String[] args) throws Exception {

        //获取目录数据
        GetIndexData getIndexData = new GetIndexData();
        IndexSheetVo indexSheetVo = getIndexData.getIndexData();

        createView(indexSheetVo);
    }

    public static void createView(IndexSheetVo indexSheetVo) {

        ExcelUtil excelUtil = new ExcelUtil();
        try {
            File excel = ExcelUtil.createNewExcel();
            excelUtil.starttrans(excel);
            //创建目录
            Sheet indexSheet = excelUtil.createNewSheet("目录");
            IndexSheetTmp indexSheetTmp = new IndexSheetTmp(indexSheet);
            indexSheetTmp.createSheet(indexSheetVo);

            excelUtil.commit(excel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
