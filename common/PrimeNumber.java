package algo.navid.common;

public class PrimeNumber {

	public static void main(String[] args) {
		System.out.println(isPrime(6));

	}
	// O(square root N)
	static boolean isPrime(int a){
		for (int i = 2; i*i<=a; i++) {
			if(a%i==0){
				return false;
			}
		}
		return true;
	}
}
