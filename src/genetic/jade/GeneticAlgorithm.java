/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.jade;

import genetic.solution.Chromossome;
import genetic.solution.Solution;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 *
 * @author renan
 */
public class GeneticAlgorithm {

    private ContainerController containerController;
    
    public GeneticAlgorithm(String host, String port, String name) {
        startMainContainer(host, port, name);
    }
    
    public GeneticAlgorithm(ContainerController containerController) {
        this.containerController = containerController;
    }
    
    
    public void startMainContainer(String host, String port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.PLATFORM_ID, name);
        
        containerController = runtime.createMainContainer(profile);
    }

    public void addAgent(String agent, Class classe, Object[] args) {
        try {
            containerController
                    .createNewAgent(agent, classe.getName(), args)
                    .start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }

    public void addSolution(String agent, Chromossome chromossome) {
        try {
            Object[] solution_parameters = { chromossome };
            containerController
                    .createNewAgent(agent, Solution.class.getName(), solution_parameters)
                    .start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }
    
}
