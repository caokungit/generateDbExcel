package com.subGrove.dto;

/**
 * 类名称 : IndexInfoDto
 * 类描述 : 索引信息
 * 创建人 : caokun
 * 创建日期 : 2019年4月16日
 */
public class IndexInfoDto {

    private String indexName;
    private String tableName;
    private String indexCol;
    private String type;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIndexCol() {
        return indexCol;
    }

    public void setIndexCol(String indexCol) {
        this.indexCol = indexCol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
