package jmaster.io.statisticservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import jmaster.io.statisticservice.entity.Statistic;
import jmaster.io.statisticservice.repository.StatisticRepo;

@Service
public class StatisticService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StatisticRepo statisticRepo;

	@RetryableTopic(attempts = "5", dltTopicSuffix = "-dlt" ,
	backoff = @Backoff(delay = 2_000, multipler = 2))
	@KafkaListener(id = "statisticGroup", topics = "statistic")
	public void listen(Statistic statistic) {
		logger.info("Received" + statistic.getMessage());
	//	statisticRepo.save(statistic);
		throw new RuntimeException();
	}
	
	@KafkaListener(id = "dtGroup", topics = "statistic-dt")
	public void dtListen(Statistic statistic) {
		logger.info("Received" + statistic.getMessage());
	//	save to database to re-send to queue
	}
}
