package com.solvd.wordSearch;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordMain {
    public static final List<String> symbolsBefore = Arrays.asList("\"", " ");
    public static final List<String> symbolsAfter = Arrays.asList("\"", " ", ".", "'", ";");
    public static void main(String[] args) {
        File inputFile = new File("src/main/resources/input.txt");
        File readFile = new File("src/main/resources/article.txt");
        File writeFile = new File("src/main/resources/res.txt");

        if(readFile.canRead()&&writeFile.canWrite()&&inputFile.canRead()){
            try {
                FileUtils.writeStringToFile(writeFile, "", "utf-8" ,false);
                List<String> inputLine = FileUtils.readLines(inputFile, "utf-8");
                String[] words = inputLine.get(0).split(" ");
                HashMap<String, Integer> resMap = new HashMap<>();
                for (String word : words){
                    resMap.putIfAbsent(word, 0);
                }
                List<String> fileLines = FileUtils.readLines(readFile, "utf-8");

                for (String line: fileLines) {
                    if (!StringUtils.isAllEmpty(line)){
                        line = " " + line.toLowerCase(Locale.ROOT)+" ";
                        for (String word : words){
                            String lowerCaseWord = word.toLowerCase(Locale.ROOT);
                            List<String> possibleVariations = new ArrayList<>();
                            for(String bs:symbolsBefore){
                                for(String as:symbolsAfter){
                                    possibleVariations.add(bs+lowerCaseWord+as);
                                }
                            }
                            Integer wordMatches = 0;
                            for(String variant : possibleVariations){
                                wordMatches+=StringUtils.countMatches(line, variant);
                            }
                            resMap.replace(word, resMap.get(word)+wordMatches);
                        }
                    }
                }
                for (HashMap.Entry<String, Integer> entry : resMap.entrySet()){
                    String tmpRes = entry.getKey()+" - "+entry.getValue().toString()+"\n";
                    FileUtils.writeStringToFile(writeFile, tmpRes, "utf-8" ,true);
                }
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
