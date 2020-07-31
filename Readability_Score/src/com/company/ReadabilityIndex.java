package com.company;

public class ReadabilityIndex {
    String text;
    int words;
    int lines;
    int characters;
    int syllables;
    int polyS;

    public ReadabilityIndex(String input) {
        text = input;
        words = input.split(" ").length;
        lines = input.split("[!?.]").length;
        characters = input.replaceAll("\\s", "").toCharArray().length;
        char c;
        int syl;
        for (String word : input.toLowerCase().replaceAll("[!?.]", "").split(" ")) {
            if (word.matches("[0-9,]+")){
                continue;
            }
            syl = 0;
            int l = word.length();
            int e = l - 1;
            for (int i = 0; i < l; ) {
                c = word.charAt(i);
                if (isVowel(c)) {
                    if (i == e && c == 'e') {
                        break;
                    }
                    if (i != e && isVowel(word.charAt(i+1))) {
                        i += 2;
                    }
                    syl++;
                }
                i++;
            }
            syl = syl > 0 ? syl : 1;
            syllables += syl;
            if (syl > 2) {
                polyS++;
            }
        }
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public void display() {
        System.out.printf("The text is:\n%s\n\n", text);
        System.out.printf("Words: %d\n", words);
        System.out.printf("Sentences: %d\n", lines);
        System.out.printf("Characters: %d\n", characters);
        System.out.printf("Syllables: %d\n", syllables);
        System.out.printf("Polysyllables: %d\n", polyS);
    }

    public int ARI() {
        double score = (4.71 * (characters / (double) words)) + (0.5 * (words / (double) lines)) - 21.43;
        int age = getAge(score);
        System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", score, age);
        return age;
    }

    public int FK() {
        double score = (0.39 * (words / (double) lines)) + (11.89 * (syllables / (double) words)) - 15.59;
        int age = getAge(score);
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).\n", score, age);
        return age;
    }

    public int SMOG() {
        double score = 1.043 * Math.sqrt(polyS * (30.0 / lines)) + 3.1291;
        int age = getAge(score);
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", score, age);
        return age;
    }

    public int CL() {
        double l = characters / (double) words * 100;
        double s = lines / (double) words * 100;
        double score = 0.0588 * l - 0.296 * s - 15.8;
        int age = getAge(score);
        System.out.printf("Coleman–Liau index: %.2f (about %d year olds).\n", score, age);
        return age;
    }

    public void ALL() {
        int ari = ARI();
        int fk = FK();
        int smog = SMOG();
        int cl = CL();
        System.out.printf("\nThis text should be understood in average by %.2f year olds.", (ari + fk + smog + cl) / 4.0);
    }

    public int getAge(Double score) {
        int ageGroup = 0;
        switch ((int) Math.round(Math.ceil(score))) {
            case 1:
                ageGroup = 6;
                break;
            case 2:
                ageGroup = 7;
                break;
            case 3:
                ageGroup = 9;
                break;
            case 4:
                ageGroup = 10;
                break;
            case 5:
                ageGroup = 11;
                break;
            case 6:
                ageGroup = 12;
                break;
            case 7:
                ageGroup = 13;
                break;
            case 8:
                ageGroup = 14;
                break;
            case 9:
                ageGroup = 15;
                break;
            case 10:
                ageGroup = 16;
                break;
            case 11:
                ageGroup = 17;
                break;
            case 12:
                ageGroup = 18;
                break;
            case 13:
                ageGroup = 24;
                break;
//            default:
//                ageGroup = "24+";
        }
        return ageGroup;
    }

}

