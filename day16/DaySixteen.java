package aoc2018.day16;

import chn.util.FileInput;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;

public class DaySixteen
{
  public static final String[] opcodes = new String[] {"addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtrr", "gtri", "gtir", "eqrr", "eqri", "eqir"};
  public static void main(String[] args)
  {
    ArrayList<int[][]> info = readAllSets(new FileInput("src/main/java/aoc2018/day16/inputPart1Clean"));

    HashMap<Integer,ArrayList<String>> hm = new HashMap<>();
    for (int i = 0; i<16; i++)
      hm.put(i,Util.toArrayListString(opcodes));

    int ct = 0;
    for (int[][] data : info)
    {
      //System.out.println(++ct+" "+ Util.aTS(possibilities(data).toArray()));
      ArrayList<String> posibs = possibilities(data);
      ArrayList<String> knownPosibs = hm.remove(data[1][0]);
      posibs.removeIf(s -> !knownPosibs.contains(s));
      hm.put(data[1][0], posibs);
      if (posibs.size() >= 3)
        ct++;
    }
    System.out.println("Part 1: " + ct);

    boolean noChange = false;
    while (!noChange)
    {
      noChange = true;
      for (int i = 0; i < 16; i++)
        if (hm.get(i).size() == 1)
          for (int j = 0; j < 16; j++)
            if (i != j)
              if (hm.get(j).remove(hm.get(i).get(0)))
                noChange = false;
    }

    HashMap<Integer, String> reference = new HashMap<>(16);
    for (int i = 0; i<16; i++)
    {
      ArrayList<String> onlyPosib = hm.get(i);
      if (onlyPosib.size() == 1)
      {
        System.out.println("Opcode " + i + " is " + onlyPosib.get(0));
        reference.put(i, onlyPosib.get(0));
      }
    }

    for (int[][] data : info)
      if (!Arrays.equals(data[2],doOp(reference.get(data[1][0]),data[0],data[1])))
      System.out.println("Error w/ op "+data[1][0]);

    ArrayList<int[]> input2 = readAll(new FileInput("src/main/java/aoc2018/day16/inputPart2"));
    int[] regist = new int[4];
    for (int i = 0; i<input2.size(); i++)
    {
      int[] instSet = input2.get(i);
      try
      {
        regist = doOp(reference.get(instSet[0]), regist, instSet);
      } catch (IndexOutOfBoundsException e) {/*e.printStackTrace();*/ System.err.println("Input: "+Util.aTS(regist));System.err.println("Instruct "+i+": ("+reference.get(instSet[0])+") "+Util.aTS(instSet));}
    }

    System.out.println("Part 2: " + Util.aTS(regist));
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

  public static ArrayList<int[][]> readAllSets(FileInput fi)
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
  public static ArrayList<int[]> readAll(FileInput fi)
  {
    ArrayList<int[]> ret = new ArrayList<>();
    while (fi.hasMoreTokens())
      ret.add(read4(fi));
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
