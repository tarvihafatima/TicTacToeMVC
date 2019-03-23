package model;

import view.*;

public class Model
{
    private View view;
    private char[][] board;
    private String message;
    private int playerId;
    private int count_of_Moves;

    //Constructor
    public Model()
    {
        this.count_of_Moves = 9;
        this.playerId = 1;
        this.board = new char[3][3];
    }

    // initializing view class reference
    public void registerView(View v)
    {
        this.view = v;
    }

    // defining all setters and getters to be used
    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public int getMovesCount()
    {
        return count_of_Moves;
    }

    public void setMovesCount(int movesCount)
    {
        this.count_of_Moves = movesCount;
    }

    public char[][] getBoard()
    {
        return board;
    }

    public void setBoard(char[][] board)
    {
        this.board = board;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    // Updating board model
    public void PlayMove(int x, int y)
    {
        if(getMovesCount() > 0)
        {
            // Marking board
            if(playerId%2 != 0)
                board[x][y] = 'X';
            else
                board[x][y] = 'O';

            // Reducing count of moves left
            setMovesCount(--count_of_Moves);

            // Win, lose, tie checks
            if(ifWinning(x, y))
            {
                setMessage("Player " + playerId + " Wins the Game! Don't be so happy you are still a loser!");
                view.isWinner(x, y, board[x][y], getMessage());
            }
            else if(getMovesCount()==0)
            {
                setMessage("You both are losers!!");
                view.isWinner(x, y, board[x][y], getMessage());
            }
            else
                {
                if(playerId%2 != 0)
                {
                    // Toggling playerId
                    setPlayerId(2);
                    setMessage("'O':  Player " +getPlayerId());
                }
                else
                    {
                    setPlayerId(1);
                    setMessage("'X':  Player " +getPlayerId());

                }
                // update the board with message for next player
                view.update(x, y, board[x][y], getMessage());
            }

        }

    }

    // Checking if someone wins
    public boolean ifWinning(int x, int y)
    {
        int rowCount = 0;
        int colCount = 0;
        int countLDiag = 0;
        int countRDiag = 0;
        char symbol;
        if(getPlayerId() %2 !=0)
        {
            symbol = 'X';
        }
        else
            {
            symbol = 'O';
        }

        for(int i=0; i<board.length;i++)
        {
            if(board[x][i] == symbol)
            {
                rowCount++;
            }
            if(board[i][y] == symbol)
            {
                colCount++;
            }
            if(board[i][i] == symbol)
            {
                countRDiag++;
            }
            if(board[board.length-1-i][i] == symbol)
            {
                countLDiag++;
            }
        }

        if(colCount==board.length || rowCount==board.length
                || countLDiag == board.length || countRDiag == board.length)
        {
            return true;
        }
        return false;
    }

    // Model reset
    public void ResetModel()
    {
        count_of_Moves = 9;
        setPlayerId(1);
        setMessage("");
        for(int i=0; i < board.length;i++)
        {
            for(int j=0; j < board.length;j++)
            {
                board[i][j] = '\0';
            }
        }
        view.resetGame();
    }

}

