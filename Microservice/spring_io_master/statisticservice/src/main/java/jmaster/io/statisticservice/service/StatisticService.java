package jmaster.io.statisticservice.service;

import java.util.List;

import jmaster.io.statisticservice.model.StatisticDTO;

public interface StatisticService {

	void add(StatisticDTO  statisticDTO);
	
	List<StatisticDTO> getAll();
}
