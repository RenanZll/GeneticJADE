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
public class SearchPartner extends SimpleBehaviour {

    
    SearchPartner(Agent agent) {
        super(agent);
    }

    
    @Override
    public void action() {   
        DFAgentDescription partner_description = null;
        try {
             partner_description =  randomPartnerDescription(partner_list());
        } catch (FIPAException ex) {
            Logger.getLogger(SearchPartner.class.getName()).log(Level.SEVERE, null, ex);
        }
        Agent partner = partner(partner_description);//Implementar comunicação entre agentes(Dança do acasalamento)
        myAgent.addBehaviour(new Reproduce(myAgent, partner));
    }
    
    
    private DFAgentDescription[] partner_list() throws FIPAException{
        return DFService.search(myAgent, mySolution().partner_description());//É o próprio agente que é o primeiro parametro
    }
    
    @Override
    public boolean done() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
