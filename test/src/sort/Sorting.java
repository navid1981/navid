package sort;

public class Sorting {

    public static void selectionSort(int[] array){

        for (int i = 0; i < array.length; i++) {

            int num=i;
            for (int j = i+1; j < array.length ; j++) {
                if(array[j]<=array[num]){
                    num=j;
                }
            }
            int temp=array[i];
            array[i]=array[num];
            array[num]=temp;
        }
    }

    public static void insertionSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int j=i;
            int value=array[i];
            while(j>0 && array[j]<array[j-1]){
//                int temp=array[j];
                array[j]=array[j-1];
                array[j-1]=value;
                j--;
            }

        }
    }

    public static void mergeSort(int[] array, int p, int r){
        if(p<r){
            int q=(p+r)/2;
            mergeSort(array, p,q);
            mergeSort(array,q+1,r);
            sort(array,p,r);
        }

    }

    private static void sort(int[] array, int p, int r) {
        int[] result=new int[r-p+1];
        for (int i = 0; i < result.length; i++) {
            result[i]=array[p+i];
        }
        int a=0;
        int b=((result.length-1)/2)+1;
        for (int i = p; i <= r ; i++) {
            if(a<((result.length-1)/2)+1 && (b==result.length || result[a]<=result[b])){
                array[i]=result[a];
                a++;
            }else{
                array[i]=result[b];
                b++;
            }
        }
    }

    public static void quickSort(int[] a, int p, int r){
        if(p<r){
            int q=partition(a,p,r);
            quickSort(a,p,q-1);
            quickSort(a,q+1,r);
        }
    }

    private static int partition(int[] a, int p,int r){
        int i=p-1;
        int pi=a[r];
        for (int j = p; j <= r; j++) {
            if(a[j]<=pi){
                i++;
                int temp=a[i];
                a[i]=a[j];
                a[j]=temp;
            }
        }
        return i;
    }
    public static void main(String[] arg){
        int[] array={3,1,6,2,1,8,6,12,7};
//        selectionSort(array);
//        insertionSort(array);
//        mergeSort(array,0, array.length-1);
        quickSort(array,0,array.length-1);
        for (int i = 0; i <array.length ; i++) {
            System.out.println(array[i]);
        }


    }
}
