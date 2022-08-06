public class Permutation {

	public static void main(String[] args) {
//		permutation("abc","");
		distinctPermutation("aa","");
	}
	
	private static void permutation(String ros, String prefix) {
		if(ros.length()==0){
			System.out.print(prefix+" ");
			return;
		}
		for (int i = 0; i < ros.length(); i++) {
			char ch=ros.charAt(i);
			String str=ros.substring(0,i)+ros.substring(i+1);
			permutation(str, prefix+ch);
		}
	}
	
	public static void distinctPermutation(String re, String prefix){
		boolean[] visited=new boolean[128];
		if(re.length()==0){
			System.out.println(prefix);
		}else{
			char[] chars=re.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int asci=chars[i];
				if(visited[asci]==true){
					continue;
				}
				visited[asci]=true;
				String remindar=re.substring(0, i)+re.substring(i+1);
				distinctPermutation(remindar,prefix+chars[i]);
			}
		}
	}

}
