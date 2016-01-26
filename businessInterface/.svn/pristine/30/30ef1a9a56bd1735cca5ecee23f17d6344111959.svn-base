package com.business.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.business.entity.BlessingsType;
import com.business.service.BlessingsManager;

@Service
@Transactional
public class BlessingsManagerImpl implements BlessingsManager {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 此句话针对查询 // 一个只读事务，可以提高效率。
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List findBlessingsTypeList(JSONObject param) {
		List<HashMap> list = (List<HashMap>) sqlSession.selectList(
				"BlessingsType.findBlessingsTypeList", param);
		return list;
	}

	@Override
	public Integer findBlessingsListCount(BlessingsType blessingsType) {
		return (Integer) sqlSession.selectOne(
				"Blessings.findBlessingsListCount", blessingsType);

	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	// 一个只读事务，可以提高效率。
	@Override
	public Object findBlessingsList(BlessingsType blessingsType) {
		Object obj = sqlSession.selectList("Blessings.findBlessingsList",
				blessingsType);
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findBlessingIndexsList(JSONObject param) {
		List<HashMap> list = (List<HashMap>) sqlSession.selectList(
				"Blessings.findBlessingIndexsList", param);
		return list;
	}

}
