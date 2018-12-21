package aoc2018.day21;

import aoc2018.day19.GoWithTheFlow;

import java.util.HashSet;

public class ChronalConversion extends GoWithTheFlow
{
  public static void main(String[] args)
  {
    long val = Integer.MAX_VALUE;

    readInput("src/main/java/aoc2018/day21/input");

    long iterations = 0;
    long largestItterationsValue = 0;
    long lastLargestItterVal = -1;
    boolean part1Done = false;
    HashSet<Long> previouslyDone = new HashSet<>();
    while (ip >= 0 && ip < instructions.size())
    {
      run(false);
      if (ip == 28)
      {
        if (registers[1] < val)
        {
          val = registers[1];
          if (!part1Done)
            System.out.println("Part 1: " + val);
          part1Done = true;
        }
        if (!previouslyDone.contains((Long)registers[1]))
        {
          previouslyDone.add((Long)registers[1]);
          largestItterationsValue = registers[1];
        }
      }
      if (iterations % 1000000000 == 0)
      {
        if (lastLargestItterVal == largestItterationsValue)
          System.out.println("Part 2: " + largestItterationsValue);
        lastLargestItterVal = largestItterationsValue;
      }
      iterations++;
    }
  }
}
