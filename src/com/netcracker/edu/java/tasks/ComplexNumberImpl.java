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

    @Override
    public void set(String value) throws NumberFormatException {
        String[] arrayValueRep = value.split("[+-]");

//        if(arrayValueRep.length > 2 || arrayValueRep.length <= 0) {
//            throw new NumberFormatException();
//        }

        if (arrayValueRep.length == 2) {
            realPart = Double.parseDouble(arrayValueRep[0]);
            imaginaryPart = Double.parseDouble(arrayValueRep[1].
                    substring(0, arrayValueRep[1].length() - 1));
            return;
        }

        if (arrayValueRep[0].contains("i")) {
            imaginaryPart = Double.parseDouble(arrayValueRep[0].
                    substring(0, arrayValueRep[0].length() - 1));
        } else {
            realPart = Double.parseDouble(arrayValueRep[0]);
        }


    }

    @Override
    public ComplexNumber copy() {
        return new ComplexNumberImpl(realPart, imaginaryPart);
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
        complex.set("1-2i");

        System.out.println(Double.parseDouble("-11dss"));
    }
}
