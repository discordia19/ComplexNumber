package com.netcracker.edu.java.tasks;

import java.util.Arrays;
import java.util.Comparator;

public class ComplexNumberImpl implements ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    private String toStringRe() {
        return (getRe() != 0) ? String.valueOf(getRe()) : "";
    }

    private String toStringIm() {
        if (getIm() > 0) {
            if (getRe() == 0) {
                return getIm() + "i";
            } else {
                return "+" + getIm() + "i";
            }
        } else if (getIm() < 0) {
            return String.valueOf(getIm()) + "i";
        } else {
            return "";
        }
    }

    public ComplexNumberImpl() {
        realPart = 0;
        imaginaryPart = 0;
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
        if (value.length() == 0)
            throw new NumberFormatException();

        realPart = 0;
        imaginaryPart = 0;
        int i = value.length(); // length of current string to parse

        // if "i" is last character(value has imaginary part)
        if (value.charAt(value.length() - 1) == 'i') {
            i = value.length() - 1;
            imaginaryPart = 1;

            // case "i"
            if (value.length() == 1) {
                return;
            }

            // case "-xi" | "+xi" | "xi"
            for (int j = i - 1; j >= 0; j--) {
                if (value.charAt(j) == '-') {
                    // case "-i"
                    if (j == i - 1) {
                        imaginaryPart *= -1;
                        i = j;
                        break;
                    }
                    imaginaryPart = Double.valueOf(value.substring(j, i));
                    i = j;
                    break;
                } else if (value.charAt(j) == '+') {
                    imaginaryPart = Double.valueOf(value.substring(j, i));
                    i = j;
                    break;
                }

                // case "xi" (real part equals zero)
                if (j == 0) {
                    imaginaryPart = Double.valueOf(value.substring(j, i));
                    return;
                }
            }

        }

        // for real part
        if (i == 0) {
            // cases "-xi" | "+xi", x != 0
            if ((value.charAt(i) == '-' || value.charAt(i) == '+') & (getIm() != 0)) {
                return;
            }

            realPart = Double.valueOf(value);
            return;
        }

        realPart = Double.valueOf(value.substring(0, i));
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumber objectCopy = new ComplexNumberImpl(getRe(), getIm());
        return objectCopy;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return (ComplexNumberImpl) ComplexNumberImpl.super.clone();
    }

    /**
     * @param complexNumber
     * @return length of complex number vector (for vector representation of complex number)
     */
    private double complexNumberLenght(ComplexNumber complexNumber) {
        return Math.sqrt((complexNumber.getRe() * complexNumber.getRe() + complexNumber.getIm() * complexNumber.getIm()));
    }

    @Override
    public int compareTo(ComplexNumber other) {
        if (complexNumberLenght(this) > complexNumberLenght(other)) {
            return 1;
        } else if (complexNumberLenght(this) < complexNumberLenght(other)) {
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
        double oldRealPart = realPart;
        realPart = getRe() * arg2.getRe() - getIm() * arg2.getIm();
        imaginaryPart = oldRealPart * arg2.getIm() + getIm() * arg2.getRe();
        return this;
    }


    @Override
    public String toString() {
        String strRepresentation = toStringRe() + toStringIm();

        if (strRepresentation.equals("")) {
            return "imi";
        } else {
            return strRepresentation;
        }
    }

    @Override
    public boolean equals(Object object) {
        if ((object instanceof ComplexNumber) &&
                (getRe() == ((ComplexNumber) object).getRe()) &&
                (getIm() == ((ComplexNumber) object).getIm())) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        //
        try {
            ComplexNumber a = new ComplexNumberImpl(0, 0);
            a.set("1");
            System.out.println("re: " + a.getRe() + " |  im: " + a.getIm() + "  |  " + a.toString());

        } catch (NumberFormatException e) {
            System.out.println("Error num format");
        }
    }
}

