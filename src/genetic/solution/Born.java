/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class Born extends SimpleBehaviour{
    
    boolean done = false;
    Solution mySolution;
    
    Born(Agent agent) {
        super(agent);
        mySolution = (Solution) agent;
    }

    @Override
    public void action() {
        mySolution.says("Nascendo...");
        ACLMessage message = getMessage();
        if(message!= null){
            setUpSolution(message);
        }else{
//            mySolution.says("Bloqueando Born.....");
            block();
        }
    }
    
        
    private void setUpSolution(ACLMessage message){
//        mySolution.says("Configurando comportamentos...");
        setFitness(message);
        registerSolution();
        reportSolution();

        mySolution.addBehaviour(new SearchPartner(mySolution));
        mySolution.addBehaviour(new RespondToInterest(mySolution));
        mySolution.addBehaviour(new Reproduce(mySolution));
        mySolution.addBehaviour(new GetOlder(mySolution));
        done = true;
    }
    
    private void registerSolution(){
     try{
        DFService.register(mySolution, mySolution.description());
     }catch (FIPAException ex){
        ex.printStackTrace();
     }
//     mySolution.says("Registrado nas paginas amarelas!");
    }
    
    private void reportSolution() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        AID reporter_name = new AID("Reporter", AID.ISLOCALNAME); 

        try {
            msg.addReceiver(reporter_name);
            msg.setOntology("BornReport");
            msg.setContentObject((Chromossome) mySolution.getChromossome());
            mySolution.send(msg);
            mySolution.says("Reportando nascimento para " + reporter_name.getName());
        } catch (IOException ex) {
            Logger.getLogger(Born.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ACLMessage getMessage(){
        MessageTemplate fitness_context = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        return mySolution.receive(fitness_context);
    }

    private void setFitness(ACLMessage message) {
        try {
            mySolution.says("Fitness: " + String.valueOf((double) message.getContentObject()));
            mySolution.setFitness((double) message.getContentObject());
        } catch (UnreadableException ex) {
            Logger.getLogger(Born.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean done() {
        return done;
    }
    
}
