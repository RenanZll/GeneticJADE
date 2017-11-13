/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 *
 * @author renan
 */
public class Solution extends Agent {

    private Chromossome chromossome;
    private Integer  age = 0;
    private Integer fitnessValue;
    
    public Solution(Chromossome chromossome) {
        super();
        this.chromossome = chromossome;
    }
    
    @Override
    protected void setup() {
        addBehaviour(new Born(this));
    }
    
    public Chromossome getChromossome(){
        return this.chromossome;
    }
    
    public void getOlder(){
        age++;
    }
    
    public void setFitness(int fitnessValue){
        this.fitnessValue = fitnessValue;
    }
    
    public DFAgentDescription description(){
        DFAgentDescription agent_description = new DFAgentDescription();
        agent_description.setName(getAID());
        agent_description.addServices(reproduction_service());
        return agent_description;
    }
    
    private ServiceDescription reproduction_service(){
        ServiceDescription reproduction_description = new ServiceDescription();
        reproduction_description.setName("Reproduce");
        reproduction_description.setOwnership("Solution");
        reproduction_description.addProperties(fitness_property());
        return reproduction_description;
    }
    
    private Property fitness_property() {
        Property fitness_property = new Property();
        fitness_property.setName("FITNESS");
        fitness_property.setValue(this.fitnessValue);
        return fitness_property;
    }
    
    private Property chromossome_property() {
        Property fitness_property = new Property();
        fitness_property.setName("SELF");
        fitness_property.setValue(this);
        return fitness_property;
    }
    
    public DFAgentDescription partner_description() {
        DFAgentDescription agent_description = new DFAgentDescription();
        agent_description.addServices(reproduction_service_without_fitness());
        return agent_description;
    }
    
    private ServiceDescription reproduction_service_without_fitness() {
        ServiceDescription reproduction_description = new ServiceDescription();
        reproduction_description.setName("Reproduce");
        reproduction_description.setOwnership("Solution");
        return reproduction_description;
    }
}
