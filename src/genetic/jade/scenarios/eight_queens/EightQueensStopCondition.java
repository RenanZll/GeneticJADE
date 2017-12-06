/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.jade.scenarios.eight_queens;

import genetic.reporter.EnvironmentState;
import genetic.reporter.StopCondition;

/**
 *
 * @author altargin
 */
public class EightQueensStopCondition implements StopCondition {

    @Override
    public boolean check(EnvironmentState environmentState) {
       return environmentState.getMinimumFitness() <= 0;
    }

    @Override
    public void reportResult(EnvironmentState environmentState) {
        System.out.println("TODO: Reportar melhor Solução");
    }    
}
