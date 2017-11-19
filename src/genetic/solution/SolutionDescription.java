/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 *
 * @author renan
 */
public class SolutionDescription {
    
    public static DFAgentDescription general() {
        DFAgentDescription agent_description = new DFAgentDescription();
        agent_description.addServices(general_reproduction_service());
        return agent_description;
    }

    private static ServiceDescription general_reproduction_service() {
        ServiceDescription reproduction_description = new ServiceDescription();
        reproduction_description.setName("Reproduce");
        reproduction_description.setOwnership("Solution");
        reproduction_description.setType("BasicNeeds");
        return reproduction_description;
    }
    
    public static DFAgentDescription individual(AID aid, Integer fitness){
        DFAgentDescription agent_description = new DFAgentDescription();
        agent_description.setName(aid);
        agent_description.addServices(reproduction_service(fitness));
        return agent_description;
    }
    
    private static ServiceDescription reproduction_service(Integer fitness) {
        ServiceDescription reproduction_description = new ServiceDescription();
        reproduction_description.setName("Reproduce");
        reproduction_description.setOwnership("Solution");
        reproduction_description.setType("BasicNeeds");
        reproduction_description.addProperties(fitness_property(fitness));
        return reproduction_description;
    }
    
    private static Property fitness_property(Integer fitness) {
        Property fitness_property = new Property();
        fitness_property.setName("FITNESS");
        fitness_property.setValue(fitness);
        return fitness_property;
    }
    
}
