/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.jade;

import genetic.solution.Chromossome;
import java.util.Random;

/**
 *
 * @author renan
 */
public class MazeChromossome implements Chromossome{
    
    protected int[] path;
    private Maze maze;
    private Random rnd;
    
    public MazeChromossome(){
        this.maze = new Maze();
        rnd = new Random();
        this.path = generatePath();
    }
    
    private MazeChromossome(int[] path){
        this.maze = new Maze();
        rnd = new Random();  
        this.path = path;
    }

    private int[] generatePath(){
        int maximumPathSize = this.maze.maximumPathSize();
        int[] directions = new int[maximumPathSize];
        for(int i=0; i<directions.length; i++){
            
            directions[i] = rnd.nextInt(4);
        }
        return directions;
    }
    
    @Override
    public Chromossome crossover(Chromossome chromossome) {
        MazeChromossome eq_chromossome = (MazeChromossome) chromossome;
        int break_position = rnd.nextInt(maze.maximumPathSize());
        int[] crossed_genes = new int[maze.maximumPathSize()];
        for (int i = 0; i < crossed_genes.length; i++) {
            if (i <= break_position) {
                crossed_genes[i] = this.path[i];
            } else {
                crossed_genes[i] = eq_chromossome.path[i];
            }
        }
        return new MazeChromossome(crossed_genes);
    }

    @Override
    public double fitness() {
        int pace = 0;
        boolean crashed;
        do{
            pace++;
            crashed = maze.goTo(path[pace]); 
        }while(!crashed);
        return maze.distanceToFinish();
    }

    @Override
    public void mutate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
