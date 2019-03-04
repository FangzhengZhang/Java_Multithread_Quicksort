/**
 * This class is a runner class to run the demo that help me to understand how
 * different is multi-thread implementation between C and Java
 * 
 * @author fangzheng
 * @version 2/19/2019
 * 
 */
public class Runner {

	static final String DEFULT_SPLIT_CHARS = ", ";

	/**
	 * the mean. everything's start point
	 * 
	 * @param args the options that uses can choose
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// System.out.println("Staring the Runner");
		if (args.length <= 1) {
			System.out.println("\n" + "Type the following as argument to run different class\n"
					+ "Basic + #(number of thread you want to create)\n" + "	e.g: Basic 4\n"
					+ "Quicksort + filename / < filenaem / # # #\n" + "e.g: " + "	Quicksort randomNumber.txt\n"
					+ "	Quicksort --rff < randomNumber.txt\n" + "	Quicksort 3 42 892 777 ...\n");
		}

		
		if (args.length == 2) {
			switch (args[0]) {
			case "Basic":
				run_Basic_thread_calss(args[1]);
				break;
			// handle the file name only
			case "Quicksort":
				run_Quicksort_starter(args[1]);
				break;
			}

		}

		else if (args.length > 2) {
			switch (args[0]) {
			case "Quicksort":
				run_Quicksort_starter(args);
				break;
			}
		}

	}

	/**
	 * This function will start the quick sort starter with a file name or --rff
	 * 
	 * @param flieName the file name that user typed in
	 */
	private static void run_Quicksort_starter(String flieName) {

		new Quicksort_starter(flieName);

	}

	/**
	 * This function will start the quick sort starter with a stand in array
	 * 
	 * @param input_numbers an array of numbers that user typed in
	 */
	private static void run_Quicksort_starter(String[] input_numbers) {
		new Quicksort_starter(input_numbers);
	}

	/**
	 * This function will start the basic thread class that will show you a small
	 * demo of java muti_thread
	 * 
	 * @param inp the number of threads you want
	 * @throws InterruptedException if the input is not a number
	 */
	private static void run_Basic_thread_calss(String inp) throws InterruptedException {
		System.out.println("Starting Basic Demo\n");
		int num_thread = check_is_number(inp);
		for (int i = 0; i < num_thread; i++) {
			Basic_thread t = new Basic_thread();
			t.start();
		}
	}

	/**
	 * this function will check the input is number
	 * 
	 * @param inp the string that should be a number in string form
	 * @return if the input is a number, return the number in int. if the input is
	 *         not a number, return null
	 * 
	 */
	private static int check_is_number(String inp) {
		try {
			return Integer.parseInt(inp);
		} catch (Exception e) {
			System.out.println(
					"Can you check your input agruments?" + " You typed in a non-number in the number position.");
			System.exit(1);
		}
		return 0;
	}

}
