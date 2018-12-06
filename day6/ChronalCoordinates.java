package aoc2018.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import hyec.util.spacial.point.Point2I;

public class ChronalCoordinates
{
  public static void main(String[] args)
  {
    ArrayList<Point2I> input = toArrayList(new Point2I[]{new Point2I(124, 262), new Point2I(182, 343), new Point2I(79, 341), new Point2I(44, 244), new Point2I(212, 64), new Point2I(42, 240), new Point2I(225, 195), new Point2I(192, 325), new Point2I(192, 318), new Point2I(42, 235), new Point2I(276, 196), new Point2I(181, 262), new Point2I(199, 151), new Point2I(166, 214), new Point2I(49, 81), new Point2I(202, 239), new Point2I(130, 167), new Point2I(166, 87), new Point2I(197, 53), new Point2I(341, 346), new Point2I(235, 241), new Point2I(99, 278), new Point2I(163, 184), new Point2I(85, 152), new Point2I(349, 334), new Point2I(175, 308), new Point2I(147, 51), new Point2I(251, 93), new Point2I(163, 123), new Point2I(151, 219), new Point2I(162, 107), new Point2I(71, 58), new Point2I(249, 293), new Point2I(223, 119), new Point2I(46, 176), new Point2I(214, 140), new Point2I(80, 156), new Point2I(265, 153), new Point2I(92, 359), new Point2I(103, 186), new Point2I(242, 104), new Point2I(272, 202), new Point2I(292, 93), new Point2I(304, 55), new Point2I(115, 357), new Point2I(43, 182), new Point2I(184, 282), new Point2I(352, 228), new Point2I(267, 147), new Point2I(248, 271)});

    HashMap<Point2I, Integer> areaMap = new HashMap<>(input.size());

    // We dont want the ones that open out to the sides, so find all of those and mark them to be ignored for the final output.
    HashSet<Point2I> toIgnore = new HashSet<>();

    // We want to check each of the boundaries of a bounding box: top bottom left right, of the box 500x500.
    for (int i = 0; i < 500; i++)
      toIgnore.add(closestPoint(input, 0, i));
    for (int i = 0; i < 500; i++)
      toIgnore.add(closestPoint(input, 499, i));
    for (int i = 0; i < 500; i++)
      toIgnore.add(closestPoint(input, i, 0));
    for (int i = 0; i < 500; i++)
      toIgnore.add(closestPoint(input, i, 499));

    // Add distance 0 for all the points to the map.
    for (Point2I d : input)
      areaMap.put(d, 0);

    // For each point, find the closest one, and increment its value in the map.
    for (int i = 0; i < 500; i++)
      for (int j = 0; j < 500; j++)
      {
        Point2I c = closestPoint(input, i, j);
        if (c != null)
          areaMap.replace(c, areaMap.get(c) + 1);
      }

      // Find the one that has the largest area that is closest to itself and record that area.
    int mostArea = 0;
    for (Point2I point : areaMap.keySet())
      if (!toIgnore.contains(point) && areaMap.get(point)>mostArea)
      mostArea = areaMap.get(point);

    System.out.println("Part 1: " + mostArea);

    // Find all points that are less than a total 10000 taxicab units away from all points.
    long pointsNearAll = 0;
    for (int i = 0; i < 500; i++)
      for (int j = 0; j < 500; j++)
        if (distaceSum(input, i, j) < 10000)
          pointsNearAll++;

    System.out.println("Part 2: " + pointsNearAll);
  }

  /**
   * Converts an array of points into an arraylist of points.
   */
  private static ArrayList<Point2I> toArrayList(Point2I[] point2IS)
  {
    ArrayList<Point2I> ret = new ArrayList<>(point2IS.length);
    for (Point2I pt : point2IS)
      ret.add(pt);
    return ret;
  }

  /**
   * Finds the closest point to a given x,y pair. (or null if two or more points are equally distant.)
   */
  private static Point2I closestPoint(ArrayList<Point2I> pts, int x, int y)
  {
    int minDist = Integer.MAX_VALUE;
    ArrayList<Point2I> closest = new ArrayList<>();
    for (Point2I pt : pts)
    {
      int d = taxicabDistance(pt, x, y);
      if (d < minDist)
        closest = new ArrayList<>();
      if (d <= minDist)
      {
        closest.add(pt);
        minDist = d;
      }
    }
    if (closest.size() == 1)
      return closest.get(0);
    return null;
  }

  /**
   * Sums the distance between every point and the x,y pair.
   */
  private static long distaceSum(ArrayList<Point2I> points, int x, int y)
  {
    long dist = 0;
    for (Point2I point : points)
      dist += taxicabDistance(point, x, y);
    return dist;
  }

  /**
   * Finds the Manhattan/Taxicab distance from a point to the x,y pair.
   */
  private static int taxicabDistance(Point2I point, int x, int y)
  {
    return Math.abs(point.x - x) + Math.abs(point.y - y);
  }
}
