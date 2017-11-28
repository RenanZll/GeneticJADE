/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import genetic.evaluator.DFEvaluatorDescription;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class FitnessRequester {
    
    Solution solution;

    public FitnessRequester(Solution solution) {
        this.solution = solution;
    }
    
    public void request(){
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        AID evaluator_name = getRandomEvaluatorName(); 
        if(evaluator_name != null){
            try {
                msg.addReceiver(evaluator_name);
                msg.setContent("Fitness");
                msg.setContentObject((Chromossome) solution.getChromossome());
                solution.send(msg);
                solution.says("Requisitando fitness para " + evaluator_name.getName());
            } catch (IOException ex) {
                Logger.getLogger(Born.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    private AID getRandomEvaluatorName(){
        try {
            DFAgentDescription[] evaluator_list = {};
            while(evaluator_list.length == 0)
                evaluator_list
                    = DFService.search(solution, DFEvaluatorDescription.for_search());
            int random_int = new Random().nextInt(evaluator_list.length);
            return evaluator_list[random_int].getName();
        } catch (FIPAException ex) {
            Logger.getLogger(Born.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
