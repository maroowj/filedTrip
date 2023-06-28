package com.field.muzi.repository;


import com.field.muzi.domain.entity.DiyEntity;
import com.field.muzi.web.admin.dto.diy.AdminDiyDetailsResponse;
import com.field.muzi.web.user.dto.diy.DiyDetailsResponse;

import java.util.List;

public interface DiyOptionsQueryRepository {
    List<DiyDetailsResponse.Options> diyOptions(DiyEntity diy);

    List<AdminDiyDetailsResponse.Options> adminDiyOptions(DiyEntity diy);
}
