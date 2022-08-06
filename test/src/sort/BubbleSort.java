package sort;

public class BubbleSort {

	public static void main(String[] args) {
		int[] array={22,11,5,65,12,43,33};
		//bubbleSort(array);
		RecursiveBubbleSort(array, array.length);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
	}

	private static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length-i-1; j++) {
				if(array[j]>array[j+1]){
					int temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
		
	}
	
	private static void RecursiveBubbleSort(int[] array, int n){
		if(n==1){
			return;
		}
		for (int i = 0; i < n-1; i++) {
			if(array[i]>array[i+1]){
				int temp=array[i];
				array[i]=array[i+1];
				array[i+1]=temp;
			}
		}
		RecursiveBubbleSort(array,n-1);
	}
}
