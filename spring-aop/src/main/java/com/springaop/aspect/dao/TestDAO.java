package com.springaop.aspect.dao;

import com.springaop.aspect.TrackTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String callDaoSuccess(){
        logger.info("callDaoSuccess is called");
        return "dao1";
    }
    public String callDaoThrowException(){
        logger.info("DAO is called");
        throw new RuntimeException("");
    }

    @TrackTime
    public String callMethodTrackTime(){
        logger.info("callDaoSuccess is called");
        return "dao1";
    }
}
