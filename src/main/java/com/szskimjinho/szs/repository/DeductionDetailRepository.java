package com.szskimjinho.szs.repository;

import com.szskimjinho.szs.entity.DeductionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeductionDetailRepository extends JpaRepository<DeductionDetail,String> {

    public List<DeductionDetail> findByIncome_IncomeKey(String income);
}
