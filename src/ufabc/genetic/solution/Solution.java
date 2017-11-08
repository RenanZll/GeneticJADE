/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufabc.genetic.solution;

import jade.core.Agent;

/**
 *
 * @author renan
 */
public class Solution extends Agent {

    private Chromossome chromossome;
    private Integer age;
    private Integer fitnessValue;
    
    public Solution(Chromossome chromossome) {
        super();
        this.chromossome = chromossome;
    }
    
    protected void setup() {
        addBehaviour(new Born(this));
    }
    
    public Chromossome getChromossome(){
        return this.chromossome;
    }
}
