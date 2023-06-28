package com.field.muzi.repository;

import com.field.muzi.domain.entity.DiyEntity;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.admin.dto.diy.AdminDiyDetailsResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyListRequest;
import com.field.muzi.web.admin.dto.diy.AdminDiyListResponse;
import com.field.muzi.web.user.dto.diy.DiyDetailsResponse;
import com.field.muzi.web.user.dto.diy.DiyListResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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

import java.util.Date;

import static com.field.muzi.domain.entity.QDiyEntity.diyEntity;

@Repository
public class DiyQueryRepositoryImpl extends QuerydslRepositorySupport implements DiyQueryRepository {
    private final JPAQueryFactory queryFactory;

    public DiyQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(DiyEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<DiyListResponse> diyListByMember(Pageable pageable, MemberEntity member) {
        JPAQuery<DiyListResponse> query = queryFactory.select(Projections.constructor(DiyListResponse.class,
                        diyEntity.diySeq,
                        diyEntity.diyTitle,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.startDate),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.endDate),
                        diyEntity.content,
                        diyEntity.etc,
                        diyEntity.state
                ))
                .from(
                        diyEntity
                )
                .where(diyEntity.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(diyEntity.getType(), diyEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<DiyListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public DiyDetailsResponse.Diy diy(DiyEntity diy) {
        return queryFactory.select(Projections.constructor(DiyDetailsResponse.Diy.class,
                        diyEntity.diySeq,
                        diyEntity.diyTitle,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.startDate),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.endDate),
                        diyEntity.content,
                        diyEntity.etc,
                        diyEntity.state,
                        diyEntity.phone
                ))
                .from(
                        diyEntity
                )
                .where(diyEntity.eq(diy))
                .fetchOne();
    }

    @Override
    public Page<AdminDiyListResponse> adminDiyList(Pageable pageable, AdminDiyListRequest request) {
        JPAQuery<AdminDiyListResponse> query = queryFactory.select(Projections.constructor(AdminDiyListResponse.class,
                        Expressions.constant(0),
                        diyEntity.diySeq,
                        diyEntity.diyTitle,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.startDate),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.endDate),
                        diyEntity.content,
                        diyEntity.etc,
                        diyEntity.state,
                        diyEntity.member.memberInfo.email,
                        diyEntity.member.memberInfo.memberName,
                        diyEntity.phone
                ))
                .from(
                        diyEntity
                )
                .where(
                        searchBySearchTypeAndKeyword(request.getSearchType(), request.getKeyword()),
                        dateGoe(request.getStartDate()),
                        dateLoe(request.getEndDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(diyEntity.getType(), diyEntity.getMetadata());
            query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<AdminDiyListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public AdminDiyDetailsResponse.Diy adminDiy(DiyEntity diy) {
        return queryFactory.select(Projections.constructor(AdminDiyDetailsResponse.Diy.class,
                        diyEntity.diySeq,
                        diyEntity.diyTitle,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.startDate),
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", diyEntity.endDate),
                        diyEntity.content,
                        diyEntity.etc,
                        diyEntity.state,
                        diyEntity.member.memberInfo.email,
                        diyEntity.member.memberInfo.memberName,
                        diyEntity.phone
                ))
                .from(
                        diyEntity
                )
                .where(diyEntity.eq(diy))
                .fetchOne();
    }

    private BooleanExpression dateGoe(Date dateGoe) {
        return dateGoe != null ? diyEntity.startDate.goe(dateGoe) : null;
    }

    private BooleanExpression dateLoe(Date dateLoe) {
        return dateLoe != null ? diyEntity.endDate.loe(dateLoe) : null;
    }

    private Predicate searchBySearchTypeAndKeyword(String searchType, String keyword) {
        if (ObjectUtils.isEmpty(searchType)) {
            return null;
        } else if (searchType.equals("diyTitle")) {
            return diyEntity.diyTitle.contains(keyword);
        } else if (searchType.equals("memberName")) {
            return diyEntity.member.memberInfo.memberName.contains(keyword);
        } else {
            return diyEntity.diyTitle.contains(keyword).or(diyEntity.member.memberInfo.memberName.contains(keyword));
        }
    }
}
