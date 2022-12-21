package oit.is.z0484.kaizi.janken03.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.janken03.model.Entry;
import oit.is.z0484.kaizi.janken03.model.Janken;

@Controller
@RequestMapping("/janken03")
public class Janken03Controller {

  @Autowired
  private Entry entry;

  @GetMapping("entry")
  public String janken03(Principal prin,  ModelMap model) {
    System.out.println("GET");
    String login_name= prin.getName();
    this.entry.addUser(login_name);
    model.addAttribute("name", login_name);
    model.addAttribute("entry", this.entry);

    return "janken.html";
  }

  @PostMapping("login")
  public String janken(@RequestParam String name, ModelMap model) {
    System.out.println("POST");
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("hand")
  public String userHand(@RequestParam String user_hand, ModelMap model) {
    String cpu_hand = Janken.cpuHand();
    String result = Janken.judge(user_hand, cpu_hand);
    model.addAttribute("hand1", user_hand);
    model.addAttribute("hand2", cpu_hand);
    model.addAttribute("result", result);
    return "janken.html";
  }

}
