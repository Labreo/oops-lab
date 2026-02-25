


public class Anagramcheck {
    public static boolean anagram(String s1,String s2) {
        boolean flag = false;
        for(char c1:s1.toCharArray()){
         for(char c2:s2.toCharArray()){
            if(c1==c2){
             flag=true;
            }
         } 
         if(flag == false){
            return false;
         }
         flag = false;
        }
        return true;
    }
    public static void main(String[] args) {
       
        String s1;
        s1=args[0];
         s1=s1.toLowerCase();
         String s2=args[1];
        s2=s2.toLowerCase();
        if(s1.length()!=s2.length()){
            System.out.println("The strings are not the same length so they cannot be anagrams.");
        }else{
            if(anagram(s1, s2)){
                System.out.println("They are anagrams");
            }else{
                System.out.println("They are not anagrams");
            }
        }
    }
}
