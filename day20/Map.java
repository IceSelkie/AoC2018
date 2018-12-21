package aoc2018.day20;

import hyec.util.spacial.point.Point2I;
import hyec.util.spacial.vector.Vector2I;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Map
{
  Path root;
  char[][] map;
  Vector2I region;

  public Map(String regex)
  {
    interpretRegex(regex);
    genEmptyMap();

    root.carve(1 - 2 * region.x1, 1 - 2 * region.y1, map);

    solidifyMapWalls();

    Vector2I range = root.getPeakRange();

    System.out.println(root);
    System.out.println(range);

    displayMap();
  }

  private void interpretRegex(String regex)
  {
    if (!regex.startsWith("^") || !regex.endsWith("$"))
      throw new IllegalArgumentException("Regex Syntax Expected: \"^{data}$\"");

    Path path = root = new Path();

    for (char c : regex.substring(1, regex.length() - 1).toCharArray())
      if (isDir(c) || isCtrl(c))
      {
        if (isDir(c))
          path = path.add(c);
        else if (c == '|')
          path = path.newAltPath();
        else if (c == '(')
          path = path.newSplit();
        else
          path = path.closeSplit();
      }

    region = root.getPeakRange();
  }

  private void genEmptyMap()
  {
    map = new char[3 + 2 * (region.y2 - region.y1)][3 + 2 * (region.x2 - region.x1)];
    for (int y = 0; y < map.length; y++)
      for (int x = 0; x < map[y].length; x++)
      {
        // edges
        if (y == 0 || y == map.length - 1 || x == 0 || x == map[y].length - 1)
          map[y][x] = '#';
          // corners
        else if (y % 2 == 0 && x % 2 == 0)
          map[y][x] = '#';
          // paths
        else if (y % 2 == 0 ^ x % 2 == 0)
          map[y][x] = '?';
          // start location
        else if (y == 1 - 2 * region.y1 && x == 1 - 2 * region.x1)
          map[y][x] = 'X';
          // rest
        else
          map[y][x] = '.';
      }
  }

  private void solidifyMapWalls()
  {

    for (int y = 0; y < map.length; y++)
      for (int x = 0; x < map[y].length; x++)
        if (map[y][x] == '?')
          map[y][x] = '#';
  }

  public void displayMap()
  {
    for (int y = map.length-1; y >= 0; y--)
    {
      for (int x = 0; x < map[y].length; x++)
        System.out.print(map[y][x]);
      System.out.println();
    }
  }

  private boolean isCtrl(char c)
  {
    return c == '|' || c == '(' || c == ')';
  }

  private boolean isDir(char c)
  {
    return c == 'N' || c == 'S' || c == 'E' || c == 'W';
  }

  public int furthestDoor()
  {
    HashMap<Point2I,ArrayList<Point2I>> paths = floodFillGeneratePaths(1 - 2 * region.x1, 1 - 2 * region.y1);
    int maxDistance = 0;
    for (Point2I key : paths.keySet())
      maxDistance = max(maxDistance,paths.get(key).size());
    return 0;
  }

  private HashMap<Point2I, ArrayList<Point2I>> floodFillGeneratePaths(int x, int y)
  {
    HashMap<Point2I, ArrayList<Point2I>> ret = new HashMap<>();

    floodFillGeneratePathsFrom(x,y,ret,new ArrayList<>());

    return ret;
  }

  private void floodFillGeneratePathsFrom(int x, int y, HashMap<Point2I, ArrayList<Point2I>> existingPoints, ArrayList<Point2I> pathToHere)
  {
    // Stack of positions and hashmap of paths and stack of current path. Or use get from hashmap.
    Point2I here = new Point2I(x, y);
    if (existingPoints.containsKey(here))
      return;
    existingPoints.put(here, pathToHere);
    for (int dr = 0; dr < 4; dr++)
    {
      int dx = (dr%2)+(dr/2-1); // -1 0 0 1
      int dy = (dr/2)-(dr%2); // 0 -1 1 0
      if (map[y + dy][x + dx] != '#')
        floodFillGeneratePathsFrom(x + 2 *dx, y + 2 * dy, existingPoints, duplicateAndAdd(pathToHere, here));
    }
  }

  private ArrayList<Point2I> duplicateAndAdd(ArrayList<Point2I> pathToHere, Point2I here)
  {
    ArrayList<Point2I> ret = new ArrayList<>(pathToHere);
    ret.add(here);
    return ret;
  }

  private class Path
  {
    private Object data;
    private Path next;
    private Path parent;

    Path() {}

    Path(Path parent) {this.parent = parent;}

    public Path add(char c)
    {
      if (data == null)
      {
        data = "" + c;
        return this;
      }
      if (data instanceof String)
      {
        data = (String)data + c;
        return this;
      }
      if (data instanceof ArrayList)
      {
        next = new Path(parent);
        next.add(c);
        return next;
      }
      throw new IllegalStateException();
    }

    public Path newAltPath()
    {
      Path newPath = new Path(parent);
      ((ArrayList)parent.data).add(newPath);
      return newPath;
    }

    public Path newSplit()
    {
      if (data != null)
      {
        next = new Path(parent);
        return next.newSplit();
      }
      data = new ArrayList();
      Path newPath = new Path(this);
      ((ArrayList)data).add(newPath);
      return newPath;
    }

    public Path closeSplit()
    {
      return parent;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append('^');
      addToStringBuilder(sb);
      return sb.append('$').toString();
    }

    private void addToStringBuilder(StringBuilder sb)
    {
      if (data instanceof String)
        sb.append((String)data);
      else if (data instanceof ArrayList)
      {
        sb.append('(');
        ArrayList ar = (ArrayList)data;
        for (int i = 0; i < ar.size(); i++)
        {
          ((Path)ar.get(i)).addToStringBuilder(sb);
          if (i != ar.size() - 1) sb.append('|');
        }
        sb.append(')');
      }
      if (next != null)
        next.addToStringBuilder(sb);
    }

    public Vector2I getPeakRange()
    {
      Vector2I ret = new Vector2I(0, 0, 0, 0);
      int x = 0, y = 0;
      if (data instanceof String)
      {
        for (char c : ((String)data).toCharArray())
        {
          if (c == 'N')
            y++;
          else if (c == 'S')
            y--;
          else if (c == 'E')
            x++;
          else if (c == 'W')
            x--;
          if (x < ret.x1)
            ret.x1 = x;
          else if (x > ret.x2)
            ret.x2 = x;
          else if (y < ret.y1)
            ret.y1 = y;
          else if (y > ret.y2)
            ret.y2 = y;
        }
      }
      else if (data instanceof ArrayList)
      {
        for (Object o : (ArrayList)data)
          ret = combine(ret, ((Path)o).getPeakRange());
      }

      if (next == null)
        return ret;
      Vector2I nextrange = next.getPeakRange();
      nextrange.translate(x, y);
      return combine(ret, nextrange);
    }

    private Vector2I combine(Vector2I a, Vector2I b)
    {
      return new Vector2I(min(a.x1, b.x1), min(a.y1, b.y1), max(a.x2, b.x2), max(a.y2, b.y2));
    }

    public void carve(int x, int y, char[][] map)
    {
      if (data instanceof String)
      {
        for (char c : ((String)data).toCharArray())
        {
          switch (c)
          {
            case 'N':
              map[++y][x] = '-';
              y++;
              break;
            case 'S':
              map[--y][x] = '-';
              y--;
              break;
            case 'E':
              map[y][++x] = '|';
              x++;
              break;
            case 'W':
              map[y][--x] = '|';
              x--;
              break;
          }
        }
      }
      else if (data instanceof ArrayList)
      {
        for (Object o : (ArrayList) data)
          ((Path)o).carve(x,y,map);
      }
      if (next!=null)
        next.carve(x,y,map);
    }
  }
}
