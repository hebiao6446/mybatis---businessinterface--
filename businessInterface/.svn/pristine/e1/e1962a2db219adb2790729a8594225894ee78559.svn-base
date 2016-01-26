package com.json;

import java.text.SimpleDateFormat;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
	/**
	 * 将bean中的java.util.Date类型转化成 yyyy-MM-dd HH:mm:ss JsonConfig jsonConfig =
	 * new JsonConfig();
	 * jsonConfig.registerJsonValueProcessor(java.util.Date.class , new
	 * JsonDateValueProcessor()); JSONArray jo = JSONArray.fromObject(bean,
	 * jsonConfig);
	 * 
	 * @param value
	 * @param config
	 * @return
	 */
	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor(String dateformat) {	
		if (dateformat != null)
			format = dateformat;
	}

	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value);
	}

	private Object process(Object value) {
		if (value instanceof java.sql.Time) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
			return sdf.format(value);
		} else if (value instanceof java.sql.Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
			return sdf.format(value);
		} else if (value instanceof java.sql.Timestamp) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
			return sdf.format(value);
		} else if (value instanceof java.util.Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}

}
