package com.szskimjinho.szs.repository;

import com.szskimjinho.szs.entity.Income;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface IncomeRepository extends JpaRepository<Income,String>{

    public Income findByMember_UserId(String userId);
}
