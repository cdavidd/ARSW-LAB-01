/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author cdavi_000
 */
public class PrimeTest {

    private PrimeFinderThread pfOne;

    public PrimeTest(){

    }

    @Before
    public void setup(){
        pfOne = new PrimeFinderThread(0, 100000);
        pfOne.start();
        try {
            pfOne.join();
        } catch (InterruptedException e) {
            fail();
        }
    }
    
    @Test
    public void evenNumberOfThreads() {
        PrimeFinderThread pfTwo = new PrimeFinderThread(0, 50000);
        PrimeFinderThread pfThree = new PrimeFinderThread(50001, 100000);
        pfTwo.start(); pfThree.start();
        List<Integer> respuesta = new LinkedList<>();
        try {
            pfTwo.join();
            pfThree.join();
        } catch (InterruptedException e) {
            fail();
        }
        respuesta.addAll(pfTwo.getPrimes());
        respuesta.addAll(pfThree.getPrimes());
        assertEquals(respuesta.size() , pfOne.getPrimes().size());
        List<Integer> comparar = pfOne.getPrimes();
        for (int i = 0 ; i < respuesta.size(); i++){
            assertEquals(respuesta.get(i),comparar.get(i));
        }
    }

    @Test
    public void oddNumberOfThreads() {
        PrimeFinderThread pfTwo = new PrimeFinderThread(0, 33333);
        PrimeFinderThread pfThree = new PrimeFinderThread(33334, 66666);
        PrimeFinderThread pfFour = new PrimeFinderThread(66667, 100000);
        pfTwo.start(); pfThree.start(); pfFour.start();
        List<Integer> respuesta = new LinkedList<>();
        try {
            pfTwo.join();
            pfThree.join();
            pfFour.join();
        } catch (InterruptedException e) {
            fail();
        }
        respuesta.addAll(pfTwo.getPrimes());
        respuesta.addAll(pfThree.getPrimes());
        respuesta.addAll(pfFour.getPrimes());
        assertEquals(respuesta.size() , pfOne.getPrimes().size());
        List<Integer> comparar = pfOne.getPrimes();
        for (int i = 0 ; i < respuesta.size(); i++){
            assertEquals(respuesta.get(i),comparar.get(i));
        }
    }

}
