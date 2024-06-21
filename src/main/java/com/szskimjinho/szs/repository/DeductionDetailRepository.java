package com.szskimjinho.szs.repository;

import com.szskimjinho.szs.entity.DeductionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionDetailRepository extends JpaRepository<DeductionDetail,String> {
}
