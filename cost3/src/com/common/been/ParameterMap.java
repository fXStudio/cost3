package com.common.been;

import java.util.HashMap;

public class ParameterMap extends HashMap {

	public String getString(Object key) {
		return (String) get(key);
	}

	public int getInt(Object key) {
		return (Integer) get(key);
	}

	public double getDouble(Object key) {
		return (Double) get(key);
	}

	public float getFloat(Object key) {
		return (Float) get(key);
	}

	public boolean getBoolean(Object key) {
		return (Boolean) get(key);
	}
}
