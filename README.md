<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
# Line Flows and Line Losses

Finding line flows and line losses in a power system.


## Formulas for Calculation

### Voltage Between Buses

- **Bus i and bus j**:
  $$
  V_{ij} = V_{i} - V_{j}
  $$

- **Bus j and bus i**:
  $$
  V_{ji} = V_{j} - V_{i}
  $$

### Current Calculation

- **Current from bus i to bus j**:
  $$
  I_{ij} = y_{ij}(V_i - V_j) + y_{i0} V_i
  $$

- **Current from bus j to bus i**:
  $$
  I_{ji} = y_{ji}(V_j - V_i) + y_{j0} V_i
  $$

### Line Flows

- **From bus i to bus j**:
  $$
  S_{ij} = V_{ij}I_{ij}^{*}
  $$

- **From bus j to bus i**:
  $$
  S_{ji} = V_{ji}I_{ji}^{*}
  $$

### Line Losses

  $$
  S_{L_{ij}} = S_{ij} + S_{ji}
  $$

## Algorithm

1. **Initialize Program**:
    - Import necessary libraries for matrix operations and table display.
    - Define a function to take complex input from the user.

2. **Main Process**:
    - Prompt the user to enter the number of buses.
    - Initialize matrices to hold the lineflows, line losses, voltages, currents, and admittances.

3. **Take Voltages and Currents Input**:
    - For each bus, ask the user to provide voltage and current values.

4. **Choose Input Type**:
    - Ask the user to choose between providing impedance values or admittance values:
        - **If Impedance**:
            - For each bus pair, ask for the impedance value.
            - Calculate the admittance for each pair.
        - **If Admittance**:
            - For each bus pair, ask for the admittance value directly.

5. **Calculate Voltages**:
    - For each pair of buses, compute the voltage difference between them.

6. **Calculate Currents**:
    - For each pair of buses, compute the current based on the voltage difference and admittance.

7. **Calculate Lineflows and Line Losses**:
    - For each pair of buses:
        - Calculate the line flow using the voltage difference and the conjugate of the current.
        - Calculate the line losses by summing the line flows for both directions.

8. **Display Results**:
    - Prepare the data for display in a table format.
    - Print the results in a table with columns for bus pairs, voltages, currents, line flows, and line losses.

9. **Execute the Main Process**:
    - Call the main process to execute the program.

## Pseudocode

  Prompt user to enter the number of buses (n)
  
  Initialize matrices S, SL, V, I, y with dimensions (n, n)
  
  For each bus i from 0 to n-1:
      Prompt user to enter the voltage at bus (i+1)
      V[i, i] = input complex voltage
      Prompt user to enter the current at bus (i+1)
      I[i, i] = input complex current
  
  Prompt user to choose between impedance or admittance input (choice)
  
  If choice is 1:
      For each bus pair (i, j) from 0 to n-1:
          Prompt user to enter the impedance between bus (i+1) and (j+1)
          yij = input complex impedance
          y[i, j] = 1 / yij
  Else if choice is 2:
      For each bus pair (i, j) from 0 to n-1:
          Prompt user to enter the admittance between bus (i+1) and (j+1)
          y[i, j] = input complex admittance
  
  For each bus pair (i, j) from 0 to n-1:
      If i is not equal to j:
          V[i, j] = V[i, i] - V[j, j]
          V[j, i] = V[j, j] - V[i, i]
  
  For each bus pair (i, j) from 0 to n-1:
      If i is not equal to j:
          I[i, j] = y[i, j] * (V[i, i] - V[j, j])
          I[j, i] = y[j, i] * (V[j, j] - V[i, i])
  
  For each bus pair (i, j) from 0 to n-1:
      S[i, j] = V[i, j] * Conjugate(I[i, j])
      S[j, i] = V[j, i] * Conjugate(I[j, i])
      SL[i, j] = S[i, j] + S[j, i]
  
  Prepare data for display
  For each bus pair (i, j) from 0 to n-1:
      Add data to table: Bus Pair (i+1)-(j+1), Voltage V[i, j], Current I[i, j], Line Flow S[i, j], Line Loss SL[i, j]
  
  Display the table with headers "Bus Pair", "Voltage", "Current", "Line Flow", "Line Loss"


## Flowchart

```mermaid
flowchart TD
A([Start]) --> B[\Take no.of buses as input\]
B --> C[[Initialize matrices S, SL, V, I, y with dimensions (n, n)]]
C --> D
```






## Actual Code

Here are the links to actual code implementations:

1. [Python](./Line%20Flows%20and%20Losses/python/lineflow_loss.py)
2. [MATLAB](./Line%20Flows%20and%20Losses/MATLAB/lineflow_loss.m)
3. [Java](./Line%20Flows%20and%20Losses/java/lineflow_loss.java)

## References

1. Hadi Saadat, *Power System Analysis*, Mc-Graw Hill Education, 2010
