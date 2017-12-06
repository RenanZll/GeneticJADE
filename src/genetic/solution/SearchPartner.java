/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author altargin
 */
public class SearchPartner extends CyclicBehaviour {

    Random rnd;
    
    SearchPartner(Agent agent) {
        super(agent);
        rnd = new Random();
    }

    @Override
    public void action() {
        DFAgentDescription partner = null;
        try {
             partner =  randomPartnerDescription(partnerList());
             mateWith(partner);
//             Thread.sleep(rnd.nextInt(10)*1000);
        } catch (FIPAException ex) {
            Logger.getLogger(SearchPartner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mateWith(DFAgentDescription partner){
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.addReceiver(partner.getName());
        msg.setContent("Reproduction");
        myAgent.send(msg);
        mySolution().says("Enviando proposta para " + partner.getName().getName());
    }
    
    
    private DFAgentDescription[] partnerList() throws FIPAException{
        DFAgentDescription[] solution_list =
                DFService.search(myAgent, DFSolutionDescription.for_search());
        return removeMySolutionFrom(solution_list);
    }
    
    private DFAgentDescription[] removeMySolutionFrom(DFAgentDescription[] solutionList){
        DFAgentDescription[] partners = Arrays.stream(solutionList)
                .filter(partner -> dontHaveSameName(partner, mySolution()))
                .toArray(DFAgentDescription[]::new);
        return partners;
    }
    
    private boolean dontHaveSameName(DFAgentDescription partner, Solution solution){
        String partnerName = partner.getName().getName();
        String selfName = mySolution().getAID().getName();
        return !partnerName.equals(selfName);
    }
    
    private Solution mySolution(){
        return (Solution) myAgent;
    }
    
    private DFAgentDescription randomPartnerDescription(DFAgentDescription[] array) {
        return array[rnd.nextInt(array.length)];
    }
}
