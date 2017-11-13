/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import genetic.jade.GeneticAlgorithm;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.Random;

/**
 *
 * @author altargin
 */
public class Reproduce extends OneShotBehaviour {

    Solution partner;
    Reproduce(Agent my_agent,Agent partner) {
        super(my_agent);
        this.partner = (Solution) partner;
    }

    @Override
    public void action() {
        Chromossome child_chromossome = reproduce();
        register_new_solution(generateRandomName(), child_chromossome);
        mySolution().getOlder();
    }
    
    private Chromossome reproduce(){
        return myChromossome().crossover(partner.getChromossome());
    }
    
    private void register_new_solution(String name, Chromossome chromossome){
        GeneticAlgorithm ga = new GeneticAlgorithm(mySolution().getContainerController());
        Object[] solution_parameters = {chromossome};
        ga.addSolution(name, chromossome);
    }
    
    private Solution mySolution() {
        return (Solution) myAgent;
    }
    
    private Chromossome myChromossome(){
        return mySolution().getChromossome();
    }
    
    private String generateRandomName(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
