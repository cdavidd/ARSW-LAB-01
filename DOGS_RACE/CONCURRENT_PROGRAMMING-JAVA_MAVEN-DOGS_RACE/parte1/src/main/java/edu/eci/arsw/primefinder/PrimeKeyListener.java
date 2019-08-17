package edu.eci.arsw.primefinder;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeKeyListener implements NativeKeyListener {

    private PrimeFinderThread pft1;
    private PrimeFinderThread pft2;
    private PrimeFinderThread pft3;

    public PrimeKeyListener(){
        pft1 = new PrimeFinderThread(0, 10000000);
        pft2 = new PrimeFinderThread(10000001, 20000000);
        pft3 = new PrimeFinderThread(20000001, 30000000);
    }

    public void empezar(){
        empieceEscucha();
        long inicio = System.currentTimeMillis();
        pft1.start(); pft2.start(); pft3.start();
        long transcurrido;
        do{
            transcurrido = System.currentTimeMillis() - inicio;
        }while (transcurrido < 5000);
        System.out.println("Presione ENTER para continuar la ejecución");
        pft1.suspend(); pft2.suspend(); pft3.suspend();
        terminar();
    }

    private void empieceEscucha() {
        try {
            apagarLogger();
            PrintStream systemOutStream = System.out;
            //Para evitar ciertos logs
            System.setOut(null);
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
            //Restaurar el print
            System.setOut(systemOutStream);
        } catch (NativeHookException ex) {
            System.err.println("Ocurrió un error al registrar el native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    private void terminar() {
        try {
            pft1.join();
        } catch (InterruptedException e) {
            System.err.println("Error de interrupcion en prime-thread 1");
            System.exit(1);
        }
        try {
            pft2.join();
        } catch (InterruptedException e) {
            System.err.println("Error de interrupcion en prime-thread 2");
            System.exit(1);
        }
        try {
            pft3.join();
        } catch (InterruptedException e) {
            System.err.println("Error de interrupcion en prime-thread 3");
            System.exit(1);
        }
        System.exit(0);
    }

    private void apagarLogger(){
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        Handler[] handlers = Logger.getLogger("").getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].setLevel(Level.OFF);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ENTER){
            pft1.resume(); pft2.resume(); pft3.resume();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
       return;
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        return;
    }

}
