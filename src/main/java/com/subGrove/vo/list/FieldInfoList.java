package com.subGrove.vo.list;

public class FieldInfoList {
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
    private String must;

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

    public String getMust() {
        return must;
    }

    public void setMust(String must) {
        this.must = must;
    }

}
