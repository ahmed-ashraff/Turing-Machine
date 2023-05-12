package org.example;

import java.util.*;

public class Main {

    static MachinesHandlingInput MD = new MachinesHandlingInput();
    static TuringMachine TM = new TuringMachine();
    static Scanner sc = new Scanner(System.in);
    static char[] MachineAlphabet; // Gama
    static char[] machineSymbols;
    static char[] alphabets;
    static char[][] transitions;
    static int num_states;
    static int num_alphabets; // Sigma
    static int num_machine_symbols;
    static boolean isValid;
    static String input_string;

    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("| Turing Machine Simulator |");
        System.out.println("----------------------------");

        // Enter the number of states
        MD.num_of_states();

        // Enter the number of alphabets
        MD.num_of_alphabets();

        alphabets = new char[num_alphabets];

        // Enter the alphabets
        MD.enter_alphabets();

        // Enter the number of machine symbols
        MD.num_of_machine_symbols();

        machineSymbols = new char[num_machine_symbols];

        // Enter the machine symbols
        MD.enter_machine_symbols();

        // to put the alphabet's user and machine symbols to the machine language
        MachineAlphabet = new char[num_alphabets + num_machine_symbols];
        int i = 0;
        for (;i < num_alphabets; i++) {
            MachineAlphabet[i] = alphabets[i];
        }
        int j = 0;
        for (; j < num_machine_symbols; i++) {
            MachineAlphabet[i] = machineSymbols[j];
            j++;
        }
        System.out.println("The Machine Alphabets: " + Arrays.toString(MachineAlphabet));

        transitions = new char[num_states * (num_alphabets + 1)][5];

        // Enter the Transitions
        MD.enter_transitions();

        // add the states in the TuringMachine
        MD.add_states();

        // the start state is always the initial one
        boolean isStartStateExist = TM.setStartState(Character.getNumericValue(transitions[0][0]));
        if (!isStartStateExist) System.exit(1);

        // add the transitions in the TuringMachine
        MD.add_transitions();

        // to loop if he wants to add another String
        while (true) {
            // to loop if he entered inValid String
            while (true) {
                // Enter The String
                MD.enter_string();

                // Enter The position of Head
                MD.pos_of_head();

                if (MD.isStringExist(input_string, MachineAlphabet)) {
                    break;
                } else {
                    System.out.println("Enter a Valid String!");
                }
            }

            System.out.println("------------------------------------");
            System.out.println(" - The Final String: " + TM.getTape());
            System.out.println(" - The headPosition at index: " + TM.getHeadPosition());

            if (isValid) {
                System.out.println(" - The Input Was Accepted.");
            } else {
                System.out.println(" - The Input Was Rejected.");
            }
            System.out.println("------------------------------------");

            // reset the head Position
            TM.setHeadPosition(0);

            String c;
            // to loop if he entered otherwise (Y/N)
            while (true) {
                System.out.print("you Want to Enter another String? (Y/N): ");
                c = sc.next();
                if (Objects.equals(c, "N") || Objects.equals(c, "n") || Objects.equals(c, "Y") || Objects.equals(c, "y")) {
                    break;
                } else {
                    System.out.println("Enter Y or N: ");
                }
            }
            if (Objects.equals(c, "N") || Objects.equals(c, "n")) {
                System.exit(1);
            }
        }
    }
}
