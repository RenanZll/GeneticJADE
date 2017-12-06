package genetic.jade;

import genetic.jade.scenarios.eight_queens.EightQueensStopCondition;
import genetic.jade.scenarios.eight_queens.EightQueensChromossome;
import genetic.jade.scenarios.maze.MazeChromossome;
import genetic.jade.scenarios.maze.MazeStopCondition;
import jade.core.Profile;

public class Main {

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        GeneticAlgorithm ga = new GeneticAlgorithm("127.0.0.1", Profile.LOCAL_PORT, "UFABC");
               
        //Add JADE agents:
        ga.addAgent("RMA", jade.tools.rma.rma.class, null);
//        ga.addAgent("Sniffer", jade.tools.sniffer.Sniffer.class, null);

        int population_size = 100;

        //Add Reporter
        ga.AddReporter("Reporter", new EightQueensStopCondition(), population_size);

        for(int i=0; i<population_size/10; i++)
            ga.addEvaluator("Valorador"+i);
        Thread.sleep(1000);
        for(int i=0; i<population_size; i++)
            ga.addSolution("EvaOuAdao"+i, new EightQueensChromossome());

    }
}