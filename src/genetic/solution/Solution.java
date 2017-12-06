/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;


import genetic.solution.behaviours.Born;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

/**
 *
 * @author renan
 */
public class Solution extends Agent {

    private Chromossome chromossome;
    private Integer  age = 0;
    private double fitnessValue;
    
    @Override
    protected void setup() {
        loadChromossome();
        says("Inicializando Solution: " + this.getName());
        requestFitness();
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
    
    public void setFitness(double fitnessValue){
        this.fitnessValue = fitnessValue;
    }

    public double getFitness(){
        return this.fitnessValue;
    }    
    
    public int getAge() {
        return this.age;
    }
       
    public DFAgentDescription description(){
        return DFSolutionDescription.for_register(getAID(), fitnessValue);
    }
    
    public void says(String text){
//        System.out.println(getName() + ": " + text);
    }

    private void requestFitness() {
        FitnessRequester fitnessRequester = new FitnessRequester(this);
        fitnessRequester.request();
    }
}
