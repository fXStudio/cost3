package com;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DoubleConvert extends StrutsTypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {

		if (Double.class == toClass) {

			String doubleStr = values[0];

			Double d = Double.parseDouble(doubleStr);

			return d;

		}

		return 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {

		return o.toString();

	}
}
