import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {
    // Instance variables for real and imaginary parts
    public double real;
    public double imaginary;

    // Constructor to initialize the real and imaginary parts
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Parse a complex number from a string like "3.5 + 4.2j" or "3.5 - 4.2j"
    public static Complex fromString(String s) {
        // Regex pattern to match a complex number with 'j' for imaginary
        Pattern pattern = Pattern.compile("([+-]?\\d*\\.?\\d+)([+-]\\d*\\.?\\d+)j");
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            double real = Double.parseDouble(matcher.group(1));  // Real part
            double imaginary = Double.parseDouble(matcher.group(2));  // Imaginary part
            return new Complex(real, imaginary);
        } else {
            throw new IllegalArgumentException("Invalid complex number format");
        }
    }

    // Add two complex numbers
    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    // Subtract two complex numbers
    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    // Multiply two complex numbers
    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    // Calculate the reciprocal of a complex number
    public Complex reciprocal() {
        double divisor = real * real + imaginary * imaginary; 
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        return new Complex(real / divisor, -imaginary / divisor);
    }

    // Negate the complex number
    public Complex negate(){
        return new Complex(-this.real, -this.imaginary);
    }

    // Conjugate of the complex number
    public Complex conjugate(){
        return new Complex(this.real, -this.imaginary);
    }

    // Divide two complex numbers
    public Complex divide(Complex other) {
        double divisor = other.real * other.real + other.imaginary * other.imaginary;
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / divisor;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / divisor;
        return new Complex(newReal, newImaginary);
    }

    // Override toString to display complex numbers in a user-friendly format
    @Override
    public String toString() {
        if (Math.abs(imaginary) < 0.0001) {  // If imaginary part is close to zero, show just the real part
            return String.format("%.4f", real);
        }
        return String.format("%.4f + %.4fj", real, imaginary);
    }
}
