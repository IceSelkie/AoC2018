package aoc2018.day8;

import chn.util.FileInput;

public class MemoryManeuver
{
  public static void main(String[] args)
  {
    TreeNode root = new TreeNode(new FileInput("src/main/java/aoc2018/day8/input"));
    System.out.println("Part 1: " + root.part1Count());
    System.out.println("Part 2: " + root.part2Count());
  }

  public static class TreeNode
  {
    TreeNode[] children;
    int[] meta;

    /**
     * Read in from the file.
     *
     * @param fi
     */
    public TreeNode(FileInput fi)
    {
      // Read header (# children and # metadata)
      children = new TreeNode[fi.readInt()];
      meta = new int[fi.readInt()];

      // Read in children (by recursively calling this)
      for (int i = 0; i < children.length; i++)
        children[i] = new TreeNode(fi);

      // Read in meta
      for (int i = 0; i < meta.length; i++)
        meta[i] = fi.readInt();
    }

    /**
     * Sum the meta and the children's meta.
     */
    public int part1Count()
    {
      int ret = 0;

      // Sum childrens' meta sums (recursively)
      for (TreeNode aChildren : children)
        ret += aChildren.part1Count();

      // Sum the meta
      for (int val : meta)
        ret += val;

      return ret;
    }

    /**
     * If no children, sum the meta
     * else, recursively call this for the children, of the indexes given by the meta.
     */
    public int part2Count()
    {
      int ret = 0;

      // No children -> sum meta
      if (children.length == 0)
        for (int val : meta)
          ret += val;

      // If children -> recursively call this for indexes determined by meta.
      else
        for (int val : meta)
          if (val >= 1 && val <= children.length)
            ret += children[val - 1].part2Count();

      return ret;
    }
  }
}
