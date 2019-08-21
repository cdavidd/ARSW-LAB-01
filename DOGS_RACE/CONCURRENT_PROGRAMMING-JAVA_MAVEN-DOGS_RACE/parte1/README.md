# Dogs Race Case

## Compile and run instructions

Entrar al directorio `ARSW-LAB-01/DOGS_RACE/CONCURRENT_PROGRAMMING-JAVA_MAVEN-DOGS_RACE/parte1`

- **Para compilar:** Ejecutar `mvn package`
- **Para ejecutar PrimeFinder:** Ejecutar `mvn exec:java -Dexec.mainClass="edu.eci.arsw.primefinder.Main`
- **Para ejecutar las pruebas:** Ejecutar `mvn test`

## Part I
1. Verificación con un hilo, en el administrador de tareas de Windows se puede ver como el programa al ejecutarse utiliza a lo mucho 2 CPU, estas cambian de 00 a 02.
2.  Verificación con tres hilos, en el administrador de tareas de Windows se puede ver como el programa al ejecutarse utiliza a lo mucho 4 CPU, estas cambian de 00 a 03 a 04 a 02.

```java
public class Main {

    public static void main(String[] args){
        PrimeFinderThread pft1 = new PrimeFinderThread(0, 10000000);
        PrimeFinderThread pft2 = new PrimeFinderThread(10000001, 20000000);
        PrimeFinderThread pft3 = new PrimeFinderThread(20000001, 30000000);
        pft1.start(); pft2.start(); pft3.start();

```

3. **Transcurso de 5 seg:**
   Utilizando `System.currentTimeMillis()` tomaremos el tiempo inicial en `inicio` para luego de la misma forma en la variable `transcurrido` para saber cuanto tiempo a transcurrido mediante la diferencia de `inicio y transcurrido`

```java
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
```

**Enter:**
Se utilizo la libreria `jnativehook`, implementamos `NativeKeyListener` para detectar cuando el usuario oprima la tecla _Enter_.

```java
@Override
public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ENTER){
        pft1.resume(); pft2.resume(); pft3.resume();
    }
}
```
