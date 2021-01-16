package io.kang.bank.persistence.mapper;

import io.kang.bank.persistence.LineItemDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LineItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LineItemDO record);

    int insertSelective(LineItemDO record);

    LineItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LineItemDO record);

    int updateByPrimaryKey(LineItemDO record);
}