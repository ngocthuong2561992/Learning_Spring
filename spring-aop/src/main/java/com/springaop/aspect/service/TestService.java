package com.springaop.aspect.service;

import com.springaop.aspect.dao.TestDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TestDAO testDAO;

    public String callDaoSuccess(){
        logger.info("Test Service callDaoSuccess ");
        return testDAO.callDaoSuccess();
    }
    public String callDaoFailed(){
        logger.info("Test Service callDaoFailed");
        return testDAO.callDaoThrowException();
    }

    public String callDaoTrackTime(){
        logger.info("Test Service callDaoTrackTime");
        return testDAO.callMethodTrackTime();
    }
}
