package com.subGrove.vo;

import java.util.List;
import java.util.Map;

import com.subGrove.vo.list.FieldInfoList;

public class TableInfoSheetVo {

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
    private List<FieldInfoList> fieldlist;

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

    public List<FieldInfoList> getFieldlist() {
        return fieldlist;
    }

    public void setFieldlist(List<FieldInfoList> fieldlist) {
        this.fieldlist = fieldlist;
    }

}
