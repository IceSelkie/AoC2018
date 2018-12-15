package aoc2018.day15;

import hyec.util.Pair;
import hyec.util.spacial.point.Point2I;
import util.Util;

import java.util.*;

public class BeverageBandits
{

  public static final int [][] adjacentDirections = new int[][]{{0,-1},{-1,0},{1,0},{0,1}};

  public static void main(String[] args)
  {
    char[][] input = Util.readAllChars("src/main/java/aoc2018/day15/input");
    Game g = new Game(input);
  }

  public static class Game
  {
    char[][] board;
    ArrayList<Unit.Elf> onBoardElf;
    ArrayList<Unit.Goblin> onBoardGob;
    ArrayList<Unit> onBoardAll;
    int roundsComplete;

    public Game(char[][] board)
    {
      this.board = board;
      onBoardElf = new ArrayList<>();
      onBoardGob = new ArrayList<>();
      onBoardAll = new ArrayList<>();
      roundsComplete = 0;

      for (int y = 0; y < board.length; y++)
        for (int x = 0; x < board[y].length; x++)
        {
          if (board[y][x] == 'E')
            onBoardElf.add(new Unit.Elf(x, y));
          else if (board[y][x] == 'G')
            onBoardGob.add(new Unit.Goblin(x, y));
        }

      onBoardAll.addAll(onBoardElf);
      onBoardAll.addAll(onBoardGob);
    }

    public boolean tick()
    {
      boolean stop;

      Unit.sortReadOrder(onBoardAll);
      for (Unit u : onBoardAll)
      {
        // Move
        Pair<Unit,List<Point2I>> closestPathableTarget;
        closestPathableTarget = findTarget(board, u, getOppList(u));
        if (closestPathableTarget == null)
          return true;
        // If no findable target, WE ARE DONE!

        boolean attackingDistance = false;
        if (closestPathableTarget != null)
          attackingDistance = u.moveTowards(closestPathableTarget, board);

        // Attack
        if (attackingDistance)
        {
          Unit killed = u.attack(this);
          if (killed!=null)
            removeUnit(killed);
        }
      }


      roundsComplete++;
      return false;
    }

    private void removeUnit(Unit killed)
    {
      if (killed instanceof Unit.Elf)
        onBoardElf.remove((Unit.Elf)killed);
      else if (killed instanceof Unit.Goblin)
        onBoardGob.remove((Unit.Goblin)killed);
      else
        System.out.println("That unit isn't an elf or goblin. Tf did u do?");
      onBoardAll.remove(killed);
      board[killed.position.y][killed.position.x] = '.';
    }

    private ArrayList getOppList(Unit u)
    {
      if (u instanceof Unit.Elf)
        return onBoardGob;
      return onBoardElf;
    }

    private <T extends Unit> Pair<T,List<Point2I>> findTarget(char[][] board, Unit attacker, ArrayList<T> enemyList)
    {
      if (enemyList.size()==0)
        return null;
      enemyList = floodFillShortestDistance(attacker,enemyList);
      Unit.sortReadOrder(enemyList);

      if (enemyList.size()>0)
        return new Pair(enemyList.get(0),path);
      System.out.println("Unit found no pathable target!");
      return new Pair(null,null);
    }

    private <T extends Unit> ArrayList<T> floodFillShortestDistance(Unit attacker, ArrayList<T> enemyList)
    {
      // TODO Sort each itteration, then when extending, linked
      // TODO list them so we can figure out which path
      // TODO is the best and will take us to the target.

      T unitfortypetofind = enemyList.get(0);
      ArrayList<T> ret = new ArrayList<>();
      int distance = 0;
      ArrayList<Point2I> ffd = new ArrayList<>(), ffl = new ArrayList<>(), ffn;

      ffl.add(attacker.position);

      while (ffl.size() != 0 && ret.size() == 0)
      {
        ffn = new ArrayList<>();
        for (Point2I n : ffl)
          for (int i = 0; i<4; i++)
          {
            Point2I p = new Point2I(n.x + adjacentDirections[i][0], n.y + adjacentDirections[i][1]);
            if (!ffd.contains(p) && !ffl.contains(p) && board[p.y][p.x] != '#')
              ffn.add(p);
          }
        ffd.addAll(ffl);
        ffl = ffn;
        ListIterator<Point2I> iter = ffl.listIterator();

        // Remove if units of the same type. If opposite type, return these.
        while (iter.hasNext())
        {
          Point2I next = iter.next();
          Unit unit = unitOnBoard(next);
          if (unit != null)
            if (unitfortypetofind instanceof Unit.Elf == unit instanceof Unit.Elf)
              ret.add((T)unit);
            else
              iter.remove();
        }
      }
      return ret;
    }

    private Unit unitOnBoard(Point2I next)
    {
      Unit u = new Unit(next.x, next.y);
      int index = indexedBinarySearch(onBoardAll, u);
      if (index >= 0)
        return onBoardAll.get(index);
      else
        return null;
    }
    
    private static <T extends Unit> int indexedBinarySearch(ArrayList<T> list, Unit key) { int low = 0;int high = list.size()-1;while (low <= high) { int mid = (low + high) >>> 1;Unit midVal = list.get(mid);int cmp = midVal.compareTo(key);if (cmp < 0) low = mid + 1;else if (cmp > 0) high = mid - 1;else return mid; }return -(low + 1);   }

    public int unitsAlive()
    {
      return onBoardAll.size();
    }

    public int totalHealth()
    {
      int ret = 0;
      for (Unit u : onBoardAll)
        ret += u.health;
      return ret;
    }

    public int roundsComplete()
    {
      return this.roundsComplete;
    }
  }

  public static class Unit implements Comparable
  {
    Point2I position;
    final int attackPower = 3;
    int health = 200;

    public Unit(int x, int y)
    {
      position = new Point2I(x, y);
    }

    public void attack(Unit victim)
    {
      victim.health -= this.attackPower;
    }

    public boolean equals(Object obj)
    {
      return position.equals(((Unit)obj).position);
    }

    public Unit attack(Game b)
    {
      ArrayList<Unit> possibles = new ArrayList<>();
      for (int i = 0; i < 4; i++)
      {
        Point2I p = new Point2I(position.x + adjacentDirections[i][0], position.y + adjacentDirections[i][1]);
        possibles.add(b.unitOnBoard(p));
      }
      possibles.removeIf(u -> u == null || (u instanceof Elf == this instanceof Elf));
      if (possibles.size() == 0)
        return this;
      sortHealthOrder(possibles);
      attack(possibles.get(0));
      return this;
    }

    public boolean moveTowards(Pair<Unit, List<Point2I>> closestPathableTarget, char[][] board)
    {
      if (closestPathableTarget.b.size() > 1)
        moveTo(closestPathableTarget.b.get(0), board);
      if (closestPathableTarget.b.size() == 1 || closestPathableTarget.b.size() == 2)
        return true;
      return false;
    }

    private void moveTo(Point2I newPos, char[][] board)
    {
      board[position.y][position.x] = '.';
      board[newPos.y][newPos.x] = this instanceof Elf?'E':'G';
      position = newPos;
    }

    public static class Elf extends Unit
    {

      public Elf(int x, int y) { super(x, y); }

      public int compareTo(Object obj)
      {
        Unit o = (Unit)obj;
        int ret = health - o.health;
        if (ret != 0)
          return ret;
        return super.compareTo(o);
      }
    }

    public static class Goblin extends Unit
    {

      public Goblin(int x, int y) { super(x, y); }

      public int compareTo(Object obj)
      {
        Unit o = (Unit)obj;
        int ret = health - o.health;
        if (ret != 0)
          return ret;
        return super.compareTo(o);
      }
    }

    public int compareTo(Object obj)
    {
      Unit o = (Unit)obj;
      int ret = position.y - o.position.y;
      if (ret != 0)
        return ret;
      ret = position.x - o.position.x;
      return ret;
    }

    public static <T extends Unit> void sortReadOrder(ArrayList<T> input)
    {
      input.sort((a, b) -> (a.position.y - b.position.y != 0) ? a.position.y - b.position.y : a.position.x - b.position.x);
    }
    public static <T extends Unit> void sortHealthOrder(ArrayList<T> input)
    {
      input.sort((a, b) -> (a.health-b.health!=0)?a.health-b.health:(a.position.y - b.position.y != 0) ? a.position.y - b.position.y : a.position.x - b.position.x);
    }
  }

  public static Unit.Elf tce(Unit u)
  {
    return (Unit.Elf)u;
  }

  public static Unit.Goblin tcg(Unit u)
  {
    return (Unit.Goblin)u;
  }
}
