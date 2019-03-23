package view;

import controller.*;
import adapter.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class View
{
    private Adapter adapter;
    private JFrame TicTacToeFrame;
    private JTextArea textArea;
    private JButton[][] grid;
    private JButton reset;
    private JTextArea playerTurn;

    // Constructor initializing TicTacToeFrame
    public View()
    {
        this.textArea = new JTextArea("Think outside the box! Not below one you Fools.");
        this.TicTacToeFrame = new JFrame("Start the Game Losers!");
        TicTacToeFrame.setForeground(Color.GRAY);
        this.grid = new JButton[3][3];
        this.reset = new JButton("Loosing the game? Reset Board! ;)");
        this.playerTurn = new JTextArea();

        // Setting up the layout and initialize buttons
        initialize();
        reset.setBackground(new Color(139,0,139));
        reset.setBackground(Color.GRAY);
        reset.setFocusPainted(false);
        reset.setFont(new Font("Tahoma", Font.BOLD, 12));

        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3; j++)
            {
                grid[i][j].setForeground(new Color(139,0,139));
                grid[i][j].setBackground(Color.DARK_GRAY);
            }
        }
    }

    // function to add action listeners to buttons
    public void setActionListener(Controller c) {
        // adapter needs reference of controller and view class
        this.adapter = new Adapter(c,this);
        for(int row = 0; row<3 ;row++) {
            for(int column = 0; column<3 ;column++) {
                grid[row][column].addActionListener(adapter);
            }
        }
        reset.addActionListener(adapter);
    }

    // Initializing layout and buttons
    public void initialize ()
    {
        TicTacToeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TicTacToeFrame.setSize(new Dimension(450, 300));
        TicTacToeFrame.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(3,3));
        gamePanel.add(textArea, BorderLayout.NORTH);
        gamePanel.add(game, BorderLayout.CENTER);
        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.lightGray);

        TicTacToeFrame.add(textArea, BorderLayout.NORTH);
        TicTacToeFrame.add(gamePanel, BorderLayout.NORTH);
        TicTacToeFrame.add(options, BorderLayout.CENTER);
        TicTacToeFrame.add(messages, BorderLayout.SOUTH);
        messages.add(playerTurn);
        playerTurn.setText("Player 1's turn, sign 'X'");

        for(int row = 0; row<3 ;row++)
        {
            for(int column = 0; column<3 ;column++)
            {
                grid[row][column] = new JButton();
                grid[row][column].setPreferredSize(new Dimension(60,60));
                grid[row][column].setText("");
                game.add(grid[row][column]);
            }
        }

        // Making the TicTacToeFrame visible
        TicTacToeFrame.setVisible(true);

    }

    // Checking if the action event was generated because of reset key
    public boolean isReset(ActionEvent event)
    {
        if(event.getSource() == reset)
        {
            return true;
        }
        return false;
    }

    // Finding x,y-coordinates of pressed button
    public ArrayList<Integer> getPosition(ActionEvent event)
    {
        ArrayList<Integer> position = new ArrayList<Integer>();

        for(int row = 0; row<3 ;row++)
        {
            for(int column = 0; column < 3 ;column++)
            {
                if(event.getSource() == grid[row][column])
                {
                    position.add(row);
                    position.add(column);
                }
            }
        }
        return position;
    }

    // Updating view with correct mark and message
    public void update(int row, int column, char symbol, String message)
    {
        grid[row][column].setText(Character.toString(symbol));
        grid[row][column].setEnabled(false);
        playerTurn.setText(message);
    }

    // Freezing view if game ends
    public void isWinner(int row, int column, char symbol, String message)
    {
        grid[row][column].setText(Character.toString(symbol));
        grid[row][column].setEnabled(false);

        for(int i = 0; i<3 ;i++)
        {
            for(int j = 0; j<3 ;j++)
            {
                grid[i][j].setEnabled(false);
            }
        }
        playerTurn.setText(message);

    }

    // Game reset
    public void resetGame()
    {
        for(int row = 0;row<3;row++)
        {
            for(int column = 0;column<3;column++)
            {
                grid[row][column].setText("");
                grid[row][column].setEnabled(true);
            }
        }
        playerTurn.setText("Player 2's turn, sign 'X'");
    }

    // Checking value of a button on the grid
    public String getButtonText(int i, int j)
    {
        return grid[i][j].getText();
    }

}

