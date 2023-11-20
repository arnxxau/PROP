package edu.upc.prop.clusterxx;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InOutPlus {
    private Scanner scanner;

    public InOutPlus() {
        this.scanner = new Scanner(System.in);
    }

    public void closeInOutPlus() {
        scanner.close();
    }

    public String readWord() {
        return scanner.next();
    }

    public Integer readInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("L'input ha de ser un número, torna-ho a intentar");
            return -1;
        }
    }

    public void print(String s) {
        System.out.println(s);
    }

}
