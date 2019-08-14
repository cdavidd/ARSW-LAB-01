## Part I - Introduction to Java Threads

Se completó el método la clase `CountThread`, esta es la implementación:
```java
public class CountThread extends Thread{
    
    private int a;
    private int b;
    
    public CountThread(int a, int b ){
        super();
        this.a=a;
        this.b=b;
    }

    @Override
    public void run() {
        for (int i=a; i<=b;i++){
            System.out.println(i);
        }
    }

}
```

Se completó el método `main` de la clase `CountThreadMain`, esta es la implementación:
```java
public class CountThreadsMain {   
    public static void main(String a[]){
        Thread t1= new CountThread(0,99);
        Thread t2= new CountThread(100,199);
        Thread t3= new CountThread(200,299);
        t1.start();
        t2.start();
        t3.start();
    }   
}
```

Los resultados al empezar los 3 threads mediante el método `start()` se obtuvo el siguente resultado:
![](img/start_count.PNG)

Como se aprecia en la imagen, no existe un orden de ejecución al iniciar los hilos.