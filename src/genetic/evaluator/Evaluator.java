/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.evaluator;

import jade.core.Agent;

/**
 *
 * @author altargin
 */
public class Evaluator extends Agent {
    
    @Override
    protected void setup() {
        addBehaviour(new EvaluateFitness(this));
    }
}
