public class BinarySearch {

    public static int binarySearch(int[] a, int x){
        int p=0;
        int r=a.length-1;

        while(p<=r){
            int q=(r+p)/2;
            if(a[q]==x){
                return q;
            }else if(a[q]<x){
                p=q+1;
            }else{
                r=q-1;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        int[] a= new int[] {1,2,3,4,7,9,12,18};
        System.out.println(binarySearch(a,9,0,a.length-1));
    }

    public static int binarySearch(int[] a, int x, int p, int r){
        if(p<=r) {
            int q = (r + p) / 2;
            if (a[q] == x) {
                return q;
            } else if (a[q] < x){
                return binarySearch(a,x,q+1,r);
            }else{
                return binarySearch(a,x,p,q-1);
            }
        }
        return 0;
    }
}
