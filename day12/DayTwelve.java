package aoc2018.day12;

import java.util.ArrayList;

public class DayTwelve
{
  public static final int LEFT = 50;
  public static final int RIGHT = LEFT;
  public static void main(String[] args)
  {
    String state = "##..#..##.#....##.#..#.#.##.#.#.######..##.#.#.####.#..#...##...#....#....#.##.###..#..###...#...#..";
    //String state = "#..#.#..##......###...###";
    String[] rulesBirth = {"#####", "###.#", "##...", "#.##.", "#.#..", "#..##", "#...#", ".##.#", ".#.#.", ".#..#", ".#...", "..##.", "...##"};
    //String[] rulesBirth = {"...##", "..#..", ".#...", ".#.#.", ".#.##", ".##..", ".####", "#.#.#", "#.###", "##.#.", "##.##", "###..", "###.#", "####."};
    //String[] rulesDie = {"####.", "###..", "##.##", "##.#.", "##..#", "#.###", "#.#.#", "#..#.", "#....", ".####", ".###.", ".##..", ".#.##", "..###", "..#.#", "..#..", "...#.", "....#", "....."};
    ArrayList<Boolean> current = toBool(state.toCharArray());
    for (int i = 0; i<20; i++)
    {
      for (int j = 0; j<state.length()+LEFT+RIGHT; j++)
        System.out.print(current.get(j)?"#":".");
      System.out.println();
      current = tick(current, rulesBirth);
    }
    int part1 = 0;
    for (int i = 0; i<state.length()+LEFT+RIGHT; i++)
      if (current.get(i))
        part1+= i-50;
      System.out.println("Part 1: "+part1);
  }

  private static ArrayList<Boolean> tick(ArrayList<Boolean> current, String[] rulesBirth)
  {
    String s = null;
    ArrayList<Boolean> ret = new ArrayList<Boolean>(current.size());
    for (int i = 0; i<current.size(); i++)
      ret.add(false);
    for (int i = 2; i<current.size()-2; i++)
    {
      s = ts(current, i);
      if (matches(s, rulesBirth))
      {
        ret.set(i, true);
        //System.out.println("Setting!");
      }
      //System.out.println(s);
    }
    return ret;
  }

  private static boolean matches(String local, String[] rulesBirth)
  {
    for (String s : rulesBirth)
      if (s.equals(local))
        return true;
      return false;
  }

  private static String ts(ArrayList<Boolean> current, int i)
  {
    StringBuilder sb = new StringBuilder();
    for (int j = i-2; j<=i+2;j++)
      sb.append(current.get(j)?'#':'.');
    return sb.toString();
  }

  private static ArrayList<Boolean> toBool(char[] toCharArray)
  {
    ArrayList<Boolean> ret = new ArrayList<>();
    for (int i = 0; i<LEFT; i++)
      ret.add(false);
    for (char c : toCharArray)
    {
      ret.add(c == '#');
      //System.out.println("Yes");
    }
    for (int i = 0; i<RIGHT; i++)
      ret.add(false);
    return ret;
  }


}
