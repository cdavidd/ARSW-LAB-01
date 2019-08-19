# Dogs Race Case

## Compile and run instructions

Entrar al directorio `ARSW-LAB-01/DOGS_RACE/CONCURRENT_PROGRAMMING-JAVA_MAVEN-DOGS_RACE/parte2`

- **Para compilar:** Ejecutar `mvn package`
- **Para ejecutar PrimeFinder:** Ejecutar `mvn exec:java -Dexec.mainClass="arsw.threads.MainCanodromo`
- **Para ejecutar las pruebas:** Ejecutar `mvn test`

## Part II

Existe una condición de carrera debido a que los hilos compartían un mismo objeto y realizaban modificaciones sobre este, por lo tanto habían casos en que los galgos tenían la misma posición al terminar la carrera.

## Part III

1. **Join():**
   En la clase `MainCanodromo.java` se añadio el metodo `join()` despues del `.start()` en los Threads Galgo.

```java
public void run() {
    for (int i = 0; i < can.getNumCarriles(); i++) {
        galgos[i] = new Galgo(can.getCarril(i), "" + i, reg);
        galgos[i].start();
    }
    for (int i = 0; i < can.getNumCarriles(); i++) {
        try {
            galgos[i].join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainCanodromo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ...
```

2.

En esta parte de código perteneciente a la clase `Galgo.java` en la función `corra()` encontramos la región critica.

```java
    int ubicacion=regl.getUltimaPosicionAlcanzada();
    regl.setUltimaPosicionAlcanzada(ubicacion+1);
    System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
    if (ubicacion==1){
        regl.setGanador(this.getName());
    }
```

3.

**modificacion:**
se sincroniza esta parte de código haciendo referencia a _regl_ en `synchronized( regl )` ya que es accedida por los demás Threads a su función de incremento de posición.

```java
synchronized (regl) {
    ubicacion = regl.getUltimaPosicionAlcanzada();
    //regl.setUltimaPosicionAlcanzada(ubicacion+1);
    regl.setUltimaPosicionAlcanzada();
    System.out.println("El galgo " + this.getName() + " llego en la posicion " + ubicacion);
    if (ubicacion == 1) {
        regl.setGanador(this.getName());
    }
}
```

4.

Al utilizar un monitor en común para todos los hilos, podemos realizar la acción de pausar y continuar mediante los métodos wait() y notifyAll() que nos permiten colocar en espera dichos hilos hasta que se reanuden al notificarlos.

_Clase MainCanodromo.java_

```java
can.setStopAction(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Carrera pausada!");
                for (int i = 0; i < can.getNumCarriles(); i++) {
                    galgos[i].setEnPausa(true);
                }
            }
        }
);

can.setContinueAction(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Carrera reanudada!");
                synchronized (monitor) {
                    for (int i = 0; i < can.getNumCarriles(); i++) {
                        galgos[i].setEnPausa(false);
                    }
                    monitor.notifyAll();
                }
            }
        }
);
```

_clase Galgo.java_

```java
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
```
