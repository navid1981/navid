package sort;

public class InsertationSort {

	public static void main(String[] args) {
		int[] array={22,1,5,65,12,43,33};
		insertationSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
	}

	public static void insertationSort(int[] array){
		for (int i = 0; i < array.length-1; i++) {
			for (int j = i+1; j >0; j--) {
				if(array[j]<array[j-1]){
					int temp=array[j];
					array[j]=array[j-1];
					array[j-1]=temp;
				}else{
					break;
				}
			}
		}
	}
}
