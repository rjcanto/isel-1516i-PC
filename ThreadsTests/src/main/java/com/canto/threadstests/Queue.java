package main.java.com.canto.threadstests;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class Queue {

	//Aula 28/07/2015. Não é normal usar reentrantlock com semaphore. 
	//Existem outros mecanismos.
	private final Lock lock = new ReentrantLock() ; //Mutex
	private final Semaphore sem = new Semaphore(0);
	
	private final LinkedList<Object> list =
			new LinkedList<>();
	
	
	//version 1 - issues with concurrency. May function or not
	public void putv1(Object obj) {
		list.add(obj);
	}
	
	//version 1 - issues with concurrency. May function or not
	public Object takev2() {
		// Tipo de código check-then-act
		while (list.size() == 0); //PROBLEMA - Espera activa
		// Entre estas instruções a lista pode estar vazia. RACE CONDITION
		return list.remove();
	}
	
	//version 2 - sincronização de dados com MUTEX ou CRITICAL_SECTION
		public void put(Object obj) {
			lock.lock();
			try {
				list.add(obj); //escrita no objecto tem de estar em zona de exclusão.
			} finally {
				lock.unlock();
			}
			sem.release(); //incremento do contador
		}
		
		//version 2 - sincronização de controlo
		public Object take() throws InterruptedException  {
			// para proteger esta instrução iremos usar um semáforo (int)
			sem.acquire(); 
			lock.lock();
			try {
				return list.remove();
			} finally {
				lock.unlock(); //boa prática envolver num try - finally
			}
		}
	

	public static void main(String[] args) throws Exception {
		
		Queue queue = new Queue();
		
		Thread p1 = new Thread(() -> {
			for (int i = 0; i < 1024; ++i) {
				queue.put(new Object()) ;
			}
		});
		
		Thread c1 = new Thread(() -> {
			try {
				for (int i = 0; i < 1024; ++i) {
					queue.take() ;
				}
			} catch (InterruptedException e) {
				System.err.println("Oh no! Exception not supported");
			}
		});
		
		c1.start();
		p1.start();
		
		c1.join();
		p1.join();
		
		System.out.println("OK");
		
	}
}


