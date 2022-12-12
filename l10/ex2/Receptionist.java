package sse.hospital;

import java.util.Scanner;

public class Receptionist extends User {

    private int r_id;
    private String r_name;

    public void Choice(){
        System.out.println("Enter your choice:\n1) GiveAppoint\n2) GenerateBill\n3) Go back\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            case 1: GiveAppoint();
                break;
            case 2: GenerateBill();
                break;
            default: main(null);
                break;
        }
    }

    public void GenerateBill(){
        Billing B = new Billing();
        int b_no = B.getB_no();
        String p_name = B.getP_name();
        int p_id = B.getP_id();
        int b_amt = B.getB_amt();
        System.out.println("BillNO: "+b_no +"\n\tPatientID: "+p_id+"\n\tPatientName: "+p_name+"\n\tBillAmount: "+b_amt+"$\n");
    }

    public void GiveAppoint(){
        Patient P = new Patient();
        String p_name =P.getP_name();
        int p_id = P.getP_id();
        Doctor D = new Doctor();
        int d_id = D.getD_id();
        String d_name = D.getD_name();
        System.out.println("\n PatientID: "+p_id+"\n\tPatientName: "+p_name+"\n\tAppointment with Doctor \n\tDoctorID: "+d_id+
                "\n\tDoctorName"+d_name+"\n");
    }
}