package com.silu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.silu.entity.Navigation;

/**
 * com.silu.repository
 * 
 * @author 廖金龙 2018年4月22日下午6:53:25 Navigation 查询 接口
 */
@Repository
public interface NavigationRepository extends JpaRepository<Navigation, Long> {
}
