/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

import genetic.solution.Born;
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
    
    @Override
    protected void setup() {
        loadStopCondition();
        
        addBehaviour(new ReportBorn(this));
    }
        
    private void loadStopCondition() {
        stopCondition = (StopCondition) this.getArguments()[0];
    }
    
    public void checkStopCondition(){
        Boolean ended = stopCondition.check(environmentState);
        
        if(ended){
            this.says("ACABOU!");
            try {
                this.getContainerController().kill();
            } catch (ControllerException ex) {
                Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addSolutionState(SolutionState state){
        environmentState.addSolution(state);
        
        checkStopCondition();
    }

    void says(String message) {
        System.out.println("Reporter: " + message); //To change body of generated methods, choose Tools | Templates.
    }
}
