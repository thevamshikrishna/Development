import java.security.cert.CollectionCertStoreParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String input="swiss";

//        HashMap<Character,Integer> map=new HashMap<>();
//
//        for(Character ch:input.toCharArray()){
//            map.put(ch,map.getOrDefault(ch,0)+1);
//        }
//
//        for (Map.Entry<Character,Integer> entry:map.entrySet()){
//            if(entry.getValue()==1){
//                System.out.println(entry.getKey());
//                break;
//            }
//        }

       Character str= input.chars().mapToObj(s->(char)s).collect(Collectors.groupingBy(s->s,Collectors.counting())).entrySet().stream().filter(s->s.getValue()==1).map(s->s.getKey()).findFirst().orElse(null);

        System.out.println(str);
    }
}
