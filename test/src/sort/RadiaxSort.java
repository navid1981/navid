package sort;

import java.util.Arrays;

public class RadiaxSort {

    public static void main(String[] args){
        int[] arr={2,65,20,34,8,125,111,15,6};
        sortByRadix(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+", ");
        }
    }

    public static int[] sortByRadix(int[] arr){
        int max=getMax(arr);
        for (int i = 1; i < max; i*=10) {
            radixSort(arr,i);
        }
        return arr;
    }

    private static void radixSort(int[] arr, int exp) {
        int[] count=new int[10];
        int[] output=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[(arr[i]/exp)%10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i]+=count[i-1];
        }
        for (int i = arr.length-1; i >=0; i--) {
            output[count[(arr[i]/exp)%10]-1]=arr[i];
            count[(arr[i]/exp)%10]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i]=output[i];
        }
    }

    private static int getMax(int[] arr) {
        int max=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>max) max=arr[i];
        }
        return max;
    }
}
