package com;

import java.util.ArrayList;

public class newMain {
    static ArrayList<Process> processes = new ArrayList<>();

    public static void main(String[] args) {
        adding();

    }

    public static void adding(){
        processes.add(new Process(1L, "Process_1", "New", false));
        processes.add(new Process(2L, "Process_2", "New", false));
        processes.add(new Process(3L, "Process_3", "Blocked", false));
        processes.add(new Process(4L, "Process_4", "Ready", false));
        processes.add(new Process(5L, "Process_5", "New", false));
    }
}
