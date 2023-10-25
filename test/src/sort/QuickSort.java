package sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] array={22,1,5,65,12,43,33};
		quickSort(array,0,array.length-1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
		char[] c1="s1".toCharArray();
	}
	private static void quickSort(int[] array, int p, int r){
		if(p<r){
			int q=partition(array,p,r);
			quickSort(array,p,q-1);
			quickSort(array,q+1,r);
		}else{
			return;
		}
	}
	private static int partition(int[] array, int p, int r) {
		int j=p-1;
		for (int i = p; i <= r; i++) {
			if(array[i]<=array[r]){
				j+=1;
				int temp=array[j];
				array[j]=array[i];
				array[i]=temp;
			}
		}
		return j;
	}
}
