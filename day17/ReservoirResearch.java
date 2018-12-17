package aoc2018.day17;

import hyec.util.spacial.point.Point2I;
import hyec.util.spacial.vector.Vector2I;
import util.Util;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ReservoirResearch
{
  public static void main(String[] args)
  {
    ArrayList<String> input = Util.readAllLines("src/main/java/aoc2018/day17/input");
    ArrayList<Vector2I> regions = new ArrayList<>(input.size());
    for (String line : input)
      regions.add(toVec(line));

    int[] minmax = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
    for (Vector2I v : regions)
    {
      minmax[0] = min(minmax[0], v.x1);
      minmax[1] = min(minmax[1], v.y1);
      minmax[2] = max(minmax[2], v.x2);
      minmax[3] = max(minmax[3], v.y2);
    }
    minmax[0]--;
    minmax[1]--;
    minmax[2] += 2;
    minmax[3] += 2;
    Vector2I rg = new Vector2I(minmax[0], minmax[1], minmax[2] - 1, minmax[3] - 1); // accurate size

    char[][] region = draw(regions, minmax);

    Point2I water = p(500, minmax[1]);
    try
    {
      doLogic(region, rg, water);
    } catch (Exception e) {e.printStackTrace();}

    //display(region);
    //System.out.println(Util.aTS(minmax));
    //System.out.println(Util.aTS(Util.iToArray(rg.x1,rg.y1,rg.x2,rg.y2)));

    int ret = 0;
    int loss = 0;
    for (int y = minmax[1] + 1; y < minmax[3] - 1; y++)
    {
      for (int x = minmax[0]; x < minmax[2]; x++)
      {
        if (region[x - rg.x1][y - rg.y1] == '|')
          loss++;
        if (region[x - rg.x1][y - rg.y1] == '~')
          ret++;
        region[x - rg.x1][y - rg.y1] = 'X';
      }
    }

    System.out.println("Part 1: " + (loss + ret));
    System.out.println("Part 2: " + (ret));

    display(region);

  }

  private static void doLogic(char[][] region, Vector2I rg, Point2I water)
  {
    boolean done = false;
    while (!done)
    {
      done = true;
      if (fall(region, rg, water))
        return;
      Point2I lf = flowSide(region, rg, water, true);
      Point2I rt = flowSide(region, rg, water, false);
      if (lf != null)
        doLogic(region, rg, lf);
      if (rt != null)
        doLogic(region, rg, rt);
      if (lf == null && rt == null)
      {
        fillLine(region, rg, water);
        water.y--;
        done = false;
      }
    }
  }

  private static void fillLine(char[][] region, Vector2I rg, Point2I water)
  {
    region[water.x - rg.x1][water.y - rg.y1] = '~';
    for (int i = 0; i < 2; i++)
    {
      Point2I local = water;
      while (open(region, rg, local = side(local, i % 2 == 0)))
        region[local.x - rg.x1][local.y - rg.y1] = '~';
    }
  }

  private static Point2I flowSide(char[][] region, Vector2I rg, Point2I water, boolean left)
  {
    if (rg.within(water)) region[water.x - rg.x1][water.y - rg.y1] = '|';
    while (open(region, rg, side(water, left)) && !open(region, rg, down(side(water, left))))
    {
      water = side(water, left);
      if (rg.within(water)) region[water.x - rg.x1][water.y - rg.y1] = '|';
    }
    if (open(region, rg, side(water, left)))
      return side(water, left);
    return null;
  }

  private static void display(char[][] region)
  {
    for (int y = 0; y < region[0].length; y++)
    {
      for (char[] aRegion : region)
        System.out.print(aRegion[y]);
      System.out.println();
    }
  }

  private static boolean fall(char[][] region, Vector2I rg, Point2I water)
  {
    if (rg.within(water)) region[water.x - rg.x1][water.y - rg.y1] = '|';
    while (open(region, rg, down(water)))
    {
      water.y++;
      if (rg.within(water)) region[water.x - rg.x1][water.y - rg.y1] = '|';
    }
    return !rg.within(p(water.x, water.y + 1));
  }

  private static boolean open(char[][] region, Vector2I rg, Point2I water)
  {
    //if (water.x-rg.x1==224)
    //  display(region);
    //    try
    //    {
    return (rg.within(water) && (region[water.x - rg.x1][water.y - rg.y1] == '.' || region[water.x - rg.x1][water.y - rg.y1] == '|'));
    //    }
    //    catch (Exception e)
    //    {
    //      display(region);
    //      throw new NullPointerException();
    //    }
  }

  public static Point2I side(Point2I existing, boolean left)
  {
    return p(existing.x + (left ? -1 : 1), existing.y);
  }

  public static Point2I down(Point2I existing)
  {
    return p(existing.x, existing.y + 1);
  }

  public static Point2I up(Point2I existing)
  {
    return p(existing.x, existing.y - 1);
  }

  private static char[][] draw(ArrayList<Vector2I> regions, int[] minmax)
  {
    char[][] ret = new char[minmax[2] - minmax[0]][minmax[3] - minmax[1]];
    for (int y = minmax[1]; y < minmax[3]; y++)
    {
      for (int x = minmax[0]; x < minmax[2]; x++)
        ret[x - minmax[0]][y - minmax[1]] = isClay(x, y, regions) ? '#' : '.';
    }
    return ret;
  }

  private static boolean isClay(int x, int y, ArrayList<Vector2I> regions)
  {
    Point2I pt = p(x, y);
    for (Vector2I v : regions)
      if (v.within(pt))
        return true;
    return false;
  }

  private static Vector2I toVec(String line)
  {
    //x=581, y=396..399
    boolean xFirst = line.charAt(0) == 'x';
    int x = new Integer(line.substring(line.indexOf('=') + 1, line.indexOf(',')));
    line = line.substring(line.indexOf(' '));
    int ya = new Integer(line.substring(line.indexOf('=') + 1, line.indexOf('.')));
    int yb = new Integer(line.substring(line.indexOf('.') + 2));

    return xFirst ? new Vector2I(x, ya, x, yb) : new Vector2I(ya, x, yb, x);
  }

  private static Point2I p(int x, int y)
  {
    return new Point2I(x, y);
  }
}
