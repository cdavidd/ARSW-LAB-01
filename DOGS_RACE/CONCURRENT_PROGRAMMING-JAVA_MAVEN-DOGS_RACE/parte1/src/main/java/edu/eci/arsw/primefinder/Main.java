package edu.eci.arsw.primefinder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener {
    PrimeFinderThread pft1;
    PrimeFinderThread pft2;
    PrimeFinderThread pft3;
    
    public static void main(String[] args){
        /*PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);
        pft.start();
        */
        Main tes= new Main();
    }

    public Main(){
        inicializador();
        threadStart();
    }

    public void threadStart(){
        pft1=new PrimeFinderThread(0, 10000000);
        pft2=new PrimeFinderThread(10000001, 20000000);
        pft3=new PrimeFinderThread(20000001, 30000000);

        long start = System.currentTimeMillis();
        pft1.start();
        pft2.start();
        pft3.start();
        long elapsed;
        do{
            elapsed = System.currentTimeMillis() - start;
        }while (elapsed < 5000);
        pft1.suspend();
        pft2.suspend();
        pft3.suspend();
    }
    
    private void inicializador() {
        setLayout(null);
        setTitle("Prueba con Teclas");
        setVisible(true);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            pft1.resume();
            pft2.resume();
            pft3.resume();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
