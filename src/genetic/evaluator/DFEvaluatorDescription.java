/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.evaluator;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 *
 * @author renan
 */
public class DFEvaluatorDescription extends DFAgentDescription {
    
    
    public static DFEvaluatorDescription for_register(AID aid){
        return new DFEvaluatorDescription(aid);
    }
    
    public static DFEvaluatorDescription for_search() {
        return new DFEvaluatorDescription();
    }
    
    public DFEvaluatorDescription() {
        addServices(generalReproductionService());
    }

    public DFEvaluatorDescription(AID aid){
        setName(aid);
        addServices(generalReproductionService());
    }
    
    private ServiceDescription generalReproductionService() {
        ServiceDescription reproductionDescription = new ServiceDescription();
        reproductionDescription.setName("EvaluateFitness");
        reproductionDescription.setOwnership("Evaluator");
        reproductionDescription.setType("Evaluate");
        return reproductionDescription;
    }   
}
