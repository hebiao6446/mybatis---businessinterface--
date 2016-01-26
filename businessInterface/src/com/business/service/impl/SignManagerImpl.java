package com.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.business.entity.AttendDescript;
import com.business.entity.Attendance;
import com.business.entity.Company;
import com.business.entity.User;
import com.business.service.SignManager;

@Service
@Transactional
public class SignManagerImpl implements SignManager {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Attendance findTodaySign(JSONObject map) {
		return (Attendance) sqlSession.selectOne("Attendance.findTodaySign",
				map);
	}

	@Override
	public Company findCompanyInfo(User user) {
		return (Company) sqlSession.selectOne("Company.findWorkTime", user);
	}

	@Override
	public void updateSign(JSONObject param) {
		sqlSession.update("Attendance.updateSign", param);
	}

	@Override
	public void insertSign(JSONObject param) {
		sqlSession.insert("Attendance.insertSign", param);
	}

	@Override
	public void insertAttendDescript(JSONObject param) {
		sqlSession.insert("AttendDescript.insertAttendDescript", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendDescript> findHistoryAttendDescript(JSONObject param) {
		return (List<AttendDescript>) sqlSession.selectList(
				"AttendDescript.findHistoryAttendDescript", param);
	}

	@Override
	public Object findHistoryAttendDescriptCount(JSONObject param) {

		return sqlSession.selectOne(
				"AttendDescript.findHistoryAttendDescriptCount", param);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Attendance> findHistorySign(JSONObject param) {
		if (param.get("status") == null || "".equals(param.getString("status"))) {
			return findHistorySign1(param);
		} else {
			return findHistorySignstatus(param);
		}
	}

	private List<Attendance> findHistorySignstatus(JSONObject param) {
		Integer status = 0;
		if (param.get("status") == null || "".equals(param.getString("status"))) {
			status = 0;
		} else {
			status = param.getInt("status");
		}
		Integer size = param.get("size") == null ? 0 : param.getInt("size");
		Integer page = param.get("page") == null ? 0 : param.getInt("page");
		Integer startNumber = (page - 1) * size;
		long dayTimeNumber = 24 * 60 * 60 * 1000L;

		String fontTime = param.get("fontTime") == null ? null : param.get(
				"fontTime").toString();
		String backTime = param.get("backTime") == null ? null : param.get(
				"backTime").toString();
		if (fontTime != null && !"".equals(fontTime) && backTime != null
				&& !"".equals(backTime)) {
			java.sql.Date backDate = java.sql.Date.valueOf(backTime);
			java.sql.Date fontDate = java.sql.Date.valueOf(fontTime);
			if (fontDate.before(backDate)) {
				String tims = fontTime;
				fontTime = backTime;
				backTime = tims;
			}
		}
		Long dateTime;
		if (fontTime != null && !"".equals(fontTime)) {
			dateTime = java.sql.Date.valueOf(fontTime).getTime();
		}else{
			dateTime = new java.sql.Date(new Date().getTime()).getTime();
		}
		List<Attendance> list = new ArrayList<Attendance>();
		long times = dateTime + dayTimeNumber;
		int count = 0;
		int countOut = 0;
		while (count < startNumber + size) {
			countOut++;
			if(countOut>366){
				break;
			}
			times = times - dayTimeNumber;
			if (backTime != null && !"".equals(backTime)) {
				Long backDate = java.sql.Date.valueOf(backTime).getTime();
				if (backDate > times) {
					break;
				}
			}
			String dayTime = new java.sql.Date(times).toString();
			param.put("dayTime", dayTime);
			Attendance a = (Attendance) sqlSession.selectOne(
					"Attendance.findTodaySign", param);
			if (a.getInTime() == null) {
				a.setStatus(6);// 没有签到与签退
			} else {
				if (a.getOutTime() == null) {
					if (a.getStatus() == 1) {
						a.setStatus(5);// 迟到,没有签退
					} else if (a.getStatus() == -1) {
						a.setStatus(4);// 没有签退
					}
				}
			}
			if (status == 0) {
				count++;
			} else if (status == a.getStatus()) {
				count++;
			}
			if (count > startNumber) {
				list.add(a);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<Attendance> findHistorySign1(JSONObject param) {
		Integer size = (Integer) param.get("size");
		Integer page = (Integer) param.get("page");
		String fontTime = param.get("fontTime") == null ? null : param.get(
				"fontTime").toString();
		String backTime = param.get("backTime") == null ? null : param.get(
				"backTime").toString();		
		long dayTimeNumber = 24 * 60 * 60 * 1000L;
		if (fontTime != null && !"".equals(fontTime) && backTime != null
				&& !"".equals(backTime)) {
			java.sql.Date backDate = java.sql.Date.valueOf(backTime);
			java.sql.Date fontDate = java.sql.Date.valueOf(fontTime);
			if (fontDate.before(backDate)) {
				String tims = fontTime;
				fontTime = backTime;
				backTime = tims;
			}
		}
		java.sql.Date date = null;
		Long flong = (page - 1) * size * dayTimeNumber;

		if (fontTime != null && !"".equals(fontTime)) {
			flong = java.sql.Date.valueOf(fontTime).getTime() - flong;
		} else {
			flong = new Date().getTime() - flong;
		}
		date = new java.sql.Date(flong);

		Long blong = size * dayTimeNumber;
		if (backTime != null && !"".equals(backTime)) {
			if (java.sql.Date.valueOf(backTime).after(
					new java.sql.Date(date.getTime() - blong))) {
				long e = date.getTime()
						- java.sql.Date.valueOf(backTime).getTime();
				size = (int) (e /dayTimeNumber) + 1;
			}
		}
		
		List<Attendance> list = null;
		if (size > 0) {
			List<String> t = new ArrayList<String>();
			for (int i = 0; i < size; i++) {
				t.add(new java.sql.Date(date.getTime() - i * dayTimeNumber)
						.toString());
			}			
			param.put("t", t);
			list= (List<Attendance>) sqlSession.selectList(
					"Attendance.findHistorySign", param);
			if (list != null && list.size() > 0) {
				for (Attendance a : list) {
					if (a.getInTime() == null) {
						a.setStatus(6);// 没有签到与签退
					} else {
						if (a.getOutTime() == null) {
							if (a.getStatus() == 1) {
								a.setStatus(5);// 迟到,没有签退
							} else if (a.getStatus() == -1) {
								a.setStatus(4);// 没有签退
							}
						}
					}
				}
			}
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List<Attendance> findManagedSign(JSONObject param) {
		List<HashMap> list = (List<HashMap>) sqlSession.selectList(
				"Attendance.findManagedSign", param);
		List<Attendance> list1 = new ArrayList<Attendance>();
		for (HashMap map : list) {
			Attendance a = new Attendance();

			a.setAttendanceId(map.get("attendance_id") == null ? 0 : Long
					.valueOf(map.get("attendance_id").toString()));
			a.setDayTime(java.sql.Date.valueOf(param.getString("dateTime")));
			a.setUserId(Long.valueOf(map.get("user_id").toString()));
			a.setUserName(map.get("name").toString());
			a.setStatus(map.get("status") == null ? 0 : Integer.valueOf(map
					.get("status").toString()));
			a.setMyTime(param.getString("dateTime"));

			a.setInSignType(map.get("in_sign_type") == null ? "" : map.get(
					"in_sign_type").toString());
			a.setInAddress(map.get("in_address") == null ? "" : map.get(
					"in_address").toString());
			a.setInLat(new BigDecimal(map.get("in_lat") == null ? "0" : map
					.get("in_lat").toString()));
			a.setInLng(new BigDecimal(map.get("in_lng") == null ? "0" : map
					.get("in_lng").toString()));
			a.setInTime(map.get("in_time") == null ? null : new java.sql.Time(
					Long.valueOf(map.get("in_time").toString())));

			a.setOutSignType(map.get("out_sign_type") == null ? "" : map.get(
					"out_sign_type").toString());
			a.setOutLat(new BigDecimal(map.get("out_lat") == null ? "0" : map
					.get("out_lat").toString()));
			a.setOutLng(new BigDecimal(map.get("out_lng") == null ? "0" : map
					.get("out_lng").toString()));
			a.setOutAddress(map.get("out_address") == null ? "" : map.get(
					"out_address").toString());
			a.setOutTime(map.get("out_time") == null ? null
					: new java.sql.Time(Long.valueOf(map.get("out_time")
							.toString())));
			list1.add(a);
		}
		return list1;
	}

	@Override
	public Object findManagedSignCount(JSONObject param) {

		return sqlSession.selectOne("Attendance.findManagedSignCount", param);
	}

}
