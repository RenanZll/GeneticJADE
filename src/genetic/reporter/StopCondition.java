/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.reporter;

/**
 *
 * @author renan
 */
public interface StopCondition {
    public boolean check(EnvironmentState environmentState);
}
