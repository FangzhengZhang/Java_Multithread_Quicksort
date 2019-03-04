import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class is a starter for quicksort, I will handle some starting data
 * presses here
 * 
 * @author fangzheng
 * @version 2/20/2019
 */
public class Quicksort_starter {

	// I set it public static so the thread class can easily read and change it.
	public static String[] input_num_str;
	private ArrayList<Integer> pivot_list = new ArrayList<Integer>();
	private ArrayList<Quicksort_small> thread_list = new ArrayList<Quicksort_small>();
	private int num_of_thread = 0;
	private long t_b, t_e;
	/**
	 * the contractor of Quicksort_starter class for type number in command line
	 * 
	 * @param in_array the number array that user typed in the command line
	 */
	public Quicksort_starter(String[] in_array) {
		input_num_str = new String[in_array.length-1];
		//System.arraycopy(in_array, 1, input_num_str, 0, in_array.length-1);
		for(int i = 0; i<input_num_str.length; i++) {
			input_num_str[i] = in_array[i+1].replaceAll(" ", "");
		}
		t_b = System.nanoTime();
		assign_and_run();
		t_e = System.nanoTime();
		print_result();
	}

	/**
	 * the contractor of Quicksort class for type a file number in command line
	 * 
	 * @param file_name the file name user typed in
	 */
	public Quicksort_starter(String file_name) {
		String rawString = null;
		if (file_name.equals("--rff")) {
			// read from standard input
			Scanner reader = new Scanner(System.in);
			rawString = reader.next();
			reader.close();
		} else {
			// read from file
			Scanner reader;
			try {
				reader = new Scanner(new File(file_name));
				rawString = reader.nextLine();
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.print("Error! Can not find the given file!\n");
				System.exit(1);
			}
		}

		t_b = System.nanoTime();
		input_num_str = cook_raw_data(rawString);
		assign_and_run();
		t_e = System.nanoTime();
		print_result();
		
	}

	/**
	 * This function will created thread and assign jobs to them
	 */
	private void assign_and_run() {
		// Initialize the list
		pivot_list.add(-1);
		pivot_list.add(input_num_str.length);

		// begin the outer loop
		while (pivot_list.size() < input_num_str.length) {
			// create the thread
			for (int i = 0; i < pivot_list.size() - 1; i++) {
				int p_begin = pivot_list.get(i), p_end = pivot_list.get(i + 1);
				if (p_end - p_begin <= 1) {
					continue;
				}
				Quicksort_small tempp = new Quicksort_small(p_begin, p_end, null);
				thread_list.add(tempp);
			}

			// start all the thread
			//System.out.println("Starting all the thread");
			for (int i = 0; i < thread_list.size(); i++) {
				thread_list.get(i).run();
			}
			num_of_thread += thread_list.size();
			
			// wait all the thread done
			for (int i = 0; i < thread_list.size(); i++) {
				try {
					thread_list.get(i).join();
				} catch (InterruptedException e) {
					System.out.println("Join error!!");
					e.printStackTrace();
				}
			}

			// Retrieve data
			for (int i = 0; i < thread_list.size(); i++) {
				if (thread_list.get(i).check_done()) {
					pivot_list.add(thread_list.get(i).get_fix_pos());
					pivot_list.add(thread_list.get(i).get_fix_pos() + 1);
				} else {
					pivot_list.add(thread_list.get(i).get_fix_pos());

				}
				pivot_list.sort(null);
			}

			thread_list.clear();

		}

	}

	/**
	 * This function will process the raw string into an array that can be sorted
	 * 
	 * @param rawString The string read from file or system stand in
	 * @return an array of integer, so I can sort them later
	 */
	private String[] cook_raw_data(String rawString) {
		if (rawString == null) {
			throw new IllegalArgumentException("The input data you given is empty!\n");
		}
		// String temp = rawString.replaceAll(" ", "");
		return rawString.replaceAll(" ", "").split("[" + Runner.DEFULT_SPLIT_CHARS + "]");
	}

	/**
	 * This function will print the result to the stand out
	 */
	private void print_result() {
		for (int i = 0; i < input_num_str.length; i++) {
			if (i != input_num_str.length - 1) {
				System.out.print(input_num_str[i] + ", ");
			} else {
				System.out.print(input_num_str[i] + "\n");
			}
		}
		
		System.out.print(num_of_thread+ " threads had been created" + "\n");
		System.out.print((t_e - t_b)/1000000 + " milliseconds had used" + "\n");
	}

}
