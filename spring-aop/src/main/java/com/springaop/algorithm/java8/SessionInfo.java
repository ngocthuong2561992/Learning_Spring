package com.springaop.algorithm.java8;

public class SessionInfo {
	private String sessionId = "";
    private long startTime = 0;
    
    SessionInfo(String sessionId, long startTime) {
    	this.sessionId = sessionId;
    	this.startTime = startTime;
    }
    
    @Override
    public String toString() {
    	return this.sessionId + ", " + this.startTime;
    }
    
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
    
}
