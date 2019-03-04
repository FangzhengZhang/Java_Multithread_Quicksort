/**
 * This class is a small version of multi-thread quicksort
 * @author fangzheng
 *
 */
public class Quicksort_small extends Thread{

	private int begin_pos, end_pos, pivot, fix_pos;
	boolean isDone = false;
	private String[] arr ;
	
	//set all the values for the sort 
	public Quicksort_small(int p_begin, int p_end, String[] array) {
		begin_pos = p_begin+1;
		end_pos = p_end-1;
		this.pivot = (begin_pos + end_pos)/2;
		fix_pos = -1;
		if(array == null) {
			arr = Quicksort_starter.input_num_str;
		}else {
			arr = array;
		}
	}

	@Override
	public void run() {
		//
		if(end_pos - begin_pos == 1) {
			if(Integer.parseInt(arr[begin_pos]) > Integer.parseInt(arr[end_pos]) ){
				pswap(begin_pos , end_pos);
			}
			isDone = true;
			fix_pos = begin_pos+1;
			return;
		}
		//System.out.println(begin_pos + " " +  end_pos);
		int pt = Integer.parseInt(arr[pivot]);
		//swap the pivot to the end
		pswap(pivot, end_pos);
		int begin_p = begin_pos, end_p = end_pos-1;
		//begin the sort presses 
		while(begin_p <= end_p) {
			//move the beginning pointer 
			if(Integer.parseInt(arr[begin_p]) > pt) {
				//found the bigger value 
				if(Integer.parseInt(arr[end_p])<pt) {
					//found the small value 
					pswap(begin_p, end_p);
				}else {
					end_p--;
				}
			}else {
				begin_p++;
			}
		}
		//last swap
		pswap(begin_p, end_pos);
		fix_pos = begin_p;
		
	}
	
	/**
	 * to swap the value in two position
	 * @param p1 the first value position 
	 * @param p2 the second value position 
	 */
	private void pswap(int p1, int p2) {
		String temp = arr[p1];
		arr[p1] = arr[p2];
		arr[p2] = temp;
	}
	
	/**
	 * the getter for the fix position 
	 * @return the fix position
	 */
	public int get_fix_pos() {
		return fix_pos;
	}
	
	/**
	 * the check if the sort is done 
	 * @return isDone? 
	 */
	public boolean check_done() {
		return isDone;
	}
	
	public String[] get_whole_input_array() {
		return arr;
	}

}
