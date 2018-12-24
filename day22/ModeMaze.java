package aoc2018.day22;

import hyec.util.Pair;
import hyec.util.spacial.point.Point2I;
import hyec.util.spacial.vector.Vector2I;

import java.util.HashMap;
import java.util.LinkedList;

public class ModeMaze
{
  public static final int depth= 5616;
  public static final Point2I target= new Point2I(785,10);
  public static final HashMap<Point2I,Integer> geoIndexs = new HashMap<>();
  public static void main(String[] args)
  {
    geoIndexs.put(target,0);
    System.out.println("Part 1: "+totalRiskLevel(new Vector2I(0,0,target)));
    display(new Vector2I(0,0,target));
    System.out.println("Part 2: " + minTimeTo(target));
  }

  private static int minTimeTo(Point2I target)
  {
    // Flood fill without changing
    // try change and floodfill that. If any locations less, replace and floodfill from there.
    // Hashmap of point to fastest times and tool equip

    HashMap<Point2I, Pair<Integer,Integer>> ff = floodFillFrom(x,y,tool);
    return 0;
  }
  public static HashMap<Point2I,Pair<Integer,Integer>> floodFillFrom(int x, int y, int tool)
  {
    HashMap<Point2I, Pair<Integer, Integer>> ret = new HashMap<>();

    LinkedList<Point2I> edgeOld, edge = new LinkedList<>();
    edge.add(new Point2I(x, y));
    int time = 0;
    while (!edge.isEmpty() && time<=100)
    {
      //System.out.println(edge.size() + " for " + time + "time");
      edgeOld = edge;
      edge = new LinkedList<>();
      for (Point2I pt : edgeOld)
        if (erosionLevel(pt.x, pt.y) % 3 != tool)
        {
          ret.put(pt, new Pair<>(tool, time));
          for (int dr = 0; dr < 4; dr++)
          {
            int dx = (dr % 2) + (dr / 2 - 1); // -1 0 0 1
            int dy = (dr / 2) - (dr % 2); // 0 -1 1 0
            Point2I newPt = new Point2I(pt.x + dx, pt.y + dy);
            if (!(newPt.x < 0 || newPt.y < 0) && !ret.containsKey(newPt) && !edge.contains(newPt))
              edge.add(newPt);
          }
        }
      time++;
    }
    return ret;
  }

  private static void display(Vector2I range)
  {
    HashMap<Point2I,Pair<Integer,Integer>> pts = floodFillFrom(0,0,1);
    for (int x = range.x1; x <= range.x2; x++)
    {
      for (int y = range.y1; y<=range.y2; y++)
      {
        Pair<Integer,Integer> pt = pts.get(new Point2I(x,y));
        int erolev = erosionLevel(x, y) % 3;
        System.out.print((char)(pt!=null?'0'+(pt.b%10):erolev==0?'.':erolev==1?'=':'|'));
      }
      System.out.println();
    }
  }

  private static int totalRiskLevel(Vector2I range)
  {
    int totalRiskLevel = 0;
    for (int x = range.x1; x<=range.x2; x++)
      for (int y = range.y1; y<=range.y2; y++)
        totalRiskLevel+=erosionLevel(x,y)%3;
      return totalRiskLevel;
  }

  public static int geologicIndex(Point2I pt)
  {
    if (!geoIndexs.containsKey(pt))
    {
      int ret;
      if (pt.x == 0)
        ret = pt.y * 16807;
      else if (pt.y == 0)
        ret = pt.x * 48271;
      else
        ret = erosionLevel(pt.x - 1, pt.y) * erosionLevel(pt.x, pt.y - 1);
      geoIndexs.put(pt, ret);
    }
    return geoIndexs.get(pt);
  }

  private static int erosionLevel(int x, int y)
  {
    return (geologicIndex(new Point2I(x,y))+depth)%20183;
  }
}
