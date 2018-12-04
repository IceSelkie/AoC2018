package aoc2018.day4;

import java.util.HashMap;

public class DayFour
{
  public static final HashMap<Integer, GuardObject> hm = new HashMap();
  public static void main(String[] args)
  {
    dostuff(151, new int[]{40, 49, 57, 58});
    dostuff(2017, new int[]{8, 30, 42, 53});
    dostuff(3539, new int[]{20, 35, 51, 52, 57, 58});
    dostuff(151, new int[]{33, 45});
    dostuff(449, new int[]{10, 26, 54, 59});
    dostuff(1459, new int[]{10, 14});
    dostuff(1459, new int[]{21, 37, 56, 57});
    dostuff(2593, new int[]{12, 58});
    dostuff(1627, new int[]{44, 52});
    dostuff(907, new int[]{25, 58});
    dostuff(1627, new int[]{38, 43, 49, 50, 57, 58});
    dostuff(523, new int[]{38, 49, 52, 53});
    dostuff(2677, new int[]{15, 40});
    dostuff(349, new int[]{14, 36});
    dostuff(3469, new int[]{19, 46});
    dostuff(1459, new int[]{5, 58});
    dostuff(523, new int[]{3, 50});
    dostuff(151, new int[]{28, 56});
    dostuff(907, new int[]{30, 35, 43, 58});
    dostuff(907, new int[]{31, 51, 54, 55});
    dostuff(71, new int[]{10, 22, 32, 33, 38, 53});
    dostuff(3539, new int[]{3, 40});
    dostuff(2677, new int[]{13, 40, 43, 58});
    dostuff(2393, new int[]{32, 44});
    dostuff(1627, new int[]{40, 48, 54, 56});
    dostuff(367, new int[]{40, 46});
    dostuff(409, new int[]{3, 40});
    dostuff(523, new int[]{49, 57});
    dostuff(2087, new int[]{20, 25, 37, 45});
    dostuff(1459, new int[]{25, 28, 46, 54});
    dostuff(367, new int[]{0, 11, 45, 48});
    dostuff(2677, new int[]{17, 46, 53, 58});
    dostuff(1627, new int[]{1, 13, 26, 39});
    dostuff(1459, new int[]{33, 59});
    dostuff(2017, new int[]{2, 59});
    dostuff(3413, new int[]{6, 29, 53, 56});
    dostuff(71, new int[]{8, 11, 20, 52});
    dostuff(2593, new int[]{18, 21, 25, 57});
    dostuff(523, new int[]{5, 16, 19, 38});
    dostuff(907, new int[]{14, 49});
    dostuff(907, new int[]{0, 54});
    dostuff(1151, new int[]{44, 48, 57, 59});
    dostuff(3413, new int[]{0, 8, 28, 58});
    dostuff(2081, new int[]{5, 15, 34, 51});
    dostuff(151, new int[]{19, 54});
    dostuff(1627, new int[]{17, 50});
    dostuff(3413, new int[]{4, 10, 45, 49});
    dostuff(2087, new int[]{19, 52});
    dostuff(3469, new int[]{16, 47, 52, 58});
    dostuff(2393, new int[]{29, 34, 47, 50, 53, 57});
    dostuff(3413, new int[]{5, 32, 42, 50});
    dostuff(523, new int[]{27, 48});
    dostuff(2017, new int[]{8, 18, 50, 56});
    dostuff(2677, new int[]{20, 48});
    dostuff(2593, new int[]{16, 30, 39, 58});
    dostuff(367, new int[]{39, 49, 52, 59});
    dostuff(2677, new int[]{34, 37});
    dostuff(3469, new int[]{17, 40});
    dostuff(907, new int[]{5, 46});
    dostuff(367, new int[]{6, 52});
    dostuff(409, new int[]{1, 24, 49, 52});
    dostuff(3469, new int[]{22, 24, 36, 58});
    dostuff(2017, new int[]{16, 22, 27, 34, 37, 48, 56, 57});
    dostuff(2017, new int[]{3, 36, 46, 49});
    dostuff(2087, new int[]{45, 47, 57, 59});
    dostuff(367, new int[]{20, 57});
    dostuff(907, new int[]{12, 49});
    dostuff(2677, new int[]{23, 54});
    dostuff(3413, new int[]{30, 38, 57, 58});
    dostuff(1459, new int[]{39, 51});
    dostuff(409, new int[]{57, 59});
    dostuff(2677, new int[]{33, 45});
    dostuff(71, new int[]{22, 53});
    dostuff(409, new int[]{4, 14, 25, 50});
    dostuff(151, new int[]{40, 42, 50, 59});
    dostuff(151, new int[]{35, 57});
    dostuff(449, new int[]{33, 46});
    dostuff(2017, new int[]{24, 43});
    dostuff(2017, new int[]{10, 18});
    dostuff(2017, new int[]{14, 38});
    dostuff(3413, new int[]{20, 31, 57, 59});
    dostuff(2087, new int[]{8, 41});
    dostuff(71, new int[]{41, 57});
    dostuff(2393, new int[]{2, 41});
    dostuff(2677, new int[]{53, 57});
    dostuff(2017, new int[]{0, 10, 35, 56});
    dostuff(2081, new int[]{21, 54});
    dostuff(1627, new int[]{7, 8, 33, 41, 45, 54});
    dostuff(409, new int[]{48, 52, 57, 59});
    dostuff(2677, new int[]{6, 43});
    dostuff(2081, new int[]{18, 22, 40, 45});
    dostuff(2593, new int[]{6, 56});
    dostuff(523, new int[]{25, 48});
    dostuff(1459, new int[]{13, 57});
    dostuff(409, new int[]{2, 55});
    dostuff(2393, new int[]{4, 51});
    dostuff(2393, new int[]{29, 43});
    dostuff(449, new int[]{31, 57});
    dostuff(2081, new int[]{35, 44, 50, 53});
    dostuff(151, new int[]{34, 38});
    dostuff(3539, new int[]{2, 30});
    dostuff(2017, new int[]{4, 27, 48, 52});
    dostuff(151, new int[]{7, 9, 12, 28, 32, 37, 55, 57});
    dostuff(1627, new int[]{16, 48, 55, 56});
    dostuff(3469, new int[]{6, 34, 40, 51});
    dostuff(449, new int[]{26, 33, 44, 54});
    dostuff(1151, new int[]{5, 23});
    dostuff(523, new int[]{14, 35});
    dostuff(3413, new int[]{35, 54});
    dostuff(367, new int[]{44, 54});
    dostuff(367, new int[]{18, 30, 39, 50});
    dostuff(71, new int[]{20, 37, 50, 56});
    dostuff(3469, new int[]{29, 59});
    dostuff(367, new int[]{35, 38, 49, 53});
    dostuff(2393, new int[]{31, 36, 57, 58});
    dostuff(2677, new int[]{39, 46, 53, 55});
    dostuff(523, new int[]{4, 13, 21, 59});
    dostuff(2081, new int[]{12, 36, 39, 59});
    dostuff(151, new int[]{10, 39});
    dostuff(907, new int[]{15, 41});
    dostuff(3469, new int[]{6, 27, 43, 45, 52, 53});
    dostuff(2081, new int[]{14, 28, 42, 59});
    dostuff(2677, new int[]{1, 51});
    dostuff(3469, new int[]{20, 33});
    dostuff(2017, new int[]{15, 20, 27, 49, 52, 56});
    dostuff(2393, new int[]{23, 41});
    dostuff(1627, new int[]{57, 59});
    dostuff(151, new int[]{18, 23, 32, 34, 56, 57});
    dostuff(3413, new int[]{2, 46, 52, 58});
    dostuff(907, new int[]{48, 56});
    dostuff(2593, new int[]{16, 42, 53, 59});
    dostuff(1627, new int[]{49, 54});
    dostuff(1151, new int[]{30, 33});
    dostuff(151, new int[]{14, 35, 42, 53});
    dostuff(409, new int[]{53, 55});
    dostuff(1459, new int[]{0, 51});
    dostuff(2081, new int[]{13, 29});
    dostuff(71, new int[]{8, 38, 42, 43, 53, 54});
    dostuff(71, new int[]{27, 28});
    dostuff(2393, new int[]{4, 5});
    dostuff(409, new int[]{50, 57});
    dostuff(409, new int[]{3, 56});
    dostuff(523, new int[]{9, 25, 29, 58});
    dostuff(3413, new int[]{1, 23, 38, 57});
    dostuff(2393, new int[]{11, 19, 22, 35});
    dostuff(2393, new int[]{0, 45});
    dostuff(409, new int[]{48, 54});
    dostuff(367, new int[]{8, 37});
    dostuff(3469, new int[]{22, 58});
    dostuff(409, new int[]{27, 45});
    dostuff(409, new int[]{2, 53});
    dostuff(523, new int[]{31, 44});
    dostuff(1459, new int[]{3, 14, 33, 43});
    dostuff(151, new int[]{18, 19, 33, 57});
    dostuff(367, new int[]{5, 28, 41, 56});
    dostuff(1459, new int[]{35, 39, 43, 55});
    dostuff(2593, new int[]{16, 33});
    dostuff(2677, new int[]{19, 50});
    dostuff(3413, new int[]{14, 33, 36, 39, 46, 47});
    dostuff(1459, new int[]{28, 41});
    dostuff(367, new int[]{13, 36, 49, 58});
    dostuff(523, new int[]{3, 54});
    dostuff(71, new int[]{33, 38, 46, 49, 54, 56});
    dostuff(71, new int[]{7, 59});
    dostuff(3539, new int[]{16, 36, 42, 44});
    dostuff(2081, new int[]{26, 31, 39, 53});
    dostuff(151, new int[]{3, 49});
    dostuff(3469, new int[]{11, 23, 29, 49});
    dostuff(907, new int[]{22, 52});
    dostuff(2393, new int[]{4, 8, 23, 47});
    dostuff(1627, new int[]{27, 29, 42, 43});
    dostuff(349, new int[]{19, 44, 51, 57});
    dostuff(2393, new int[]{7, 41});
    dostuff(907, new int[]{14, 46, 53, 55});
    dostuff(2593, new int[]{32, 54});
    dostuff(2087, new int[]{20, 48, 53, 59});
    dostuff(449, new int[]{28, 46});
    dostuff(1627, new int[]{29, 59});
    dostuff(367, new int[]{51, 59});
    dostuff(2017, new int[]{27, 58});
    dostuff(409, new int[]{36, 46});
    dostuff(907, new int[]{7, 55});
    dostuff(907, new int[]{43, 56});
    dostuff(2593, new int[]{26, 55});
    dostuff(2017, new int[]{25, 54});
    dostuff(367, new int[]{6, 22, 48, 51});
    dostuff(449, new int[]{29, 55});
    dostuff(3539, new int[]{2, 9, 49, 56});
    dostuff(409, new int[]{38, 42});
    dostuff(349, new int[]{18, 32, 41, 54});
    dostuff(2593, new int[]{21, 58});
    dostuff(2393, new int[]{25, 27, 30, 35});
    dostuff(2081, new int[]{34, 44, 50, 51});
    dostuff(367, new int[]{16, 57});
    dostuff(3413, new int[]{17, 54});
    dostuff(2393, new int[]{20, 29});
    dostuff(1627, new int[]{10, 27, 31, 43});
    dostuff(1627, new int[]{31, 52});
    dostuff(523, new int[]{22, 25, 32, 42});
    dostuff(2677, new int[]{7, 29, 34, 57});
    dostuff(409, new int[]{46, 58});
    dostuff(349, new int[]{27, 29, 37, 51});
    dostuff(523, new int[]{13, 41, 49, 51});
    dostuff(1151, new int[]{16, 56});
    dostuff(907, new int[]{2, 35, 38, 55});
    dostuff(71, new int[]{27, 57});
    dostuff(907, new int[]{21, 23, 42, 57});
    dostuff(2393, new int[]{32, 37, 41, 42, 50, 59});
    dostuff(2393, new int[]{29, 35});
    dostuff(907, new int[]{4, 24});
    dostuff(71, new int[]{14, 56});
    dostuff(907, new int[]{8, 14, 40, 48, 51, 54});
    dostuff(3413, new int[]{23, 29, 53, 55});
    dostuff(409, new int[]{36, 37, 41, 45});
    dostuff(2393, new int[]{26, 51});
    dostuff(2593, new int[]{18, 31});
    dostuff(151, new int[]{34, 48});
    dostuff(2081, new int[]{6, 37, 42, 57});
    dostuff(1151, new int[]{0, 13, 17, 50});
    dostuff(151, new int[]{42, 55});
    dostuff(1459, new int[]{26, 47, 56, 57});
    dostuff(349, new int[]{39, 48, 56, 58});
    dostuff(151, new int[]{13, 40});
    dostuff(523, new int[]{15, 19, 49, 51});
    dostuff(2393, new int[]{11, 43});
    dostuff(2393, new int[]{30, 35, 48, 51});
    dostuff(349, new int[]{36, 46, 53, 57});
    dostuff(3413, new int[]{3, 4, 25, 32, 46, 59});
    dostuff(449, new int[]{6, 52});
    dostuff(367, new int[]{15, 49});
    dostuff(1459, new int[]{26, 31, 37, 53});
    dostuff(449, new int[]{29, 58});
    dostuff(349, new int[]{34, 43, 52, 56});
    dostuff(2081, new int[]{7, 20, 26, 37});
    dostuff(2593, new int[]{2, 48});
    dostuff(1151, new int[]{18, 28, 37, 59});
    dostuff(2393, new int[]{28, 33, 36, 58});
    dostuff(2087, new int[]{13, 51});
    dostuff(2593, new int[]{25, 43});
    dostuff(409, new int[]{26, 52});
    dostuff(349, new int[]{16, 52});
    dostuff(1627, new int[]{21, 35});
    dostuff(3539, new int[]{20, 37});
    dostuff(71, new int[]{17, 53});
    dostuff(1627, new int[]{20, 52, 55, 59});
    dostuff(367, new int[]{9, 14, 30, 58});
    dostuff(349, new int[]{15, 57});
    dostuff(3413, new int[]{7, 8, 11, 47});
    dostuff(409, new int[]{35, 59});
    dostuff(523, new int[]{5, 27, 49, 52});
    dostuff(349, new int[]{18, 59});
    dostuff(1459, new int[]{22, 32, 42, 47, 51, 55});
    dostuff(3469, new int[]{48, 57});
    dostuff(2677, new int[]{31, 38, 51, 52});
    dostuff(2087, new int[]{38, 43});
    dostuff(2087, new int[]{34, 36});
    dostuff(2087, new int[]{18, 24});
    dostuff(367, new int[]{29, 41, 48, 55});
    dostuff(151, new int[]{26, 34});
    dostuff(449, new int[]{15, 45});
    dostuff(409, new int[]{30, 44, 51, 56});
    dostuff(409, new int[]{7, 53});
    dostuff(1151, new int[]{38, 42, 45, 56});
    dostuff(1459, new int[]{40, 41, 48, 53});
    dostuff(2593, new int[]{22, 56});
    dostuff(449, new int[]{44, 45});
    dostuff(523, new int[]{9, 42});
    dostuff(71, new int[]{37, 58});
    dostuff(349, new int[]{29, 57});
    dostuff(2677, new int[]{19, 22});
    dostuff(2087, new int[]{23, 40});
    dostuff(409, new int[]{5, 54});
    dostuff(367, new int[]{19, 53});
    dostuff(2593, new int[]{9, 36, 57, 58});
    dostuff(2393, new int[]{19, 54});
    dostuff(3539, new int[]{29, 38});
    dostuff(449, new int[]{8, 43, 46, 51});
    dostuff(907, new int[]{41, 42});
    dostuff(409, new int[]{28, 57});
    dostuff(1627, new int[]{1, 8, 12, 47, 53, 56});
    dostuff(2393, new int[]{10, 34});
    dostuff(2393, new int[]{4, 38});
    dostuff(2081, new int[]{41, 42});
    dostuff(2593, new int[]{12, 37, 42, 44});
    dostuff(2593, new int[]{30, 37});
    dostuff(409, new int[]{16, 32, 50, 58});
    dostuff(151, new int[]{1, 44, 52, 55});

    int maxSleep = 0;
    int guardID = 0;
    for (int k : hm.keySet())
    {
      GuardObject o = hm.get(k);
      if (o.timeSlept > maxSleep)
      {
        maxSleep = o.timeSlept;
        guardID = k;
      }
    }
    GuardObject o = hm.get(guardID);
    System.out.println("Guard #" + guardID + " has slept for: " + maxSleep);
    int maxSleepInTime = 0;
    int time = 0;
    //System.out.println(Util.aTS(o.freqArray));
    for (int i = 0; i < 60; i++)
    {
      if (maxSleepInTime < o.freqArray[i])
      {
        maxSleepInTime = o.freqArray[i];
        time = i;
      }
    }
    System.out.println("Guard slept " + maxSleepInTime + " during " + time);
    System.out.println("Part 1: " + (time * guardID));

    //    guardID;
    //    maxSleepInTime
    for (int k : hm.keySet())
    {
      o = hm.get(k);
      for (int i = 0; i < 60; i++)
        if (maxSleepInTime < o.freqArray[i])
        {
          maxSleepInTime = o.freqArray[i];
          time = i;
          guardID = k;
        }
    }
    System.out.println();
    System.out.println("Guard #" + guardID + " has slept for: " + o.timeSlept);
    System.out.println("Guard slept " + maxSleepInTime + " during " + time);
    System.out.println("Part 2: " + (time * guardID));

  }

  public static class GuardObject
  {
    int timeSlept = 0;
    int[] freqArray = new int[60];
  }
  public static void dostuff(int a, int[] b)
  {
    if (!hm.containsKey(a))
      hm.put(a,new GuardObject());
    for (int i = 1; i<b.length; i+=2)
    {
      hm.get(a).timeSlept+=b[i]-b[i-1];
      for (int j = b[i-1]; j<b[i]; j++)
        hm.get(a).freqArray[j]++;
    }
  }
//  public static int sleepTimeInShift(int[] shift)
//  {
//    int[] shift2 = new int[shift.length - 1];
//    System.arraycopy(shift, 1, shift2, 0, shift2.length);
//    Arrays.sort(shift2);
//    int shiftSleep = 0;
//    for (int i = 1; i < shift2.length; i += 2)
//    {
//      shiftSleep += shift2[i] - shift2[i - 1];
//    }
//    return shiftSleep;
//  }
//  public static boolean isSleepAtTimeInShift(int[] shift, int time)
//  {
//    int[] shift2 = new int[shift.length - 1];
//    System.arraycopy(shift, 1, shift2, 0, shift2.length);
//    Arrays.sort(shift2);
//    for (int i = 1; i < shift2.length; i += 2)
//    {
//      if (shift2[i - 1] <= time && shift2[i - 1] > time) return true;
//    }
//    return false;
//  }
}
