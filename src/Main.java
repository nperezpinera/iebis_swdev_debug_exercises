package com.company;

public class Main {

    public static void main(String[] args) {
        WordAnalyzer wordAnalyzer = new WordAnalyzer("hello");
        System.out.println(wordAnalyzer.firstRepeatedCharacter());          // expected output: l
        System.out.println(wordAnalyzer.countGroupsRepeatedCharacters());   // expected output: 1

        wordAnalyzer = new WordAnalyzer("comprehensive");
        System.out.println(wordAnalyzer.firstMultipleCharacter());          // expected output: e
        System.out.println(wordAnalyzer.firstRepeatedCharacter());          // expected output:

        wordAnalyzer = new WordAnalyzer("development");
        System.out.println(wordAnalyzer.firstRepeatedCharacter());          // expected output:
        System.out.println(wordAnalyzer.countGroupsRepeatedCharacters());   // expected output: 0

        wordAnalyzer = new WordAnalyzer("mississippi!!!");
        System.out.println(wordAnalyzer.firstMultipleCharacter());          // expected output: i
        System.out.println(wordAnalyzer.countGroupsRepeatedCharacters());   // expected output: 4

        wordAnalyzer = new WordAnalyzer("aabbcdaaaabb");
        System.out.println(wordAnalyzer.countGroupsRepeatedCharacters());   // expected output: 4
        System.out.println(wordAnalyzer.firstRepeatedCharacter());          // expected output: a
        System.out.println(wordAnalyzer.firstMultipleCharacter());          // expected output: a
    }
}
