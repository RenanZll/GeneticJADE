/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.jade;

import genetic.mutator.Mutation;
import genetic.solution.Chromossome;
import java.util.Random;

/**
 *
 * @author renan
 */
public class EightQueensChromossome implements Chromossome {
    protected int[] genes = new int[8];
    private Random rnd  = new Random();
    
    public EightQueensChromossome() {
        for(int i = 0; i < genes.length; i++)
            genes[i] = rnd.nextInt(7);
    }
    
    private EightQueensChromossome(int[] genes) {
        this.genes = genes;
    }

    @Override
    public Chromossome crossover(Chromossome chromossome) {
        EightQueensChromossome eq_chromossome = (EightQueensChromossome) chromossome;
        int break_position = rnd.nextInt(7);
        int[] crossed_genes = new int[7]; 
        for(int i = 0; i < crossed_genes.length; i++){
            if(i <= break_position)
                crossed_genes[i] = this.genes[i];
            else
                crossed_genes[i] = eq_chromossome.genes[i];
        }
        print(crossed_genes);
        return new EightQueensChromossome(crossed_genes);
    }
    
    private void print(int[] genes){
        String to_s = " [";
        for(int i = 0; i < genes.length; i++)
            to_s += genes[i] + " ";
        System.out.println(to_s+"]");
    }

    @Override
    public void mutate(Mutation mutator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

