package com.subGrove.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.subGrove.dto.FieldInfoDto;
import com.subGrove.dto.TableInfoDto;
import com.subGrove.mapper.TableInfoMapper;
import com.subGrove.utils.MybatisUtil;

public class GetDataBase {

    private static Logger log = LoggerFactory.getLogger(GetDataBase.class);

    public static JSONObject Entire_DataBase;

    static {

        try {
            Entire_DataBase = getAllFieldInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getAllFieldInfo() throws Exception {
        log.info("-----getAllFieldInfo-----");
        //创建SQL工具
        MybatisUtil sqlUtil = new MybatisUtil();
        //创建表信息集合
        JSONObject tableInfoMap = new JSONObject(true);

        TableInfoMapper mapper = sqlUtil.getMapper(TableInfoMapper.class);
        List<FieldInfoDto> list = mapper.selectAllTable();

        for (FieldInfoDto field : list) {
            //获取表名
            String tableName = field.getTableName();
            //拿到表信息
            TableInfoDto tableInfo = tableInfoMap.getObject(tableName, TableInfoDto.class);
            //新建表信息
            if (tableInfo == null) {
                tableInfo = new TableInfoDto();
                tableInfo.setTableName(tableName);
                tableInfo.setTableCom(field.getTableCom());
            }

            //获取字段名
            String fieldName = field.getColName();
            Map<String, FieldInfoDto> fieldInfo = tableInfo.getFieldInfo();
            if (fieldInfo == null) {
                fieldInfo = new HashMap<>();
            }

            fieldInfo.put(fieldName, field);
            tableInfo.setFieldInfo(fieldInfo);
            tableInfoMap.put(tableName, tableInfo);
        }

        return tableInfoMap;
    }

    public static void main(String[] args) {
        try {
            getAllFieldInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
