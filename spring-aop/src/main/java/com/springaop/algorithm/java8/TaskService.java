package com.springaop.algorithm.java8;

import java.util.*;

public class TaskService {
	
	private static final Comparator<SessionInfo> timeoutComparator = (o1, o2) -> (int) (o2.getStartTime() - o1.getStartTime());
	
	private static final PriorityQueue<SessionInfo> taskQueue = new PriorityQueue<>(timeoutComparator);
	
	public static void main(String[] args) {
		taskQueue.add(new SessionInfo("a", new Date().getTime()/1000));
		taskQueue.add(new SessionInfo("b", new Date().getTime()/1000));
		taskQueue.add(new SessionInfo("c", new Date().getTime()/1000));
		taskQueue.add(new SessionInfo("d", new Date().getTime()/1000));
		
		SessionInfo a = taskQueue.poll();
		System.out.println(a.getSessionId());
		System.out.println(new Date().getTime()/1000);
		
//		List<SessionInfo> list = Arrays.asList(new SessionInfo("b", 0), new SessionInfo("a", 0), new SessionInfo("c", 0));
//
//		Comparator<SessionInfo> compare = new Comparator<SessionInfo>() {
//			@Override
//			public int compare(SessionInfo o1, SessionInfo o2) {
//				return o1.getSessionId().compareTo(o2.getSessionId());
//			}
//		};
//		int index = Collections.binarySearch(list, new SessionInfo("a", 0), compare);
//		System.out.println(index);
	}
}
