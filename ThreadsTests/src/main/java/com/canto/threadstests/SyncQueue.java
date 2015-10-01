package main.java.com.canto.threadstests;

import java.util.LinkedList;

public class SyncQueue {

		private final LinkedList<Object> list = new LinkedList<>();
		
		public synchronized void put (Object obj) {
			list.add(obj);
			notify() ;
		
		}
		
		public synchronized Object take() {
			while (!list.isEmpty())
				;
			return null; //terminar
		}
}
