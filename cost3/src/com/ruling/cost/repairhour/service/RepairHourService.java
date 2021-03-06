package com.ruling.cost.repairhour.service;

import com.ruling.cost.hours.domain.Hour;
import com.ruling.cost.utils.PageModel;

public interface RepairHourService {

	public void addRepairHour(Hour h);

	public void deleteRepairHour(int id);

	public PageModel findAllRepairHour(Hour h);

}
