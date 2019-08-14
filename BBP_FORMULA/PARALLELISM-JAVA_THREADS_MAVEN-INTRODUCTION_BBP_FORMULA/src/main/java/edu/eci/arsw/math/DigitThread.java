package edu.eci.arsw.math;

public class DigitThread extends Thread{

    private int a;
    private int b;

    public DigitThread(int a,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        System.out.println(Main.bytesToHex(PiDigits.getDigits(a,b-1)));
    }
}
