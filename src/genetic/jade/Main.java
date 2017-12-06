package genetic.jade;

import jade.core.Profile;

public class Main {

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        GeneticAlgorithm ga = new GeneticAlgorithm("127.0.0.1", Profile.LOCAL_PORT, "UFABC");
               
        //Add JADE agents:
        ga.addAgent("RMA", jade.tools.rma.rma.class, null);
//        ga.addAgent("Sniffer", jade.tools.sniffer.Sniffer.class, null);

        //Add Reporter
        ga.AddReporter("Reporter", new EightQueensStopCondition());

        for(int i=0; i<10; i++)
            ga.addEvaluator("Valorador"+i);
        Thread.sleep(1000);
        for(int i=0; i<100; i++)
            ga.addSolution("EvaOuAdao"+i, new EightQueensChromossome());
        
        //Mutator
        //ga.addAgent("Mutator", Mutator.class.getName(), null);
        
        
    }
}