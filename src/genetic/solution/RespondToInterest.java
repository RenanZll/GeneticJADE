/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class RespondToInterest extends CyclicBehaviour {

    RespondToInterest(Agent agent) {
        super(agent);
    }

    @Override
    public void action() {
        ACLMessage message = getMessage();
        if (message!= null) accept_proposal(message);
        else
        {
           mySolution().says("Bloqueado para esperar receber mensagem.....");
           block();
        }
    }
    
    private void accept_proposal(ACLMessage message){
        mySolution().says("Aceitando proposta de " + message.getSender().getName());
        try {
            ACLMessage answer = message.createReply();
            answer.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
            answer.setContentObject((Chromossome) mySolution().getChromossome());
            mySolution().send(answer);
        } catch (IOException ex) {
            Logger.getLogger(RespondToInterest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ACLMessage getMessage(){
        MessageTemplate reproduction_context
                = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                        MessageTemplate.MatchContent("Reproduction")
                );
        return mySolution().receive(reproduction_context);
    }
    
    
    private Solution mySolution(){
        return (Solution) myAgent;
    } 
}
