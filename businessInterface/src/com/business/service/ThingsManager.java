package com.business.service;

import java.util.List;

import com.business.entity.Things;
import com.business.entity.ThingsThingsFinish;

import net.sf.json.JSONObject;

public interface ThingsManager {

	List<ThingsThingsFinish> findAllThings(JSONObject param);
	
	List<Things> findAllCreateThings(JSONObject param);

	void updateNewThings(JSONObject param);

	Things findThingsInfo(JSONObject param);

	void finishThings(JSONObject param);

	Things findThingsminInfo(JSONObject param);

	void deleteThings(JSONObject param);
	
}
