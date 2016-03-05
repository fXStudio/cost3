package com.ruling.cost.itemreport.service;

import com.common.been.ParameterMap;
import com.common.been.ResultHashMap;

public interface ItemReportManager {

	public ResultHashMap showReport(ParameterMap map) throws Exception;

	public ResultHashMap add(ParameterMap map) throws Exception;

	public ResultHashMap deleteItemReport(ParameterMap map) throws Exception;

	public ResultHashMap updateItemReport(ParameterMap map) throws Exception;

	public ResultHashMap addItemReport2(ParameterMap map) throws Exception;

	public ResultHashMap showItemReport2(ParameterMap map) throws Exception;

	public ResultHashMap deleteItemReport2(ParameterMap map) throws Exception;

	public ResultHashMap updateItemReport2(ParameterMap map) throws Exception;

	public ResultHashMap addItemReport3(ParameterMap map) throws Exception;

	public ResultHashMap showItemReport3(ParameterMap map) throws Exception;

	public ResultHashMap deleteItemReport3(ParameterMap map) throws Exception;

	public ResultHashMap updateItemReport3(ParameterMap map) throws Exception;

	public ResultHashMap forwardAddItemReport3Page(ParameterMap map)
			throws Exception;

	public ResultHashMap forwardAddItemReport2Page(ParameterMap map)
			throws Exception;

	public ResultHashMap getYusuanByRiqi(ParameterMap map) throws Exception;
}
