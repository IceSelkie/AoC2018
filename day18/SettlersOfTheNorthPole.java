package aoc2018.day18;

import util.Util;

public class SettlersOfTheNorthPole
{
  public static void main(String[] args)
  {
    char[][] land = Util.readAllChars("src/main/java/aoc2018/day18/input");

    for (int i = 0; i<10; i++)
      land = tick(land);
    display(land);
    System.out.println("Part 1: " + (count(land, '|')*count(land, '#')));

    for (int i = 10; i<1000000000; i++)
    {
      land = tick(land);
      if (i==999) // 1000 are done, and it repeats every 7000, so this will fall on 1bil.
      {
        display(land);
        System.out.println("Part 2: " + (count(land, '|') * count(land, '#')));
        break;
      }
    }
  }

  private static int count(char[][] land, char find)
  {
    int ret = 0;
    for (char[] row : land)
      for (char c : row)
        if (find == c)
          ret++;
    return ret;
  }

  private static void display(char[][] land)
  {
    for (int y = 0; y < land.length; y++)
    {
      for (int x = 0; x<land[0].length; x++)
        System.out.print(land[y][x]);
      System.out.println();
    }
    System.out.println();
  }

  private static char[][] tick(char[][] land)
  {
    char[][] ret = new char[land.length][land[0].length];

    for (int i = 0; i < ret.length; i++)
      for (int j = 0; j < ret[0].length; j++)
      {
        int tre = 0, lum = 0, ope = 0;
        for (int k = -1; k <= 1; k++)
          for (int l = -1; l <= 1; l++)
            if ((k != 0 || l != 0) && ((i + k) >= 0 && i + k < ret.length && (j + l) >= 0 && j + l < ret[0].length))
              switch (land[i + k][j + l])
              {
                case '.':
                  ope++;
                  break;
                case '#':
                  lum++;
                  break;
                case '|':
                  tre++;
                  break;
              }
        if (land[i][j] == '.' && tre >= 3)
          ret[i][j] = '|';
        else if (land[i][j] == '|' && lum >= 3)
          ret[i][j] = '#';
        else if (land[i][j] == '#' && lum >= 1 && tre>=1)
          ret[i][j] = '#';
        else if (land[i][j] == '#')
          ret[i][j] = '.';
        else
          ret[i][j] = land[i][j];
      }
    return ret;
  }
}
