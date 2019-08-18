package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.List;

public class DigitCalculation{

    private int n;
    private DigitThread piDigitsThread[];

    public DigitCalculation(int n){
        this.n = n;
        piDigitsThread = new DigitThread[n];
    }

    public List<byte[]> calcular(int start, int numberOfDigits){
        List<byte[]> answer = new ArrayList<>();
        int step = numberOfDigits / n;
        int remainder = numberOfDigits % n;
        int end;
        for(int i=0;i<n;i++){
            end = start + step;
            if (remainder > 0){ end++; remainder--; }
            piDigitsThread[i] = new DigitThread(start,end-start);
            piDigitsThread[i].start();
            start = end;
        }
        for (int i = 0; i < n ; i++){
            try {
                piDigitsThread[i].join();
                answer.add(piDigitsThread[i].getByteAnswer());
            } catch (InterruptedException e) {
                System.err.println("Error " + e.getMessage());
                System.exit(1);
            }
        }
        return answer;
    }
}
