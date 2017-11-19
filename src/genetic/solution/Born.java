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
        getFitness();
        registerSolution();
        
        mySolution().addBehaviour(new SearchPartner(mySolution()));
        mySolution().addBehaviour(new GetOlder(mySolution()));
    }
    
    private void registerSolution(){
     try{
        DFService.register(mySolution(), mySolution().description());
     }catch (FIPAException ex){
        ex.printStackTrace();
     }
     mySolution().says("Registrado nas paginas amarelas!");
    }
    
    private void getFitness(){
        int fitness = 0;//TODO: Adquirir Fitness
        mySolution().setFitness(fitness);
    }
    
    private Solution mySolution(){
        return (Solution) myAgent;
    }
    
}
