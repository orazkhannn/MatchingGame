import java.util.Random;
import comp102x.Canvas;

/**
 * The GameBoard class represents the game board which contains the cards
 */
public class GameBoard
{   
    private Card[][] cards = new Card[MatchingGame.NUMBER_OF_ROWS][MatchingGame.NUMBER_OF_CARDS_PER_ROW]; //The array of Card objects which represent the cards on the game board
    
    /**
     * The constructor which initlizes the Card objects and randomizes the cards
     */
    public GameBoard()
    {
        //initialize the Card objects
        for(int i=0; i<MatchingGame.NUMBER_OF_ROWS; i++)
        {
            for (int j = 0; j < MatchingGame.NUMBER_OF_CARDS_PER_ROW; j++)
            {
                cards[i][j] = new Card(j % (MatchingGame.NUMBER_OF_CARDS_PER_ROW*MatchingGame.NUMBER_OF_ROWS/2) + 1);
            }
        }
        
        //shuffle the cards 100 times
        Random randomNumberGenerator = new Random();
        for(int i=0; i<100; i++)
        {
            //swap two randomly-chosen cards
            int card1Column = randomNumberGenerator.nextInt(MatchingGame.NUMBER_OF_CARDS_PER_ROW);
            int card1Row = randomNumberGenerator.nextInt(MatchingGame.NUMBER_OF_ROWS);
            int card2Column = randomNumberGenerator.nextInt(MatchingGame.NUMBER_OF_CARDS_PER_ROW);
            int card2Row = randomNumberGenerator.nextInt(MatchingGame.NUMBER_OF_ROWS);
            swapCards(card1Row, card1Column, card2Row, card2Column);
        }
    }
    
    /**
     * Swap two cards, card A and card B
     * 
     * @param   cardAIndex      the card index of card A
     * @param   cardBIndex      the card index of card B
     */
    private void swapCards(int cardARow, int cardAColumn, int cardBRow, int cardBColumn)
    {
        // Please write your code after this line
        Card originalCardA = cards[cardARow][cardAColumn];
        cards[cardARow][cardAColumn] = cards[cardBRow][cardBColumn];
        cards[cardBRow][cardBColumn] = originalCardA;
    }
    
    /**
     * Flip the card to be facing up
     * 
     * @param   cardIndex      the card index of the card to be flipped facing up
     */ 
    public void flipCardUp(int cardRow, int cardColumn)
    {
        cards[cardRow][cardColumn].setFacingUp(true);
    }
    
    /**
     * Flip the card to be facing down
     * 
     * @param   cardIndex      the card index of the card to be flipped facing down
     */ 
    public void flipCardDown(int cardRow, int cardColumn)
    {
        cards[cardRow][cardColumn].setFacingUp(false);
    }
    
    /**
     * Check if all the first card matches the second card
     * 
     * @param   firstCardIndex      the card index of the first card
     * @param   secondCardIndex     the card index of the second card
     * @return  true if first card matches the second card; false otherwise
     */ 
    public boolean checkCardMatch(int firstCardRow, int firstCardColumn, int secondCardRow, int secondCardColumn)
    {
        return (cards[firstCardRow][firstCardColumn].getValue() == cards[secondCardRow][secondCardColumn].getValue());
    }
    
     /**
     * Check if all the cards are flipped facing up, i.e., all matches are found
     * 
     * @return  true if all cards are flipped facing up; false otherwise
     */ 
    public boolean checkAllMatchesFound()
    {       
        for(int i = 0; i < MatchingGame.NUMBER_OF_ROWS; i++) 
        {
            for (int j = 0; j < MatchingGame.NUMBER_OF_CARDS_PER_ROW; j++)
            {
                if(!cards[i][j].isFacingUp()) return false;
            }
        }
        return true;
    }
    
    /**
     * Draw the card images on the given canvas
     * 
     * @param   canvas      the canvas to draw on
     */
    public void draw(Canvas canvas)
    {
        //clear the canvas
        canvas.removeAll();
        
        //draw all the card images on the canvas
        for(int i=0; i<MatchingGame.NUMBER_OF_ROWS; i++)
        {
            for (int j = 0; j < MatchingGame.NUMBER_OF_CARDS_PER_ROW; j++)
            {
                cards[i][j].draw(canvas, MatchingGame.BOARD_MARGIN+j*MatchingGame.CARD_WIDTH, MatchingGame.BOARD_MARGIN+i*MatchingGame.CARD_HEIGHT);
            }
        }
    }
}
