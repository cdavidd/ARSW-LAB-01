package edu.eci.arsw.math;

public class DigitCalculation{

    private int n;
    private Thread piDigitsThread[];

    public DigitCalculation(int n){
        this.n = n;
        piDigitsThread = new DigitThread[n];
    }

    public byte[] calcular(){
        for(int i=0;i<n;i++){
            int start= i* (100/n);
            int end = (i+1)*(100/n)+1 - start;
            System.out.println("Rango: " + start + " " + end);
            piDigitsThread[i] = new DigitThread(start,end);
            piDigitsThread[i].start();
            try {
                piDigitsThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
