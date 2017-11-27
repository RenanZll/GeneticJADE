/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.evaluator;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;

/**
 *
 * @author altargin
 */
public class Evaluator extends Agent {
    
    @Override
    protected void setup() {
        registerEvaluator();
        addBehaviour(new EvaluateFitness(this));
    }
    
    private void registerEvaluator() {
        try {
            DFService.register(this, description());
        } catch (FIPAException ex) {
            ex.printStackTrace();
        }
        says("Registrado nas paginas amarelas!");
    }
    
    public void says(String text) {
        System.out.println(getName() + ": " + text);
    }
    
    public DFAgentDescription description(){
        return DFEvaluatorDescription.for_register(getAID());
    }
}
