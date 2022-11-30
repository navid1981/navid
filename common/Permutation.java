import java.util.*;
import java.util.stream.Collectors;

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

//	Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();

		HashMap<Integer, Integer> counter = new HashMap<>();
		for (int num : nums) {
			if (!counter.containsKey(num))
				counter.put(num, 0);
			counter.put(num, counter.get(num) + 1);
		}

		LinkedList<Integer> comb = new LinkedList<>();
		this.backtrack(comb, nums.length, counter, results);
		return results;
	}

	private void backtrack(LinkedList<Integer> comb, Integer N, HashMap<Integer, Integer> counter, List<List<Integer>> results) {
		if (comb.size() == N) {
			results.add(new ArrayList<Integer>(comb));
			return;
		}

		for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
			Integer num = entry.getKey();
			Integer count = entry.getValue();
			if (count == 0)
				continue;

			comb.addLast(num);
			counter.put(num, count - 1);

			backtrack(comb, N, counter, results);

			comb.removeLast();
			counter.put(num, count);
		}
	}
	//second way
	List<List<Integer>> lists=new ArrayList<>();
	public List<List<Integer>> permuteUnique2(int[] nums) {
		LinkedList<Integer> list=new LinkedList<>();
		List<Integer> re=Arrays.stream(nums).boxed().collect(Collectors.toList());
		recursion(list,re);
		return lists;
	}

	public void recursion(LinkedList<Integer> list, List<Integer> re){
		if(re.size()==0) {
			List<Integer> nList=new LinkedList<>(list);
			lists.add(nList);
			return;
		}
		Set<Integer> set=new HashSet<>();
		int size=re.size();
		for(int i=0;i<size;i++){
			int v=re.get(i);
			if(set.contains(v)) continue;
			set.add(v);
			list.addLast(v);
			re.remove(i);
			recursion(list,re);
			re.add(i,v);
			list.removeLast();

		}
	}

}
