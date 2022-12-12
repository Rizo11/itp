package sse.hospital;

public class Billing {

    private int b_no=201;
    private int b_amt=1000;

    Patient P = new Patient();

    public int getB_no() {
        return b_no;
    }
    public int getB_amt() {
        return b_amt;
    }

    public void setB_no(int b_no) {
        this.b_no = b_no;
    }
    public void setB_amt(int b_amt) {
        this.b_amt = b_amt;
    }

    public String getP_name() {
        return P.getP_name();
    }
    public Patient getP() {
        return P;
    }
    public int getP_id() {
        return P.getP_id();
    }
}

