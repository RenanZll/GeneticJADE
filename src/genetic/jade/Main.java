package genetic.jade;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import genetic.solution.Solution;
import genetic.mutator.Mutator;
import genetic.evaluator.Evaluator;

public class Main {

    static ContainerController containerController;
    static AgentController agentController;

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "UFABC");
        
        //Add agents:
        //Solution
        addAgent(containerController, "Solution", Solution.class.getName(), null);
        //Mutator
        addAgent(containerController, "Mutator", Mutator.class.getName(), null);
        //Evaluator
        addAgent(containerController, "Evaluator", Evaluator.class.getName(), null);
    }
    
    public static void startMainContainer(String host, String port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.PLATFORM_ID, name);
        
        containerController = runtime.createMainContainer(profile);
    }

    public static void addAgent(ContainerController cc, String agent, String classe, Object[] args) {
        try {
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }
}