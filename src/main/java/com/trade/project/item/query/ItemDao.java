package com.trade.project.item.query;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.trade.project.item.application.ItemResponse;
import com.trade.project.item.application.QItemResponse;
import static com.trade.project.item.domain.QItem.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Component
public class ItemDao {
    private final int size = 20;

    private final JPAQueryFactory queryFactory;

    public List<ItemResponse> showMain() {
        return queryFactory.selectDistinct(new QItemResponse(item))
                .from(item)
                .orderBy(item.createdDate.desc())
                .limit(size)
                .fetch();
    }
}
