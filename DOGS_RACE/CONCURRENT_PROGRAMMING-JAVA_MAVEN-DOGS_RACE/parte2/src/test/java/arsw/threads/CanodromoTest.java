/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.threads;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author cdavi_000
 */
public class CanodromoTest {
    public CanodromoTest(){}
    
    @Test
    public void posicionFinalUnica(){
        Canodromo can = new Canodromo(17, 100);
        Galgo[] galgos = new Galgo[can.getNumCarriles()];
        RegistroLlegada reg = new RegistroLlegada();
        for (int i = 0; i < can.getNumCarriles(); i++) {
            galgos[i] = new Galgo(can.getCarril(i), "" + i, reg);
            galgos[i].start();
        }
        for (int i = 0; i < can.getNumCarriles(); i++) {
            try {
                galgos[i].join();
            } catch (InterruptedException ex) {
                fail();
            }
        }
        boolean ok = true;
        Set<Integer> pos = new HashSet<Integer>();
        for (int i = 0; i < can.getNumCarriles(); i++) {
            int p = galgos[i].getUbicacion();
            if(!pos.contains(p)){
                pos.add(p);
            }else{
                ok= false;
                break;
            }
        }
        assertTrue(ok);
    }
}
