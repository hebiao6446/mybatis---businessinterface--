package com.json;

import java.text.SimpleDateFormat;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonAllDateValueProcessor implements JsonValueProcessor {

	private String formatTime = "HH:mm:ss";
	private String formatShortDate = "yyyy-MM-dd";
	private String formatTimestamp = "yyyy-MM-dd HH:mm:ss";
	private String formatDate = "yyyy-MM-dd HH:mm:ss";

	public JsonAllDateValueProcessor() {
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig config) {

		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {

		return process(value);
	}

	private Object process(Object value) {
		if (value instanceof java.sql.Time) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatTime, Locale.UK);
			return sdf.format(value);
		} else if (value instanceof java.sql.Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatShortDate,
					Locale.UK);
			return sdf.format(value);
		} else if (value instanceof java.sql.Timestamp) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatTimestamp,
					Locale.UK);
			return sdf.format(value);
		} else if (value instanceof java.util.Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.UK);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}

}
