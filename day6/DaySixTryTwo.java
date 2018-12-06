package aoc2018.day6;

import java.util.ArrayList;
import java.util.HashMap;

import hyec.util.spacial.point.Point2I;
import util.Util;

public class DaySixTryTwo
{
  public static void main(String[] args)
  {
    ArrayList<Point2I> data = al(new Point2I[]{new Point2I(124, 262), new Point2I(182, 343), new Point2I(79, 341), new Point2I(44, 244), new Point2I(212, 64), new Point2I(42, 240), new Point2I(225, 195), new Point2I(192, 325), new Point2I(192, 318), new Point2I(42, 235), new Point2I(276, 196), new Point2I(181, 262), new Point2I(199, 151), new Point2I(166, 214), new Point2I(49, 81), new Point2I(202, 239), new Point2I(130, 167), new Point2I(166, 87), new Point2I(197, 53), new Point2I(341, 346), new Point2I(235, 241), new Point2I(99, 278), new Point2I(163, 184), new Point2I(85, 152), new Point2I(349, 334), new Point2I(175, 308), new Point2I(147, 51), new Point2I(251, 93), new Point2I(163, 123), new Point2I(151, 219), new Point2I(162, 107), new Point2I(71, 58), new Point2I(249, 293), new Point2I(223, 119), new Point2I(46, 176), new Point2I(214, 140), new Point2I(80, 156), new Point2I(265, 153), new Point2I(92, 359), new Point2I(103, 186), new Point2I(242, 104), new Point2I(272, 202), new Point2I(292, 93), new Point2I(304, 55), new Point2I(115, 357), new Point2I(43, 182), new Point2I(184, 282), new Point2I(352, 228), new Point2I(267, 147), new Point2I(248, 271)});

    HashMap<Point2I, Integer> hm = new HashMap<>(data.size());

    ArrayList<Point2I> rem = new ArrayList<>();
    for (int i = 0; i < 1000; i++)
    {
      Point2I c = closest(data, 0, i);
      if (!rem.contains(c))
        rem.add(c);
      c = closest(data, 999, i);
      if (!rem.contains(c))
        rem.add(c);
      c = closest(data, i, 0);
      if (!rem.contains(c))
        rem.add(c);
      c = closest(data, i, 999);
      if (!rem.contains(c))
        rem.add(c);
    }

    System.out.println("Done checking objects to remove.");

    for (Point2I d : data)
      hm.put(d, 0);

    for (int i = 0; i < 1000; i++)
    {
      for (int j = 0; j < 1000; j++)
      {
        Point2I c = closest(data, i, j);
        if (c == null)
          continue;
        hm.replace(c, hm.get(c) + 1);
      }
      //System.out.print("r" + i);
    }

    for (Point2I d : rem)
    {
      hm.remove(d);
      //data.remove(d);
    }

    int val = 0;
    for (Point2I d : hm.keySet())
      val = Math.max(val, hm.get(d));

    System.out.println("Part 1: " + val);

    int[] minmax = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,0,0};
    long count = 0;
    for (int i = -2000; i < 3000; i++)
    {
      for (int j = -2000; j < 3000; j++)
      {
        if (distaceSum(data,i,j)<10000)
        {
          count++;
          minmax[0] = Math.min(minmax[0],i);
          minmax[1] = Math.min(minmax[1],j);
          minmax[2] = Math.max(minmax[2],i);
          minmax[3] = Math.max(minmax[3],j);
        }
      }
    }

    System.out.println("Part 2: "+count + "   " + Util.aTS(minmax));
  }

  private static ArrayList<Point2I> al(Point2I[] point2IS)
  {
    ArrayList<Point2I> ret = new ArrayList<>(point2IS.length);
    for (Point2I pt : point2IS)
      ret.add(pt);
    return ret;
  }

  private static Point2I closest(ArrayList<Point2I> pts, int x, int y)
  {
    int minDist = Integer.MAX_VALUE;
    ArrayList<Point2I> closest = new ArrayList<>();
    for (Point2I pt : pts)
    {
      int d = taxicab(pt, x, y);
      if (d<minDist)
        closest = new ArrayList<>();
      if (d<=minDist)
      {
        closest.add(pt);
        minDist = d;
      }
    }
    if (closest.size()==1)
      return closest.get(0);
    return null;
  }

  private static long distaceSum(ArrayList<Point2I> pts, int x, int y)
  {
    long dist = 0;
    for (Point2I pt : pts)
    {
      dist += taxicab(pt,x,y);
    }
    return dist;
  }

  private static int taxicab(Point2I pt, int x, int y)
  {
    return Math.abs(pt.x-x)+Math.abs(pt.y-y);
  }
}
