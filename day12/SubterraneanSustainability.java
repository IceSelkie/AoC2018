package aoc2018.day12;

import java.util.ArrayList;

public class SubterraneanSustainability
{
  public static int LEFT = 64;
  public static int RIGHT = LEFT;

  public static void main(String[] args)
  {
    // 1672
    String state = "##..#..##.#....##.#..#.#.##.#.#.######..##.#.#.####.#..#...##...#....#....#.##.###..#..###...#...#..";
    //String state = "#..#.#..##......###...###";
    String[] rulesBirth = {"#####", "###.#", "##...", "#.##.", "#.#..", "#..##", "#...#", ".##.#", ".#.#.", ".#..#", ".#...", "..##.", "...##"};
    //String[] rulesBirth = {"...##", "..#..", ".#...", ".#.#.", ".#.##", ".##..", ".####", "#.#.#", "#.###", "##.#.", "##.##", "###..", "###.#", "####."};
    //String[] rulesDie = {"####.", "###..", "##.##", "##.#.", "##..#", "#.###", "#.#.#", "#..#.", "#....", ".####", ".###.", ".##..", ".#.##", "..###", "..#.#", "..#..", "...#.", "....#", "....."};

    ArrayList<Boolean> current = toBoolList(state);
    boolean[][] birth = toBool(rulesBirth);

    long ticks = 0;
    for (long i = 0; i < 20; i++)
    {
      for (int j = 0; j<state.length()+LEFT+RIGHT; j++)
        System.out.print(current.get(j)?"#":".");
      System.out.println();
      current = buffer(tick(current, birth, ++ticks));
    }

    int part1 = 0;
    for (int i = 0; i < state.length() + LEFT + RIGHT; i++)
      if (current.get(i))
        part1 += i - LEFT;
    System.out.println("Part 1: " + part1);

    long t = System.currentTimeMillis();
    for (long i = 0; i < 50000000000L - 20; i++)
    {
      current = buffer(tick(current, birth, ++ticks));
      if (i%10000000==0)
        System.out.println("Reached: " + ticks + " after "+(System.currentTimeMillis()-t) +" ms");
      int part2 = 0;
      for (int j = 0; j < current.size(); j++)
        if (current.get(j))
          part2 += j - LEFT;
      System.out.println(ticks+", " + part2);
    }
  }

  private static ArrayList<Boolean> buffer(ArrayList<Boolean> current)
  {
    ArrayList<Boolean> ret = current;

    if (current.get(0)||current.get(1)||current.get(2)||current.get(3))
    {
      //System.out.println("Expanding Left");
      ArrayList<Boolean> temp = new ArrayList<>(ret.size()+LEFT);
      for (int i = 0; i<LEFT; i++)
        temp.add(false);
      temp.addAll(ret);
      LEFT*=2;
      ret = temp;
    }
    if (current.get(current.size()-1)||current.get(current.size()-2)||current.get(current.size()-3)||current.get(current.size()-4))
    {
      //System.out.println("Expanding Right");
      ArrayList<Boolean> temp = new ArrayList<>(ret.size()+RIGHT);
      temp.addAll(ret);
      for (int i = 0; i<RIGHT; i++)
        temp.add(false);
      RIGHT*=2;
      ret = temp;
    }
    return ret;
  }

  private static boolean[][] toBool(String[] strs)
  {
    boolean[][] ret = new boolean[strs.length][5];
    for (int i = 0; i < strs.length; i++)
      for (int j = 0; j < 5; j++)
        ret[i][j] = strs[i].charAt(j) == '#';
    return ret;
  }

  private static ArrayList<Boolean> tick(ArrayList<Boolean> current, boolean[][] rulesBirth, long ticknum)
  {
    ArrayList<Boolean> ret = new ArrayList<Boolean>(current.size());
    for (int i = 0; i < current.size(); i++)
      ret.add(false);
    for (int i = 2; i < current.size() - 2; i++)
    {
      if (matches(current, i, rulesBirth))
      {
        ret.set(i, true);
        //System.out.println("Setting!");
      }
      //System.out.println(s);
    }
    return ret;
  }

  private static boolean matches(ArrayList<Boolean> local, int offset, boolean[][] rulesBirth)
  {
    for (boolean[] birthrule : rulesBirth)
      for (int i = 0; i < 5; i++)
      {
        if (local.get(i + offset - 2) != birthrule[i])
          break;
        if (i == 4)
          return true;
      }
    return false;
  }

  private static String ts(ArrayList<Boolean> current, int i)
  {
    StringBuilder sb = new StringBuilder();
    for (int j = i - 2; j <= i + 2; j++)
      sb.append(current.get(j) ? '#' : '.');
    return sb.toString();
  }

  private static ArrayList<Boolean> toBoolList(String initial)
  {
    ArrayList<Boolean> ret = new ArrayList<>();
    for (int i = 0; i < LEFT; i++)
      ret.add(false);
    for (char c : initial.toCharArray())
      ret.add(c == '#');
    for (int i = 0; i < RIGHT; i++)
      ret.add(false);
    return ret;
  }

}
