package sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] array={22,1,5,65,12,43};
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
		System.out.println();
		
		int[] arrayR=sortR(array, 0, 5);
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
		System.out.println();
//		sort(array,0,5);
		for (int i = 0; i < arrayR.length; i++) {
			System.out.print(arrayR[i]+",");
		}

	}

	public static void sort(int[] array,int p,int r){
		if(r>p){
			int q=(p+r)/2;
			sort(array,p,q);
			sort(array,q+1,r);
			merge(array,p,r);
		}else{
			return;
		}
	}
	
	public static void merge(int[] array, int p, int r){
		int[] temp=new int[r-p+1];
		for (int i = 0; i < temp.length; i++) {
			temp[i]=array[p+i];
		}
		int l=0;
		int ri=(temp.length-1)/2+1;
		for (int i = p; i <= r; i++) {
			if(ri>=temp.length || (l<((temp.length-1)/2)+1 && temp[l]<temp[ri])){
				array[i]=temp[l];
				l+=1;
			}else{
				array[i]=temp[ri];
				ri+=1;
			}
		}

	}
	
	public static int[] sortR(int[] array,int p,int r){
		if(r>p){
			int q=(p+r)/2;
			int[] le=sortR(array,p,q);
			int[] ri=sortR(array,q+1,r);
			return mergeR(le,ri);
		}else{
			return Arrays.copyOfRange(array, p, r);
		}
	}
	public static int[] mergeR(int[] le,int[] ri){
		int[] temp=new int[le.length+ri.length];

		int l=0;
		int r=0;		
		for (int i = 0; i < temp.length; i++) {
			if(r>=ri.length || (l<le.length-1 && le[l]<ri[r])){
				temp[i]=le[l];
				l+=1;
			}else{
				temp[i]=ri[r];
				r+=1;
			}
		}
		return temp;
	}
}
