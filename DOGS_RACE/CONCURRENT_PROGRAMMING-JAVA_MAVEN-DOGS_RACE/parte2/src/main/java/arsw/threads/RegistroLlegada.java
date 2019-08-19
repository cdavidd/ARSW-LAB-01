package arsw.threads;

public class RegistroLlegada {
        //private final AtomicLong count = new AtomicLong(1); 
	private  int ultimaPosicionAlcanzada=1;

	private String ganador=null;
	
	public String getGanador() {
            return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public int getUltimaPosicionAlcanzada() {
                
                return ultimaPosicionAlcanzada;
	}
        /*
	public void setUltimaPosicionAlcanzada(int ultimaPosicionAlcanzada) {
                synchronized(this){
                    this.ultimaPosicionAlcanzada = ultimaPosicionAlcanzada;
                }
	}
        */
        public void setUltimaPosicionAlcanzada() {
                this.ultimaPosicionAlcanzada ++;
	}
	
	
}
