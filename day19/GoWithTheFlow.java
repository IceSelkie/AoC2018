package aoc2018.day19;

import aoc2018.day16.ChronalClassification;
import chn.util.FileInput;
import hyec.util.Pair;
import util.Util;

import java.util.ArrayList;

public class GoWithTheFlow
{
  public static final int SIZE = 6;

  public static ArrayList<Pair<String,long[]>> instructions;
  public static int ip;
  public static int ipBind;
  public static long[] registers = new long[]{0,0,0,0,0,0};//{0, 1, 10551270, 10551270, 4, 10551276};
  //blic static long[] registers = new long[]{0, 1, 224815, 224815, 4, 10551276};

  public static void main(String[] args)
  {
    readInput("src/main/java/aoc2018/day19/input");

    //ip = 4;
    //registers = new long[]{1, 7, 1, 1, 3, 101*23};
    //registers = new long[]{1, 501, 10551277, 1, 13, 10551276};
    System.out.println(ip+" "+Util.aTS(registers));

    long last0 = 1;
    long[] rprev = new long[1];
    while (ip>=0&&ip<instructions.size())
    {
      run(false);
      if (registers[5]==10551276)
        registers[5] = 16*27;
      if (last0!=registers[0])
        System.out.println((last0=registers[0])+" " + Util.aTS(rprev));
      rprev = registers;
      //try{Thread.sleep(1);}catch(Exception e){}
    }


    System.out.println(Util.aTS(registers));
  }

  protected static boolean run( boolean debug)
  {
    registers[ipBind] = ip;
    if (debug) System.out.print(ip + " " + instructions.get(ip).a + " " + Util.aTS(instructions.get(ip).b) + " "+ Util.aTS(registers) + " ");
    registers = runOpcode(instructions.get(ip).a,registers, instructions.get(ip).b);
    ip = (int)registers[ipBind];
    if (debug) System.out.println(Util.aTS(registers));
    ip++;
    // dump();
    return true;
  }

  protected static void readInput(String fileName)
  {
    FileInput fi = new FileInput(fileName);
    String line = fi.readLine();
    ipBind = new Integer(line.substring(4));
    ip = 0;
    instructions = new ArrayList<>();
    while (fi.hasMoreLines())
    {
      String s = fi.readToken();
      long[] data = new long[]{fi.readInt(),fi.readInt(),fi.readInt()};
      instructions.add(new Pair<>(s,data));
    }
  }


  public static long[] runOpcode(String opcode, long[] register, long[] instructions)
  {
    long[] registerFinal = new long[SIZE];
    System.arraycopy(register, 0, registerFinal, 0, SIZE);

    boolean binary = true;
    if (opcode.startsWith("gt") || opcode.startsWith("eq"))
      binary = false;

    boolean r1 = opcode.startsWith("set") ? opcode.charAt(3) == 'r' : (binary || opcode.charAt(2) == 'r');
    boolean r2 = opcode.charAt(3) == 'r';

    long value_A = r1 ? register[(int)instructions[0]] : instructions[0];
    long value_B = r2 ? register[(int)instructions[1]] : instructions[1];

    opcode = opcode.substring(0, binary ? 3 : 2);
    switch (opcode)
    {
      case "add":
        registerFinal[(int)instructions[2]] = value_A + value_B;
        break;
      case "mul":
        registerFinal[(int)instructions[2]] = value_A * value_B;
        break;
      case "ban":
        registerFinal[(int)instructions[2]] = value_A & value_B;
        break;
      case "bor":
        registerFinal[(int)instructions[2]] = value_A | value_B;
        break;
      case "set":
        registerFinal[(int)instructions[2]] = value_A;
        break;
      case "gt":
        registerFinal[(int)instructions[2]] = value_A > value_B ? 1 : 0;
        break;
      case "eq":
        registerFinal[(int)instructions[2]] = value_A == value_B ? 1 : 0;
        break;
    }
    return registerFinal;
  }

  public static void dump()
  {
    System.out.println(ip+" "+Util.aTS(registers));
  }
}
