/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife;

import java.util.Scanner;

/**
 *
 * @author jihua5758
 */
public class GameOfLife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //arrays for cells and cells in previous day
        //true means the crature is alive
        boolean[][]cell=new boolean[20][20],previous=new boolean[20][20];
        Scanner in=new Scanner(System.in);
        //n is the number of creatures on first day
        //x and y are its coordinates
        int n,x,y;
        boolean stop=false;
        System.out.print("number of live cells:");
        n=in.nextInt();
        //read input for first day creatures
        for(int i=0;i<n;i++){
            System.out.print("x coordinate:");
            x=in.nextInt()-1;
            System.out.print("y coordinate:");
            y=in.nextInt()-1;
            cell[x][y]=true;
        }
        while(!stop){
            //print the cell
            for(int j=0;j<20;j++){
                for(int i=0;i<20;i++){
                    if(cell[i][j]){
                        System.out.print("O");
                    }else{
                        System.out.print("X");
                    }
                }
                System.out.println();
            }
            //stop the loop when all cells are dead
            stop=true;
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    if(cell[i][j]){
                        stop=false;
                    }
                }
            }
            //save the cell of previous day
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    previous[i][j]=cell[i][j];
                }
            }
            //go through each cell
            for(int j=0;j<20;j++){
                for(int i=0;i<20;i++){
                    //add neighbour if the neighbor cell is alive
                    n=0;
                    //do not count if the neibour cell does not exist
                    try{
                        if(previous[i-1][j]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i+1][j]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i][j-1]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i][j+1]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i-1][j-1]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i-1][j+1]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i+1][j-1]){
                            n++;
                        }
                    }catch(Exception e){}
                    try{
                        if(previous[i+1][j+1]){
                            n++;
                        }
                    }catch(Exception e){}
                    //game rules
                    if(previous[i][j]&&!(n==3||n==2)){
                        cell[i][j]=false;
                    }else if(!previous[i][j]&&n==3){
                        cell[i][j]=true;
                    }
                }
            }
            //let the user read the cell
            System.out.println("input anything to continue");
            String a=in.next();
        }
    }
    
}
