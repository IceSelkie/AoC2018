package aoc2018.day24;

import hyec.util.Pair;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ImmuneSystemSimulator20XX
{
  public static ArrayList<Group> immune, infect, mo, no;
  public static final char[] NONE = new char[0];
  public static final boolean displayAttack = false;

  public static void main(String[] args)
  {
    readInput(false);
    simulate();
    System.out.println("Part 1: " + unitsLeft());
    for (int i = 0; i<5000; i++)
    {
      readInput(false);
      boost(i);
      if (simulate())
        System.out.println("Part 2: " + i + " failed.");
      System.out.println("Part 2: " + i + " "+ unitsLeftImmune());
    }
    // Example: 782+4434=5216
    // Mine:    785+4445=5230
    // My New:  778+4434=5212
  }

  private static void boost(int i)
  {
    for (Group g : immune)
      g.attackType.a+=i;
  }

  private static boolean simulate()
  {
    boolean infiloop = false;
    while (immune.size() > 0 && infect.size() > 0 && !infiloop)
    {
      HashMap<Group, Group> targets = targetSelection();
      //display(targets);
      infiloop = attack(targets);
    }
    return infiloop;
  }

  private static boolean attack(HashMap<Group, Group> targets)
  {
    boolean infiloop = true;
    ArrayList<Group> attackQueue = sortInitiative(targets.keySet());
    while (attackQueue.size()>0)
    {
      Group attacker = attackQueue.remove(attackQueue.size()-1);
      Group defender = targets.get(attacker);

      if (displayAttack)
      {
        boolean aInIm = immune.contains(attacker);
        System.out.println((mo.indexOf(aInIm ? attacker : defender) + 1) + (aInIm ? "\t->\t" : "\t<-\t") + (no.indexOf(aInIm ? defender : attacker) + 1) + "\tKilling " + min(defender.num, attacker.num * attacker.attackType.a * defender.getDamage(attacker.attackType.b) / defender.hp));
      }

      int numKilled = attacker.attack(defender);
      if (defender.num<=0)
      {
        targets.remove(defender);
        attackQueue.remove(defender);
        immune.remove(defender);
        infect.remove(defender);
      }
      if (numKilled>0)
        infiloop = false;
    }
    if (displayAttack) System.out.println();
    return infiloop;
  }

  private static HashMap<Group, Group> targetSelection()
  {
    HashMap<Group, Group> hm = new HashMap<>();

    for (int i = 0; i < 2; i++)
    {
      ArrayList<Group> att = i == 0 ? sortEffectivePowerInitiative(immune) : sortEffectivePowerInitiative(infect), def = i == 0 ? sortEffectivePowerInitiative(infect) : sortEffectivePowerInitiative(immune);

      while (att.size() > 0)
      {
        Group attr = att.remove(att.size()-1);
        int defri = highestDamageInitiative(def, attr.attackType.b);
        if (defri == -1)
          continue;
        Group defr = def.remove(defri);
        hm.put(attr, defr);
      }
    }
    return hm;
  }

  private static int highestDamageInitiative(ArrayList<Group> defenders, Character attackType)
  {
    int highest = -1;
    for (int i = defenders.size()-1; i >=0; i--)
      highest = max(highest, defenders.get(i).getDamage(attackType));
    if (highest == -1 || highest == 0)
      return -1;
    for (int i = defenders.size()-1; i >=0; i--)
      if (defenders.get(i).getDamage(attackType) == highest)
        return i;
    throw new IllegalStateException();
  }

  private static void readInput(boolean debug)
  {
    immune = new ArrayList<Group>();
    infect = new ArrayList<Group>();
    if (debug)
    {
      immune.add(new Group(17, 5390, NONE, a('r','b'), p(4507, 'f'), 2));
      immune.add(new Group(989, 1274, a('f'), a('b','s'), p(25, 's'), 3));

      infect.add(new Group(801, 4706, NONE, a('r'), p(116, 'b'), 1));
      infect.add(new Group(4485, 2961, a('r'), a('f', 'c'), p(12, 's'), 4));
    }
    else
    {
      immune.add(new Group(790, 3941, NONE, NONE, p(48, 'b'), 5));
      immune.add(new Group(624, 2987, NONE, NONE, p(46, 'b'), 16));
      immune.add(new Group(5724, 9633, a('b', 's', 'f'), NONE, p(16, 's'), 9));
      immune.add(new Group(1033, 10664, NONE, NONE, p(89, 's'), 1));
      immune.add(new Group(6691, 9773, NONE, a('s'), p(13, 'b'), 12));
      immune.add(new Group(325, 11916, NONE, a('b'), p(276, 's'), 8));
      immune.add(new Group(1517, 6424, NONE, NONE, p(35, 'b'), 13));
      immune.add(new Group(1368, 9039, a('b'), NONE, p(53, 's'), 4));
      immune.add(new Group(3712, 5377, a('c', 'r'), a('f'), p(14, 's'), 14));
      immune.add(new Group(3165, 8703, NONE, a('s', 'b'), p(26, 'r'), 11));

      infect.add(new Group(1113, 44169, a('b'), a('r'), p(57, 'f'), 7));
      infect.add(new Group(3949, 20615, NONE, a('r', 'c'), p(9, 'b'), 6));
      infect.add(new Group(602, 35167, a('b', 'c'), a('f'), p(93, 'r'), 20));
      infect.add(new Group(1209, 34572, NONE, NONE, p(55, 'b'), 3));
      infect.add(new Group(902, 12983, a('f'), NONE, p(28, 'f'), 19));
      infect.add(new Group(1132, 51353, NONE, NONE, p(66, 'r'), 15));
      infect.add(new Group(7966, 49894, a('b'), NONE, p(9, 'c'), 10));
      infect.add(new Group(3471, 18326, NONE, a('r'), p(8, 'f'), 18));
      infect.add(new Group(110, 38473, a('f'), a('b'), p(640, 's'), 2));
      infect.add(new Group(713, 42679, NONE, a('s'), p(102, 'b'), 17));
    }
    mo = (ArrayList<Group>)immune.clone();
    no = (ArrayList<Group>)infect.clone();
  }

  private static int unitsLeft()
  {
    int ret = 0;
    for (Group g : immune)
      ret += g.num;
    for (Group g : infect)
      ret += g.num;
    return ret;
  }
  private static int unitsLeftImmune()
  {
    int ret = 0;
    for (Group g : immune)
      ret += g.num;
    return ret;
  }

  private static Pair<Integer, Character> p(int i, char c) { return new Pair<>(i, c); }

  private static char[] a(char... cs) { return cs; }

  private static ArrayList<Group> sortEffectivePower(Collection<Group> c)
  {
    ArrayList<Group> ret = new ArrayList<>(c);
    ret.sort(Comparator.comparingInt(Group::getEffectivePower));
    return ret;
  }

  private static ArrayList<Group> sortEffectivePowerInitiative(Collection<Group> c)
  {
    ArrayList<Group> ret = new ArrayList<>(c);
    ret.sort(Comparator.comparingInt(o -> (o.getEffectivePower() * 50 + o.getInitiative())));
    return ret;
  }

  private static ArrayList<Group> sortInitiative(Collection<Group> c)
  {
    ArrayList<Group> ret = new ArrayList<>(c);
    ret.sort(Comparator.comparingInt(Group::getInitiative));
    return ret;
  }
}
