package edu.upc.prop.clusterxx;

import java.io.IOException;
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
        return scanner.nextInt();
    }

    public void print(String s) {
        System.out.println(s);
    }

}
