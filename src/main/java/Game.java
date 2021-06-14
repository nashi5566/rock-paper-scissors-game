import java.util.Arrays;
import java.util.Scanner;

class Game {

    public Game(){
        this.who = 0;
        this.player1_act = null;
        this.player2_act = null;
    }

    public void play(){
        System.out.println("=== Welcome to Rock-Paper-Scissors game");
        System.out.println("Enter Player 1 choice (rock, paper or scissors):");
        this.player1_act = getAction();
        System.out.println("Enter Player 2 choice (rock, paper or scissors):");
        this.player2_act = getAction();
        this.who = whoWin(player1_act, player2_act);
    }

    private int whoWin(String player1_act, String player2_act){
        if(player1_act.equals("rock") && player2_act.equals("paper")){  // rock paper
            return 2;
        }
        else if(player1_act.equals("rock") && player2_act.equals("scissors")){ // rock scissors
            return 1;
        }
        else if(player1_act.equals("paper") && player2_act.equals("rock")){ // paper rock
            return 1;
        }
        else if(player1_act.equals("paper") && player2_act.equals("scissors")){ // paper scissor
            return 2;
        }
        else if(player1_act.equals("scissors") && player2_act.equals("rock")){ // scissor rock
            return 2;
        }
        else if(player1_act.equals("scissors") && player2_act.equals("paper")){ // scissor paper
            return 1;
        }

        return -1; // same
    }

    private String getAction() throws IllegalArgumentException {
        String player_act;
        Scanner input = new Scanner(System.in);
        player_act = input.nextLine();

        if(player_act == null){
            throw new NullPointerException("No input!\n");
        }

        if (!Arrays.asList(validActs).contains(player_act)) {
            throw new IllegalArgumentException("Invalid input throw IllegalArgumentException.");
        }
        return player_act;
    }

    public int who;
    private String player1_act;
    private String player2_act;
    private final String[] validActs = {"rock", "paper", "scissors"};
}
