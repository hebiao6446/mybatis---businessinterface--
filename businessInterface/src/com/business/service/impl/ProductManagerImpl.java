package com.business.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.business.entity.Contacts;
import com.business.entity.Product;
import com.business.entity.ProductCategory;
import com.business.entity.Project;
import com.business.entity.Sell;
import com.business.entity.SellProduct;
import com.business.service.ProductManager;

@Service
@Transactional
public class ProductManagerImpl implements ProductManager {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 此句话针对查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List findProductCategoryList(JSONObject param) {
		param.put("parentId", 0);
		List<ProductCategory> list = (List<ProductCategory>) sqlSession
				.selectList("ProductCategory.findProductCategoryList", param);
		for (ProductCategory pc : list) {
			param.put("parentId", pc.getProductCategoryId());
			List<ProductCategory> list1 = (List<ProductCategory>) sqlSession
					.selectList("ProductCategory.findProductCategoryList",
							param);
			pc.setProductlist(list1);
		}
		return list;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findProductList(JSONObject param) {
		List<Product> list = (List<Product>) sqlSession.selectList(
				"Product.findProductList", param);
		return list;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	
	@Override
	public Object findProductImgList(Product product) {
		Object obj = sqlSession.selectList("ProductImg.findProductImgList",
				product);
		return obj;
	}

	@Override
	public void insertOrder(JSONObject param) {

		sqlSession.insert("Product.insertOrder", param);

	}

	@Override
	public void insertSellVisit(JSONObject param) {
		sqlSession.insert("SellVisit.insertSellVisit", param);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findSellList(JSONObject param) {
		List<Sell> list = (List<Sell>) sqlSession.selectList(
				"Sell.findSellList", param);
		return list;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public Object findSellVisitList(Sell sell) {
		Object obj = sqlSession.selectList("SellVisit.findSellVisitList", sell);
		return obj;
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<SellProduct> findsellProductList(Sell sell) {
		List<SellProduct> list = (List<SellProduct>) sqlSession.selectList(
				"SellProduct.findsellProductList", sell);
		return list;
	}

	@Override
	public Contacts findSellContactsList(Sell sell) {
		Contacts obj = (Contacts) sqlSession.selectOne(
				"Contacts.findSellContactsList", sell);
		return obj;
	}

	@Override
	public Product findSellProductList(SellProduct product) {
		Product list = (Product) sqlSession.selectOne(
				"Product.findSellProductList", product);
		return list;
	}

	@Override
	public void updateSellInfo(JSONObject param) {
		if (param.get("sellId") != null
				&& param.get("sellId").toString().trim().length() > 0) {
			sqlSession.update("Sell.updateSellInfo", param);
		} else {
			sqlSession.insert("Sell.insertSellInfo", param);

		}
	}

	@Override
	public void insertProjectVisit(JSONObject param) {
		sqlSession.insert("ProjectVisit.insertProjectVisit", param);

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findProjectList(JSONObject param) {
		List<Project> list = (List<Project>) sqlSession.selectList(
				"Project.findProjectList", param);
		return list;
	}

	@Override
	public Contacts findProjectContactsList(Project project) {
		Contacts obj = (Contacts) sqlSession.selectOne(
				"Contacts.findProjectContactsList", project);
		return obj;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	
	@Override
	public Object findProjectVisitList(Project project) {
		Object obj = sqlSession.selectList("ProjectVisit.findProjectVisitList",
				project);
		return obj;
	}

	@Override
	public void updateProjectInfo(JSONObject param) {
		if (param.get("projectId") != null
				&& param.get("projectId").toString().trim().length() > 0) {
			sqlSession.update("Project.updateProjectInfo", param);
		} else {
			sqlSession.insert("Project.insertProjectInfo", param);
		}
	}

}
