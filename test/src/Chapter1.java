public class Chapter1 {

    public static void main(String[] args){
        //1.1
//        System.out.println(isUniqCharacter("tesl"));

        //1.2
//        System.out.println(permutation("salam","lamas"));

        //1.3
//        System.out.println(URLify("Mr John Smith    ".toCharArray(),13));

        //1.4
//        System.out.println(isPalindrome("tact coa"));

        //1.5
//        System.out.println(isOneEdit("pale","bake"));

        //1.6
//        System.out.println(compression("abcccccca"));

        int mat[][] = {
                { 5, 5, 5, 5, 5, 5, 5, 5, 5, 2 },
                { 4, 5, 5, 5, 5, 5, 5, 5, 2, 2 },
                { 4, 4, 1, 0, 1, 1, 1, 0, 2, 2 },
                { 4, 4, 1, 1, 1, 0, 1, 1, 2, 2 },
                { 4, 4, 0, 1, 0, 0, 0, 1, 2, 2 },
                { 4, 4, 1, 1, 1, 0, 0, 1, 2, 2 },
                { 4, 4, 0, 0, 1, 0, 0, 1, 2, 2 },
                { 4, 4, 1, 1, 1, 1, 1, 1, 2, 2 },
                { 4, 4, 3, 3, 3, 3, 3, 3, 3, 2 },
                { 4, 3, 3, 3, 3, 3, 3, 3, 3, 3 },
        };
        //1.7
        rotateMatrix(mat);

        //1.8
        int[][] mat2={
                {0,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,0,1}
        };
//        zeroMatrix(mat2);

        //1.9
//        System.out.println(isSubString("waterbottle","erbottlewat"));
    }

    //1.9
    static boolean isSubString(String s1, String s2){
        if(s1.length()!=s2.length()) return false;
        String s=s1+s1;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==s2.charAt(0)){
                for (int j = 0; j < s2.length() & i+j<s.length(); j++) {
                    if(s.charAt(i+j)!=s2.charAt(j)){
                        break;
                    }
                    if(j==s2.length()-1) return true;
                }
            }
        }
        return false;
    }

    //1.8
    static int[][] zeroMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j]==0){
                    matrix[i][0]=Integer.MAX_VALUE;
                    matrix[0][j]=Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if(matrix[i][0]==Integer.MAX_VALUE){
                for (int j = 0; j <matrix[i].length ; j++) {
                    matrix[i][j]=0;
                }
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if(matrix[0][i]==Integer.MAX_VALUE){
                for (int j = 0; j <matrix.length ; j++) {
                    matrix[j][i]=0;
                }
            }
        }

        if(matrix[0][0]==0){
            for (int j = 0; j <matrix[0].length ; j++) {
                matrix[0][j]=0;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+", ");
            }
            System.out.println();
        }
        return matrix;
    }

    //1.7
    static int[][] rotateMatrix(int[][] matrix){
        int level= matrix.length/2;

        for (int i = 0; i < level; i++) {
//            int fIndex=i;
            int lIndex= matrix.length-1;
            for (int j = i; j <lIndex-i ; j++) {
//                int tmp=matrix[i][lIndex-j];
                int temp=matrix[i][j];
                //up=left
//                matrix[i][lIndex-j]=matrix[j][i];
                matrix[i][j]=matrix[lIndex-j][i];
                //left=down
//                matrix[j][i]=matrix[lIndex-i][j];
                matrix[lIndex-j][i]=matrix[lIndex-i][lIndex-j];
                //down=right
//                matrix[lIndex-i][j]=matrix[lIndex-j][lIndex-i];
                matrix[lIndex-i][lIndex-j]=matrix[j][lIndex-i];
                //right=up
                matrix[j][lIndex-i]=temp;
//                matrix[lIndex-j][lIndex-i]=tmp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+", ");
            }
            System.out.println();
        }
        return matrix;
    }
    //1.6
    static String compression(String s){
        StringBuilder sb=new StringBuilder();
        sb.append(s.charAt(0));
        int count=1;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i)==s.charAt(i-1)){
                count++;
            }else{
                sb.append(count);
                sb.append(s.charAt(i));
                count=1;
            }
        }
        sb.append(count);
        String result=sb.toString();
        return result.length()<s.length()?result:s;
    }

    //1.5
    static boolean isOneEdit(String s1, String s2){
        if(s1.length()==s2.length()) {
            boolean edit =false;
            for (int i = 0; i < s1.length(); i++) {
                if(s1.charAt(i)!=s2.charAt(i)){
                    if(edit) return false;
                    edit=true;
                }
            }
            return true;
        }else{
            if(Math.abs(s1.length()-s2.length())>1) return false;
            if(s1.length()>s2.length()){
                return insertRemove(s2,s1);
            }else{
                return insertRemove(s1,s2);
            }
        }
    }

    private static boolean insertRemove(String s1, String s2) {
        boolean edit=false;
        for (int i = 0,j=0; i < s1.length(); i++,j++) {
            if(s1.charAt(i)!=s2.charAt(j)){
                if(edit) return false;
                i--;
                edit=true;
            }
        }
        return true;
    }

    //1.4
    static boolean isPalindrome(String s){

        int[] check=new int['z'-'a'+1];
        int oddCount=0;
        for (int i = 0; i < s.length(); i++) {
            int value=getNumber(s.charAt(i));
            if(value<0) continue;
            check[value]++;
            if(check[value]%2==1){
                oddCount++;
            }else{
                oddCount--;
            }
        }
        if(oddCount>1) return false;
        return true;
    }
    static int getNumber(char c){
        int v=c;
        int a='a';
        int z='z';
        if(v>=a && v<=z) return v-a;
        else return -1;
    }

    //1.3
    static String URLify(char[] chars, int trueLength){
        int count=0;
        for (int i = 0; i <trueLength ; i++) {
            if(chars[i]==' ') count++;
        }
        int index=trueLength+count*2;
        for (int i = trueLength-1; i >-1; i--) {
            if(chars[i]==' '){
                chars[index-1]='0';
                chars[index-2]='2';
                chars[index-3]='%';
                index=index-3;
            }else{
                chars[index-1]=chars[i];
                index--;
            }
        }
        return chars.toString();
    }

    //1.2
    static boolean permutation(String s1, String s2){
        if(s1.length() != s2.length()) return false;
        int[] check =new int[128];
        for (int i = 0; i < s1.length(); i++) {
            int value=s1.charAt(i);
            check[value]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            int value=s2.charAt(i);
            check[value]--;
            if(check[value]<0) return false;
        }
        return true;
    }

    //1.1
    static boolean isUniqCharacter2(String s){
        boolean[] check=new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            int value=s.charAt(i);
            if(check[value]){
                return false;
            }
            check[value]=true;
        }
        return true;
    }
    //1.1
    static boolean isUniqCharacter(String s){
        char[] chars=s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            char c=chars[i];
            for (int j = i+1; j < chars.length; j++) {
                if(c==chars[j]) return false;
            }
        }
        return true;
    }
}
