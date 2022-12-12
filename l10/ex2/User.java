package sse.hospital;

import java.util.Scanner;

public class User {

    public static void main(String[] args) {
        System.out.println("Main menu. Enter your choice:\n1) Receptionist\n2) Patient\n3) Doctor\n4) Exit\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        do {
            switch (choice) {
                case 1: Receptionist R = new Receptionist();
                    R.Choice();
                    break;
                case 2: Patient P = new Patient();
                    P.Choice();
                    break;
                case 3: Doctor D = new Doctor();
                    D.Choice();
                    break;
                default:
                    break;
            }
        } while (choice!=4);

        System.out.println("Thank you for using our services.\n");
    }
}