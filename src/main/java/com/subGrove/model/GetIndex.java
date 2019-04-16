package com.subGrove.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.subGrove.dto.IndexInfoDto;
import com.subGrove.mapper.TableInfoMapper;
import com.subGrove.utils.MybatisUtil;

public class GetIndex {

    public static Map<String, List<IndexInfoDto>> Entire_Index;

    static {
        try {
            Entire_Index = new LinkedHashMap<>(100);
            getAllIndexInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllIndexInfo() throws Exception {

        MybatisUtil sqlUtil = new MybatisUtil();

        TableInfoMapper mapper = sqlUtil.getMapper(TableInfoMapper.class);

        List<IndexInfoDto> list = mapper.selectAllIndex();

        for (IndexInfoDto unit : list) {

            String tableName = unit.getTableName();
            List<IndexInfoDto> tableIndexs = Entire_Index.get(tableName);
            if (tableIndexs == null) {
                tableIndexs = new ArrayList<>();
            }
            tableIndexs.add(unit);
            Entire_Index.put(tableName, tableIndexs);
        }
    }
}
