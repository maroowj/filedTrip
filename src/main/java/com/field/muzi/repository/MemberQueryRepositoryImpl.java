package com.field.muzi.repository;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.entity.MemberInfoEntity;
import com.field.muzi.domain.user.Role;
import com.field.muzi.web.admin.dto.diy.AdminDiyListResponse;
import com.field.muzi.web.admin.dto.member.AdminMemberListRequest;
import com.field.muzi.web.admin.dto.member.AdminMemberListResponse;
import com.field.muzi.web.common.dto.member.LoginRequest;
import com.querydsl.core.BooleanBuilder;
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

import java.util.List;
import java.util.Optional;

import static com.field.muzi.domain.entity.QDiyEntity.diyEntity;
import static com.field.muzi.domain.entity.QFirstCourseEntity.firstCourseEntity;
import static com.field.muzi.domain.entity.QMemberEntity.memberEntity;
import static com.field.muzi.domain.entity.QMemberInfoEntity.memberInfoEntity;
import static com.field.muzi.domain.entity.QMemberTypeEntity.memberTypeEntity;

@Repository
public class MemberQueryRepositoryImpl extends QuerydslRepositorySupport implements MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public MemberQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(MemberEntity.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<MemberEntity> findByMemberIdAndRole(LoginRequest request, List<Role> role) {
        return Optional.ofNullable(
                queryFactory
                        .select(memberEntity)
                        .from(memberEntity)
                        .join(memberTypeEntity).on(memberTypeEntity.member.eq(memberEntity))
                        .where(
                                memberEntity.memberId.eq(request.getMemberId()),
                                searchRole(role),
                                memberEntity.withdrawal.isFalse()
                        )
                        .fetchOne()
        );
    }

    @Override
    public Optional<MemberEntity> findByMemberIdAndRole(String id, Role role) {
        return Optional.ofNullable(
                queryFactory
                        .select(memberEntity)
                        .from(memberEntity)
                        .join(memberTypeEntity).on(memberTypeEntity.member.eq(memberEntity))
                        .where(
                                memberEntity.memberId.eq(id),
                                memberTypeEntity.memberType.eq(role),
                                memberEntity.withdrawal.isFalse()
                        )
                        .fetchOne()
        );
    }

    @Override
    public Page<AdminMemberListResponse> memberList(Pageable pageable, AdminMemberListRequest request) {
        JPAQuery<AdminMemberListResponse> query = queryFactory.select(Projections.constructor(AdminMemberListResponse.class,
                        Expressions.constant(0),
                        memberEntity.memberSeq,
                        memberEntity.memberInfo.email,
                        memberEntity.memberInfo.memberName,
                        Expressions.stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", memberEntity.createDate)
                ))
                .from(
                        memberEntity
                )
                .where(
                        memberEntity.withdrawal.isFalse(),
                        memberEntity.roles.contains(Role.USER),
                        searchBySearchTypeAndKeyword(request.getSearchType(), request.getKeyword())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(memberEntity.getType(), memberEntity.getMetadata());
//            PathBuilder<? extends MemberInfoEntity> pathBuilder = new PathBuilder<MemberInfoEntity>(memberInfoEntity.getType(), memberInfoEntity.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<AdminMemberListResponse> result = query.fetchResults();

        return new PageImpl(result.getResults(), pageable, result.getTotal());
    }


    private Predicate searchRole(List<Role> roles) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (Role role : roles) {
            booleanBuilder.or(
                    memberTypeEntity.memberType.eq(role)
            );
        }

        return booleanBuilder;
    }

    private Predicate searchBySearchTypeAndKeyword(String searchType, String keyword) {
        if (ObjectUtils.isEmpty(searchType)) {
            return null;
        } else if (searchType.equals("all")) {
            return memberEntity.memberInfo.email.contains(keyword).or(memberEntity.memberInfo.memberName.contains(keyword));
        } else if (searchType.equals("id")) {
            return memberEntity.memberInfo.email.contains(keyword);
        } else if (searchType.equals("memberName")) {
            return memberEntity.memberInfo.memberName.contains(keyword);
        } else {
            return null;
        }
    }
}
