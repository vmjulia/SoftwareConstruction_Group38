package ch.uzh.group38;


import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class PieceFieldTest {
    PieceField f;



    @Test
    public void testCoordinates(){
        f = new PieceField(PieceField.Color.WHITE,PieceField.Type.PAWN, 1, 2);
        assertEquals(1, f.getX());
        assertEquals(2, f.getY());
        f.updatePosition(2, 1);
        assertEquals(2, f.getX());
        assertEquals(1, f.getY());
    }

    @Test
    public void testIsSomething(){

        PieceField PW = new PieceField(PieceField.Color.WHITE,PieceField.Type.PAWN, 1, 2);
        assertFalse(PW.isKing());
        assertFalse(PW.isRed());
        assertTrue(PW.isWhite());
        assertFalse(PW.isEmpty());
        PW.convertToKing();
        assertTrue(PW.isKing());
        PieceField WK = new PieceField(PieceField.Color.WHITE,PieceField.Type.KING,1, 2);
        assertTrue(WK.isKing());
        assertFalse(WK.isRed());
        assertTrue(WK.isWhite());
        assertFalse(WK.isEmpty());
        WK.convertToKing();
        assertTrue(WK.isKing());
       PieceField RP = new PieceField(PieceField.Color.RED,PieceField.Type.PAWN, 1, 2);
        assertFalse(RP.isKing());
        assertTrue(RP.isRed());
        assertFalse(RP.isWhite());
        assertFalse(RP.isEmpty());
        RP.convertToKing();
        assertTrue(RP.isKing());
        PieceField RK = new PieceField(PieceField.Color.RED,PieceField.Type.KING, 1, 2);
        assertTrue(RK.isKing());
        assertTrue(RK.isRed());
        assertFalse(RK.isWhite());
        assertFalse(RK.isEmpty());
        RK.convertToKing();
        assertTrue(RK.isKing());

    }

    @Test
    public void testPossibleMoveStorage(){
        RuleEvaluator.resetCurrentPlayer();
        Board b  = new Board();

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(!b.getField(i, j).isEmpty()){
                    if (i == 2){
                        assertTrue(b.getField(i, j).isAnyMovePossible());
                    }
                    else {assertFalse(b.getField(i, j).isAnyMovePossible());}
                }
            }
        }

        b.removePiece(2, 1);
        assertFalse(b.getField(1, 0).isAnyMovePossible());
        assertFalse(b.getField(1, 2).isAnyMovePossible());
        b.getField(1, 0).update();
        assertTrue(b.getField(1, 0).isAnyMovePossible());
        assertFalse(b.getField(1, 2).isAnyMovePossible());

    }

    @Test
    public void testGeneralMoveChecks(){
        RuleEvaluator.resetCurrentPlayer();
        Board b  = new Board();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(!b.getField(i, j).isEmpty()){
                    if (i == 2){
                        Move m = new Move(i, j, i+1, j-1);
                        assertTrue(b.getField(i, j).isMoveStored(m));
                    }
                    else {
                        Move m = new Move(i, j, i+1, j-1);
                        assertFalse(b.getField(i, j).isMoveStored(m));}
                }
            }
        }

    }

    @Test
    public void testJumpMoveChecks(){
        RuleEvaluator.resetCurrentPlayer();
        Board b  = new Board();
        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;
        int x3 = 5;
        int y3 = 2;
        int x4 = 4;
        int y4 = 1;

        Move m1 = new Move(x1, y1, x2, y2);
        m1.move(b);
        Move m2 = new Move(x3, y3, x4, y4);
        m2.move(b);
        Move jumpMove = new Move(x2, y2, x3, y3);
        assertTrue(b.getField(x2, y2).isMoveStored(jumpMove));
        assertTrue(b.getField(x2, y2).isJumpMoveStored(jumpMove));

    }
}