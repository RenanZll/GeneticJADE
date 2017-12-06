/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution.behaviours;

import genetic.solution.DFSolutionDescription;
import genetic.solution.Solution;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import java.util.Arrays;
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
        Double[] fitnesList = availableFitnesses(aliveSolution());
        double maxFitness = getMax(fitnesList);
        double minFitness = getMin(fitnesList);
        float succeded_reproductions = (float) (2.0f*((maxFitness - solution.getFitness())/((float)(maxFitness-minFitness)))+1.0f);
        float live_probability = 1 - (solution.getAge() / SUCCEDED_REPRODUCTIONS);
        solution.says("Fitness: "+solution.getFitness()+"Prob de viver: "+succeded_reproductions);
        if(death >= live_probability) killSolution();
    }    
    
    private DFAgentDescription[] aliveSolution(){
        try {
            return DFService.search(solution, DFSolutionDescription.for_search());
        } catch (FIPAException ex) {
            Logger.getLogger(GetOlder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Double[] availableFitnesses(DFAgentDescription[] solutionList){
        return Arrays.stream(solutionList)
                .map(agent_desc -> DFSolutionDescription.parse(agent_desc))
                .map(solution_desc -> solution_desc.fitness())
                .distinct()
                .sorted()
                .toArray(Double[]::new);
    }
    
    public void killSolution(){
        try {
            DFService.deregister(solution);
            solution.doDelete();
            solution.says("I'm dead! :(");
        } catch (FIPAException ex) {
            Logger.getLogger(GetOlder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double getMax(Double[] inputArray){ 
        double maxValue = inputArray[0]; 
        for(int i=1;i < inputArray.length;i++){ 
          if(inputArray[i] > maxValue){ 
             maxValue = inputArray[i]; 
          } 
        } 
        return maxValue; 
    }
    
    public double getMin(Double[] inputArray) {
        double minValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] < minValue) {
                minValue = inputArray[i];
            }
        }
        return minValue;
    }
}
