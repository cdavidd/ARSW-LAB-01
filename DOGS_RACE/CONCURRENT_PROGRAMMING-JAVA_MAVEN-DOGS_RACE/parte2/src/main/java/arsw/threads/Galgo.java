package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 *
 * @author rlopez
 */
public class Galgo extends Thread {
    private int paso;
    private Carril carril;
    RegistroLlegada regl;
    private boolean enPausa;
    private int ubicacion;

    public static Object monitor = MainCanodromo.monitor;

    public Galgo(Carril carril, String name, RegistroLlegada reg) {
        super(name);
        this.carril = carril;
        paso = 0;
        this.regl = reg;
        enPausa = false;
    }

    public void setEnPausa(boolean pausa) {
        this.enPausa = pausa;
    }

    public void corra() throws InterruptedException {
        while (paso < carril.size()) {
            if (!enPausa) {
                Thread.sleep(100);
                carril.setPasoOn(paso++);
                carril.displayPasos(paso);
                if (paso == carril.size()) {
                    carril.finish();
                    synchronized (regl) {
                        ubicacion = regl.getUltimaPosicionAlcanzada();
                        //regl.setUltimaPosicionAlcanzada(ubicacion+1);
                        regl.setUltimaPosicionAlcanzada();
                        System.out.println("El galgo " + this.getName() + " llego en la posicion " + ubicacion);
                        if (ubicacion == 1) {
                            regl.setGanador(this.getName());
                        }
                    }
                }
            } else {
                pausar();
            }
        }
    }

    private void pausar() {
        synchronized (monitor) {
            if (enPausa) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
					System.err.println("Error " + e.getMessage());
                }
            }
            enPausa = false;
        }
    }

    @Override
    public void run() {
        try {
            corra();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getUbicacion(){
        return ubicacion;
    }
}
