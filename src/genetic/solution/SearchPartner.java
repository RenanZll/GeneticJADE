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
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author altargin
 */
public class SearchPartner extends CyclicBehaviour {

    
    SearchPartner(Agent agent) {
        super(agent);
    }

    
    @Override
    public void action() {
        mySolution().says("Procurando por parceiros...");
        DFAgentDescription partner_description = null;
        try {
             partner_description =  randomPartnerDescription(partner_list());
        } catch (FIPAException ex) {
            Logger.getLogger(SearchPartner.class.getName()).log(Level.SEVERE, null, ex);
        }
        Agent partner = mySolution();// partner(partner_description);//Implementar comunicação entre agentes(Dança do acasalamento)
        mySolution().says("Nome do Parceiro: " + partner.getName());
        myAgent.addBehaviour(new Reproduce(myAgent, partner));
    }
    
    
    private DFAgentDescription[] partner_list() throws FIPAException{
        return DFService.search(myAgent, SolutionDescription.general());//O próprio agente que é o primeiro parametro?
    }
    
    private Solution mySolution(){
        return (Solution) myAgent;
    }
    
    private static DFAgentDescription randomPartnerDescription(DFAgentDescription[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    private Solution partner(DFAgentDescription partner_description) {
        ServiceDescription reproduction = (ServiceDescription) partner_description.getAllServices().next();
        Iterator properties = reproduction.getAllProperties();
        while(properties.hasNext()){
            Property property = (Property) properties.next();
            if("SELF".equals(property.getName()))
                return (Solution) property.getValue();
        }
        return null;
    }
}
