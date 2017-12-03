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
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author renan
 */
public class DFSolutionDescription extends DFAgentDescription {
    
    
    public static DFSolutionDescription for_register(AID aid, Double fitness){
        return new DFSolutionDescription(aid, fitness);
    }
    
    public static DFSolutionDescription for_search() {
        return new DFSolutionDescription();
    }
    
    private DFSolutionDescription() {
        addServices(generalReproductionService());
    }

    private DFSolutionDescription(AID aid, double fitness){
        setName(aid);
        addServices(individualReproductionService(fitness));
    }
    
    private DFSolutionDescription(DFAgentDescription agentDescription) {
        setName(agentDescription.getName());
        agentDescription.getAllServices().forEachRemaining(
                service -> {
                    addServices((ServiceDescription) service);
                }
        );

    }
    
    private ServiceDescription generalReproductionService() {
        ServiceDescription reproductionDescription = new ServiceDescription();
        reproductionDescription.setName("Reproduce");
        reproductionDescription.setOwnership("Solution");
        reproductionDescription.setType("BasicNeeds");
        return reproductionDescription;
    }
    
    private ServiceDescription individualReproductionService(double fitness) {
        ServiceDescription individualReproductionService =
                generalReproductionService();
        individualReproductionService
                .addProperties(fitnessProperty(fitness)); 
        return individualReproductionService;
    }
    
    private Property fitnessProperty(double fitness) {
        Property fitnessProperty = new Property();
        fitnessProperty.setName("FITNESS");
        fitnessProperty.setValue(fitness);
        return fitnessProperty;
    }
    
    public double fitness(){
         ServiceDescription reproduction = (ServiceDescription) getAllServices().next();
         Iterator properties = reproduction.getAllProperties();
         while(properties.hasNext()){
             Property property = (Property) properties.next();
             if("FITNESS".equals(property.getName()))
                 return Double.parseDouble((String) property.getValue());
         }
        return 0;
    }
    
    public static DFSolutionDescription parse(DFAgentDescription agentDescription){
        return new DFSolutionDescription(agentDescription);
    }
    
}
