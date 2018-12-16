package aoc2018.day16;

import chn.util.FileInput;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChronalClassification
{
  public static final String[] opcodes = new String[]{"addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtrr", "gtri", "gtir", "eqrr", "eqri", "eqir"};

  public static void main(String[] args)
  {
    ArrayList<int[][]> input1 = readAllSets(new FileInput("src/main/java/aoc2018/day16/inputPart1Clean"));

    HashMap<Integer, ArrayList<String>> possibleOpcodesMap = new HashMap<>();
    for (int i = 0; i < 16; i++)
      possibleOpcodesMap.put(i, Util.toArrayListString(opcodes));

    int countOverThree = 0;
    for (int[][] data : input1)
    {
      ArrayList<String> possibilitiesFromThisRun = possibilities(data);
      if (possibilitiesFromThisRun.size() >= 3)
        countOverThree++;
      possibilitiesFromThisRun.removeIf(s -> !possibleOpcodesMap.get(data[1][0]).contains(s));
      possibleOpcodesMap.replace(data[1][0], possibilitiesFromThisRun);
    }
    System.out.println("Part 1: " + countOverThree);

    String[] reference = new String[16];
    int opcodesKnown = 0;

    while (opcodesKnown < 16)
      for (int k = 0; k < 16; k++)
        if (possibleOpcodesMap.containsKey(k) && possibleOpcodesMap.get(k).size() == 1 && opcodesKnown++ > -1)
        {
          reference[k] = possibleOpcodesMap.remove(k).get(0);
          for (Integer k2 : possibleOpcodesMap.keySet())
            possibleOpcodesMap.get(k2).remove(reference[k]);
        }

    ArrayList<int[]> input2 = readAll(new FileInput("src/main/java/aoc2018/day16/inputPart2"));
    int[] register = new int[4];
    for (int[] instructionSet : input2)
      register = runOpcode(reference[instructionSet[0]], register, instructionSet);

    System.out.println("Part 2: " + register[0]);
  }

  public static ArrayList<String> possibilities(int[][] dataset)
  {
    ArrayList<String> ret = new ArrayList<String>(16);
    for (String op : opcodes)
    {
      try
      {
        int[] output = runOpcode(op, dataset[0], dataset[1]);
        if (Arrays.equals(output, dataset[2]))
          ret.add(op);
      } catch (ArrayIndexOutOfBoundsException e) {}
    }
    return ret;
  }

  private static int[] runOpcode(String opcode, int[] register, int[] instructions)
  {
    int[] registerFinal = new int[4];
    System.arraycopy(register, 0, registerFinal, 0, 4);

    boolean binary = true;
    if (opcode.startsWith("gt") || opcode.startsWith("eq"))
      binary = false;

    boolean r1 = opcode.startsWith("set") ? opcode.charAt(3) == 'r' : (binary || opcode.charAt(2) == 'r');
    boolean r2 = opcode.charAt(3) == 'r';

    int value_A = r1 ? register[instructions[1]] : instructions[1];
    int value_B = r2 ? register[instructions[2]] : instructions[2];

    opcode = opcode.substring(0, binary ? 3 : 2);
    switch (opcode)
    {
      case "add":
        registerFinal[instructions[3]] = value_A + value_B;
        break;
      case "mul":
        registerFinal[instructions[3]] = value_A * value_B;
        break;
      case "ban":
        registerFinal[instructions[3]] = value_A & value_B;
        break;
      case "bor":
        registerFinal[instructions[3]] = value_A | value_B;
        break;
      case "set":
        registerFinal[instructions[3]] = value_A;
        break;
      case "gt":
        registerFinal[instructions[3]] = value_A > value_B ? 1 : 0;
        break;
      case "eq":
        registerFinal[instructions[3]] = value_A == value_B ? 1 : 0;
        break;
    }
    return registerFinal;
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
    for (int i = 0; i < 3; i++)
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
    for (int i = 0; i < 4; i++)
      ret[i] = fi.readInt();
    return ret;
  }

}
