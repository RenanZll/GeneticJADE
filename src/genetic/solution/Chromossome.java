/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import java.io.Serializable;

/**
 *
 * @author renan
 */
public interface Chromossome extends Serializable {

    public Chromossome crossover(Chromossome chromossome);
    
    public double fitness();
            
    public void mutate();
    
    public void printGenes();
}
