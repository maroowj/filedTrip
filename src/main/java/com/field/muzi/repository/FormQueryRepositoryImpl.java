package com.field.muzi.repository;

import com.field.muzi.domain.entity.DiyOptionsEntity;
import com.field.muzi.domain.entity.FormEntity;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.admin.dto.form.AdminFormListRequest;
import com.field.muzi.web.admin.dto.form.AdminFormListResponse;
import com.field.muzi.web.user.dto.diy.DiyListResponse;
import com.field.muzi.web.user.dto.form.FormListResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import static com.field.muzi.domain.entity.QDiyEntity.diyEntity;
import static com.field.muzi.domain.entity.QFormEntity.formEntity;

@Repository
public class FormQueryRepositoryImpl extends QuerydslRepositorySupport implements FormQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FormQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(FormEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<FormListResponse> formListByMember(Pageable pageable, MemberEntity member) {
        JPAQuery<FormListResponse> query = queryFactory.select(Projections.constructor(FormListResponse.class,
                        formEntity.formSeq,
                        formEntity.title,
                        formEntity.category,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", formEntity.createDate),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", formEntity.confirmedDate),
                        formEntity.answer
                ))
                .from(
                        formEntity
                )
                .where(formEntity.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(formEntity.getType(), formEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<FormListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<AdminFormListResponse> adminFormList(Pageable pageable, AdminFormListRequest request) {
        JPAQuery<AdminFormListResponse> query = queryFactory.select(Projections.constructor(AdminFormListResponse.class,
                        Expressions.constant(0),
                        formEntity.formSeq,
                        formEntity.title,
                        formEntity.category,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", formEntity.createDate),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", formEntity.confirmedDate),
                        formEntity.answer,
                        formEntity.member.memberInfo.memberName
                ))
                .from(
                        formEntity
                )
                .where(
                        searchByCategory(request.getCategory()),
                        searchBySearchTypeAndKeyword(request.getSearchType(), request.getKeyword())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(formEntity.getType(), formEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<AdminFormListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    private Predicate searchBySearchTypeAndKeyword(String searchType, String keyword) {
        if (ObjectUtils.isEmpty(searchType)) {
            return null;
        } else if (searchType.equals("title")) {
            return formEntity.title.contains(keyword);
        } else if (searchType.equals("memberName")) {
            return formEntity.member.memberInfo.memberName.contains(keyword);
        } else {
            return formEntity.title.contains(keyword).or(formEntity.member.memberInfo.memberName.contains(keyword));
        }
    }

    private Predicate searchByCategory(String category) {
        if (ObjectUtils.isEmpty(category)) {
            return null;
        } else if (category.equals("전체")) {
            return null;
        } else if (category.equals("차량")) {
            return formEntity.category.contains("차량");
        } else if (category.equals("항공권")) {
            return formEntity.category.contains("항공권");
        } else {
            return formEntity.category.contains("여행자");
        }
    }
}
