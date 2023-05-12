# Turing Machine Simulator With Java <br>
<b>Takes from the user:</b> <br>
1. Number of input states. <br>
2. Number of input symbols known as machine symbols. <br>
3. The transition function. This is the 5-tuples of a Turing machine and must be provided to the machine with every possible combination of states and symbols to avoid hanging.<br>
   - q0 -> The current state that the machine is on. <br>
   - Σ0 -> The current string pointed by the head. <br>
   - q1 -> The next state of the machine. <br>
   - Σ1 -> The next symbol to be written on cell by the head (As the head of a Turing machine has the ability to read and write, hence making computations).<br>
   - A -> The Action of the machine. Can be one of 4 options: <br>
     - R for going one cell to the right.<br>
     - L for going one cell to the left.<br>
     - Y for accepting . <br>
     - N for halting (indicating some error occurred or the string was not accepted).<br>
    
4. The input string that you want to test.<br>
5. The initial position of the head (starts from zero).<br>


# if the Transitions not have the Y action or N the machine will hanging and this case has been handle it.
-As the Tape has a infinte length to the right can be extended, i was changed this moudule to maximum 10 hashes to the right to avoid the infinte loop.
