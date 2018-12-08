package aoc2018.day8;

import chn.util.FileInput;

public class MemoryManeuver
{
  public static void main(String[] args)
  {
    FileInput fi = new FileInput("src/main/java/aoc2018/day8/input");
    System.out.println("Part 1: " + readO(fi));
    fi.close();

    Node root = new Node(new FileInput("src/main/java/aoc2018/day8/input"));
    System.out.println("Part 2: " + root.part2Count());
  }

  private static int readO(FileInput fi)
  {
    int subNodes = fi.readInt();
    int metaCount = fi.readInt();
    int ret = 0;
    for (int i = 0; i<subNodes; i++)
      ret += readO(fi);
    for (int i = 0; i<metaCount; i++)
      ret += fi.readInt();
    return ret;
  }

  public static class Node
  {
    Node[] children;
    int[] meta;
    public Node(FileInput fi)
    {
      children = new Node[fi.readInt()];
      meta = new int[fi.readInt()];
      for (int i = 0; i<children.length; i++)
        children[i] = new Node(fi);
      for (int i = 0; i<meta.length; i++)
        meta[i] = fi.readInt();
    }
    public int part1Count()
    {
      int ret = 0;
      for (Node aChildren : children)
        ret += aChildren.part1Count();
      for (int val : meta)
        ret += val;
      return ret;
    }
    public int part2Count()
    {
      int ret = 0;
      if (children.length == 0)
        for (int val : meta)
          ret += val;
      else
        for (int val : meta)
          if (val >= 1 && val <= children.length)
            ret += children[val - 1].part2Count();
      return ret;

    }
  }
}
