/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

import genetic.solution.Born;
import genetic.solution.Chromossome;
import jade.core.Agent;

/**
 *
 * @author renan
 */
public class Reporter extends Agent{
    
    StopCondition stopCondition;
    
    @Override
    protected void setup() {
        
    }
    
    private void loadStopCondition() {
        stopCondition = (StopCondition) this.getArguments()[0];
    }
}
