package genetic.jade;

import jade.core.Profile;

public class Main {

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        GeneticAlgorithm ga = new GeneticAlgorithm("127.0.0.1", Profile.LOCAL_PORT, "UFABC");
        
        //Add agents:
        //Solution
        ga.addSolution("PrimeSolution", new EightQueensChromossome());
        //Mutator
        //ga.addAgent("Mutator", Mutator.class.getName(), null);
        //Evaluator
        //ga.addAgent("Evaluator", Evaluator.class.getName(), null);
    }
}