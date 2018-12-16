package aoc2018.day16;

import chn.util.FileInput;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class DaySixteen
{
  public static final String[] opcodes = new String[] {"addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtrr", "gtri", "gtir", "eqrr", "eqri", "eqir"};
  public static void main(String[] args)
  {
    ArrayList<int[][]> info = readAll(new FileInput("src/main/java/aoc2018/day16/inputPart1Clean"));
    int ct = 0;
    for (int[][] data : info)
      //System.out.println(++ct+" "+ Util.aTS(possibilities(data).toArray()));
      if (possibilities(data).size()>=3)
        ct++;
    System.out.println("Part 1: " + ct);

    System.out.println("Part 2: ");
  }

  public static ArrayList<String> possibilities(int[][] dataset)
  {
    ArrayList<String> ret = new ArrayList<String>(16);
    for (String op : opcodes)
    {
      try
      {
        int[] output = doOp(op, dataset[0], dataset[1]);
        if (Arrays.equals(output, dataset[2]))
          ret.add(op);
      } catch (ArrayIndexOutOfBoundsException e) {}
    }
    return ret;
  }

  private static int[] doOp(String op, int[] input, int[] instructions)
  {
    int[] ret = new int[4];
    for (int i = 0; i<4; i++)
      ret[i]=input[i];

    boolean binary = true;
    if (op.startsWith("gt")||op.startsWith("eq"))
      binary = false;

    boolean r1= op.substring(0,binary?3:2).equals("set")?op.charAt(3) == 'r':(binary || op.charAt(2) == 'r');
    boolean r2= op.charAt(3)=='r';

    int outReg = instructions[3];
    int vala = r1?input[instructions[1]]:instructions[1];
    int valb = r2?input[instructions[2]]:instructions[2];
    int out = -1;


    op = op.substring(0,binary?3:2);
    switch (op)
    {
      case "add":
        out = vala+valb;
        break;
      case "mul":
        out = vala*valb;
        break;
      case "ban":
        out = vala&valb;
        break;
      case "bor":
        out = vala|valb;
        break;
      case "set":
        out = vala;
        break;
      case "gt":
        out = vala>valb?1:0;
        break;
      case "eq":
        out = vala==valb?1:0;
        break;
      default:
        outReg=-1;
        System.err.println("Op code not found.");
    }
    if (outReg!=-1)
      ret[outReg] = out;


    return ret;
  }

  public static ArrayList<int[][]> readAll(FileInput fi)
  {
    ArrayList<int[][]> ret = new ArrayList<>();
    while (fi.hasMoreTokens())
      ret.add(readSet(fi));
    return ret;
  }
  public static int[][] readSet(FileInput fi)
  {
    int[][] ret = new int[3][];
    for (int i = 0; i<3; i++)
      ret[i] = read4(fi);
    return ret;
  }
  public static int[] read4(FileInput fi)
  {
    int[] ret = new int[4];
    for (int i = 0; i<4; i++)
      ret[i] = fi.readInt();
    return ret;
  }

}
