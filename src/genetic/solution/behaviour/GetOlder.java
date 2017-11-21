/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution.behaviour;

import genetic.solution.Solution;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altargin
 */
public class GetOlder extends CyclicBehaviour {

    private Solution solution;
    
    static final float SUCCEDED_REPRODUCTIONS = 0.9f;
    
    public GetOlder(Solution solution) {
        this.solution = solution;
    }

    @Override
    public void action() {
        Random rnd = new Random();
        float death = rnd.nextFloat();
        float live_probability = 1 - (solution.getAge() / SUCCEDED_REPRODUCTIONS);
        if(death >= live_probability) killSolution();
    }    
    
    private void killSolution(){
        try {
            DFService.deregister(solution);
            solution.doDelete();
            solution.says("I'm dead! :(");
        } catch (FIPAException ex) {
            Logger.getLogger(GetOlder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
