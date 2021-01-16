package io.kang.bank.persistence.mapper;

import io.kang.bank.persistence.OrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}