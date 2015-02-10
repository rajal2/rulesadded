package edu.pdx.gomoku.core;

import android.util.Log;

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

        //Random random = new Random();

        //this is placeholder code
        while (true)
        {
            try
            {
                //int row = random.nextInt() % game.getBoard().getRowCount();
                //int col = random.nextInt() % game.getBoard().getColumnCount();
                GameBoard board = game.getBoard();
                int row = board.getRowCount()/2;
                int column = board.getColumnCount()/2;
                if(board.isEmpty()) {
                    game.acceptMove(this.getColor(), row, column);
                }
                else {
                    int[] bestCell = bestBet(board, this.getColor());
                    game.acceptMove(this.getColor(), bestCell[0], bestCell[1]);
                }

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

    private void calculateMove(Game game)
    {
        GameBoard board = game.getBoard();

        int[][] masterBoard = new int[board.getRowCount()][board.getColumnCount()];


        //TODO: Yuriy - add things up and grab the best payoff
    }

    private int[] bestBet(GameBoard board, StoneColor color) {
        int[] bestCell = {-1, -1};
        int[][] computerScoreMatrix, opponentScoreMatrix;

        StoneColor opponentColor = color == StoneColor.Black ? StoneColor.White:StoneColor.Black;

        //calculate score matrix for the computer player
        computerScoreMatrix = getPayoff_Series(board, color);
        //calculate score matrix for the opponent human
        opponentScoreMatrix = getPayoff_Series(board, opponentColor);

        int[] bestCellCompPlayer = getBestCell(computerScoreMatrix);
        int[] bestCellOpponent = getBestCell(opponentScoreMatrix);

        Log.d("bestBet", "computer vs human max score is: " + bestCellCompPlayer[2] + ", " + bestCellOpponent[2]);

        if(bestCellCompPlayer[2] >= bestCellOpponent[2]) {
            bestCell[0] = bestCellCompPlayer[0];
            bestCell[1] = bestCellCompPlayer[1];
        }
        else {
            bestCell[0] = bestCellOpponent[0];
            bestCell[1] = bestCellOpponent[1];
        }

        return bestCell;
    }

    private int[] getBestCell(int[][] decisionMatrix) {

        int[] bestCell = {-1, -1, -2^10};//{row, column, score}
        int noRows = decisionMatrix[0].length;
        int noCols = decisionMatrix.length;
        int max = -2^10;

        for(int  i= 0; i < noRows; i++) {
            for(int j = 0; j <noCols; j++) {
                if(decisionMatrix[i][j] >= max) {
                    max = decisionMatrix[i][j];
                    bestCell[0] = i;
                    bestCell[1] = j;
                    bestCell[2] = max;
                }
            }
        }
        return bestCell;
    }

    private float[][] addMatrix(float scalar,int[][] target, int[][] source)
    {
        int noRows = target[0].length;
        int noCols = target.length;
        float[][] result = new float[noRows][noCols];

        for(int i = 0; i < noRows; i++){
            for(int j = 0; j <noCols; j++) {
                result[i][j] = target[i][j] + 0.7f * (float)source[i][j];
            }
        }
        return result;
    }

    private int[][] scalarMultiplyMatrix(int[][] target, int[][] source)
    {
        return null;
    }

    private static final float OPPOSING_PLAYER_PAYOFF_RATIO = 0.75F;

    //imminent win/loss payoffs
    private static final int INSTANT_WIN_PAYOFF = 100;//2^10;

    //base adjacency payoffs
    private static final int ANDJACENCY_1 = 2^5; //right next to the cell
    private static final int ANDJACENCY_2 = 2^4; //one cell over
    private static final int ANDJACENCY_3 = 2^3; //and so on
    private static final int ANDJACENCY_4 = 2^2;


    //base series payoffs
    //unblocked 4
    private static final int SERIES_1 = 90;//2^9;
    //half blocked 4
    private static final int SERIES_2 = 80;// 2^8;
    //unblocked 3
    private static final int SERIES_3 = 70;//2^7;
    //half blocked 3
    private static final int SERIES_4 = 60;//2^6;
    private static final int SERIES_5 = 50;//2^5;
    private static final int SERIES_6 = 40;//2^4;
    private static final int SERIES_7 = 30;//2^3;
    private static final int SERIES_8 = 20;//2^2;
    private static final int SERIES_9 = 10;//2^1;

    /**
     *
     * @param board
     * @param color
     * @return Array of rows/columns containing payoffs for each cell
     */

    //I incorporated this step into the getPayoffSeries because the scanning procedure is the same in these two
    // and in most steps we won't get an immediate win so this full scan is wasted, plus, to add the immediate win
    // as the top of the series won't make that one slower and we won't miss the spot either.
    private int[][] getPayoff_InstantWin(GameBoard board, StoneColor color)
    {
        //the matrix to fill in
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
        //for every spot in matrix, call int getScore(row, column, board, color):
            //for each direction:
                // find start and end spot and get number (end - start + 1)
                // checkLiveState(), return blocked/dead/alive
                // if(blocked || number >= 6), do nothing
                // if(alive)
                    //if(number == 4) nAlive4++;
                    //if(number == 3) nAlive3++;
                    //etc.
                // if(dead), do similar thing as alive
            //after checking all 4 directions, check nAlive4 through nDead2, return score level
            //if( nAlive4 >= 1 || nDead4 >=2 || (nDead4 = 1&& nAlive3 >=1) ), return SERIES_1
        //Then assign the returned number to the corresponding spot in payoffMatrix.

        //for every spot in matrix, call int getScore(board, color):
        for(int i = 0; i < board.getRowCount(); i++){
            for(int j = 0; j < board.getColumnCount(); j++)
                payoffMatrix [i][j] = getScore(i, j, board, color);
        }
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

    /**
     *
     * @param row
     * @param column
     * @param board
     * @param color
     * @return Score for the position at (row, column)
     * This function has too much duplicated code, needs to be re-organized later!
     */
    private int getScore(int row, int column, GameBoard board, StoneColor color){
        //for each direction:
        // find start and end spot and get number (end - start + 1)
        // checkLiveState to get blocked/dead/alive
        // if(dead || number >= 6), do nothing
        // if(alive)
        //if(number == 4) nAlive4++;
        //if(number == 3) nAlive3++;
        //etc.
        // if(blocked), do similar thing as alive
        //after checking all 4 directions, check nAlive4 through nDead2, return score level
        //if( nAlive4 >= 1 || nBlocked4 >=2 || (nBlocked4 = 1&& nAlive3 >=1) ), return SERIES_1
        //and so on

        int nNonDead5 = 0, nAlive4 = 0, nBlocked4 = 0, nAlive3 = 0, nBlocked3 = 0, nAlive2 = 0, nBlocked2 = 0;
        int start, end, startC, endC, startR, endR;
        int number, nBlockedEnds;
        LiveState liveState;

        GameCellState[][] cells = board.getBoardState();
        GameCellState targetState = color == StoneColor.Black ? GameCellState.BlackStone : GameCellState.WhiteStone;

        //return negative score if this cell is not empty
        if(cells[row][column] != GameCellState.Empty)
            return -2^10;

        //check row:
        start = column;
        end = column;
        nBlockedEnds = 0;

        while (start > 0 && cells[row][start - 1] == targetState) {
            start--;
        }

        while (end < board.getColumnCount() - 1 && cells[row][end + 1] == targetState) {
            end++;
        }

        number = end -  start + 1;

        if(start == 0 || cells[row][start - 1] != GameCellState.Empty)
            nBlockedEnds++;
        if(end == board.getColumnCount() - 1 || cells[row][end+1] != GameCellState.Empty)
            nBlockedEnds++;

        liveState = checkLiveState(nBlockedEnds);

        if(number == 5 && (liveState != LiveState.Dead || (liveState == LiveState.Dead && (start == 0 || end == board.getColumnCount()-1))))
            //get one winning state
            nNonDead5++;
        if(number == 4) {
            if(liveState == LiveState.Alive)
                nAlive4++;
            if(liveState == LiveState.Blocked)
                nBlocked4++;
        }
        if(number == 3) {
            if(liveState == LiveState.Alive)
                nAlive3++;
            if(liveState == LiveState.Blocked)
                nBlocked3++;
        }
        if(number == 2) {
            if(liveState == LiveState.Alive)
                nAlive2++;
            if(liveState == LiveState.Blocked)
                nBlocked2++;
        }

        //checkColumn
        start = row; end = row;
        nBlockedEnds = 0;
        while(start>0 && cells[start - 1][column]== targetState)
        {
            start--;
        }

        while(end<board.getRowCount()-1 && cells[end + 1][column]== targetState)
        {
            end++;
        }

        number = end -  start + 1;

        if(start == 0 || cells[start - 1][column] != GameCellState.Empty)
            nBlockedEnds++;
        if(end == board.getRowCount() - 1 || cells[end+1][column]  != GameCellState.Empty)
            nBlockedEnds++;

        liveState = checkLiveState(nBlockedEnds);

        if(number == 5 && (liveState != LiveState.Dead || (liveState == LiveState.Dead && (start == 0 || end == board.getRowCount()-1))))
            //get one winning state
            nNonDead5++;
        if(number == 4) {
            if(liveState == LiveState.Alive)
                nAlive4++;
            if(liveState == LiveState.Blocked)
                nBlocked4++;
        }
        if(number == 3) {
            if(liveState == LiveState.Alive)
                nAlive3++;
            if(liveState == LiveState.Blocked)
                nBlocked3++;
        }
        if(number == 2) {
            if(liveState == LiveState.Alive)
                nAlive2++;
            if(liveState == LiveState.Blocked)
                nBlocked2++;
        }

        //check from upper left
        startC = column; endC = column; startR = row; endR = row;
        nBlockedEnds = 0;

        while(startC>0 && startR < board.getRowCount() - 1 && cells[startR+1][startC-1]== targetState)
        {
            startC--;
            startR++;
        }

        while(endC<board.getColumnCount()-1 && endR>0 && cells[endR-1][endC+1]== targetState)
        {
            endC++;
            endR--;
        }

        number = endC -  startC + 1;

        if(startC == 0 || startR == board.getRowCount()-1 || cells[startR+1][startC-1] != GameCellState.Empty)
            nBlockedEnds++;
        if(endC == board.getColumnCount()-1 || endR == 0 || cells[endR-1][endC+1] != GameCellState.Empty)
            nBlockedEnds++;

        liveState = checkLiveState(nBlockedEnds);

        boolean blockedAtEdge = (startC == 0 || startR == board.getRowCount()-1) || (endC == board.getColumnCount()-1 || endR == 0);

        if(number == 5 && (liveState != LiveState.Dead || (liveState == LiveState.Dead && blockedAtEdge)))
            //get one winning state
            nNonDead5++;
        if(number == 4) {
            if(liveState == LiveState.Alive)
                nAlive4++;
            if(liveState == LiveState.Blocked)
                nBlocked4++;
        }
        if(number == 3) {
            if(liveState == LiveState.Alive)
                nAlive3++;
            if(liveState == LiveState.Blocked)
                nBlocked3++;
        }
        if(number == 2) {
            if(liveState == LiveState.Alive)
                nAlive2++;
            if(liveState == LiveState.Blocked)
                nBlocked2++;
        }

        //check from lower left
        startC = column; endC = column; startR = row; endR = row;
        nBlockedEnds = 0;
        while(startC>0 && startR >0 && cells[startR-1][startC-1]== targetState)
        {
            startR--;
            startC--;
        }

        while(endC<board.getColumnCount()-1 && endR < board.getRowCount()-1 && cells[endR+1][endC+1]== targetState)
        {
            endR++;
            endC++;
        }

        number = endC -  startC + 1;

        if(startC == 0 || startR == 0 || cells[startR-1][startC-1] != GameCellState.Empty)
            nBlockedEnds++;
        if(endC == board.getColumnCount()-1 || endR == board.getRowCount()-1 || cells[endR+1][endC+1] != GameCellState.Empty)
            nBlockedEnds++;

        liveState = checkLiveState(nBlockedEnds);
        blockedAtEdge = (startC == 0 || startR == 0) || (endC == board.getColumnCount()-1 || endR == board.getRowCount()-1);
        if(number == 5 && (liveState != LiveState.Dead || liveState == LiveState.Dead && blockedAtEdge))
            //get one winning state
            nNonDead5++;
        if(number == 4) {
            if(liveState == LiveState.Alive)
                nAlive4++;
            if(liveState == LiveState.Blocked)
                nBlocked4++;
        }
        if(number == 3) {
            if(liveState == LiveState.Alive)
                nAlive3++;
            if(liveState == LiveState.Blocked)
                nBlocked3++;
        }
        if(number == 2) {
            if(liveState == LiveState.Alive)
                nAlive2++;
            if(liveState == LiveState.Blocked)
                nBlocked2++;
        }

        //Now compare combinations of different threat patterns
        if( nNonDead5 >= 1) return INSTANT_WIN_PAYOFF;
        if( nAlive4 >= 1 || nBlocked4 >=2 || (nBlocked4 == 1 && nAlive3 >=1) ) return SERIES_1;
        if (nAlive3 >=2) return SERIES_2;
        if (nAlive3 == 1 && nBlocked3 >= 1) return SERIES_3;
        if (nBlocked4 == 1) return SERIES_4;
        if (nAlive3 == 1) return SERIES_5;
        if (nAlive2 >= 2) return SERIES_6;
        if (nBlocked3 >= 1) return SERIES_7;
        if (nAlive2 == 1) return SERIES_8;
        if (nBlocked2 >= 1) return SERIES_9;
        //otherwise, it's a non_connected single button
        return 0;

    }

    LiveState checkLiveState (int nBlockedEnds) {

        LiveState liveState;

        switch (nBlockedEnds) {
            case 0:
                liveState = LiveState.Alive;
                break;
            case 1:
                liveState = LiveState.Blocked;
                break;
            case 2:
                liveState = LiveState.Dead;
                break;
            default:
                liveState = LiveState.Alive;
                Log.d("getScore", "Unexpected live state!");
                break;
        }
        return liveState;
    }

}
