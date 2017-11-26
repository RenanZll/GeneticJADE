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
public class DFSolutionDescription extends DFAgentDescription {
    
    
    public static DFSolutionDescription for_register(AID aid, Integer fitness){
        return new DFSolutionDescription(aid, fitness);
    }
    
    public static DFSolutionDescription for_search() {
        return new DFSolutionDescription();
    }
    
    public DFSolutionDescription() {
        addServices(generalReproductionService());
    }

    public DFSolutionDescription(AID aid, Integer fitness){
        setName(aid);
        addServices(individualReproductionService(fitness));
    }
    
    private ServiceDescription generalReproductionService() {
        ServiceDescription reproductionDescription = new ServiceDescription();
        reproductionDescription.setName("Reproduce");
        reproductionDescription.setOwnership("Solution");
        reproductionDescription.setType("BasicNeeds");
        return reproductionDescription;
    }
    
    private ServiceDescription individualReproductionService(Integer fitness) {
        ServiceDescription individualReproductionService =
                generalReproductionService();
        individualReproductionService
                .addProperties(fitnessProperty(fitness)); 
        return individualReproductionService;
    }
    
    private Property fitnessProperty(Integer fitness) {
        Property fitnessProperty = new Property();
        fitnessProperty.setName("FITNESS");
        fitnessProperty.setValue(fitness);
        return fitnessProperty;
    }
    
}
