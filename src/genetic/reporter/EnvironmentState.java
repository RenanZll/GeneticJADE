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

    private double maximumFitness;
    private double minimumFitness;
    
    private List<SolutionState> solutionStates;

    public EnvironmentState() {
        maximumFitness = Double.MIN_VALUE;
        minimumFitness = Double.MAX_VALUE;
        
        solutionStates = new ArrayList<>();
    }

    //max min thing
    void addSolution(SolutionState state) {
        solutionStates.add(state);
        
        double fitness = state.getChromossome().fitness();
        
        if (fitness > maximumFitness)
            maximumFitness = fitness;
        
        if (fitness < minimumFitness)
            minimumFitness = fitness;
    }  

    public double getMaximumFitness() {
        return maximumFitness;
    }

    public double getMinimumFitness() {
        return minimumFitness;
    }

    public List<SolutionState> getSolutionStates() {
        return solutionStates;
    }

}
