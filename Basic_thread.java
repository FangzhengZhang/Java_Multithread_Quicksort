/**
 * in this class, I extend the Thread class, and put the wanted function in the run()
 * method. The function will print which thread is start to run and when it ends.
 * You also can use run by implements Runnable
 * @author fangzheng
 * @version 2/20/2019
 */
public class Basic_thread extends Thread{

	/**
	 * I use this function to run the things i want it to run.
	 * more detail please to read the Java Thread class doc.
	 */
	public void run() {
		System.out.println("Thread " 
	+ Thread.currentThread().getId() 
	+ " is running!\n");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread " 
	+ Thread.currentThread().getId() 
	+ " is end!\n");
	} 
}
