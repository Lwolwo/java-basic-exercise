import java.util.*;
import java.util.stream.*;

public class GrammarExercise {
    public static void main(String[] args) {

        while(true) {
            //需要从命令行读入
            String firstWordList = "";
            String secondWordList = "";

            Scanner scanner = new Scanner(System.in);
            firstWordList = scanner.nextLine();
            secondWordList = scanner.nextLine();

            List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
            //按要求输出到命令行
//            System.out.println(result);
            result.stream().forEach(System.out::println);
        }
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> wordList1 = new ArrayList<String>(Arrays.asList(firstWordList.split(",")));
        List<String> wordList2 = new ArrayList<String>(Arrays.asList(secondWordList.split(",")));

        List<String> upperWordList1 = wordList1.stream().map(word -> {
            if (word.equals("") || word.matches(".*[^a-zA-Z].*")) {
                throw new RuntimeException("input not valid");
            }
            return word.toUpperCase();
        }).collect(Collectors.toList());

        List<String> upperWordList2 = wordList2.stream().map(word -> {
            if (word.equals("")) {
                throw new RuntimeException("input not valid");
            }
            return word.toUpperCase();
        }).collect(Collectors.toList());

        List<String> repeat = upperWordList1.stream()
                .filter(word -> upperWordList2.contains(word))
                .distinct()
                .map(word -> {
                    String regex = "(.{1})";
                    word = word.replaceAll(regex,"$1 ");
                    return word.trim();
                })
                .collect(Collectors.toList());

//        System.out.println(repeat);
        return repeat;
    }
}
