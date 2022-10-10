public class Aufgabe1 {
	public static void main(String[] args) {
		System.out.println("Starting");
		Eieruhr(5000,"Fertig");
	}
	
	
	public static void Eieruhr(int _timeInMs, String _notification) {
		System.out.println("Start");
		Thread t1 = new Sleepthread(_timeInMs,_notification);
		t1.start();
		System.out.println("Time Remaining: ");
		for (int remainingTime = _timeInMs; remainingTime >= 0; remainingTime = remainingTime-1000) {
			try {
			if(remainingTime < 1000) {
				
				Thread.sleep(remainingTime);
			}
			else {
				System.out.println(remainingTime/1000 + " seconds");
				Thread.sleep(1000);
			}
			}	 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	

}

class Sleepthread extends Thread {
	int timeInMs;
	String notification; 
	
	public Sleepthread(int _timeInMs, String _notification) {
		this.timeInMs = _timeInMs;
		this.notification = _notification;
	}
	
	public void run() {
		
		schlafen(timeInMs/1000);
		System.out.println(notification);
	}
	
	public static void schlafen(int _m) {
		try {
		Thread.sleep(1000*_m);
		} catch (InterruptedException t) {
		
		}
	}
}
