package edu.eci.arsw.math;

public class DigitThread extends Thread{

    private int start;
    private int count;
    private String byteAnswer;

    public DigitThread(int start,int count){
        this.start = start;
        this.count = count;
    }

    public String getByteAnswer(){
        return this.byteAnswer;
    }

    @Override
    public void run() {
        byteAnswer = Main.bytesToHex(PiDigits.getDigits(start,count));
    }
}
