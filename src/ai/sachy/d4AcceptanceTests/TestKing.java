package ai.sachy.d4AcceptanceTests;

import ai.sachy.ChessThinker;
import ai.sachy.DeepTeal;

/**
 * design task 04 comp2911
 * @author richard buckland
 * @date may 2010
 */
public class TestKing implements Test {

   public String toString () {
      return "richard's king tests";
   }

   public void run () {      
      {
         String game = 
            "|kd|  |  |  |  |  |  |ql|=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|rl|  |  |  |  |kl|  |  |";
         
         ChessThinker ct = new DeepTeal (game);
         assert (ct.blackIsInCheck ());
         assert (!ct.blackIsInCheckMate ());
      }
      
      {
         String game = 
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |kd|  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |  |=\n"+
            "|  |rl|rl|rl|  |  |  |  |=\n"+
            "|  |  |  |  |  |  |  |kl|";
         
         ChessThinker ct = new DeepTeal (game);
         assert (ct.blackIsInCheck ());
         assert (ct.blackIsInCheckMate ());
      }     
   }
}

