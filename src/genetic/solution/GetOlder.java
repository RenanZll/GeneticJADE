/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.behaviours.CyclicBehaviour;
import java.util.Random;

/**
 *
 * @author altargin
 */
public class GetOlder extends CyclicBehaviour {

    private Solution solution;
    
    static final int SUCCEDED_REPRODUCTIONS = 2;
    
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
    
    public void killSolution(){
        solution.doDelete();
        solution.says("I'm dead! :(");
    }
}
