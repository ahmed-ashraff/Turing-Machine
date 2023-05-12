# Turing Machine Simulator With Java <br>
Takes from the user: <br>
1. Number of input states. <br>
2. Number of input symbols known as machine symbols. <br>
3. The transition function. This is the 5-tuples of a Turing machine and must be provided to the machine with every possible combination of states and symbols to avoid hanging.<br>
   - q0 -> The current state that the machine is on. <br>
   - Σ0 -> The current string pointed by the head. <br>
   - q1 -> The next state of the machine. <br>
   - Σ1 -> The next symbol to be written on cell by the head (As the head of a Turing machine has the ability to read and write, hence making computations).<br>
   - R -> The Action of the machine. Can be one of 4 options: <br>
     - R for going one cell to the right.<br>
     - L for going one cell to the left.<br>
     - Y for halting and accepting, hence exiting with code 0.<br>
     - N for halting and exiting with code 1 (indicating some error occurred or the string was not accepted).<br>
4. The input string that you want to test.<br>
5. The initial position of the head (starts from zero).<br>
