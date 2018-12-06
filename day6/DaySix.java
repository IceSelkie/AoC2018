package aoc2018.day6;

import java.awt.geom.Point2D;

import hyec.util.spacial.point.*;

public class DaySix
{
  public static void main(String[] args)
  {
    Point2I[] input = new Point2I[]{new Point2I(124, 262), new Point2I(182, 343), new Point2I(79, 341), new Point2I(44, 244), new Point2I(212, 64), new Point2I(42, 240), new Point2I(225, 195), new Point2I(192, 325), new Point2I(192, 318), new Point2I(42, 235), new Point2I(276, 196), new Point2I(181, 262), new Point2I(199, 151), new Point2I(166, 214), new Point2I(49, 81), new Point2I(202, 239), new Point2I(130, 167), new Point2I(166, 87), new Point2I(197, 53), new Point2I(341, 346), new Point2I(235, 241), new Point2I(99, 278), new Point2I(163, 184), new Point2I(85, 152), new Point2I(349, 334), new Point2I(175, 308), new Point2I(147, 51), new Point2I(251, 93), new Point2I(163, 123), new Point2I(151, 219), new Point2I(162, 107), new Point2I(71, 58), new Point2I(249, 293), new Point2I(223, 119), new Point2I(46, 176), new Point2I(214, 140), new Point2I(80, 156), new Point2I(265, 153), new Point2I(92, 359), new Point2I(103, 186), new Point2I(242, 104), new Point2I(272, 202), new Point2I(292, 93), new Point2I(304, 55), new Point2I(115, 357), new Point2I(43, 182), new Point2I(184, 282), new Point2I(352, 228), new Point2I(267, 147), new Point2I(248, 271)};

    Point2I[][] world = new Point2I[5000][5000];

    for (Point2I d : input)
      world[d.getX()+2000][d.getY()+2000] = d;

    boolean changes = true;
    while (changes)
    {
      changes = false;
      for (int i = 0; i < 5000; i++)
        for (int j = 0; j < 5000; j++)
        {
          Point2I[] dirs = new Point2I[5];
          dirs[0] = (i > 0) ? world[i - 1][j] : null;
          dirs[1] = (i < 4999) ? world[i + 1][j] : null;
          dirs[2] = (j > 0) ? world[i][j - 1] : null;
          dirs[3] = (j < 4999) ? world[i][j + 1] : null;
          if (dirs[0] == null && dirs[1] == null && dirs[2] == null && dirs[3] != null)
            dirs[4] = dirs[3];
          if (dirs[0] == null && dirs[1] == null && dirs[3] == null && dirs[2] != null)
            dirs[4] = dirs[2];
          if (dirs[0] == null && dirs[2] == null && dirs[3] == null && dirs[1] != null)
            dirs[4] = dirs[1];
          if (dirs[1] == null && dirs[2] == null && dirs[3] == null && dirs[0] != null)
            dirs[4] = dirs[0];
          if (dirs[4] != null)
          {
            changes = true;
            world[i][j] = dirs[4];
          }
        }
    }

    int val = 0;
    for (Point2I d : input)
      if (notEdge(d,world))
      val = Math.max(count(d, world),val);

    System.out.println("Part 1: " + val);

    System.out.println("Part 2: ");
  }

  private static int count(Point2I d, Point2I[][] world)
  {
    int ret = 0;
    for (Point2I[] line : world)
      for (Point2I v : line)
        if (v == d)
          ret++;
    return ret;
  }

  private static boolean notEdge(Point2I d, Point2I[][] world)
  {
    for (int i = 0; i<5000; i++)
    {
      if (world[i][0]==d || world[i][4999]==d || world[0][i]==d || world[4999][i]==d)
        return false;
    }
    return true;
  }
}
