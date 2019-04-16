package com.subGrove.mapper;

import java.util.List;

import com.subGrove.dto.FieldInfoDto;
import com.subGrove.dto.IndexInfoDto;

public interface TableInfoMapper {
    List<FieldInfoDto> selectAllTable();

    List<IndexInfoDto> selectAllIndex();
}
