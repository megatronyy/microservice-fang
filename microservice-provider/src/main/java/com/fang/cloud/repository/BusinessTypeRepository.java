package com.fang.cloud.repository;

import com.fang.cloud.entity.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by quwb on 2017/11/28.
 */
@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {

}
