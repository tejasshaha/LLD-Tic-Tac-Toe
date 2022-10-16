package com.service;

import com.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private boolean turn= false;
    private static int idGenerator=1;
    List<Person> playerList = new ArrayList<>();

    private String[][] board = new String[3][3] ;

    int counter=0;
    public void createPlayer(String name,int phoneNo,String mail){
        Person player = new Person();
        player.setId(idGenerator++);
        player.setName(name);
        player.setPhoneNo(phoneNo);
        player.setMail(mail);
        playerList.add(player);
    }

    public void displayPlayers(){
        for(int counter =0;counter<playerList.size();counter++){
            Person p = playerList.get(counter);
            System.out.println("Player id is >>" + p.getId());
            System.out.println("Player name is >>" + p.getName());
            System.out.println("Player phone no is >>" + p.getPhoneNo());
            System.out.println("Player mail id is >>" + p.getMail());
            System.out.println("___________________");
        }
    }

    public void initializeBoard(){
        //int boardValue=1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("- ");
                board[i][j]="-";
          //      boardValue++;
            }
            System.out.println();
        }
    }

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        String winner = null;
        while(true){
            turn=!turn;
            int row=scanner.nextInt();
            int col=scanner.nextInt();
            if(isValid(row,col)){
                if(turn){
                    board[row-1][col-1]="X";
                }
                else{
                    board[row-1][col-1]="O";
                }
                counter++;
                printBoard();
                winner=checkWinner(turn,row-1,col-1);
                if(counter==9 || winner!=null){
                    break;
                }
            }
            else{
                turn=!turn;
                System.out.println("Wrong index selected to place piece");
                System.out.println("Its either out of board or already another piece is there");
                continue;
            }
        }

        if(counter==9 && winner==null){
            System.out.println("Its draw");
        }
        else if(winner.equalsIgnoreCase("X")){
            System.out.println("Tejas you won");
        }
        else{
            System.out.println("Deepak you won");
        }
    }

    private boolean isValid(int row,int col){
//        String r=String.valueOf(row);
//        String c=String.valueOf(col);

        if( (row-1>=0 && row-1<=2) && (col-1>=0 && col-1<=2 ) && (board[row-1][col-1].equalsIgnoreCase("-"))){
            return true;
        }
        return false;
    }

    private String checkWinner(boolean turn,int row,int col){
        String winner = null;
        String piece= "X";
        String line="XXX";
        if(!turn){
            piece="O";
            line="OOO";
        }

        for(int i=0;i<8;i++){
            String state=null;
            switch (i){
                case 0:
                    state=board[0][0]+board[0][1]+board[0][2];
                    break;

                case 1:
                    state=board[0][0]+board[1][0]+board[2][0];
                    break;

                case 2:
                    state=board[2][0]+board[2][1]+board[2][2];
                    break;

                case 3:
                    state=board[0][2]+board[1][2]+board[2][2];
                    break;

                case 4:
                    state=board[0][1]+board[1][1]+board[2][1];
                    break;

                case 5:
                    state=board[1][0]+board[1][1]+board[1][2];
                    break;

                case 6:
                    state=board[0][0]+board[1][1]+board[2][2];
                    break;

                case 7:
                    state=board[0][2]+board[1][1]+board[2][0];
                    break;
            }

            //System.out.println(state);
            if(state.equalsIgnoreCase(line)){
                winner=piece;
                break;
            }
        }
        return winner;
    }

    private void printBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
