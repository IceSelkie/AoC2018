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

    int maxsize = -1;
    int[][] map, last = null;
    for (int size = 0; size < 300; size++)
    {
      map = new int[301 - size][301 - size];
      for (int i = 1; i <= 300 - size; i++)
        for (int j = 1; j <= 300 - size; j++)
        {
          int val = 0;
          if (last==null)
          {
            for (int x = 0; x <= size; x++)
              for (int y = 0; y <= size; y++)
                val += getPower(i + x, j + y);
            map[i][j] = val;
            if (val > maxVal)
            {
              maxVal = val;
              maxx = i;
              maxy = j;
              maxsize = size;
            }
          }
          else
          {
            val = last[i][j];
            for (int x = 0; x <= size; x++)
              val += getPower(i+size, j+x) + getPower(i+x, j+size);
            val -= getPower(i+size,j+size);
            map[i][j] = val;
            if (val > maxVal)
            {
              maxVal = val;
              maxx = i;
              maxy = j;
              maxsize = size;
            }
          }
        }
      last = map;
    }

    System.out.println("Part 2: " + maxx + "," + maxy + "," + (maxsize+1));
  }

  private static int[] findMax(int[][][] values)
  {
    int maxValue = Integer.MIN_VALUE;
    int[] ret = new int[]{-1, -1, -1};
    for (int i = 0; i < 301; i++)
      for (int j = 0; j < 301; j++)
        for (int k = 0; k < 301; k++)
          if (values[i][j][k] > maxValue)
          {
            maxValue = values[i][j][k];
            ret = new int[]{i, j, k};
          }
    return ret;
  }

  private static int getPower(int x, int y)
  {
    return (((x+10)*y+SERIALNUMBER)*(x+10))/100%10-5;
  }
}
