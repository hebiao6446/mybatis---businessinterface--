package com.business.service;

import java.util.List;

import net.sf.json.JSONObject;


import com.business.entity.Contacts;
import com.business.entity.Product;
import com.business.entity.Project;
import com.business.entity.Sell;
import com.business.entity.SellProduct;

public interface ProductManager {

	@SuppressWarnings("rawtypes")
	List findProductCategoryList(JSONObject param);

	@SuppressWarnings("rawtypes")
	List findProductList(JSONObject param);

	Object findProductImgList(Product product);

	void insertOrder(JSONObject param);

	void insertSellVisit(JSONObject param);

	@SuppressWarnings("rawtypes")
	List findSellList(JSONObject param);

	Object findSellVisitList(Sell sell);

	List <SellProduct> findsellProductList(Sell sell);

	Contacts findSellContactsList(Sell sell);

	Product findSellProductList(SellProduct product);

	void updateSellInfo(JSONObject param);

	void insertProjectVisit(JSONObject param);

	@SuppressWarnings("rawtypes")
	List findProjectList(JSONObject param);

	Contacts findProjectContactsList(Project project);

	Object findProjectVisitList(Project project);

	void updateProjectInfo(JSONObject param);





}
