package com.subGrove.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import com.subGrove.dto.IndexInfoDto;
import com.subGrove.vo.list.FieldInfoList;

public class TableInfoSheetVo {

    //表名
    private String tableName;
    //表描述
    private String tableCom;
    //索引
    private Map<String, IndexInfoDto> userIndex = new LinkedHashMap<>();
    //字段信息
    private Map<String, FieldInfoList> fieldInfo;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCom() {
        return tableCom;
    }

    public void setTableCom(String tableCom) {
        this.tableCom = tableCom;
    }

    public Map<String, IndexInfoDto> getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(Map<String, IndexInfoDto> userIndex) {
        this.userIndex = userIndex;
    }

    public Map<String, FieldInfoList> getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(Map<String, FieldInfoList> fieldInfo) {
        this.fieldInfo = fieldInfo;
    }
}
