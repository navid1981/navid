import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SortingSearching {

    public static void main(String[] args) throws FileNotFoundException {
        int[] a = {1, 3, 4, 7, 0, 0, 0};
        int[] b = {2, 4, 6};
        //10.1
//        merge(a,b,3,b.length-1);
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]+", ");
//        }

        //10.2
//        String[] s={"hello","ali","llohe"};
//        sortAnagram(s);
//        for (int i = 0; i < s.length; i++) {
//            System.out.print(s[i]+", ");
//        }

        //10.3
//        int[] c={6,7,1,2,3,4,5};
//        System.out.println(searchInRotatedArray(c,5 ,0,c.length-1));

        //10.4
//        Listy list=new Listy();
//        list.addElement(1);
//        list.addElement(2);
//        list.addElement(3);
//        list.addElement(4);
//        System.out.println(find(list,1));
//        String[] arr={"ab","","","","ac","","","ad","ae",""};
//        System.out.println(findString(arr,"af",0,arr.length-1));

        //10.7
//        findMissing();

        //10.9
//        int mat[][] = {
//                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
//                {10, 11, 12, 13, 14, 15, 16, 17, 18, 19},
//                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
//                {30, 31, 32, 33, 34, 35, 36, 37, 38, 39},
//        };
//        System.out.println(findInMatrix(mat, 4, 0, mat[0].length - 1));

        //10.10
//        BTS bts = new BTS();
//        bts.methodTrack(5);
//        bts.methodTrack(1);
//        bts.methodTrack(4);
//        bts.methodTrack(4);
//        bts.methodTrack(5);
//        bts.methodTrack(9);
//        bts.methodTrack(7);
//        bts.methodTrack(13);
//        bts.methodTrack(3);
//        System.out.println(bts.getRankOfNumber(5));

        //10.11
//        int[] arr={5,3,1,2,3};
//        getPeakAndValley(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]+", ");
//        }

    }

    //10.11
    public static int[] getPeakAndValley(int[] arr){

        for (int i = 1; i < arr.length-1; i++) {
            if(arr[i+1]>arr[i] && arr[i+1]>arr[i-1]){
                int temp=arr[i+1];
                arr[i+1]=arr[i];
                arr[i]=temp;
            }else if(arr[i-1]>arr[i] && arr[i-1]>arr[i+1]){
                int temp=arr[i-1];
                arr[i-1]=arr[i];
                arr[i]=temp;
            }
            i++;
        }

        return arr;
    }
    //10.9
    public static boolean findInMatrix(int[][] mat, int num, int row, int column) {
        int m = mat.length - 1;
        int n = mat[0].length - 1;
        while (row <= m && column >= 0) {
            if (mat[row][column] == num) return true;
            else if (mat[row][column] < num) {
                return findInMatrix(mat, num, row + 1, column);
            } else {
                return findInMatrix(mat, num, row, column - 1);
            }
        }
        return false;
    }

    //10.8
    public static void getDublicate(int[] arr) {
        BitSet bs = new BitSet(32000);
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int num0 = num - 1;
            if (bs.get(num0)) {
                System.out.println(num);
            } else {
                bs.set(num0);
            }
        }
    }

    //10.7
    public static void findMissing() throws FileNotFoundException {
        Scanner scanner=new Scanner(new FileReader("C:\\Users\\nsalehvaziri\\OneDrive\\code\\navid\\test\\src\\test.txt"));
        long number=((long)Integer.MAX_VALUE)+1;
        byte[] buffer=new byte[(int)(number/8)];
        while (scanner.hasNext()){
            int value=scanner.nextInt();
            buffer[value/8]|=1<<value%8;
        }
        for (int i = 0; i < buffer.length; i++) {
            int value=buffer[i];
            for (int j = 0; j < 8; j++) {
                if((value&1<<j)==0) {
                    System.out.println(8*i+j);
                    return ;
                }
            }
        }
    }
    //10.5
    public static int findString(String[] arr, String s, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].equals("")) {
                mid = getMid(arr, mid, low, high);
                if (mid == -1) return -1;
            }
            if (s.compareTo(arr[mid]) == 0) {
                return mid;
            } else if (s.compareTo(arr[mid]) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        return -1;
    }

    private static int getMid(String[] arr, int mid, int low, int high) {
        int current = mid;
        while (current >= low) {
            if (!arr[current].equals("")) return current;
            current--;
        }
        current = mid;
        while (current <= high) {
            if (!arr[current].equals("")) return current;
            current++;
        }
        return -1;
    }

    //10.4
    public static int find(Listy list, int a) {
        int i = 1;
        while (list.elementAt(i) != -1 && a > list.elementAt(i)) {
            i *= 2;
        }
        int low = i / 2;
        int high = i;
        return find(list, a, low, high);
    }

    private static int find(Listy list, int a, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (list.elementAt(mid) == a) return mid;
        else if (list.elementAt(mid) > a || list.elementAt(mid) == -1) return find(list, a, low, mid - 1);
        else return find(list, a, mid + 1, high);
    }

    //10.3
    public static int searchInRotatedArray(int[] arr, int a, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a == arr[mid]) return mid;
            else if (a > arr[mid] && a <= arr[high]) {
                low = mid + 1;
            } else if (a < arr[mid] && a >= arr[low]) {
                high = mid - 1;
            } else if (arr[mid] < arr[low]) {
                high = mid - 1;
            } else if (arr[mid] == arr[low]) {
                if (arr[mid] != arr[high]) {
                    low = mid + 1;
                } else {
                    int result = searchInRotatedArray(arr, a, mid + 1, high);
                    if (result == -1) return searchInRotatedArray(arr, a, low, mid - 1);
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //10.2
    public static void sortAnagram(String[] arr) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String sort = sort(arr[i]);
            if (map.containsKey(sort)) {
                List<String> list = map.get(sort);
                list.add(arr[i]);
                map.put(sort, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(arr[i]);
                map.put(sort, list);
            }
        }
        int index = 0;
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            for (String s : list) {
                arr[index] = s;
                index++;
            }
        }
    }

    private static String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    //10.1
    public static void merge(int[] a, int[] b, int lastA, int lastB) {
        int j = lastA + lastB + 1;
        while (lastB > -1) {
            if (lastA == -1 || lastB > -1 && b[lastB] > a[lastA]) {
                a[j] = b[lastB];
                lastB--;
            } else {
                a[j] = a[lastA];
                lastA--;
            }
            j--;
        }
    }
}

class Listy {
    private List<Integer> list = new ArrayList<>();

    public void addElement(Integer i) {
        list.add(i);
        Collections.sort(list);
    }

    public int elementAt(int i) {
        if (i < list.size()) return list.get(i);
        return -1;
    }
}

//10.10
class BTSNode {
    int value;
    BTSNode left;
    BTSNode right;
    int leftSize;

    public BTSNode(int value) {
        this.value = value;
    }
}

class BTS {
    BTSNode root;

    public void methodTrack(int x) {
        BTSNode newNode = new BTSNode(x);
        if (root == null) {
            root = newNode;
            return;
        }
        BTSNode current = root;
        BTSNode parent = root;
        while (true) {
            if (x <= current.value) {
                current.leftSize++;
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    break;
                }
            } else {
                newNode.leftSize=current.leftSize+1;
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    break;
                }
            }
            parent = current;
        }
    }

    public int getRankOfNumber(int x) {
        BTSNode current = root;
        while (current != null) {
            if (x == current.value) return current.leftSize;
            else if (x > current.value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return -1;
    }
}