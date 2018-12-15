package aoc2018.day14;

import util.ArrayListCircular;

public class ChocolateCharts
{
  public static void main(String[] args)
  {
    int input = 190221;
    System.out.println("Part 1: " + scoresAfter(input));
    System.out.println("Part 2: " + recipesTo(input + ""));
  }

  private static String scoresAfter(int num)
  {
    ArrayListCircular<Integer> scores = new ArrayListCircular<>(num + 12);
    scores.add(3);
    scores.add(7);
    int elfOneLocation = 0;
    int elfTwoLocation = 1;
    while (scores.size() < num + 10)
    {
      int tempScore = scores.get(elfOneLocation) + scores.get(elfTwoLocation);
      if (tempScore >= 10)
      {
        scores.add(tempScore / 10);
        tempScore %= 10;
      }
      scores.add(tempScore);

      elfOneLocation = (elfOneLocation + scores.get(elfOneLocation) + 1) % scores.size();
      elfTwoLocation = (elfTwoLocation + scores.get(elfTwoLocation) + 1) % scores.size();
    }
    StringBuilder ret = new StringBuilder(10);
    for (int i = num; i < num + 10; i++)
      ret.append(scores.get(i));
    return ret.toString();
  }

  private static int recipesTo(String num)
  {
    ArrayListCircular<Integer> scores = new ArrayListCircular<>(5000);
    scores.add(3);
    scores.add(7);
    int elfOneLocation = 0;
    int elfTwoLocation = 1;
    int lastChecked = 0;
    int answerSize = num.length();
    boolean done = false;
    while (!done)
    {
      int tempScore = scores.get(elfOneLocation) + scores.get(elfTwoLocation);
      if (tempScore >= 10)
      {
        scores.add(tempScore / 10);
        tempScore %= 10;
      }
      scores.add(tempScore);

      elfOneLocation = (elfOneLocation + scores.get(elfOneLocation) + 1) % scores.size();
      elfTwoLocation = (elfTwoLocation + scores.get(elfTwoLocation) + 1) % scores.size();

      while (lastChecked < scores.size() - answerSize)
      {
        StringBuilder val = new StringBuilder(answerSize);
        for (int i = lastChecked; i < lastChecked + answerSize; i++)
          val.append(scores.get(i));
        if (val.toString().equals(num))
          done = true;
        lastChecked++;
      }
    }
    return lastChecked - 1;
  }
}
