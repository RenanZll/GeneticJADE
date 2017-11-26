/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.evaluator;

import genetic.solution.Chromossome;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altargin
 */
public class EvaluateFitness extends CyclicBehaviour {
    
    Evaluator myEvaluator;
    
    public EvaluateFitness(Agent agent) {
        super(agent);
        myEvaluator = (Evaluator) agent;
    }
    
    @Override
    public void action() {
        ACLMessage message = getMessage();
        if (message!= null) evaluateFitness(message);
        else
        {
           myEvaluator.says("Bloqueado para esperar receber mensagem.....");
           block();
        }
    }
    
    private void evaluateFitness(ACLMessage message){
        myEvaluator.says("Calculando fitness de " + message.getSender().getName());
        Chromossome chromossome = chromossomeFrom(message);
        myEvaluator.send(buildAnswer(message, chromossome));
    }
    
    private Chromossome chromossomeFrom(ACLMessage message){
        try {
            return (Chromossome) message.getContentObject();
        } catch (UnreadableException ex) {
            Logger.getLogger(EvaluateFitness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private ACLMessage buildAnswer(ACLMessage message, Chromossome chromossome){
        try {
            ACLMessage answer = message.createReply();
            answer.setPerformative(ACLMessage.INFORM);
            answer.setContentObject((int) chromossome.fitness());
            return answer;
        } catch (IOException ex) {
            Logger.getLogger(EvaluateFitness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    private ACLMessage getMessage(){
        MessageTemplate fitness_context
                = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
//                MessageTemplate.and(
//                        MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
//                        MessageTemplate.MatchContent("Fitness")
//                );

        return myEvaluator.receive(fitness_context);
    }    
}
