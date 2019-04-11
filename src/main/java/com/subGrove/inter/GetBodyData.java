package com.subGrove.inter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.subGrove.dto.FieldInfoDto;
import com.subGrove.dto.TableInfoDto;
import com.subGrove.model.GetDataBase;
import com.subGrove.vo.TableInfoSheetVo;
import com.subGrove.vo.list.FieldInfoList;

/**
 * 类名称 : GetBodyData
 * 类描述 : 
 * 创建人 : caokun
 * 创建日期 : 2019年4月11日
 */
public class GetBodyData {

    public List<TableInfoSheetVo> getBodyData() {

        List<TableInfoSheetVo> list = new ArrayList<>();

        Set<Entry<String, Object>> enSet = GetDataBase.Entire_DataBase.entrySet();
        for (Entry<String, Object> entry : enSet) {
            TableInfoSheetVo sheetVo = new TableInfoSheetVo();
            TableInfoDto tableInfoDto = (TableInfoDto) entry.getValue();
            sheetVo.setTableName(tableInfoDto.getTableName());
            sheetVo.setTableCom(tableInfoDto.getTableCom());

            //字段集合
            Map<String, FieldInfoList> fieldView = new LinkedHashMap<>();
            Set<Entry<String, FieldInfoDto>> fieldEnSet = tableInfoDto.getFieldInfo().entrySet();
            for (Entry<String, FieldInfoDto> fieldSet : fieldEnSet) {

                //数据库查询出值
                FieldInfoDto fieldUnit = fieldSet.getValue();
                //视图层需要值
                FieldInfoList fieldInfoView = new FieldInfoList();

                fieldInfoView.setColName(fieldUnit.getColName());
                fieldInfoView.setDataType(fieldUnit.getDataType());
                fieldInfoView.setLength(fieldUnit.getLength());
                fieldInfoView.setDataDef(fieldUnit.getDataDef());

                //字段描述拆分
                String colComTmp = fieldUnit.getColCom();
                colComTmp = colComTmp == null?"":colComTmp;
                if (colComTmp.contains(":") || colComTmp.contains("：")) {

                    int signNo = colComTmp.indexOf(":") < 0 ? colComTmp.indexOf("：")
                            : colComTmp.indexOf(":");
                    fieldInfoView.setColCom(colComTmp.substring(0, signNo));
                    fieldInfoView.setRemark(colComTmp.substring(signNo+1));
                } else {
                    fieldInfoView.setColCom(colComTmp);
                    fieldInfoView.setRemark("");
                }

                //非空转换
                String nullAble = fieldUnit.getNullAble();
                if ("N".equals(nullAble)) {
                    fieldInfoView.setMust("Y");
                }

                fieldView.put(fieldSet.getKey(), fieldInfoView);
            }

            sheetVo.setFieldInfo(fieldView);
            list.add(sheetVo);
        }

        return list;
    }
}
