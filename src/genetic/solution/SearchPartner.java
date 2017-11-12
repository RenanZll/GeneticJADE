/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author altargin
 */
public class SearchPartner extends SimpleBehaviour {

    
    SearchPartner(Agent agent) {
        super(agent);
    }

    
    @Override
    public void action() {
        
        ACLMessage reproductionPropose = new ACLMessage(ACLMessage.PROPOSE);
//        reproductionPropose.addReceiver(new AID(receiver));
//        reproductionPropose.setContent("<content>");
        
    }
    
    private Agent choosen_partner(){
        return new Agent();
    }
    
    private DFAgentDescription[] partner_list(){
        try {
            DFService.search(myAgent, mySolution().partner_description());//É o próprio agente que é o primeiro parametro
        } catch (FIPAException ex) {
            Logger.getLogger(SearchPartner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean done() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Solution mySolution() {
        return (Solution) myAgent;
    }
    
}
