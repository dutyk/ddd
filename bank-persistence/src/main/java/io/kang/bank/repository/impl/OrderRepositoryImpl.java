package io.kang.bank.repository.impl;

import io.kang.bank.Repository.OrderRepository;
import io.kang.bank.Repository.core.DbRepositorySupport;
import io.kang.bank.converter.LineItemDataConverter;
import io.kang.bank.converter.OrderDataConverter;
import io.kang.bank.domain.entity.LineItem;
import io.kang.bank.domain.entity.Order;
import io.kang.bank.persistence.LineItemDO;
import io.kang.bank.persistence.OrderDO;
import io.kang.bank.persistence.mapper.LineItemDOMapper;
import io.kang.bank.persistence.mapper.OrderDOMapper;
import io.kang.bank.types.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl extends DbRepositorySupport<Order, AggregateId> implements OrderRepository {
    @Autowired
    OrderDOMapper orderDAO;

    @Autowired
    LineItemDOMapper lineItemDAO;

    @Autowired
    OrderDataConverter orderConverter;

    @Autowired
    LineItemDataConverter lineItemConverter;

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public Long countItemList(Order order) {
        return 0L;
    }


    @Override
    protected void onInsert(Order aggregate) {
        OrderDO orderDO = orderConverter.toData(aggregate);
        orderDAO.insert(orderDO);

        aggregate.setId(new AggregateId(orderDO.getId()));

        List<LineItem> items = aggregate.getLineItems();

        for(LineItem item: items) {
            LineItemDO lineItemDO = lineItemConverter.toData(item);
            lineItemDAO.insert(lineItemDO);
        }
    }

    @Override
    protected Order onSelect(AggregateId id) {
        OrderDO orderDO = orderDAO.selectByPrimaryKey(id.getValue());

        return orderConverter.fromData(orderDO);
    }

    @Override
    protected void onUpdate(Order aggregate, EntityDiff diff) {
        if (diff.isSelfModified()) {
            OrderDO orderDO = orderConverter.toData(aggregate);
            orderDAO.updateByPrimaryKeySelective(orderDO);
        }

        Diff lineItemDiffs = diff.getDiff("lineItems");
        if (lineItemDiffs instanceof ListDiff) {
            List<Diff> diffList = ((ListDiff) lineItemDiffs).getDiffs();
            for (Diff itemDiff : diffList) {
                if (itemDiff.getType() == DiffType.Removed) {
                    LineItem line = (LineItem) itemDiff.getOldValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    lineItemDAO.deleteByPrimaryKey(lineDO.getId());
                }
                if (itemDiff.getType() == DiffType.Added) {
                    LineItem line = (LineItem) itemDiff.getNewValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    lineItemDAO.insert(lineDO);
                }
                if (itemDiff.getType() == DiffType.Modified) {
                    LineItem line = (LineItem) itemDiff.getNewValue();
                    LineItemDO lineDO = lineItemConverter.toData(line);
                    lineItemDAO.updateByPrimaryKeySelective(lineDO);
                }
            }
        }
    }

    @Override
    protected void onDelete(Order aggregate) {
        OrderDO orderDO = orderConverter.toData(aggregate);
        orderDAO.deleteByPrimaryKey(orderDO.getId());
    }
}