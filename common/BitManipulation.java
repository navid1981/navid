public class BitManipulation {

    public static void main(String[] args){
        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(-1>>>2));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE>>1));
//        System.out.println((int)Long.parseLong("11000000000000000000000000000000", 2));
//        System.out.println((int)Long.parseLong("10000000000000000000000000000000", 2));

//        int num=15;
//        System.out.println(Integer.toBinaryString(num));
//        System.out.println(Integer.toBinaryString(clearBitMSBthroughI(num,2)));
//        System.out.println(Integer.toBinaryString(clearBitIthrough0(num,2)));

//        System.out.println(Integer.toBinaryString('a'));
//        System.out.println(Integer.toBinaryString('A'));
//        System.out.println((char)('f'-32));
//        System.out.println(Integer.toBinaryString('b'-32));

//        System.out.println(numberOfSetBit(5));
        System.out.println(0x1F);
    }

    static boolean getBitAtI(int num, int i){
        return ((num & (1<<i)) !=0);
    }

    static int setBitAtI(int num, int i){
        num |=1<<i;
        return num;
    }

    static int clearBitAtI(int num, int i){
        int mask=~(1<<i);
        num &=mask;
        return num;
    }

    static int clearBitMSBthroughI(int num, int i){
        int mask=(1<<i)-1;
        num &=mask;
        return num;
    }

    static int clearBitIthrough0(int num, int i){
        int mask=-1<<(i+1);
        return num & mask;
    }

    static boolean isEvenNumber(int num){
        return (num & 1)==0;
    }

    static int numberOfSetBit(int n){
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
