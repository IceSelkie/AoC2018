package aoc2018.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class InventoryManagementSystem
{
  public static final String[] inputs = {"ayitmcjvlhedbsyoqfzukjpxwt", "agirmcjvlheybsyogfzuknpxxt", "wgirmcjvlvedbsyoqfzujnpxwt", "agizmcjvlhedbsyoqfzuenlxwt", "aryrmcjvlheebsyoqfzuknpxwt", "agirmcjelhedbsyoqfzukosxwt", "azirmcjvlhedbsooqfzuknpxvt", "agirmcjvffedbsyoqfzudnpxwt", "agilmcjvlhedbsyrqfzuknpxrt", "agirmcjvlhndbsyoofzukcpxwt", "awirmcjvlhedbsyoqfzuknpxlz", "aghrmcjmlhewbsyoqfzuknpxwt", "apirmcjvlmedbsyoqfzcknpxwt", "jgiricjvlhedbsyrqfzuknpxwt", "abirmcjvlbedbsyoqfzuknpxwo", "agirmcjvlhedbsyojfzuknpgkt", "agicmclvlhedbmyoqfzuknpxwt", "aslrzcjvlhedbsyoqfzuknpxwt", "agiqmcjvlhedbsymqfzurnpxwt", "agirmcjvlwedbsyoqfzuknfxmt", "agiumcjvlhedbsyoqfzuknpbyt", "xgirxcjvlwedbsyoqfzuknpxwt", "bgtrvcjvlhedbsyoqfzuknpxwt", "afirmcjvlpedbsyoqvzuknpxwt", "agirmcjjvhedbsyoqfzukmpxwt", "ggirmcjvlhedbsyoqfzukypxvt", "agirmdjulhekbsyoqfzuknpxwt", "agirmcjqlhedbsyoqfztknixwt", "agirmcjvjhedbsyomfduknpxwt", "agirmcjvlhedbgyoqfzuknpxtq", "agirmvjvlhbdbsyfqfzuknpxwt", "agirmcjvlhedbsyatfzbknpxwt", "agirmcjvlrlybsyoqfzuknpxwt", "agirmajvlhedbsqovfzuknpxwt", "abinmcrvlhedbsyoqfzuknpxwt", "agnrmcjvlhedbsyurfzuknpxwt", "agirmpjvlhedbsyoqezuknpxct", "agirmxjvlhedbsgoqjzuknpxwt", "agirmcjvlhehbstoqfzuknpxht", "qgirmcjvlhepcsyoqfzuknpxwt", "tgirmcjvlhkdbsyoqszuknpxwt", "agirmcjvdhedbscoqftuknpxwt", "agbrmcjvlhedbsyoqfzukqpxwj", "agurmcjvlhedbsyaqfzuknpxmt", "agirmcjvohudbsyoqfmuknpxwt", "agirmcjvlhekbsyoqfbuktpxwt", "agirmcjvlhedhsyoqfzugnnxwt", "agirmcjvlhedbsyjqyzuknpxft", "agirmcjvlhedbsymufznknpxwt", "agirmcjhlheubsyoqfzuknpxmt", "agirmcjvlhwdbsywqfzwknpxwt", "agirmcjvljedbsgqqfzuknpxwt", "aglrmcjelhedbsyoqfzuknpxkt", "agxrmcjvlhxdbsyoqfquknpxwt", "agirmcjvnhedbsyoqfzuenfxwt", "agirmcjvlhedbsyoqfzatnqxwt", "agirmcvvlhedbsboqfzuknuxwt", "agirncjvlhezbsyoqfzulnpxwt", "agiamcjvdiedbsyoqfzuknpxwt", "agirmcjvwhedbskoqfzhknpxwt", "agiwmcjflhedbsyoqfzulnpxwt", "agirmcjvlhedboyoqfzuknpjwl", "agivmcjslhedbsyoqfzdknpxwt", "agirmcjvlcedbsyoqfzukepxyt", "akirmcjvlhjdbssoqfzuknpxwt", "agvrmcjvldedmsyoqfzuknpxwt", "agirecjvlhidbsyoqfzukbpxwt", "abirmcjvlhjdbsyoqfkuknpxwt", "agirmcjelhedbfyoqfzuknpxwj", "agirmcjvlhedbbyoqrzukwpxwt", "akirmcjvlhedbsyoyfzuknplwt", "agirmcjvlhedbsydsfzuknpxwq", "agirrcjvlhedbsyoqazuknpmwt", "aeirmcjvlhedbsyoqfvuknpwwt", "akirmcjvlhedbsyoqpzudnpxwt", "agijmcjvlhedbsyuqfzunnpxwt", "agirmcjilhedasyoqizuknpxwt", "agirmczvlhzdbsyoqfzuknpxwx", "agirmcjvlhehbsyoifzuknpxwo", "agirwcjvlhedbsyoqfzuenpxst", "agirmcjvlhedbsyoquzuknhxft", "agirmcqvlkedbsyoqfzrknpxwt", "agirmcqvlhenbsyoqfzuknpuwt", "agirmcjvleedbsyoqfzhhnpxwt", "agirmcjvlhembsyrqfauknpxwt", "agirmcjvlhedbssoqflcknpxwt", "aqirmcjvlnedbsyoqfzuknpxpt", "agirmcjqlhedbxpoqfzuknpxwt", "fgirmcjvlhedbsyoqfzukqpqwt", "aggrmcjvlhpdbsyoqfzuknpxjt", "agirmwjvlhedbsywqfzuknpzwt", "agirmcailhembsyoqfzuknpxwt", "aglrmcjvlhxdbsyoqfzuknpxet", "xgirmcjvlhzdbsyoqfzukrpxwt", "agvrmcjvuhedbsyoqfzuknpxgt", "agikmcjvlhecbsyoqfzuknpxwr", "agyrmcjvlhezbsyoqfouknpxwt", "agirmcjvfhjdbsyokfzuknpxwt", "agkrmjjvlhedtsyoqfzuknpxwt", "agirmgjvlhedbiyoqfzuknpxwv", "wcirmcjvlhedbsyoqfzuknpxwo", "aairmcjvlhedbstoqfguknpxwt", "hgirmcjvlhedwfyoqfzuknpxwt", "agirmcjvmhfdbmyoqfzuknpxwt", "agirmcjvlhvdbsioqfzuanpxwt", "agrrmcjvgsedbsyoqfzuknpxwt", "agirmcjvlqetbsysqfzuknpxwt", "agirccjvlhedbsyoqfzuknkcwt", "agirmqjvlhedbsdoqfzkknpxwt", "agirmcjvlheobsyopfzuknpxwg", "agirmcjolhedbsyofpzuknpxwt", "agirmcjnlhedbsyoqkzukfpxwt", "agiumcjvlheabsyoqfzuknpxbt", "agipmcjvlhedbsyoqfzukupxwz", "atirmcrvlhedbsyoqfnuknpxwt", "agirmcjvnhedfkyoqfzuknpxwt", "agirmrjvlhedboyoqfzvknpxwt", "abhrmcjvlhedbtyoqfzuknpxwt", "cbirmcjvlhedbfyoqfzuknpxwt", "agirmcjvlhedbsyoqfmwknjxwt", "ahirmcjvlhedbsloqfzuknpfwt", "agarmjjvlhedbsyoqfzyknpxwt", "ajirmcjvlhevjsyoqfzuknpxwt", "agirmcjvlhpdbstoqfzuknpewt", "agirmcsvlhedbsyoqfbupnpxwt", "agirmcjvlhexbsyodfzukqpxwt", "auiymcjblhedbsyoqfzuknpxwt", "azirmcjvchedbsyoqfziknpxwt", "aeirmcjvlhedvsyoqfzuonpxwt", "agirmcjvlhedbfyoqfbukjpxwt", "ygirmcjvlhidbsyoqfzukncxwt", "agirmxpvlhedbsyoqffuknpxwt", "ztirmcjvlhedosyoqfzuknpxwt", "agirmcjvlhepbsyoqfzuenppwt", "agirmcjvshedbsyoqnzaknpxwt", "awirmcjvlhydbsyoqfzuknoxwt", "ucirmcjvlhedbsyoqfjuknpxwt", "agirmwjvlhkbbsyoqfzuknpxwt", "agirmcjvldedbsyohfzuknpxzt", "agirmcjvwhedbsyoqfznknpxgt", "agiricjvlhedxqyoqfzuknpxwt", "agirmcjvlhzdbjyoqfzukapxwt", "agirmcgvlhedbsyoqfzuknaowt", "agidmcjvlhedbsyoqayuknpxwt", "agirmcjvlhedisnoqfzuknpxnt", "wkjrmcjvlhedbsyoqfzuknpxwt", "agirmcjvlhedbuyojfzukxpxwt", "agkrmcjvlhedbsybqfzurnpxwt", "agirmcjvghedbsyoqfzuknexwj", "agirmcjvnhedbsyoqfzuznpxit", "agirmcjvlbedbsyoqfiukwpxwt", "agirlctvlheabsyoqfzuknpxwt", "agirmcjzzhedbsyoqfzcknpxwt", "akirmcjvlnedbsyoqfzlknpxwt", "agirmdjvlhedpsyoqfzuknpjwt", "agiyjcuvlhedbsyoqfzuknpxwt", "agirmcbvltedysyoqfzuknpxwt", "agirmcjvlhedfdyoqfzubnpxwt", "agidmcjvlhedesfoqfzuknpxwt", "aeirmcjvlhedqsyoqfxuknpxwt", "agifmcjvlhedbsyoqfquknptwt", "agidmcjvlhedbsyfqfzuknpxwb", "agirvcjvlhedbsroqfzuknjxwt", "agirmcqvlhddbsyoqfzuknpxwj", "agirmcjvlhmdqsyoqizuknpxwt", "atirmcjvltedbsyoqfzuknpxwz", "agirxnjvlhedbsyoqfzuknpxkt", "agihmcjvlhedbsyoqfzukepxqt", "agirmcjvlhedbsmoqzsuknpxwt", "agirycjvlhedbuyoqfwuknpxwt", "agirmcjvlhedbsyoqfzfkrfxwt", "agirzcjvlhedbsyoqfhuknpxnt", "agigmcjvlhedbsqnqfzuknpxwt", "agirmgzvlhedbsyoqfzuonpxwt", "agirmcjvqhedbqyoqfzukqpxwt", "anarmcjvlhedbsyocfzuknpxwt", "agirmcjuihedbshoqfzuknpxwt", "agirdckvlhedbsyoqfzxknpxwt", "ugirmujvlhwdbsyoqfzuknpxwt", "mgirmcjvlheobsyovfzuknpxwt", "agirmcjvghedbsyoqfzufxpxwt", "agirmcjvlhedbsyoinzuknuxwt", "agirmzjvlhbdbsyoqfzlknpxwt", "agivmcjvlhedbsconfzuknpxwt", "agirmwfvlhedtsyoqfzuknpxwt", "agirmcjvlhedbbyoqrzukncxwt", "agirmcjvlhelbsyoqfzupnlxwt", "agirmmjvluedqsyoqfzuknpxwt", "agjrmcjvlhedbsyaqfcuknpxwt", "agiwmcjvlhedbsyoqzzuknpswt", "agirxcjvlhedbsyoqfyvknpxwt", "agirmljvlhedbsyoqkzuknpxjt", "agirmcjvchedbsyoqfzmknyxwt", "agirmcjvlhedbsyovfzuynpxwl", "agtrmcjvlhedysyoqfzuknexwt", "agirmcjvmhedbslonfzuknpxwt", "agirmcjfshedbsyoqfziknpxwt", "agirmcjvlhedbsygqfzkknpbwt", "agyrmcivlhedbsyovfzuknpxwt", "agirmcjvghedbsyoqjzuknkxwt", "agirmcjvlhedqsyoqfzukspxmt", "ayirmcjvhhedbsyomfzuknpxwt", "agirmcjvlnembsypqfzuknpxwt", "agirmcjqlhedbsyuvfzuknpxwt", "agirmcjvlhembsybqfzuknpxwa", "agirjcfvlhedbsyoqfuuknpxwt", "agirmcjvohedbsyowfzuknxxwt", "agirmcjvlhedroyoqfzukncxwt", "agrrmijvlhedbsyoqfnuknpxwt", "agirmjjvlhsdbsyoqfzumnpxwt", "agirrcjvnhedbsyoqfzuktpxwt", "agirmcjvlzedjsyoqfzuknpdwt", "agirmkjvlhedbsyoqfzxinpxwt", "agirmcjvlhedbzyojfzuknpvwt", "arirmcjvlheddsyoqfzuknrxwt", "agirmcjvlhedbsyoqhzuanpxmt", "agirmcjvluedbsyoqozuknwxwt", "afirmcjwlhedxsyoqfzuknpxwt", "agirmcjvlhefbsyoqfkuinpxwt", "agirycjvltedbsypqfzuknpxwt", "agirmrxvlhedbsyoqfzeknpxwt", "agfrmcqvlhedbsyoqxzuknpxwt", "agormcjvuhexbsyoqfzuknpxwt", "agyrmcjvehddbsyoqfzuknpxwt", "agirmcjvlheqbsynqfzgknpxwt", "agirmcjvlhedbsloufwuknpxwt", "tgirmcjvlwedbsyoqfzuknpqwt", "agirmcjvlhesbzyogfzuknpxwt", "agitmdjvlhedpsyoqfzuknpjwt", "bgirmejvlhtdbsyoqfzuknpxwt", "aginmcjvlhedzsyoqfzuknoxwt", "agvrzcjvlhedbsuoqfzuknpxwt", "agormcjvlhedbsyoqfzuknpodt", "agirmcevlhedbgyojfzuknpxwt", "agirmcjblhedboytqfzuknpxwt", "qgibmcjvlhedbsyoqfzuknbxwt", "agirmcjvlhedbsyoafzutnnxwt", "agiamcjvchkdbsyoqfzuknpxwt", "agirmcjvehedblyoqwzuknpxwt", "agirmcpvlhwdbsyoafzuknpxwt", "agirmcjvlhtdbsyoqfzumnpxtt", "agirmcjalhegtsyoqfzuknpxwt", "agirdijvlhedbsyoqfzutnpxwt", "agirmckvlhgdbsyovfzuknpxwt", "qgmrmcjvlkedbsyoqfzuknpxwt", "agirjcjvlhodbsyoqfzuanpxwt", "ajirmcjvlhedbpyoqftuknpxwt", "cgirmcjvlhedbsyoqfiuonpxwt", "ayirmcjvlhedbsyaqfzuknwxwt", "agirmcjvlhedbdyoqbzwknpxwt"};
  //public static final String[] inputs  = {" abcdef~", " bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab"};

  public static void main(String[] args)
  {
    int foundTwos = 0;
    int foundThrees = 0;
    for (String string : inputs)
    {
      if (hasCharNum(string, 2))
        foundTwos++;
      if (hasCharNum(string, 3))
        foundThrees++;
    }
    System.out.println("Part 1: " + foundTwos * foundThrees);

    ArrayList<String> partTwoResults = new ArrayList<>();
    for (int i = 0; i < inputs.length; i++)
      for (int j = i + 1; j < inputs.length; j++)
      {
        String answer = testPart2(inputs[i], inputs[j]);
        if (answer != null)
          partTwoResults.add(answer);
      }
      for (String s : partTwoResults)
    System.out.println("Part 2: " + s);
  }

  public static boolean hasCharNum(String stringToSearch, int quantityToFind)
  {
    for (char characterToCount : stringToSearch.toCharArray())
      if (countCharacters(stringToSearch, characterToCount) == quantityToFind)
        return true;
    return false;
  }

  public static int countCharacters(String stringToSearch, char characterToCount)
  {
    int lastIndex = 0;
    int quantityFound = 0;
    while ((lastIndex = 1 + stringToSearch.indexOf(characterToCount, lastIndex)) != 0)
      quantityFound++;
    return quantityFound;
  }

  public static String testPart2(String a, String b)
  {
    int count = 0;
    int loc = -1;
    for (int j = 0; j < a.length(); j++)
    {
      if (a.charAt(j) != b.charAt(j))
      {
        loc = j;
        count++;
      }
    }
    if (count == 1)
    {
      return "Part 2: " + a.substring(0, loc) + a.substring(loc + 1);
    }
    return null;
  }
}
