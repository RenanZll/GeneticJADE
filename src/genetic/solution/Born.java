/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;

/**
 *
 * @author renan
 */
public class Born extends OneShotBehaviour{

    Born(Agent agent) {
        super(agent);
    }

    @Override
    public void action() {
        mySolution().says("Nascendo...");
        int fitness = 0;//TODO: Adquirir Fitness
        mySolution().setFitness(fitness);
        register_solution();
        
        myAgent.addBehaviour(new SearchPartner(mySolution()));
        //myAgent.addBehaviour(new GetOlder());
    }
    
    private void register_solution(){
     try{
        DFService.register(mySolution(), mySolution().description());
     }catch (FIPAException ex){
        ex.printStackTrace();
     }
     mySolution().says("Registrado nas paginas amarelas!");
    }
    
    private Solution mySolution(){
        return (Solution) myAgent;
    }
    
}
