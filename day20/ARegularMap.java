package aoc2018.day20;

import chn.util.FileInput;
import util.Util;

public class ARegularMap
{
  public static void main(String[] args)
  {
    FileInput fi = new FileInput("src/main/java/aoc2018/day20/input");
    String regex = fi.readLine();
    System.out.println(regex);
    Map m = new Map(regex);
    System.out.println("Part 1: "+m.furthestDoor());
  }


}
