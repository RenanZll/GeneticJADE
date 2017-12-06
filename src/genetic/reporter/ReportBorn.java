/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

import genetic.solution.Chromossome;
import genetic.solution.ChromossomeStatus;
import genetic.solution.RespondToInterest;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author altargin
 */
public class ReportBorn extends CyclicBehaviour {

    private final Reporter myReporter;
    
    ReportBorn(Agent agent) {
        super(agent);
        myReporter = (Reporter) agent;
    }

    @Override
    public void action() {
        ACLMessage message = getMessage();
        if (message!= null) addSolutionState(message);
        else
        {
           myReporter.says("Bloqueado para esperar receber mensagem.....");
           block();
        }
    }
    
    private ACLMessage getMessage(){
        MessageTemplate report_context //= MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                        MessageTemplate.MatchOntology("BornReport")
                );
        
        return myReporter.receive(report_context);
    }

    private void addSolutionState(ACLMessage message)  {
        try {
            Chromossome chromossome = (Chromossome) message.getContentObject();
            SolutionState state = new SolutionState(chromossome, ChromossomeStatus.Alive);
            myReporter.addSolutionState(state);
        } catch (UnreadableException ex) {
            Logger.getLogger(RespondToInterest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}
