package org.example;

import static org.example.Main.*;

public class MachinesHandlingInput {
    public MachinesHandlingInput() {
    }

    public String removeCommas(String str) {
        str = str.replaceAll("\\s", "");
        return str.replace(",", "");
    }

    public boolean isExistInALPHABET(char[] arr, char key) {
        for (char c : arr) {
            if (c == key) return false;
        }
        return true;
    }

    public boolean checkValidTransition(String str) {
        if (str.length() != 5) {
            System.out.println("[!] Wrong on The Length of The Transition");
            return false;
        }

        if (!Character.isDigit(str.charAt(0))) {
            System.out.println("[!] Wrong on The Current State Must BE Digit");
            return false;
        }
        if (isExistInALPHABET(MachineAlphabet, str.charAt(1))) {
            System.out.println("[!] The Current input is not in The Machine Language");
            return false;
        }
        if (!Character.isDigit(str.charAt(2))) {
            System.out.println("[!] Wrong on The New State Must BE Digit");
            return false;
        }

        if (isExistInALPHABET(MachineAlphabet, str.charAt(3))) {
            System.out.println("[!] The New input is not in The Machine Language");
            return false;
        }
        if (Character.toUpperCase(str.charAt(4)) != 'Y' && Character.toUpperCase(str.charAt(4)) != 'N'
                && Character.toUpperCase(str.charAt(4)) != 'R' && Character.toUpperCase(str.charAt(4)) != 'L') {
            System.out.println("[!] The Action is Not in The Machine Language");
            return false;
        }
        return true;
    }

    public void num_of_states() {
        while (true) {
            System.out.print("Enter the number of states: ");

            if (sc.hasNextInt()) {
                num_states = sc.nextInt();
                if (num_states <= 0) {
                    System.out.println("The Number must be greater than Zero!");
                    continue;
                }
                break;
            } else {
                System.out.println("Invalid Input please Enter a Number!");
                sc.next();
            }
        }
    }

    public void num_of_alphabets() {
        while (true) {
            System.out.print("Enter the number of alphabets: ");
            if (sc.hasNextInt()) {
                num_alphabets = sc.nextInt();
                if (num_alphabets <= 0) {
                    System.out.println("The Number must be greater than Zero!");
                    continue;
                }
                break;
            } else {
                System.out.println("Invalid Input please Enter a Number!");
                sc.next();
            }
        }
    }

    public void enter_alphabets() {
        for (int i = 0; i < num_alphabets; i++) {
            System.out.print("Enter the " + (i + 1) + "th alphabet Line by Line: ");
            alphabets[i] = sc.next().charAt(0);
        }
    }

    public void num_of_machine_symbols() {
        while (true) {
            System.out.print("Enter the number of Machine alphabets: ");
            if (sc.hasNextInt()) {
                num_machine_symbols = sc.nextInt();
                if (num_machine_symbols <= 0) {
                    System.out.println("The Number must be greater than Zero!");
                    continue;
                }
                break;
            } else {
                System.out.println("Invalid Input please Enter a Number!");
                sc.next();
            }
        }
    }

    public void enter_machine_symbols() {
        System.out.println("Enter the Machine Symbols Line by Line (Excluding input alphabets): ");

        for (int i = 0; i < num_machine_symbols; i++) {
            System.out.print("Enter the " + (i + 1) + "th Machine Symbol: ");
            machineSymbols[i] = sc.next().charAt(0);
        }
    }

    public void enter_transitions() {
        System.out.println("Enter The Transitions Separated by comma Ex: 0,a,1,a,R");
        boolean flag = true;

        for (int i = 0; i < transitions.length; i++) {
            while (true) {
                System.out.print("Enter The " + (i + 1) + "th Transition: ");

                if (flag)
                    sc.nextLine();
                String Transitions = sc.nextLine();

                String newTR = MD.removeCommas(Transitions);

                if (MD.checkValidTransition(newTR)) {
                    flag = false;
                    for (int j = 0; j < transitions[0].length; j++) {
                        transitions[i][j] = newTR.charAt(j);
                    }
                    break;
                } else {
                    System.out.println("Try Again...");
                }
            }
        }
    }

    public void add_states() {
        for (int i = 0; i < num_states * (num_alphabets + 1); i += (num_alphabets + 1)) {
            boolean isStateExists = TM.addState(Character.getNumericValue(transitions[i][0]));
            if (!isStateExists) {
                return;
            }
        }
    }

    public void add_transitions() {
        for (char[] transition : transitions) {
            for (int j = 0; j < transitions[0].length; j++) {
                boolean isTRExists = TM.addTransition(Character.getNumericValue(transition[0]), transition[1], Character.getNumericValue(transition[2]), transition[3], transition[4]);
                if (!isTRExists) System.exit(1);
            }
        }
    }

    public void enter_string() {
        System.out.print("Enter The String: ");
        input_string = sc.next();
        TM.setTape(input_string);
    }

    public boolean isStringExist(String str, char[] machineAlphabet) {
        char[] input = str.toCharArray();
        boolean a1 = true;

        for (char c : input) {
            for (char value : machineAlphabet) {
                if (value != c) {
                    a1 = false;
                }if (value == c) {
                    a1 = true;
                    break;
                }
            }
        }
        return a1;
    }

    public void pos_of_head() {
        while (true) {
            System.out.print("Enter The initial position of head (Starts from 0): ");
            int head_position = sc.nextInt();

            if (TM.checkTheValidHead(head_position)) {
                isValid = TM.Run(input_string);
                break;
            } else {
                System.out.println("[!] Wrong Input Try Again");
            }
        }
    }
}