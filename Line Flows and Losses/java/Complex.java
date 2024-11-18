import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {
    public static double real;
    public static double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public static Complex fromString(String s) {
        Pattern pattern = Pattern.compile("([+-]?\\d*\\.?\\d+)([+-]\\d*\\.?\\d+)j");
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            double real = Double.parseDouble(matcher.group(1));
            double imaginary = Double.parseDouble(matcher.group(2));
            return new Complex(real, imaginary);
        } else {
            throw new IllegalArgumentException("Invalid complex number format");
        }
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex reciprocal() { 
        double divisor = real * real + imaginary * imaginary; 
        return new Complex(real / divisor, -imaginary / divisor);
    }

    public Complex negate(){
        return new Complex(-this.real,-this.imaginary);
    }

    public Complex conjugate(){
        return new Complex(this.real,-this.imaginary);
    }

    public Complex divide(Complex other) {
        double divisor = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / divisor;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / divisor;
        return new Complex(newReal, newImaginary);
    }

    @Override
    public String toString() {
        return String.format("%.4f + %.4fj", real, imaginary);
    }
}