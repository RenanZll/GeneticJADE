/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.solution;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

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
        Chromossome child_chromossome = 
                myChromossome().crossover(partner.getChromossome());
        new Solution(child_chromossome);
        mySolution().getOlder();
    }
    
    private Solution mySolution() {
        return (Solution) myAgent;
    }
    
    private Chromossome myChromossome(){
        return mySolution().getChromossome();
    }
}
