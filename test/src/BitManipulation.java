import java.util.ArrayList;
import java.util.List;

public class BitManipulation {

    public static void main(String[] args){
        int n=Integer.parseInt("10000000000",2);
        int m=Integer.parseInt("10011",2);
        int output=insertion(n,m,2,6);
        //5.1
//        System.out.println(Integer.toBinaryString(output));

        //5.2
//        System.out.println(getBinary(0.75));

        //5.3
//        System.out.println(getLongest(1775));

        //5.4
        int i=19;
        System.out.println(Integer.toBinaryString(i));
        int o=printNextSmaller(i);
        System.out.println(Integer.toBinaryString(o));
        o=printNextLarger(i);
        System.out.println(Integer.toBinaryString(o));
    }

    //5.4
    public static int printNextLarger(int n){
        int c=n;
        int count0=0;
        int count1=0;
        while(c!=0 && (c&1)==0){
                count0++;
                c=c>>1;
        }
        while(c!=0 && (c&1)==1){
            count1++;
            c=c>>1;
        }

        int p=count0+count1;
        int mask=~((1<<p)-1);
        n=mask&n;

        n=n|1<<p;

        n=n|(1<<count1-1)-1;
        return n;
    }
    public static int printNextSmaller(int n){
        int c=n;
        int count0=0;
        int count1=0;
        while(c!=0 && (c&1)==1){
            count1++;
            c=c>>1;
        }
        while(c!=0 && (c&1)==0){
            count0++;
            c=c>>1;
        }

        int p=count0+count1;
        int mask=~((1<<p)-1);
        n=mask&n;

        n=n&~(1<<p);

        n=flipto1(n,p-1,count1+1);
        return n;
    }
    private static int flipto1(int n, int p, int num1) {
        int mask=1<<p;
        while (num1>0){
            n=n|mask;
            mask=mask>>1;
            num1--;
        }
        return n;
    }

    //5.3
    public static int getLongest(int n){
        int current=n;
        int count=0;
        while(current>0){
            current=current/2;
            count++;
        }
        List<Integer> list=new ArrayList<>();
        int seq=0;
        for (int i = 0; i <= count; i++) {
            if((n&(1<<i))>=1){
                seq++;
            }else{
                list.add(seq);
                seq=0;
                list.add(0);
            }
        }
        list.add(seq);
        int max=0;
        for (int i = 2; i < list.size(); i++) {
            int sum=list.get(i-2)+ list.get(i-1)+list.get(i);
            if(sum>max) max=sum;
        }
        return max+1;
    }

    //5.2
    public static String getBinary(double m){
        while (m%1!=0){
            if(m>Math.pow(2,32)) return "error";
            m=m*10;
        }
        int n=(int)m;
        StringBuilder sb=new StringBuilder();
        int count=0;
        while(n>1&count<32){
            int re=(int)n%2;
            sb.append(re);
            count++;
            n=n/2;
        }
        sb.append(n);
        sb.reverse();
        return sb.toString();
    }
    //5.1
    public static int insertion(int n, int m, int i, int j){
        int mask=m<<i;
        int right=(1<<i)-1; //seq 1
        int left=(-1<<(j+1)); // seq 1
        int add=left | right;
        return(n & add)|mask;
    }
}
