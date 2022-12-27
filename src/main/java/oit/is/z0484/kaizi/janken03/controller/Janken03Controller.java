package oit.is.z0484.kaizi.janken03.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.janken03.model.Janken;
import oit.is.z0484.kaizi.janken03.model.MatchMapper;
import oit.is.z0484.kaizi.janken03.model.Match;
import oit.is.z0484.kaizi.janken03.model.User;
import oit.is.z0484.kaizi.janken03.model.UserMapper;

@Controller
public class Janken03Controller {

  @Autowired
  UserMapper usermapper;

  @Autowired
  MatchMapper matchmapper;

  @GetMapping("/janken03")
  public String janken03(Principal prin, ModelMap model) {
    ArrayList<User> users = usermapper.selectAllUsers();
    ArrayList<Match> matches = matchmapper.selectAllMatches();
    System.out.println("GET");
    String login_name = prin.getName();
    // this.entry.addUser(login_name);
    model.addAttribute("name", login_name);
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);

    return "janken.html";
  }

  @PostMapping("/login")
  public String janken(@RequestParam String name, ModelMap model) {
    System.out.println("POST");
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/fight")
  @Transactional
  public String fight(@RequestParam Integer opp_id, @RequestParam String user_hand, ModelMap model, Principal prin) {

    User player = usermapper.selectByName(prin.getName());
    User opponent = usermapper.selectById(opp_id);
    String opp_hand = Janken.cpuHand();
    String result = Janken.judge(user_hand, opp_hand);

    int uflag = 0; // UPDATEが行われたか判定するフラグ
    ArrayList<Match> matches = matchmapper.selectAllMatches();

    Match match = new Match(player.getId(), opp_id, user_hand, opp_hand);

    if (result.equals("Win!")) {
      match.setWinner(player.getName());
    } else if (result.equals("Lose")) {
      match.setWinner(opponent.getName());
    } else {
      match.setWinner("draw");
    }

    // debug
    System.out.println("opp_id : " + opp_id);
    System.out.println("hand1 : " + user_hand);
    System.out.println("hand2 : " + opp_hand);
    System.out.println("result : " + result);
    System.out.println("winner : " + match.getWinner());

    model.addAttribute("opponent", opponent);
    model.addAttribute("hand1", user_hand);
    model.addAttribute("hand2", opp_hand);
    model.addAttribute("result", result);

    for (Match m : matches) {
      if (m.getUser1() == match.getUser1() && m.getUser2() == match.getUser2()) {
        matchmapper.updateById(m);
        System.out.println("updataId : " + m.getId());
        uflag = 1;
      }
    }

    System.out.println("uflag : " + uflag);

    if (uflag == 0) {
      matchmapper.insertMatch(match);
    }

    return "match.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, ModelMap model) {
    User opponent = usermapper.selectById(id);
    model.addAttribute("opponent", opponent);

    return "match.html";
  }

}
