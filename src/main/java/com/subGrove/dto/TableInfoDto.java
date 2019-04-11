package com.subGrove.dto;

import java.util.Map;

public class TableInfoDto {

    //表名
    private String tableName;
    //表描述
    private String tableCom;
    //主键
    private Map<String, String> primaryKey;
    //唯一索引
    private Map<String, String> uniqueKey;
    //普通索引
    private Map<String, String> norKey;
    //字段信息
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

    public Map<String, String> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Map<String, String> primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Map<String, String> getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(Map<String, String> uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Map<String, String> getNorKey() {
        return norKey;
    }

    public void setNorKey(Map<String, String> norKey) {
        this.norKey = norKey;
    }

    public Map<String, FieldInfoDto> getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(Map<String, FieldInfoDto> fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

}
