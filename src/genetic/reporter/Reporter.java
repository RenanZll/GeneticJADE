/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

import genetic.solution.behaviours.Born;
import genetic.solution.Chromossome;
import jade.core.Agent;
import jade.wrapper.ControllerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class Reporter extends Agent{
    
    StopCondition stopCondition;
    EnvironmentState environmentState = new EnvironmentState();
    int population_size;
    
    @Override
    protected void setup() {
        loadStopCondition();
        loadPopulationSize();
        addBehaviour(new ReportBorn(this));
    }
        
    private void loadStopCondition() {
        stopCondition = (StopCondition) this.getArguments()[0];
    }
    
    private void loadPopulationSize() {
        population_size = (int) this.getArguments()[1];
    }
    
    public void checkStopCondition(){
        Boolean ended = stopCondition.check(environmentState);
        
        if(ended){
            System.out.println("CONDIÇÃO DE PARADA ATINGIDA");
            stopCondition.reportResult(environmentState);
            try {
                this.getContainerController().kill();
            } catch (ControllerException ex) {
                Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addSolutionState(SolutionState state){
        environmentState.addSolution(state);
        
        printStatistics();
        
        checkStopCondition();
    }
    
    int last_generation_number = 0;
    
    private void printStatistics(){
        int all_solution_states = environmentState.getSolutionStates().size();
        int current_generation_number = all_solution_states/population_size;
        if(current_generation_number>last_generation_number){
            System.out.println("Current Generation = "+current_generation_number);
            last_generation_number = current_generation_number;
        }
    }
    void says(String message) {
//        System.out.println("Reporter: " + message); //To change body of generated methods, choose Tools | Templates.
    }
}
