/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.mutator;

import jade.core.Agent;

/**
 *
 * @author altargin
 */
public class Mutator extends Agent {
    
    @Override
    protected void setup() {
        addBehaviour(new MutateAgent(this));
    }
}
