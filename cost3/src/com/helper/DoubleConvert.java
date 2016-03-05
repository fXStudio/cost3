package com.helper;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DoubleConvert extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {

		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>调用类型转换");
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + toClass);
		if (Double.class == toClass) {
			String doubleStr = values[0];
			System.out.println("获取到的字符串" + doubleStr);
			Double d = Double.parseDouble(doubleStr);
			return d;
		}
		return 0;
	}

	@Override
	public String convertToString(Map context, Object o) {

		System.out.println(">>>>>>>>>>>>>>>>>>value   " + o);
		System.out
				.println("value instanceof Double   " + (o instanceof Double));
		return o.toString();
	}

}