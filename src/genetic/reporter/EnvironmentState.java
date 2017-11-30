/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renan
 */
public class EnvironmentState {

    private int maximumFitness;
    private int minimumFitness;
    
    private List<SolutionState> solutionStates;

    public EnvironmentState() {
        maximumFitness = Integer.MIN_VALUE;
        minimumFitness = Integer.MAX_VALUE;
        
        solutionStates = new ArrayList<>();
    }

    //max min thing
    void addSolution(SolutionState state) {
        solutionStates.add(state);
        
        int fitness = state.getChromossome().fitness();
        
        if (fitness > maximumFitness)
            maximumFitness = fitness;
        
        if (fitness < minimumFitness)
            minimumFitness = fitness;
    }  

    public int getMaximumFitness() {
        return maximumFitness;
    }

    public int getMinimumFitness() {
        return minimumFitness;
    }

    public List<SolutionState> getSolutionStates() {
        return solutionStates;
    }

}
