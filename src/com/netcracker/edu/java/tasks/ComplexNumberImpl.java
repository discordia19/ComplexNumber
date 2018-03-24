package com.netcracker.edu.java.tasks;

import java.util.Arrays;
import java.util.Comparator;

public class ComplexNumberImpl implements ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumberImpl() {
        this.realPart = 0;
        this.imaginaryPart = 0;
    }

    public ComplexNumberImpl(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }


    @Override
    public double getRe() {
        return realPart;
    }

    @Override
    public double getIm() {
        return imaginaryPart;
    }

    @Override
    public boolean isReal() {
        return (imaginaryPart == 0);
    }


    @Override
    public void set(double re, double im) {
        realPart = re;
        imaginaryPart = im;
    }


    // without check
    @Override
    public void set(String value) throws NumberFormatException {
        String[] parsedString = value.split("");
        double realPart = 0;
        double imaginaryPart = 0;
        boolean hasRe = false;
        boolean hasIm = false;
        boolean firstIter = true;

        for (int i = parsedString.length - 1; i >= 0 ; i--) {
            if (parsedString[i].equals("i")) {
                i--;
                int j = 1;
                while(i >= 0) {
                    if (parsedString[i].equals("-")) {
                        if (firstIter) {
                            throw new NumberFormatException();
                        }

                        imaginaryPart *= -1;
                        break;
                    }
                    if (parsedString[i].equals("+")) {
                        if (firstIter) {
                            throw new NumberFormatException();
                        }

                        break;
                    }

                    if (j == 1) {
                        firstIter = false;
                        hasIm = true;
                        imaginaryPart = Double.parseDouble(parsedString[i]);
                    } else {
                        imaginaryPart += (Double.parseDouble(parsedString[i]) == 0) ?
                                (j) : (Double.parseDouble(parsedString[i]) * j);
                    }
                    j *= 10;
                    i--;
                }
                continue;
            }

            int j = 1;
            while (i >= 0) {
                if (parsedString[i].equals("-")) {
                    realPart *= -1;
                    break;
                }
                if (j == 1) {
                    hasRe = true;
                    realPart = Double.parseDouble(parsedString[i]);
                } else {
                    realPart += (Double.parseDouble(parsedString[i]) == 0) ?
                            (j) : (Double.parseDouble(parsedString[i]) * j);
                }

                j *= 10;
                i--;
            }
        }

        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumber objectCopy = new ComplexNumberImpl();
        objectCopy.set(this.getRe(), this.getIm());
        return objectCopy;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return copy();
    }

    @Override
    // lazy
    public int compareTo(ComplexNumber other) {
        if (getRe() > other.getRe()) {
            return 1;
        } else if (getRe() < other.getRe()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array, new Comparator<ComplexNumber>() {
            @Override
            public int compare(ComplexNumber o1, ComplexNumber o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Override
    public ComplexNumber negate() {
        realPart *= -1;
        imaginaryPart *= -1;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        realPart += arg2.getRe();
        imaginaryPart += arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        realPart = getRe() * arg2.getRe() - getIm() * arg2.getIm();
        imaginaryPart = getRe() * arg2.getIm() + getIm() * arg2.getRe();
        return this;
    }

    public static void main(String[] args) {
        ComplexNumber complex = new ComplexNumberImpl();
        complex.set("1+2i");
        System.out.println("Real part = " + complex.getRe() + " Imaginary part = " + complex.getIm());
        complex.set(5, -3);
        System.out.println("Real part = " + complex.getRe() + " Imaginary part = " + complex.getIm());

        ComplexNumber complex1 = new ComplexNumberImpl(3, -2);
        System.out.println("Complex1:");
        System.out.println("Real part = " + complex1.getRe() + " Imaginary part = " + complex1.getIm());

        ComplexNumber cop = complex1.copy();
        System.out.println(cop.getRe() + "  im: " + cop.getIm());
        System.out.println(cop.equals(complex1));

    }
}
