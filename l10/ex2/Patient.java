package sse.hospital;

import java.util.Scanner;

public class Patient extends User {

    private int p_id=101;
    private String p_name="Anushka";

    public void Choice(){
        System.out.println("Enter your choice:\n1) PayBill\n2) Go back\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            case 1: PayBill();
                break;
            default: main(null);
                break;
        }
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void PayBill(){
        System.out.println("Thank you for paying your bill.\n");
    }
}

