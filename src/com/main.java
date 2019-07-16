package com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class main {
    static ArrayList<Process> processes = new ArrayList<>();
    static Queue<Process> new_state = new LinkedList<>();
    static Queue<Process> ready_state = new LinkedList<>();
    static Queue<Process> running_state = new LinkedList<>();
    static Queue<Process> blocked_state = new LinkedList<>();
    static Queue<Process> blocked_suspend_state = new LinkedList<>();
    static Queue<Process> ready_suspend_state = new LinkedList<>();
    static Queue<Process> exit_state = new LinkedList<>();
    public static void main(String[] args) {
        processes.add(new Process(1L, "Process_1", "New", false));
        processes.add(new Process(2L, "Process_2", "New", false));
        processes.add(new Process(3L, "Process_3", "Blocked", false));
        processes.add(new Process(4L, "Process_4", "Ready", false));
        processes.add(new Process(5L, "Process_5", "New", false));

        for(Process p: processes){
            addProcessToQueue(p);
        }
        int c = 1;
        while(!(new_state.isEmpty())||!(ready_state.isEmpty())||!(blocked_state.isEmpty())||!(running_state.isEmpty())||!(blocked_suspend_state.isEmpty())||!(ready_suspend_state.isEmpty())){
            System.out.println("Time: " + c);
            for(Process p: processes){
                System.out.println(p);
            }
            if(!new_state.isEmpty()){
                new_state.peek().setStateStatus("Ready");
                ready_state.add(new_state.peek());
                new_state.poll();
            }
            if(!ready_state.isEmpty()&&running_state.size()<2){
                ready_state.peek().setStateStatus("Running");
                running_state.add(ready_state.peek());
                ready_state.poll();
            }
            if(!running_state.isEmpty()){
                if(running_state.peek().isActionNeed()){
                    running_state.peek().setStateStatus("Blocked");
                    running_state.peek().setActionNeed(false);
                    if(!blocked_state.isEmpty()){
                        if(blocked_state.size()>4){
                            blocked_state.peek().setStateStatus("Blocked/Suspend");
                            blocked_suspend_state.add(blocked_state.peek());
                            blocked_state.poll();
                        }else{
                            blocked_state.peek().setStateStatus("Ready");
                            ready_state.add(blocked_state.peek());
                            blocked_state.poll();
                        }
                    }
                    blocked_state.add(running_state.peek());
                }else{
                    running_state.peek().setStateStatus("Exit");
                    exit_state.add(running_state.peek());

                }
                running_state.poll();
            }
            if(!blocked_suspend_state.isEmpty()){
                blocked_suspend_state.peek().setStateStatus("Ready/Suspend");
                ready_suspend_state.add(blocked_suspend_state.peek());
                blocked_suspend_state.poll();
            }
            if(!blocked_state.isEmpty()){
                blocked_state.peek().setStateStatus("Blocked/Suspend");
                blocked_suspend_state.add(blocked_state.peek());
                blocked_state.poll();
            }
            if(!ready_suspend_state.isEmpty()){
                ready_suspend_state.peek().setStateStatus("Ready");
                ready_state.add(ready_suspend_state.peek());
                ready_suspend_state.poll();
            }
            c++;
        }
        System.out.println("Time: " + c);
        for(Process p: processes){
            System.out.println(p);
        }
    }

    private static void addProcessToQueue(Process process) {
        if(process.getStateStatus().equals("New")){
            new_state.add(process);
        }
        if(process.getStateStatus().equals("Ready")){
            ready_state.add(process);
        }
        if(process.getStateStatus().equals("Running")){
            running_state.add(process);
        }
        if(process.getStateStatus().equals("Blocked")){
            blocked_state.add(process);
        }
        if(process.getStateStatus().equals("Blocked/Suspend")){
            blocked_suspend_state.add(process);
        }
        if(process.getStateStatus().equals("Ready/Suspend")){
            ready_suspend_state.add(process);
        }
        if(process.getStateStatus().equals("Exit")){
            new_state.add(process);
        }
    }

}
