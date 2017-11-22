/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import genetic.jade.GeneticAlgorithm;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altargin
 */
public class Reproduce extends CyclicBehaviour {

    Reproduce(Agent my_agent) {
        super(my_agent);
    }

    @Override
    public void action() {
        ACLMessage message = getMessage();
        if (message != null) try {
            reproduce(message);
            mySolution().getOlder();
        } catch (UnreadableException ex) {
            Logger.getLogger(Reproduce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ACLMessage getMessage() {
        MessageTemplate reproduction_context
                = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        return mySolution().receive(reproduction_context);
    }
    
    private void reproduce(ACLMessage message) throws UnreadableException{
        mySolution().says("Se reproduzindo com " + message.getSender().getName());
        Chromossome partner_chromossome = (Chromossome) message.getContentObject();
        register_new_solution(
                generateRandomName(),
                crossover_with(partner_chromossome));
    }
    
    private Chromossome crossover_with(Chromossome partner_chromossome){
        return myChromossome().crossover(partner_chromossome);
    }
    
    private void register_new_solution(String name, Chromossome chromossome){
        GeneticAlgorithm ga = new GeneticAlgorithm(mySolution().getContainerController());
        ga.addSolution(name, chromossome);
    }
    
    private Solution mySolution() {
        return (Solution) myAgent;
    }
    
    private Chromossome myChromossome(){
        return mySolution().getChromossome();
    }
    
    private String generateRandomName(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
