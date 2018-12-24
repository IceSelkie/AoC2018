package aoc2018.day23;

import aoc2018.day16.ChronalClassification;
import chn.util.FileInput;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class ExperimentalEmergencyTeleportation
{
  public static void main(String[] args)
  {
    ArrayList<int[]> input = ChronalClassification.readAll(new FileInput("src/main/java/aoc2018/day23/inputModified"));

    System.out.println("Part 1: "+numInRange(input,findStrongest(input)));
  }

  private static int numInRange(ArrayList<int[]> input, int strongest)
  {
    int count = 0;
    int[] strong = input.get(strongest);
    for (int i =0;i<input.size();i++)
    {
      int[] other = input.get(i);
      if ((abs(strong[0]-other[0])+abs(strong[1]-other[1])+abs(strong[2]-other[2]))<=strong[3])
        count++;
    }
    return count;
  }

  private static int findStrongest(ArrayList<int[]> input)
  {
    int ret = 0;
    for (int i = 0; i < input.size(); i++)
      if (input.get(i)[3] > input.get(ret)[3])
        ret = i;
    return ret;
  }
}
