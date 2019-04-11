package com.subGrove.dto;

public class FieldInfoDto {

    //所属表名
    private String tableName;
    //所属表描述
    private String tableCom;
    //字段名
    private String colName;
    //字段描述
    private String colCom;
    //字段类型
    private String dataType;
    //字段长度
    private String length;
    //默认值
    private String dataDef;
    //必输项
    private String nullAble;

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

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColCom() {
        return colCom;
    }

    public void setColCom(String colCom) {
        this.colCom = colCom;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDataDef() {
        return dataDef;
    }

    public void setDataDef(String dataDef) {
        this.dataDef = dataDef;
    }

    public String getNullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

}
