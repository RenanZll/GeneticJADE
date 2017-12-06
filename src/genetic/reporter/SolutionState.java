/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

import genetic.solution.Chromossome;
import genetic.solution.ChromossomeStatus;

/**
 *
 * @author altargin
 */
class SolutionState {
    
    private final Chromossome chromossome;
    private final ChromossomeStatus status;
    
    public SolutionState(Chromossome chromossome, ChromossomeStatus status){
        this.chromossome = chromossome;
        this.status = status;
    }

    public Chromossome getChromossome() {
        return chromossome;
    }

    public ChromossomeStatus getStatus() {
        return status;
    }
}
