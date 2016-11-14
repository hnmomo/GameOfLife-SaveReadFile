/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife;

import java.io.*;
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
        FileReader input;
        FileWriter out;
        String s;
        BufferedReader read;
        BufferedWriter write;
        File file=new File("file.txt");
        boolean stop=false;
        System.out.print("load a state(l) or number of live cells:");
        if((s=in.next()).equals("l")){
            try{
                input=new FileReader(file);
                read=new BufferedReader(input);
                while((s=read.readLine())!=null){
                    String[]sp=s.split(" ");
                    x=Integer.parseInt(sp[0]);
                    y=Integer.parseInt(sp[1]);
                    cell[x][y]=true;
                }
                read.close();
                input.close();
            }catch(IOException e){
                System.out.println("error");
                System.exit(0);
            }
        }else{
            n=Integer.parseInt(s);
            //read input for first day creatures
            for(int i=0;i<n;i++){
                System.out.print("x coordinate:");
                x=in.nextInt()-1;
                System.out.print("y coordinate:");
                y=in.nextInt()-1;
                cell[x][y]=true;
            }
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
            System.out.println("save this state?(y/n)");
            if(in.next().equals("y")){
                try{
                    file.createNewFile();
                    out=new FileWriter(file);
                    write=new BufferedWriter(out);
                    for(int i=0;i<20;i++){
                        for(int j=0;j<20;j++){
                            if(previous[i][j]){
                                write.write(String.valueOf(i));
                                write.write(" ");
                                write.write(String.valueOf(j));
                                write.newLine();
                            }
                        }
                    }
                    out.close();
                    write.close();
                }catch(IOException e){
                    System.out.println("error"+e.getMessage());
                    System.exit(0);
                }
            }
        }
    }
    
}
