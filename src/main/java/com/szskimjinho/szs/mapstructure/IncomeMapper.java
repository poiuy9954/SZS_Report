package com.szskimjinho.szs.mapstructure;

import com.szskimjinho.szs.dto.IncomeDTO;
import com.szskimjinho.szs.entity.Income;
import org.mapstruct.Mapper;

@Mapper
public interface IncomeMapper {
    IncomeDTO toDto(Income income);
}
