package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class CardsManager {
    private LinkedHashMap<String, String> cards;
    private Scanner scanner;
    private ArrayList<String> toBeLogged;
    private TreeMap<String, Integer> hardestCards;
    private boolean saveFlag;
    private String fileToExportTo;

    CardsManager() {
        saveFlag = false;
        cards = new LinkedHashMap();
        scanner = new Scanner(System.in);
        toBeLogged = new ArrayList<>();
        hardestCards = new TreeMap<>();
    }

    public void argParser(String... args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-import")) {
                loadFile(args[i+1]);
            } else {
                fileToExportTo = args[i+1];
                saveFlag = true;
            }
        }
    }

    public void addCard() {
        recordAndOut("The card:", false);
        String tempterm = scanner.nextLine();
        recordAndOut(tempterm, true);
        if (cards.containsKey(tempterm)) {
            recordAndOut("The card \"" + tempterm + "\" already exists.", false);
            return;
        }
        System.out.println("The definition of card:");
        String tempdef = scanner.nextLine();
        recordAndOut(tempdef, true);
        if (cards.containsValue(tempdef)) {
            recordAndOut("The definition \"" + tempdef + "\" already exists.", false);
            return;
        }
        cards.put(tempterm, tempdef);
        recordAndOut(String.format("The pair (\"%s\":\"%s\") has been added.", tempterm, tempdef), false);
    }

    public void removeCard() {
        recordAndOut("The card:", false);
        String query = scanner.nextLine();
        recordAndOut(query, true);
        if (cards.containsKey(query)) {
            cards.remove(query);
            hardestCards.remove(query);
            recordAndOut("The card has been removed.", false);
        } else {
            recordAndOut(String.format("Can't remove \"%s\": there is no such card.", query), false);
        }
    }

    public void ask() {
        recordAndOut("How many times to ask?", false);
        int count = Integer.parseInt(scanner.nextLine());
        recordAndOut(Integer.toString(count), true);
        int i = 0;
        int size = cards.size();
        String answeredQuestion;
        while (count-- > 0) {
            var question = cards.entrySet().stream().skip(i).findFirst().get();
            recordAndOut("Print the definition of \"" + question.getKey() + "\":", false);
            String tempAns = scanner.nextLine();
            recordAndOut(tempAns, true);
            if (tempAns.equals(question.getValue())) {
                recordAndOut("Correct answer.", false);
            } else if (cards.containsValue(tempAns)) {
                answeredQuestion = cards.entrySet().stream().filter(k -> k.getValue().equals(tempAns)).findFirst().get().getKey();
                recordAndOut(String.format("Wrong answer. (The correct one is \"%s\", you've just written the definition of \"%s\" card.)", question.getValue(), answeredQuestion), false);
                hardestCards.put("\"" + question.getKey() + "\"", hardestCards.getOrDefault(question.getKey(), 0) + 1);
            } else {
                recordAndOut(String.format("Wrong answer. (The correct one is \"%s\".)", question.getValue()), false);
                hardestCards.put(question.getKey(), hardestCards.getOrDefault(question.getKey(), 0) + 1);
            }
            i = ++i % size;
        }
    }

    public void loadFile(String... fileToBeLoaded) {
        String fname;
        if (fileToBeLoaded.length == 0 ){
            recordAndOut("File name:", false);
            fname = scanner.nextLine().stripTrailing();
            recordAndOut(fname, true);
        } else {
            fname = fileToBeLoaded[0];
        }
        fname += "./";
        File file = new File(fname);
        int count = 0;
        String[] line;
        int hv;
        try (Scanner load = new Scanner(file)) {
            hv = Integer.parseInt(load.nextLine());
            if (hv == 0) {
                load.nextLine();
            } else {
                Arrays.stream(load.nextLine().split("\\s+")).forEach(x -> hardestCards.put(x, hv));
            }
            while (load.hasNextLine()) {
                line = load.nextLine().stripTrailing().split(", ");
                cards.put(line[0], line[1]);
                count++;
            }
        } catch (Exception e) {
            recordAndOut("File not found.", false);
            return;
        }
        recordAndOut(count + " cards have been loaded.", false);
    }

    public void exportFile(String... fileToBeExportedTo) {
        String fname;
        if (fileToBeExportedTo.length == 0) {
            recordAndOut("File name:", false);
            fname = scanner.nextLine().stripTrailing();
            recordAndOut(fname, true);
        } else {
            fname = fileToBeExportedTo[0];
        }
        fname +=  "./";
        File file = new File(fname);
        boolean flag = true;
        int hv = 0;
        ArrayList<String> hq = new ArrayList<>();
        if (hardestCards.size() > 0) {
            hv = hardestCards.lastEntry().getValue();
            hardestCards.entrySet().stream().filter(x -> x.getValue() == hardestCards.lastEntry().getValue()).forEach(x -> hq.add(x.getKey()));
            flag = false;
        }
        try (PrintWriter f = new PrintWriter(file)) {
            if (!flag) {
                f.println(hv);
                f.println(hq.stream().collect(Collectors.joining(" ")));
            } else {
                f.println(0);
                f.println();
            }
            cards.forEach((k, v) -> f.printf("%s, %s\n", k, v));
        } catch (Exception e) {
            System.out.println("Error");
        }
        recordAndOut(cards.size() + " cards have been saved.", false);
    }

    public void recordAndOut(String data, boolean input) {
        if (!input) {
            System.out.println(data);
        }
        toBeLogged.add(data);
    }

    public void logIt() {
        recordAndOut("File name:", false);
        String fname = scanner.nextLine();
        recordAndOut(fname, true);
        fname += "./";
        File file = new File(fname);
        try (PrintWriter f = new PrintWriter(file)) {
            toBeLogged.forEach((f::println));
        } catch (IOException e) {
            System.out.println("Error!");
        }
        System.out.println("The log has been saved");
    }

    public void getHardestQuestions() {
        ArrayList<String> hq = new ArrayList<>();
        String op = "";
        if (hardestCards.size() == 0) {
            recordAndOut("There are no cards with errors.", false);
            return;
        }
        int hqv = hardestCards.lastEntry().getValue();
        hardestCards.entrySet().stream().filter(x -> x.getValue() == hqv).forEach(x -> hq.add(x.getKey()));
        String t = hq.stream().map(x -> "\"" + x + "\"").collect(Collectors.joining(", "));
        int l = hq.size();
        if (l == 1) {
            op += "The hardest card is \"" + hq.stream().findFirst().get() + "\". You have " + hqv + " errors answering it.";
        } else {
            op += "The hardest cards are " + t + ". You have " + hqv + " errors answering them.";
        }
        recordAndOut(op, false);
    }

    public void resetStats() {
        hardestCards.clear();
        recordAndOut("Card statistics has been reset.", false);
    }

    public void exitCards() {
        System.out.println("Bye bye!");
        if (saveFlag) {
            exportFile(fileToExportTo);
        }
    }

}
