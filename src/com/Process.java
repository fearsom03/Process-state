package com;

public class Process {

    private Long id;
    private String name;
    private String stateStatus;
    private boolean actionNeed;

    public Process() {
    }

    public Process(Long id, String name, String stateStatus, boolean actionNeed) {
        this.id = id;
        this.name = name;
        this.stateStatus = stateStatus;
        this.actionNeed = actionNeed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateStatus() {
        return stateStatus;
    }

    public void setStateStatus(String stateStatus) {
        this.stateStatus = stateStatus;
    }

    public boolean isActionNeed() {
        return actionNeed;
    }

    public void setActionNeed(boolean actionNeed) {
        this.actionNeed = actionNeed;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.stateStatus;
    }
}
