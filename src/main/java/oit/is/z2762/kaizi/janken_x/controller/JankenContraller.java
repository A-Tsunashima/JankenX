package oit.is.z2762.kaizi.janken_x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import java.util.Random;
import java.security.Principal;

import oit.is.z2762.kaizi.janken_x.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class JankenContraller {

  @Autowired
  private Entry entry;

  private static final String[] HANDS = { "Gu", "Choki", "Pa" };

  @GetMapping("/janken")
  public String janken(ModelMap model, Principal prin) {
    String username = prin.getName(); // ログインユーザの名前を取得
    this.entry.addUser(username); // アレイリストにユーザを追加
    model.addAttribute("username", username);
    model.addAttribute("entry", entry);
    return "janken";
  }

  @PostMapping("/janken")
  public String janken(@RequestParam String username, ModelMap model) {
    model.addAttribute("username", username);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankenMatch(@RequestParam String hand, ModelMap model) {
    model.addAttribute("hand", hand);
    Random random = new Random();
    String cpuhand = HANDS[random.nextInt(HANDS.length)];
    model.addAttribute("cpuhand", cpuhand);

    String result;
    if (hand.equals(cpuhand)) {
      result = "Draw";
    } else if ((hand.equals("Gu") && cpuhand.equals("Choki")) ||
        (hand.equals("Choki") && cpuhand.equals("Pa")) ||
        (hand.equals("Pa") && cpuhand.equals("Gu"))) {
      result = "You Win!";
    } else {
      result = "You Lose";
    }
    model.addAttribute("result", result);
    return "janken.html";
  }
}
