package com.trade.project.item.query;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.trade.project.item.application.ItemResponse;
import com.trade.project.item.application.QItemResponse;
import static com.trade.project.item.domain.QItem.*;

import com.trade.project.item.domain.enums.Category;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    public Page<ItemResponse> showPageByCategory(Pageable pageable, String category) {
        QueryResults<ItemResponse> result = queryFactory.selectDistinct(new QItemResponse(item))
                .from(item)
                .orderBy(item.createdDate.desc())
                .where(equalCategory(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    public Page<ItemResponse> showPageByKeyword(Pageable pageable,String keyword) {
        QueryResults<ItemResponse> result = queryFactory.selectDistinct(new QItemResponse(item))
                .from(item)
                .orderBy(item.createdDate.desc())
                .where(containsTitle(keyword).or(containsTradeArea(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    private BooleanExpression equalCategory(String category){
        return item.category.eq(Category.fromString(category));
    }

    private BooleanExpression containsTitle(String keyword) {
        return item.title.contains(keyword);
    }

    private BooleanExpression containsTradeArea(String keyword) {
        return item.tradeArea.contains(keyword);
    }

}
