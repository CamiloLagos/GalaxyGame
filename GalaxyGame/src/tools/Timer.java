package tools;

public class Timer extends Thread {

	
	private double seconds;
	private boolean done;
	
	public Timer(double seconds) {
		
		this.seconds = seconds*1000;
		done = false;
		
	}
	
	
	@Override
	public void run() {
		
		while(!done) {
			
			try {
				
				Thread.sleep((long)seconds);
				
				done = true;
				
			}catch(Exception ex) {
				
				ex.printStackTrace();
				
			}
			
			
		}
		
		
	}
}