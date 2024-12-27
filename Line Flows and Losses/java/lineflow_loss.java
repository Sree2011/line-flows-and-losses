import java.util.*;

/**
 * Main class to calculate line flows and losses
 * 
 * Functions:
 * 1. get_input(n,V,I,Y): Takes input from the user
 * 2. calculate_lineflow_loss(n,V,I,y): Calculates line flows and losses
 * 3. display_output(n,V,I,S,SL): Displays the output
 * 4. main(): Main function to run the program
 */
public class lineflow_loss {
    /**
     * Scanner object to take input from the user
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * Main function to run the program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Enter the number of buses:");
        int n = sc.nextInt();
        sc.nextLine();
        // Initialise matrices
        Complex[][] V = new Complex[n][n];
        Complex[][] I = new Complex[n][n];
        Complex[][] y = new Complex[n][n];
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                V[i][j] = new Complex(0, 0);
                I[i][j] = new Complex(0, 0);
                y[i][j] = new Complex(0, 0);
            }
        }

        ArrayList<Complex[][]> input = get_input(n, V, I, y);
        V = input.get(0);
        I = input.get(1);
        y = input.get(2);

        ArrayList<Complex[][]> output = calculate_lineflow_loss(n, V, I, y);
        Complex[][] S = output.get(0);
        Complex[][] SL = output.get(1);

        display_output(n, V, I, S, SL);
    
    }

    /**
     * Function to take input from the user
     * @param n total no.of buses
     * @param V voltage matrix
     * @param I current matrix
     * @param y line admittance matrix
     * @return ArrayList of matrices V,I,y
     */
    public static ArrayList<Complex[][]> get_input(int n, Complex[][] V, Complex[][] I, Complex[][] y) {
        // Take voltages and currents as input
        for(int i=0;i<n;i++){
            System.out.println("Enter voltage at bus "+(i+1));
            String s = sc.nextLine();
            V[i][i] = Complex.fromString(s);
            System.out.println("Enter current at bus "+(i+1));
            String t = sc.nextLine();
            I[i][i] = Complex.fromString(t);
        }

        // Get the choice of input type (impedance or admittance)
        int choice;
        System.out.println("Enter 1 for impedance and 2 for admittance: ");
        choice = sc.nextInt();
        sc.nextLine();
        

        if (choice == 1) {
            // Take impedance as input and calculate Admittance
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("Enter the impedance between bus " + (i+1) + " and bus " + (j+1));
                    String s = sc.nextLine();
                    y[i][j] = Complex.fromString(s);
                    y[i][j] = y[i][j].reciprocal();
                }
            }
        } else if (choice == 2) {
            // Take Admittance as input
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("Enter the admittance between bus " + (i+1) + " and bus " + (j+1));
                    String s = sc.nextLine();
                    y[i][j] = Complex.fromString(s);
                }
            }
        }


        ArrayList<Complex[][]> res = new ArrayList<>();
        res.add(V);
        res.add(I);
        res.add(y);
        return res;
    }


    /**
     * Function to calculate line flows and losses
     * @param n total no.of buses
     * @param V voltage matrix
     * @param I current matrix
     * @param y line admittance matrix
     * @return ArrayList of matrices S,SL
     */
    public static ArrayList<Complex[][]> calculate_lineflow_loss(int n,Complex[][] V, Complex[][] I, Complex[][] y){
        Complex[][] S = new Complex[n][n];
        Complex[][] SL = new Complex[n][n];

        // Calculate voltages
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i!= j){
                    V[i][j] = V[i][i].subtract(V[j][j]);
                    V[j][i] = V[j][j].subtract(V[i][i]);
                }
            }
        }
        // Calculate currents
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    I[i][j] = y[i][j].multiply(V[i][i].subtract(V[j][j]));
                    I[j][i] = y[j][i].multiply(V[j][j].subtract(V[i][i]));
                }
            }
        }
        
        // Calculate Lineflows and line losses
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                // Line Flows
                S[i][j] = V[i][j].multiply(I[i][j].conjugate());
                S[j][i] = V[j][i].multiply(I[j][i].conjugate());
                
                // Line losses
                SL[i][j] = S[i][j].add(S[j][i]);
            }
        }

        ArrayList<Complex[][]> res = new ArrayList<>();
        res.add(S);
        res.add(SL);
        return res;
    }


    /**
     * Function to display the output
     * @param n total no.of buses
     * @param V voltage matrix
     * @param I current matrix
     * @param S line flow matrix
     * @param SL line loss matrix
     */
    public static void display_output(int n, Complex[][] V,Complex[][] I, Complex[][] S,Complex[][] SL){
        // Display results (Optional, remove if you want no output at all)
        ArrayList<String[]> data = new ArrayList<>();  // Initialize an ArrayList to store data

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String[] rowData = new String[5];
                rowData[0] = String.format("%d-%d", i + 1, j + 1);  // Bus Pair
                rowData[1] = String.format("%.4f + %.4fj", V[i][j].real, V[i][j].imaginary);  // Use %f instead of %.4f
                rowData[2] = String.format("%.4f + %.4fj", I[i][j].real, I[i][j].imaginary);  // Use %f instead of %.4f
                rowData[3] = String.format("%.4f + %.4fj", S[i][j].real, S[i][j].imaginary);  // Use %f instead of %.4f
                rowData[4] = String.format("%.4f + %.4fj", SL[i][j].real, SL[i][j].imaginary);  // Use %f instead of %.4f
                data.add(rowData);  // Add the row data to the list
            }
        }

        // Print the table header
        String[] headers = {"Bus Pair", "Voltage", "Current", "Line Flow", "Line Loss"};
        System.out.printf("%-10s %-20s %-20s %-20s %-20s%n",headers[0],headers[1],headers[2],headers[3],headers[4]);
        
        // Print each row of data
        for (String[] row : data) {
            System.out.printf("%-10s %-20s %-20s %-20s %-20s%n", row[0], row[1], row[2], row[3], row[4]);
        }
    }
    

    
}
