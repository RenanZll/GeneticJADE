/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic.jade;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author renan
 */
public class Maze implements Serializable {
    
    private boolean[][] layout;
    private Point pelego;
    private Point finish;
    
    public Maze(){
        this.pelego = new Point(4, 1);
        this.finish = new Point(4, 3);
        initialize_layout();
    }
    
    private void initialize_layout(){
        layout = new boolean[][]
        {
            {false,false,false,false,false},
            {false,true,true,true,false},
            {false,true,false,true,false},
            {false,true,false,true,false},
            {false,true,false,true,false},
            {false,false,false,false,false}
        };
    }
    
    public int maximumPathSize(){
        return width()*height();
    }
    
    private int width(){
        return layout[0].length;
    }
    
    private int height(){
        return layout.length;
    }
    
    private Point goTo(int direction){
        switch(direction){
            case 0:
                return north();
            case 1:
                return east();
            case 2:
                return south();
            case 3:
                return west();
        }
        return null;
    }
    
    public double distanceToFinish(){
        return pelego.distance(finish);
    }
    
    public Point north(){
        return new Point(pelego.x - 1, pelego.y);
    }
    
    public Point south() {
        return new Point(pelego.x + 1, pelego.y);

    }
    
    public Point east() {
        return new Point(pelego.x, pelego.y + 1);
    }
    
    public Point west() {
        return new Point(pelego.x, pelego.y - 1);
    }
    
    public boolean isValid(int direction){
        Point newPosition = goTo(direction);
        boolean notCrashed = layout[newPosition.x][newPosition.y];
        if(notCrashed){
            pelego.setLocation(newPosition);
            return true;
        }
        return false;
    }
}
