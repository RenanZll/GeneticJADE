/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

/**
 *
 * @author renan
 */
public class Solution extends Agent {

    private Chromossome chromossome;
    private Integer  age = 0;
    private Integer fitnessValue;
    
    @Override
    protected void setup() {
        loadChromossome();
        System.out.println("Inicializando Solution: " + this.getName());
        addBehaviour(new Born(this));
    }
    
    private void loadChromossome(){
        this.chromossome = (Chromossome) this.getArguments()[0];
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
    
    
    public int getAge() {
        return this.age;
    }
       
    public DFAgentDescription description(){
        return new SolutionDescription(getAID(), fitnessValue);
    }
    
    public void says(String text){
        System.out.println(getName() + ": " + text);
    }
}
