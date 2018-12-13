package aoc2018.day13;

import hyec.util.spacial.point.Point2I;
import util.Util;

import java.util.*;

public class MineCartMadness
{
  public static void main(String[] args)
  {
    Object[] objs = generate("src/main/java/aoc2018/day13/input");
    char[][] map = (char[][])objs[0];
    ArrayList<Cart> carts = (ArrayList<Cart>)objs[1];

    String s = null;
    int itters = 0;
    while (s == null)
    {
      itters++;
      s = tick(map, carts, true);
    }
    System.out.println("Part 1: " + s);


    objs = generate("src/main/java/aoc2018/day13/input");
    map = (char[][])objs[0];
    carts = (ArrayList<Cart>)objs[1];

    s = null;
    itters = 0;
    while (s == null)
    {
      itters++;
      s = tick(map, carts, false);
    }
    System.out.println("Part 2: " + s);
  }

  private static Object[] generate(String file)
  {
    char[][] map = Util.readAllChars(file);
    ArrayList<Cart> carts = new ArrayList<>();
    for (int x = 0; x<map.length; x++)
      for (int y = 0; y<map[x].length; y++)
      {
        if (map[x][y] == '<' || map[x][y] == '>' || map[x][y] == '^' || map[x][y] == 'v')
        {
          carts.add(new Cart(map[x][y], x, y));
          if (map[x][y] == '^' || map[x][y] == 'v')
            map[x][y] = '|';
          else
            map[x][y] = '-';
        }
      }

    return new Object[]{map,carts};
  }

  private static String tick(char[][] map, ArrayList<Cart> carts, boolean part1)
  {
    boolean output = false;
    Collections.sort(carts);
    HashMap<Point2I, Cart> hs = new HashMap<>(carts.size());
    for (Cart c : carts)
      hs.put(c.location, c);
    for (int ci = 0; ci<carts.size(); ci++)
    {
      Cart c = carts.get(ci);
      hs.remove(c.location);
      if (output) System.out.print(c+" \'");
      c.move();
      //if (output) System.out.print("map[" + c.location.x + "][" + c.location.y + "] = \'");
      char mapval = map[c.location.x][c.location.y]; // cuz first array is the ys cuz thats how it was read
      if (output) System.out.print((char)mapval + "\' ");
      if (mapval == '+')
      {
        c.turn();
        //if (output) System.out.print(" +turn");
      }
      else if (mapval == '/' || mapval == '\\')
      {
        //if (output) System.out.print(" Xturn: " + c.direction + "->");
        c.guidedTurn(mapval);
        //if (output) System.out.print(c.direction + "!");
      }

      if (output) System.out.print(c.toString());

      if (hs.get(c.location) != null)
      {
        if (part1)
          return c.location.y + "," + c.location.x;
        else
        {
          if (output) System.out.print("Del: "+c);
          carts.remove(c);
          if (carts.indexOf(hs.get(c.location))<ci)
            ci--;
          ci--;
          if (output) System.out.print("Del: "+hs.get(c.location));
          carts.remove(hs.remove(c.location));
        }
      }
      else
        hs.put(c.location, c);
      if (output) System.out.println();
    }
    if (output) System.out.println();
    if (carts.size() == 1)
      return carts.get(0).location.y + "," + carts.get(0).location.x;
    else
      return null;
  }

  public static class Cart implements Comparable
  {
    int direction; //0> 1^ 2< 3v
    int nextTurn; // 0left 1stright, 2right
    Point2I location;

    public Cart(char c, int x, int y)
    {
      nextTurn = 0;
      location = new Point2I(x,y);
      switch (c)
      {
        case '>':
          this.direction = 0;
          break;
        case '^':
          direction = 1;
          break;
        case '<':
          direction = 2;
          break;
        case 'v':
          direction = 3;
          break;
      }
    }

    public Cart move()
    {
      switch (direction)
      {
        case 0:
          location.y++;
          break;
        case 1:
          location.x--;
          break;
        case 2:
          location.y--;
          break;
        case 3:
          location.x++;
          break;
      }
      return this;
    }
    public Cart turn()
    {
      turn(nextTurn);
      nextTurn = (nextTurn+1)%3;
      return this;
    }
    public Cart turn(int direction)
    {
      if (direction == 0)
        this.direction = (this.direction + 1) % 4;
      if (direction == 2)
        if (this.direction == 0)
          this.direction = 3;
        else
          this.direction--;
      return this;
    }

    public int compareTo(Object obj)
    {
      Cart o = (Cart)obj;
      int ret = location.x-o.location.x;
      if (ret!=0)
        return ret;
      return location.y-o.location.y;
    }

    public void guidedTurn(char mapval)
    {
      if (mapval=='/')
        turn(2*(direction%2));
      else
        turn(2*(1-direction%2));
    }

    @Override
    public String toString()
    {
      char dirStr = ' ';
      switch (direction)
      {
        case 0:
          dirStr = '>';
          break;
        case 1:
          dirStr = '^';
          break;
        case 2:
          dirStr = '<';
          break;
        case 3:
          dirStr = 'v';
          break;
      }
      return "("+location.x+","+location.y+")"+dirStr;
    }
  }
}
