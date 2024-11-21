import java.util.*;

public class lineflow_loss {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter the number of buses:");
        int n = sc.nextInt();
        sc.nextLine();
        // Initialise matrices
        Complex[][] S = new Complex[n][n];
        Complex[][] V = new Complex[n][n];
        Complex[][] I = new Complex[n][n];
        Complex[][] y = new Complex[n][n];
        Complex[][] SL = new Complex[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                S[i][j] = new Complex(0, 0);
                V[i][j] = new Complex(0, 0);
                I[i][j] = new Complex(0, 0);
                y[i][j] = new Complex(0, 0);
                SL[i][j] = new Complex(0, 0);
                
            }
        }


        // Take voltages and currents as input
        for(int i=0;i<n;i++){
            System.out.println("Enter voltage at bus "+i);
            String s = sc.nextLine();
            V[i][i] = Complex.fromString(s);
            System.out.println("Enter current at bus "+i);
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
                if(i!= j){
                    I[i][j] = I[i][i].subtract(I[j][j]);
                    I[j][i] = I[j][j].subtract(I[i][i]);
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



        // Display results (Optional, remove if you want no output at all)
        ArrayList<String[]> data = new ArrayList<>();  // Initialize an ArrayList to store data

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String[] rowData = new String[5];
                rowData[0] = String.format("%d-%d", i + 1, j + 1);  // Bus Pair
                rowData[1] = String.format("%f + %fj", V[i][j].real, V[i][j].imaginary);  // Use %f instead of %.4f
                rowData[2] = String.format("%f + %fj", I[i][j].real, I[i][j].imaginary);  // Use %f instead of %.4f
                rowData[3] = String.format("%f + %fj", S[i][j].real, S[i][j].imaginary);  // Use %f instead of %.4f
                rowData[4] = String.format("%f + %fj", SL[i][j].real, SL[i][j].imaginary);  // Use %f instead of %.4f
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
