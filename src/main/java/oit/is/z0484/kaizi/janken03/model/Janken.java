package oit.is.z0484.kaizi.janken03.model;

import java.util.Random;

public class Janken {

  public static String cpuHand() {
    String hand = "";
    Random rnd = new Random();
    int num = rnd.nextInt(3);
    switch (num) {
      case 0:
        hand = "gu";
        break;
      case 1:
        hand = "choki";
        break;
      case 2:
        hand = "pa";
        break;
    }
    return hand;
  }

  public static String judge(String userHand, String cpuHand) {
    String u = userHand;
    String c = cpuHand;

    if (u.equals(c)) {
      return "draw";
    } else if (u.equals("gu") && c.equals("choki")) {
      return "Win!";
    } else if (u.equals("pa") && c.equals("gu")) {
      return "Win!";
    } else if (u.equals("choki") && c.equals("pa")) {
      return "Win!";
    }

    return "Lose";
  }

}
