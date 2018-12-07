package aoc2018.day7;


import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class DaySeven
{
  public static final Pair<String,String>[] input = new Pair[]{p("T", "X"), p("G", "O"), p("X", "B"), p("I", "W"), p("N", "V"), p("K", "H"), p("S", "R"), p("P", "J"), p("L", "V"), p("D", "E"), p("J", "R"), p("U", "W"), p("M", "Q"), p("B", "F"), p("F", "E"), p("V", "Q"), p("C", "A"), p("H", "Z"), p("A", "Y"), p("O", "Y"), p("W", "Q"), p("E", "Y"), p("Y", "Z"), p("Q", "R"), p("R", "Z"), p("S", "E"), p("O", "W"), p("G", "B"), p("I", "N"), p("G", "I"), p("H", "R"), p("N", "C"), p("M", "W"), p("Y", "R"), p("T", "B"), p("G", "D"), p("J", "O"), p("I", "A"), p("J", "H"), p("T", "Y"), p("N", "H"), p("B", "V"), p("M", "R"), p("Y", "Q"), p("X", "J"), p("A", "E"), p("P", "Z"), p("P", "C"), p("N", "Q"), p("A", "O"), p("G", "X"), p("P", "U"), p("T", "S"), p("I", "V"), p("V", "H"), p("U", "F"), p("D", "Q"), p("D", "O"), p("G", "H"), p("I", "Z"), p("N", "D"), p("B", "Y"), p("J", "M"), p("V", "Y"), p("W", "Y"), p("E", "Z"), p("T", "N"), p("L", "U"), p("S", "A"), p("Q", "Z"), p("T", "F"), p("F", "Z"), p("J", "C"), p("X", "Y"), p("K", "V"), p("T", "I"), p("I", "O"), p("C", "W"), p("B", "Q"), p("W", "Z"), p("D", "H"), p("K", "A"), p("M", "E"), p("T", "U"), p("I", "J"), p("O", "Q"), p("M", "Z"), p("U", "C"), p("N", "F"), p("C", "H"), p("X", "E"), p("F", "O"), p("P", "O"), p("J", "A"), p("H", "Y"), p("A", "Q"), p("V", "Z"), p("S", "L"), p("H", "E"), p("X", "I"), p("O", "R")};
  //public static final Pair<String, String>[] input = new Pair[]{p("C", "A"), p("C", "F"), p("A", "B"), p("A", "D"), p("B", "E"), p("D", "E"), p("F", "E")};
  public static void main(String[] args)
  {
    HashMap<String, ArrayList<String>> hm = new HashMap();
    for (Pair<String,String> p : input)
    {
      if (!hm.containsKey(p.a))
        hm.put(p.a, new ArrayList<String>());
      hm.get(p.a).add(p.b);
    }
    ArrayList<String> starting = keysettoarraylist(hm.keySet());
    for (String s : hm.keySet())
      for (String k : hm.get(s))
        starting.remove(k);

    System.out.print("Part 1: ");
    ArrayList<String> remaining = new ArrayList<>();
    ArrayList<String> done = new ArrayList<>();
    add(remaining,starting);
    while (!remaining.isEmpty())
    {
      Collections.sort(remaining);
      String s = remaining.get(0);
      if (!done.contains(s))
      {
        System.out.print(s);
        add(remaining, hm.get(s));
        hm.remove(s);
      }
      done.add(s);
      while (remaining.contains(s))
        remaining.remove(s);
      for (String key: hm.keySet())
        for (String val : hm.get(key))
          remaining.remove(val);
    }
    System.out.println();

    // Now you WHAT?!?!?!? Part 2 will take me like an hour...

    System.out.print("Part 2: ");
    // Redo Setup
    hm = new HashMap();
    for (Pair<String,String> p : input)
    {
      if (!hm.containsKey(p.a))
        hm.put(p.a, new ArrayList<String>());
      hm.get(p.a).add(p.b);
    }
    starting = keysettoarraylist(hm.keySet());
    for (String s : hm.keySet())
      for (String k : hm.get(s))
        starting.remove(k);
      // Done with setup again.

    remaining = new ArrayList<>();
    add(remaining,starting);

    Pair<Integer,String>[] workers = new Pair[]{new Pair<Integer,String>(0,null),new Pair<Integer,String>(0,null), new Pair<Integer,String>(0,null),new Pair<Integer,String>(0,null), new Pair<Integer,String>(0,null)};
    int time = -1; // So its 0 on the first run of the loop.
    while (!remaining.isEmpty())
    {
      time++;
      for (int i = 0; i < workers.length; i++)
      {
        workers[i].a = Math.max(0,workers[i].a-1);
        if (workers[i].a==0&&workers[i].b!=null)
        {
          //System.out.println("["+time+"] Worker"+i+" has finished: "+workers[i].b);
          free(remaining, hm, workers[i].b);
          workers[i].b = null;
        }
      }

      String s;
      for (int i = 0; i < workers.length; i++)
        if (workers[i].b==null&&(s = workAvailable(remaining, workers))!=null)
        {
          workers[i] = p(s.compareTo("A") + 61, s);
          //System.out.println("["+time+"] Worker"+i+" has started: "+s + " for "+workers[i].a);
          System.out.print(s);
        }
    }

    System.out.println(time);

  }

  private static void free(ArrayList<String> remaining, HashMap<String, ArrayList<String>> hm, String s)
  {
    add(remaining, hm.get(s));
    hm.remove(s);

    while (remaining.contains(s))
      remaining.remove(s);
    for (String key: hm.keySet())
      for (String val : hm.get(key))
        remaining.remove(val);
  }

  private static String workAvailable(ArrayList<String> remaining, Pair<Integer, String>[] workers)
  {
    Collections.sort(remaining);
    for (String s : remaining)
    {
      boolean found = false;
      for (Pair<Integer, String> worker : workers)
        if (worker.b!=null&&worker.b.equals(s))
          found = true;
      if (found == false)
        return s;
    }
    return null;
  }

  private static ArrayList<String> keysettoarraylist(Set<String> keySet)
  {
    ArrayList<String> ret = new ArrayList<>();
    for (String s : keySet)
      ret.add(s);
    return ret;
   }

  private static void add(ArrayList<String> remaining, ArrayList<String> strings)
  {
    if (strings!=null && remaining!=null)
    for (String s : strings)
      if (s!=null)
        remaining.add(s);
  }

  public static class Pair<T,F>
  {
    T a;
    F b;

    public Pair(T a, F b)
    {
      this.a = a;
      this.b = b;
    }
  }

  public static Pair<String,String> p(String a, String b)
  {
    return new Pair<String,String>(a,b);
  }
  public static Pair<Integer,String> p(int a, String b)
  {
    return new Pair<Integer,String>(a,b);
  }
}
