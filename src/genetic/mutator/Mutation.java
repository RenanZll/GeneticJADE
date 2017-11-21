/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.mutator;

import genetic.solution.Chromossome;

/**
 *
 * @author renan
 */
public interface Mutation {
    public Chromossome mutate(Chromossome chromossome);
}
