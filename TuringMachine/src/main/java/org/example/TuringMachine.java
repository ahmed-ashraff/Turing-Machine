package org.example;

import java.util.*;

public class TuringMachine {
    private final List<Integer> States;
    private final List<Transition> Transitions;
    private int StartState;
    private String Tape;
    private int CurrentState;
    private int headPosition;
    public TuringMachine() {
        States = new ArrayList<>();
        Transitions = new ArrayList<>();
        StartState = 0;
        Tape = "";
        CurrentState = 0;
        headPosition = 0;
    }

    public boolean Run(String input) {
        CurrentState = StartState;
        Tape = input;
        int tapeLength = Tape.length();

        // has maximum 10 hashes to the right to avoid infinite loop
        while (headPosition < tapeLength + 10) {
            boolean foundTransition = false;
            Transition CurrentTransition = null;

            for (Transition nextTransition : Transitions) {
                if (nextTransition.currState == CurrentState && nextTransition.currSymbol == Tape.charAt(headPosition)) {
                    foundTransition = true;
                    CurrentTransition = nextTransition;
                    break;
                }
            }

            if (!foundTransition) {
                return false;
            } else {
                CurrentState = CurrentTransition.newState;
                char[] tempTape = Tape.toCharArray();
                tempTape[headPosition] = CurrentTransition.newSymbol;
                Tape = new String(tempTape);

                if (Character.toUpperCase(CurrentTransition.action) == 'R') {
                    if (headPosition == Tape.length() - 1) Tape = Tape.concat("#");
                    headPosition++;
                } else if (Character.toUpperCase(CurrentTransition.action) == 'L') {
                    headPosition--;
                } else if (Character.toUpperCase(CurrentTransition.action) == 'Y') {
                    return true;
                } else if (Character.toUpperCase(CurrentTransition.action) == 'N') {
                    return false;
                } else {
                    return false;
                }
                if (headPosition < 0) headPosition = 0;
            }
        }
        System.out.println("The Machine Hanged!");
        System.exit(1);
        return false;
    }

    public boolean addState(int newState) {
        if (States.contains(newState)) {
            System.out.println("The State is Already Exists!");
            return false;
        } else {
            States.add(newState);
            return true;
        }
    }

    public boolean addTransition(int currentState, char currentSymbol, int newState, char newSymbol, char Action) {
        if (!States.contains(currentState) || !States.contains(newState)) {
            System.out.println("Some State in Transition is not exists");
            return false;
        }

        Transition newTransition = new Transition();
        newTransition.currState = currentState;
        newTransition.currSymbol = currentSymbol;
        newTransition.newState = newState;
        newTransition.newSymbol = newSymbol;
        newTransition.action = Action;
        Transitions.add(newTransition);
        return true;
    }

    public boolean checkTheValidHead(int headPosition) {
        return headPosition < Tape.length() && headPosition >= 0;
    }

    public boolean setStartState(int newStartState) {
        if (States.contains(newStartState)) {
            StartState = newStartState;
            return true;
        } else {
            System.out.println("The State is Already Exists");
            return false;
        }
    }

    public String getTape() {
        return Tape;
    }

    public void setTape(String tape) {
        Tape = tape;
    }

    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }
    public int getHeadPosition() {
        return headPosition;
    }
}