package aoc2018.day24;

import hyec.util.Pair;
import util.Util;

public class Group
{
  int num;
  int hp;
  char[] immunities;
  char[] weaknesses;
  Pair<Integer, Character> attackType;
  int initiative;

  public Group(int num, int hp, char[] immunities, char[] weaknesses, Pair<Integer, Character> attackType, int initiative)
  {
    this.num = num;
    this.hp = hp;
    this.immunities = immunities;
    this.weaknesses = weaknesses;
    this.attackType = attackType;
    this.initiative = initiative;
  }

  public int getEffectivePower()
  {
    return num * attackType.a;
  }
  public int getInitiative()
  {
    return initiative;
  }

  public int getDamage(char type)
  {
    if (Util.contains(immunities, type))
      return 0;
    if (Util.contains(weaknesses, type))
      return 2;
    return 1;
  }

  public int attack(Group defender)
  {
    return defender.takeDamage(num*attackType.a*defender.getDamage(attackType.b));
  }

  private int takeDamage(int i)
  {
    num -= i/hp;
    return i/hp;
  }
}