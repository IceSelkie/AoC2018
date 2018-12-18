package aoc2018.day15;

import hyec.util.Pair;
import hyec.util.spacial.point.Point2I;
import util.Util;

import java.util.*;

public class BeverageBandits
{
  public static final ArrayList<Unit> adjdirs = new ArrayList<Unit>();
  public static final Point2I[] adjacentDirections = new Point2I[]{new Point2I(0,-1),new Point2I(-1,0),new Point2I(1,0),new Point2I(0,1)}; // down right

  public static void main(String[] args)
  {
    adjdirs.add(new Unit(0,-1));
    adjdirs.add(new Unit(-1,0));
    adjdirs.add( new Unit(1,0));
    adjdirs.add( new Unit(0,1));
    Unit.sortReadOrder(adjdirs);
    for (int i =0; i<4; i++)
      if (!adjacentDirections[i].equals(adjdirs.get(i).position))
        System.out.println("error");
    char[][] input = Util.readAllChars("src/main/java/aoc2018/day15/input");
    Game g = new Game(input);

    g.display();
    g.tick();
    g.display();
    /*
    while (!g.tick())
    {
      g.display();
      try {Thread.sleep(2000);} catch (Exception e) {e.printStackTrace();}
    }*/
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
      int next = 0;
      while (next < onBoardAll.size())
      {
        Unit u = onBoardAll.get(next++);
        // Move
        Pair<Unit, LinkedList<Point2I>> closestPathableTarget;
        closestPathableTarget = findTarget(board, u, getOppList(u));
        if (closestPathableTarget == null)
          return true;
        // If no findable target, WE ARE DONE!

        // MOVE
        boolean attackingDistance;
        if (closestPathableTarget.a == null)
          continue;
        attackingDistance = closestPathableTarget.b.size() == 1;
        if (!attackingDistance)
        {
          u.moveTo(closestPathableTarget.b.get(0), board);
          closestPathableTarget.b.removeFirst();
          attackingDistance = closestPathableTarget.b.size() == 1;
        }

        // ATTACK
        if (attackingDistance)
        {
          Unit killed = u.attack(this);
          if (killed != null)
            next = removeUnit(next, killed);
        }
      }


      roundsComplete++;
      return false;
    }

    private int removeUnit(int next, Unit killed)
    {
      if (killed instanceof Unit.Elf)
        onBoardElf.remove((Unit.Elf)killed);
      else if (killed instanceof Unit.Goblin)
        onBoardGob.remove((Unit.Goblin)killed);
      else
        System.out.println("That unit isn't an elf or goblin. Tf did u do?");

      int index = onBoardAll.indexOf(killed);
      if (index<next)
        next--;
      onBoardAll.remove(index);
      board[killed.position.y][killed.position.x] = '.';
      return next;
    }

    private ArrayList getOppList(Unit u)
    {
      if (u instanceof Unit.Elf)
      {
        Unit.sortReadOrder(onBoardGob);
        return onBoardGob;
      }
      Unit.sortReadOrder(onBoardElf);
      return onBoardElf;
    }

    private <T extends Unit> Pair<T,LinkedList<Point2I>> findTarget(char[][] board, Unit attacker, ArrayList<T> enemyList)
    {
      if (enemyList.size()==0)
        return null;
      ArrayList<Pair<T,LinkedList<Point2I>>> enemies = floodFillShortestDistance(attacker,enemyList);
      //Unit.sortHealthOrder(enemyList);

      if (enemies.size()>0)
        return enemies.get(0);
      System.out.println("Unit found has no pathable target!");
      return new Pair(null,null);
    }

    private <T extends Unit> ArrayList<Pair<T,LinkedList<Point2I>>> floodFillShortestDistance(Unit attacker, ArrayList<T> enemyList)
    {
      class LinkedTreeNode
      {
        LinkedTreeNode parent;
        Point2I data;
        ArrayList<LinkedTreeNode> children = new ArrayList<>();

        LinkedTreeNode(Point2I data) {this.data = data;}
        LinkedTreeNode(Point2I data, LinkedTreeNode parent) {this.data = data; this.parent = parent;}

        LinkedTreeNode addchild(Point2I child) {LinkedTreeNode tn = new LinkedTreeNode(child, this); children.add(tn); return tn;}
        LinkedList<Point2I> buildList() {LinkedList<Point2I> ret = new LinkedList<>(); LinkedTreeNode tn = this; while (tn.parent!=null) {ret.addFirst(tn.data); tn = tn.parent;} return ret;}
        boolean contains(Point2I loc) {if (loc.equals(data)) return true; for (LinkedTreeNode child:children) if (loc.equals(child.data)) return true; return false;}
      }
      // TODO Sort each itteration, then when extending, linked tree
      // TODO list them so we can figure out which path
      // TODO is the best and will take us to the target.

      T unitForTypeToFind = enemyList.get(0);

      ArrayList<Pair<T,LinkedList<Point2I>>> ret = new ArrayList<>();

      LinkedTreeNode root = new LinkedTreeNode(attacker.position);

      ArrayList<LinkedTreeNode> leaves = new ArrayList<>();
      ArrayList<LinkedTreeNode> branches = new ArrayList<>();
      branches.add (root);

      boolean foundTargets = false;
      while (branches.size()>0 && !foundTargets)
      {
        for (LinkedTreeNode oldLeaf : branches)
          for (int i = 0; i < 4; i++)
          {
            Point2I p = new Point2I(oldLeaf.data.x + adjacentDirections[i].x, oldLeaf.data.y + adjacentDirections[i].y);
            if (board[p.y][p.x] != '#' && !root.contains(p))
            {
              LinkedTreeNode tn = oldLeaf.addchild(p);
              leaves.add(tn);
              Unit temp = unitOnBoard(p);
              if (temp != null)
                if ((unitForTypeToFind instanceof Unit.Elf == temp instanceof Unit.Elf))
                {
                  foundTargets = true;
                  ret.add(new Pair<>((T)temp, tn.buildList()));
                  display(tn.buildList());
                }
            }
          }
        branches = leaves;
          leaves = new ArrayList<>();
      }

      return ret;
      /*
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
            if (unitForTypeToFind instanceof Unit.Elf == unit instanceof Unit.Elf)
              ret.add((T)unit);
            else
              iter.remove();
        }
      }
      return ret;
      */
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

    public void display()
    {
      for (int y = 0; y< board.length; y++)
      {
        for (int x = 0; x < board[y].length; x++)
          System.out.print(board[y][x]);
        System.out.println();
      }
      System.out.println();
    }
    public void display(LinkedList<Point2I> ll)
    {
      ArrayList<Point2I> pts = new ArrayList<>(ll);
      pts.remove(pts.size()-1);
      for (int y = 0; y< board.length; y++)
      {
        for (int x = 0; x < board[y].length; x++)
        {
          Point2I pt = new Point2I(x, y);
          System.out.print((char)((pts.contains(pt)) ? '0'+pts.indexOf(pt)%10 : board[y][x]));
        }
        System.out.println();
      }
      System.out.println();
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
        Point2I p = new Point2I(position.x + adjacentDirections[i].x, position.y + adjacentDirections[i].y);
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
