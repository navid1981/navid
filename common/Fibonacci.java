

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(fib(12));
//
//		int n=8;
//		int[] mem=new int[n+1];
//		for (int i = 0; i <= n; i++) {
//			fibByMem(i,mem);
//		}
//		for (int i = 0; i < mem.length; i++) {
//			System.out.print(mem[i]+", ");
//		}

		System.out.println(fib2(12));
	}
	//O(2^n)
	public static int fib(int n){
		if(n<=0) return 0;
		else if(n==1) return 1;
		else return fib(n-1)+fib(n-2);
	}
	//O(n)
	public static int fibByMem(int n,int[] mem){
		if(n<=0) return 0;
		else if(n==1) return 1;
		else if(mem[n]>0) return mem[n];
		else{
			mem[n]=fibByMem(n-1,mem)+fibByMem(n-2,mem);
			return mem[n];
		}
	}
	//bottom-top approach
	public static int fib2(int n){
		int[] nums=new int[n];
		nums[0]=0;
		nums[1]=1;
		for (int i = 2; i <n ; i++) {
			nums[i]=nums[i-1]+nums[i-2];
		}
		return nums[n-1]+nums[n-2];
	}
}
