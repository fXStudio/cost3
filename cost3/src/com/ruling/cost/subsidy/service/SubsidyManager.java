package com.ruling.cost.subsidy.service;

import java.io.OutputStream;
import java.util.List;

import com.ruling.cost.subsidy.domain.Subsidy;
import com.ruling.cost.subsidy.domain.SubsidyHistory;
import com.ruling.cost.utils.PageModel;

public interface SubsidyManager {

	public void addSubsidy(Subsidy subsi);

	public void delSubsidy(List subsids);

	public void updateSubsidy(Subsidy subsi);

	public PageModel findAllSubsidy(Subsidy subsidy) throws Exception;

	public Subsidy findSubsidyById(int subsidyid);
	
	public void exportPersonSalary(Integer id, OutputStream out);
	
	public void excelAllChdange(Subsidy s, OutputStream out);
	
	public void excelCompanySalary(Subsidy s, OutputStream out);
	
	public SubsidyHistory findSubsidyHistoryById(int id);
}
