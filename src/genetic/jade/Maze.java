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
        this.pelego = new Point(2, 2);
        this.finish = new Point(13, 1);
        initialize_layout();
    }
    
    private void initialize_layout(){
        layout = new boolean[][]
        {
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,true,true,true,true,true,true,false,true,true,false,true,true,true,false},
            {false,true,false,true,false,false,true,false,true,false,true,false,true,true,false},
            {false,true,false,true,true,false,true,false,true,false,true,true,true,true,false},
            {false,true,false,true,true,false,true,false,true,false,true,false,false,false,false},
            {false,true,false,true,false,false,true,true,true,false,true,false,true,true,false},
            {false,true,false,true,false,true,false,false,true,false,true,false,false,true,false},
            {false,true,false,false,false,true,true,true,true,true,true,false,false,true,false},
            {false,true,true,false,true,true,false,false,false,false,true,true,false,true,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
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
    
    public boolean goTo(int direction){
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
        return false;
    }
    
    public double distanceToFinish(){
        return pelego.distance(finish);
    }
    
    public boolean north(){
        this.pelego.move(pelego.x, pelego.y + 1);
        return layout[pelego.x][pelego.y];
    }
    
    public boolean south() {
        this.pelego.move(pelego.x, pelego.y - 1);
        return layout[pelego.x][pelego.y];
    }
    
    public boolean east() {
        this.pelego.move(pelego.x + 1, pelego.y);
        return layout[pelego.x][pelego.y];
    }
    
    public boolean west() {
        this.pelego.move(pelego.x - 1, pelego.y);
        return layout[pelego.x][pelego.y];
    }
}
