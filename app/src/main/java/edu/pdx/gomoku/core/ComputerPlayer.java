package edu.pdx.gomoku.core;

import java.util.Random;

/**
 * Created by yuriy on 1/21/15.
 */
public class ComputerPlayer extends Player {


    public ComputerPlayer(StoneColor color) {
        super(PlayerType.Computer, color);
    }

    @Override
    public void startMove(Game game) {

        game.setState(GameState.WaitingForPlayerMove);

        Random random = new Random();

        //this is placeholder code
        while (true)
        {
            try
            {
                int row = random.nextInt() % game.getBoard().getRowCount();
                int col = random.nextInt() % game.getBoard().getColumnCount();

                game.acceptMove(this.getColor(), Math.abs(row), Math.abs(col));

                break;
            }
            catch(IllegalMoveException mex)
            {
                //do nothing here
            } catch (MoveNotAllowedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void acceptMove(int row, int column) {
        //do nothing ()
    }

    private void calcuateMove(Game game)
    {
        GameBoard board = game.getBoard();

        int[][] masterBoard = new int[board.getRowCount()][board.getColumnCount()];


        //TODO: Yuriy - add things up and grab the best payoff
    }

    private int[][] addMatrix(int[][] target, int[][] source)
    {
        return null;
    }

    private int[][] scalarMultiplyMatrix(int[][] target, int[][] source)
    {
        return null;
    }

    private static final float OPPOSING_PLAYER_PAYOFF_RATIO = 0.75F;

    //imminent win/loss payoffs
    private static final int INSTANT_WIN_PAYOFF = 2^16;

    //base adjacency payoffs
    private static final int ANDJACENCY_1 = 2^5; //right next to the cell
    private static final int ANDJACENCY_2 = 2^4; //one cell over
    private static final int ANDJACENCY_3 = 2^3; //and so on
    private static final int ANDJACENCY_4 = 2^2;


    //base series payoffs
    private static final int SERIES_1 = 2^9;
    private static final int SERIES_2 = 2^8;
    private static final int SERIES_3 = 2^7;
    private static final int SERIES_4 = 2^6;

    /**
     *
     * @param board
     * @param color
     * @return Array of rows/columns containing payoffs for each cell
     */
    private int[][] getPayoff_InstantWin(GameBoard board, StoneColor color)
    {
        int[][] payoffMatrix = new int[board.getRowCount()][board.getColumnCount()];

        //TODO: Do the magic here

        return payoffMatrix;

    }

    /**
     *
     * @param board
     * @param color
     * @return Array of rows/columns containing payoffs for each cell
     */
    private int[][] getPayoff_Adjascency(GameBoard board, StoneColor color)
    {
        int[][] payoffMatrix = new int[board.getRowCount()][board.getColumnCount()];

        //TODO: Do the magic here

        return payoffMatrix;
    }

    /**
     *
     * @param board
     * @param color
     * @return Array of rows/columns containing payoffs for each cell
     */
    private int[][] getPayoff_Series(GameBoard board, StoneColor color)
    {
        int[][] payoffMatrix = new int[board.getRowCount()][board.getColumnCount()];

        //TODO: Do the magic here

        return payoffMatrix;
    }

    /**
     *
     * @param board
     * @param color
     * @return Array of rows/columns containing payoffs for each cell
     */
    private int[][] getPayoff_(GameBoard board, StoneColor color)
    {
        int[][] payoffMatrix = new int[board.getRowCount()][board.getColumnCount()];

        //TODO: Do the magic here

        return payoffMatrix;
    }
}
