package aoc2018.day9;

import util.ArrayListCircular;
import util.LinkedListCircular;
import util.Util;

public class MarbleMania
{
  public static void main(String[] args)
  {
    long t = System.currentTimeMillis();
    System.out.println("Part 1: "+new MarbleGame(419,72164).getTopScore() + " t=" + (System.currentTimeMillis()-t));
    t = System.currentTimeMillis();
    System.out.println("Part 2: "+new MarbleGame(419,100*72164).getTopScore() + " t=" + (System.currentTimeMillis()-t));
  }

  public static class MarbleGame
  {
    long scores[] = null;
    LinkedListCircular<Integer> gameMap = null;
    public MarbleGame(int players, int maxMarbleValue)
    {
      scores = new long[players];
      gameMap = new LinkedListCircular<Integer>();

      int player = 0;
      int marbleValue = 0;
      int current = 0;
      while (marbleValue<=maxMarbleValue)
      {
        if (marbleValue%72164==0)
        System.out.println(current+" : "+(gameMap.size()==0?0:gameMap.get())+ " in "+getTopScore()/*+Util.aTS(gameMap.toArray())*/);
        if (marbleValue%23!=0 || marbleValue==0)
        {
          try{gameMap.next();}catch(Exception e){}
          gameMap.add(marbleValue);
          gameMap.next();
        }
        else
        {
          scores[player] += marbleValue;
          for (int i = 0;i<7; i++)
            gameMap.previous();
          scores[player] += gameMap.get();
          gameMap.remove();
        }

        marbleValue++;
        player = (player+1)%players;
      }
    }

    public long getTopScore()
    {
      long top = 0;
      for (long val : scores)
        top = Math.max(val,top);
      return top;
    }
  }
}
