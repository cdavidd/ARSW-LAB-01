package edu.eci.arsw.math;

public class DigitCalculation{

    private int n;
    private DigitThread piDigitsThread[];

    public DigitCalculation(int n){
        this.n = n;
        piDigitsThread = new DigitThread[n];
    }

    public String calcular(int start, int numberOfDigits){
        int step = numberOfDigits / n;
        int remainder = numberOfDigits % n;
        int end;
        StringBuilder digitsBuilder = new StringBuilder();
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
                digitsBuilder.append(piDigitsThread[i].getByteAnswer());
            } catch (InterruptedException e) {
                System.err.println("Error " + e.getMessage());
                System.exit(1);
            }
        }
        return digitsBuilder.toString();
    }
}
