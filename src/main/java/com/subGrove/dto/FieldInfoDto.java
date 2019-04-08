package com.subGrove.dto;

public class FieldInfoDto {
    private String tableName;
    private String tableCom;
    private String colName;
    private String colCom;
    private String dataType;
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

    public String getNullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    @Override
    public String toString() {
        return "TableInfoDto [tableName=" + tableName + ", tableCom=" + tableCom + ", colName="
                + colName + ", colCom=" + colCom + ", dataType=" + dataType + ", nullAble="
                + nullAble + "]";
    }

}
