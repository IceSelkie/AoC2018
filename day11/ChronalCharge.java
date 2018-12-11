package aoc2018.day11;

public class ChronalCharge
{
  public static final int SERIALNUMBER = 8141;
  public static void main(String[] args)
  {
    int maxx = -1, maxy = -1, maxVal = -45;
    for (int i = 2; i <= 299; i++)
      for (int j = 2; j <= 299; j++)
      {
        int val = 0;
        for (int x = -1; x <= 1; x++)
          for (int y = -1; y <= 1; y++)
            val += getPower(i + x, j + y);
        if (val > maxVal)
        {
          maxVal = val;
          maxx = i - 1;
          maxy = j - 1;
        }
      }
    System.out.println("Part 1: " + maxx + "," + maxy);
  }

  private static int getPower(int x, int y)
  {
    int ret = x+10;
    ret*=y;
    ret+=SERIALNUMBER;
    ret*=x+10;
    ret/=100;
    ret%=10;
    ret-=5;
    return ret;
  }
}
