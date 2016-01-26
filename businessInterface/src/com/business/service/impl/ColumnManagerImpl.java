package com.business.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.business.entity.Company;
import com.business.service.ColumnManager;

@Service
@Transactional
public class ColumnManagerImpl implements ColumnManager {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ColumnManagerImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> loadColumnList(int rows, int page, Integer isCheck,
			Integer columnAuthority) {
		return (List<Company>) sqlSession.selectList("Company.findCompanyList");
	}

}
