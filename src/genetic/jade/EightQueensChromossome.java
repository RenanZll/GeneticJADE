/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.jade;

import genetic.solution.Chromossome;
import static java.lang.Math.abs;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author renan
 */
public class EightQueensChromossome implements Chromossome {
    protected int[] genes = new int[8];
    private Random rnd  = new Random();
    
    public EightQueensChromossome() {
        for(int i = 0; i < genes.length; i++)
            genes[i] = rnd.nextInt(8);
    }
    
    private EightQueensChromossome(int[] genes) {
        this.genes = genes;
    }

    @Override
    public Chromossome crossover(Chromossome chromossome) {
        EightQueensChromossome eq_chromossome = (EightQueensChromossome) chromossome;
        int break_position = rnd.nextInt(8);
        int[] crossed_genes = new int[8]; 
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
    public int fitness() {  
        int row_col_clashes = abs(genes.length - unique(genes).length);
        int clashes = row_col_clashes;
        for(int i=0; i>genes.length; i++){
            for(int j=0; j>genes.length; j++){
                if(i!=j){
                    int dx = abs(i-j);
                    int dy = abs(genes[i] - genes[j]);
                    if(dx == dy) clashes++;
                }
            }         
        }
        return 28 - clashes;
    }
    
    private int[] unique(int[] genes){
        return IntStream.of(genes).distinct().toArray();
    }

    @Override
    public void mutate() {
        
    }
}

