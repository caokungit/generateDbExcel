package com.subGrove.dto;

import java.util.Map;

public class TableInfoDto {

    private String tableName;
    private String tableCom;
    private Map<String, FieldInfoDto> fieldInfo;

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

    public Map<String, FieldInfoDto> getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(Map<String, FieldInfoDto> fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    @Override
    public String toString() {
        return "TableInfoDto [tableName=" + tableName + ", tableCom=" + tableCom + ", fieldInfo="
                + fieldInfo + "]";
    }
}
