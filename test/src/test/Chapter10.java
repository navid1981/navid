package test;

import java.util.*;

public class Chapter10 {
    public static void main(String[] args){
        int[] arr={7123,5342,4456,67,567};
//        mergeSort(arr,0, arr.length-1);
//        quickSort(arr,0, arr.length-1);
//        radixSort(arr);
//        System.out.println();;

        //10.1
//        int[] a={1,5,7,23,56,0,0,0,0};
//        int[] b={8,9,12,45};
//        mergeSortedArrays(a,b,4,3);
//        System.out.println();

        //10.3
//        int[] c={6,7,1,2,3,4,5};
//        System.out.println(find(c,6,0,c.length-1));

        //10.9
//        int mat[][] = {
//                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
//                {10, 11, 12, 13, 14, 15, 16, 17, 18, 19},
//                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
//                {30, 31, 32, 33, 34, 35, 36, 37, 38, 39},
//        };
//        findElementInSortedMatrix(mat,15,0,mat.length-1,0,mat[0].length-1);

//        10.11
        int[] arr1={5,3,1,2,3,4,8,9,10};
        sortPeaksValleys(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]+", ");
        }
    }

    //10.11
    static int[] sortPeaksValleys(int[] arr){
        for (int i = 0; i < arr.length-2; i++) {
            if((arr[i]<arr[i+1] && arr[i+1]<arr[i+2]) || (arr[i]>arr[i+1] && arr[i+1]>arr[i+2])){
                int temp=arr[i+1];
                arr[i+1]=arr[i+2];
                arr[i+2]=temp;
            }

        }
        return arr;
    }

    //10.9
    static void findElementInSortedMatrix(int[][] matrix, int n, int up, int down, int left, int right){
        if(down<up || left>right) return;
        int midRow=(up+down)/2;
        int midColumn=(left+right)/2;
        if(matrix[midRow][midColumn]==n) System.out.println("row:"+midRow+", column:"+midColumn);
        if(matrix[up][midColumn]>n){
            right=midColumn-1;
        }
        if(matrix[down][midColumn]<n){
            left=midColumn+1;
        }

        if(matrix[midRow][right]<n){
            up=midRow+1;
        }
        if(matrix[midRow][left]>n){
            down=midRow-1;
        }
        findElementInSortedMatrix(matrix,n,up,down,left,right);
    }

    //10.3
    static int find(int[] arr, int n, int p, int r){
        if(p>r) return -1;
        int q=(p+r)/2;
        if(arr[q]==n) return q;
        if(arr[q]>n && n>arr[p]){
            return find(arr,n, p,q-1);
        }else if(arr[q]<n && n<arr[r]){
            return find(arr,n, q+1,r);
        }else if(arr[q]<arr[p]){
            return find(arr,n, p,q-1);
        }else if(arr[q]>arr[r]){
            return find(arr,n, q+1,r);
        }else{
            int result=find(arr,n, p,q-1);
            if(result!=-1) return result;
            return find(arr,n, q+1,r);
        }
    }





    //10.1
    static void mergeSortedArrays(int[] a,int[] b, int lastA, int lastB){
//        int[] temp=new int[lastA+lastB+2];
        int aIndex=lastA;
        int bIndex=lastB;
        for (int i = a.length-1; i >=0 ; i--) {
            if(bIndex<0 || (aIndex>=0 && a[aIndex]> b[bIndex])){
                a[i]=a[aIndex];
                aIndex--;
            }else{
                a[i]=b[bIndex];
                bIndex--;
            }
        }

    }

    static void mergeSort(int[] arr, int p, int r){
        if(p<r){
            int q=(p+r)/2;
            mergeSort(arr,p,q);
            mergeSort(arr,q+1,r);
            merge(arr,p,r);
        }
    }

    private static void merge(int[] arr, int p, int r) {
        int[] helper=new int[r-p+1];
        for (int i = 0; i < helper.length; i++) {
            helper[i]=arr[p+i];
        }
        int leftIndex=0;
        int middle=(helper.length-1)/2;
        int rightIndex= middle+1;
        for (int i = p; i <=r ; i++) {
            if((rightIndex<helper.length && helper[leftIndex]>helper[rightIndex]) || leftIndex>middle){
                arr[i]=helper[rightIndex];
                rightIndex++;
            }else{
                arr[i]=helper[leftIndex];
                leftIndex++;
            }
        }
    }

    static void quickSort(int[] arr,int p,int r){
        if(p<r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    private static int partition(int[] arr, int p, int r) {
        int pivotal=arr[r];
        int index=p-1;
        for (int i = p; i <=r ; i++) {
            if(pivotal>=arr[i]){
                index++;
                int temp=arr[index];
                arr[index]=arr[i];
                arr[i]=temp;
            }
        }
        return index;
    }

    static void radixSort(int[] arr){
        int max=getMax(arr);
        for (int i = 1; i < max; i*=10) {
            radixSort(arr,i);
        }
    }

    private static void radixSort(int[] arr, int exp) {
        int[] count=new int[10];
        int[] temp=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i]=arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            count[(arr[i]/exp)%10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i]+=count[i-1];
        }
        for (int i = temp.length-1; i >= 0; i--) {
            temp[count[(arr[i]/exp)%10]-1]=arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i]=temp[i];
        }
    }

    private static int getMax(int[] arr) {
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }

}
