package com.example.myapplication;

import java.util.ArrayList;

public class GrandpalsData {
    private ArrayList<Integer> matches = new ArrayList<>(); // people ids to show on the matches screen (starts with 0-5)
    private ArrayList<Integer> convos = new ArrayList<>();  // people ids to show on the convo screen (starts empty)
    public GrandpalsData() {
        for (int i : new int[] {0, 1, 2, 3, 4, 5})
            matches.add(i);
    }
    public ArrayList<Integer> getMatches() {
        return matches;
    }
    public ArrayList<Integer> getConvos() {
        return convos;
    }
    public void setMatches(ArrayList<Integer> data) {
        this.matches = data;
    }
    public void setConvos(ArrayList<Integer> data) {
        this.convos = data;
    }

    private static final GrandpalsData data = new GrandpalsData();
    public static GrandpalsData getInstance() {
        return data;
    }
}