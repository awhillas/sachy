package ai.sachy.d4AcceptanceTests;
import ai.sachy.ChessThinker;
import ai.sachy.DeepTeal;

public class AcceptanceTest_King implements Test {

   public String toString () {
      return "testing kings";
   }

   public void run () {
      {
         String boardString = 
            "|  |  |  |  |  |  |kd|  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |kl|  |  |=\n" +
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n";

         ChessThinker deepTeal = new DeepTeal(); 

         deepTeal.fromString (boardString);

         assert (boardString.equals (deepTeal.toString()));

         assert (!deepTeal.blackIsInCheck());

         assert (!deepTeal.blackIsInCheckMate());

         assert (!deepTeal.whiteCanMateInOneMove());
      }

      {
         String boardString = 
            "|  |  |  |  |  |  |  |kd|=\n" + 
            "|  |  |  |  |  |  |pl|  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" +
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |  |=\n" + 
            "|  |  |  |  |  |  |  |kl|=\n";

         ChessThinker deepTeal = new DeepTeal(); 

         deepTeal.fromString (boardString);

         assert (boardString.equals (deepTeal.toString()));
         assert (deepTeal.blackIsInCheck());
         assert (!deepTeal.blackIsInCheckMate());
         assert (!deepTeal.whiteCanMateInOneMove());
      }
   }
}
