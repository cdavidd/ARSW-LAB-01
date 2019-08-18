package edu.eci.arsw.math;

public class DigitThread extends Thread{

    private int start;
    private int count;
    private byte[] byteAnswer;

    public DigitThread(int start,int count){
        this.start = start;
        this.count = count;
    }

    public byte[] getByteAnswer(){
        return this.byteAnswer;
    }

    @Override
    public void run() {
        byteAnswer = PiDigits.getDigits(start,count);
    }
}
