package pc1516i;

public class FutureHolder<T> {

	private T holder;
	
	public synchronized void setValue( T value) {
		holder = value;
		notify();
	}
	
	public synchronized T getValue(long timeout) throws InterruptedException {
		while (!isValueAvailable()) {
			wait(timeout);
		}
		return holder;
	}
	
	private synchronized boolean isValueAvailable() {
		return holder != null;
	}
	
	public static void main(String[] args) throws Exception {
		FutureHolder<Integer> fholder = new FutureHolder<Integer>();
	      
	      Thread p1 = new Thread(() -> {
	         for (int i = 0; i < 1024; ++i) {
	            fholder.setValue(13);;
	         }
	      });
	      
	      Thread c1 = new Thread(() -> {
	         for (int i = 0; i < 1024; ++i) {
	            try {
	               fholder.getValue(0) ;
	            } catch (Exception e) {
	               System.err.println("Interrupt not supported!");
	               System.exit(-1);
	            }
	         }
	      });
	      
	      c1.start();
	      p1.start();
	      
	      p1.join();
	      c1.join();
	      
	      System.out.println("OK");
	}
}

