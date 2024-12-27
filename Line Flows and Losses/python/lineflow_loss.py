"""
This program calculates lineflows and line losses of a power system by taking line voltages, currents,
and Bus Admittance Matrix as input from the user.
"""
import numpy as np
from tabulate import tabulate

"""
This function takes a complex number as input from the user

Args:
    prompt (str): The prompt to be displayed to the user

Returns:
    c(complex): The complex number entered by the user
"""
def get_complex_input(prompt):
    c = complex(input(prompt))
    return c


"""
This is the main function

Args:
    None

Returns:
    None
"""
def main():
    print("Enter the number of buses:")
    n = int(input())

    # Initialise matrices
    V = np.zeros((n, n), dtype=complex)
    I = np.zeros((n, n), dtype=complex)
    y = np.zeros((n, n), dtype=complex)

    [V,I,y] = get_input(n,V,I,y)
    [S,SL] = calculate_lineflow_loss(n,V,I,y)
    display_output(n,V,I,S,SL)


"""
This function takes the input from the user

Args:
    n (int): The number of buses
    V (numpy.ndarray): The voltage matrix
    I (numpy.ndarray): The current matrix
    y (numpy.ndarray): The admittance matrix

Returns:
    [V,I,y]: The voltage, current and admittance matrices

"""
def get_input(n,V,I,y):
    # Take voltages and currents as input
    for i in range(n):
        V[i, i] = get_complex_input(f"Enter the voltage at bus {i+1}: ")
        I[i, i] = get_complex_input(f"Enter the current at bus {i+1}: ")

    # Get the choice of input type (impedance or admittance)
    choice = int(input("Enter 1 for impedance and 2 for admittance: "))

    if choice == 1:
        # Calculate Admittance
        for i in range(n):
            for j in range(n):
                yij = get_complex_input(f"Enter the impedance between bus {i+1} and {j+1}: ")
                y[i, j] = 1 / yij
    elif choice == 2:
        # Take Admittance as input
        for i in range(n):
            for j in range(n):
                y[i, j] = get_complex_input(f"Enter the admittance between bus {i+1} and {j+1}: ")

    return [V,I,y]


"""
This function calculates the lineflows and line losses

Args:
    n (int): The number of buses
    V (numpy.ndarray): The voltage matrix
    I (numpy.ndarray): The current matrix
    y (numpy.ndarray): The admittance matrix

Returns:
    [S,SL]: The lineflows and line losses matrices
"""
def calculate_lineflow_loss(n,V,I,y):
    S = np.zeros((n, n), dtype=complex)
    SL = np.zeros((n, n), dtype=complex)
    # Calculate voltages
    for i in range(n):
        for j in range(n):
            if i != j:
                V[i, j] = V[i, i] - V[j, j]
                V[j, i] = V[j, j] - V[i, i]

    # Calculate currents
    for i in range(n):
        for j in range(n):
            if i != j:
                I[i, j] = y[i, j] * (V[i, i] - V[j, j])
                I[j, i] = y[j, i] * (V[j, j] - V[i, i])

   

    # Calculate Lineflows and line losses
    for i in range(n):
        for j in range(n):
            # Line Flows
            S[i][j] = V[i][j]*np.conj(I[i][j])
            S[j][i] = V[j][i]*np.conj(I[j][i])

            # Line losses
            SL[i][j] = S[i][j] + S[j][i]

    return [S,SL]

"""
This function displays the output

Args:
    n (int): The number of buses
    V (numpy.ndarray): The voltage matrix
    I (numpy.ndarray): The current matrix
    S (numpy.ndarray): The lineflows matrix
    SL (numpy.ndarray): The line losses matrix

Returns:
    None
"""
def display_output(n,V,I,S,SL):
    # Prepare data for tabulate 
    data = [] 
    for i in range(n):
        for j in range(n):
            data.append([ f"{i + 1}-{j + 1}", f"{V[i, j].real:.4f} + {V[i, j].imag:.4f}j", f"{I[i, j].real:.4f} + {I[i, j].imag:.4f}j", f"{S[i, j].real:.4f} + {S[i, j].imag:.4f}j", f"{SL[i, j].real:.4f} + {SL[i, j].imag:.4f}j" ])
    print(tabulate(data, headers=["Bus Pair", "Voltage", "Current", "Line Flow", "Line Loss"]))


if __name__ == "__main__":
    main()
