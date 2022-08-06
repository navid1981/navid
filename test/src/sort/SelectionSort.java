package sort;

public class SelectionSort {

	public static void main(String[] args) {
		int[] array={22,3,5,65,12,43,33};
		selectionSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
	}
	public static void selectionSort(int[] array){
		for (int i = 0; i < array.length-1; i++) {
			int min=i;
			for (int j = i+1; j < array.length; j++) {
				if(array[j]<array[min]){
					min=j;
				}
			}
			int temp=array[i];
			array[i]=array[min];
			array[min]=temp;
		}
	}
}
