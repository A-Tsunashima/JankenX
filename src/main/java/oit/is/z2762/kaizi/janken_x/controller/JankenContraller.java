package oit.is.z2762.kaizi.janken_x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Random;
import java.security.Principal;

import oit.is.z2762.kaizi.janken_x.model.Entry;
import oit.is.z2762.kaizi.janken_x.model.Match;
import oit.is.z2762.kaizi.janken_x.model.MatchMapper;
import oit.is.z2762.kaizi.janken_x.model.User;
import oit.is.z2762.kaizi.janken_x.model.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class JankenContraller {

  @Autowired
  private Entry entry;

  @Autowired
  private MatchMapper MatchMapper;

  @Autowired
  UserMapper userMapper;

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
    return "janken";
  }

  @GetMapping("/jankengame")
  public String jankenMatch(@RequestParam String hand, ModelMap model) {
    model.addAttribute("entry", entry);
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

    ArrayList<Match> matches = MatchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    return "janken";
  }

  //追加分

  @GetMapping("/match")
  public String match(@RequestParam int id, ModelMap model, Principal prin) {

    // ログインユーザ
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);

    // 対戦相手（ID指定）
    User enemy = userMapper.selectById(id);
    model.addAttribute("enemy", enemy);

    // CPU 以外とは試合不可
    boolean canBattle = enemy.getName().equals("CPU");
    model.addAttribute("canBattle", canBattle);

    return "match";
  }

  @GetMapping("/matchplay")
  public String matchPlay(@RequestParam int id,
      @RequestParam String hand,
      ModelMap model,
      Principal prin) {

    // ログインユーザ名
    String loginName = prin.getName();

    // ★ name から User を取得（自分）
    User me = userMapper.selectByUsername(loginName);

    // ★ 対戦相手（CPU）
    User enemy = userMapper.selectById(id);

    // CPUの手（固定でOK）
    String cpuHand = "Gu";

    // ★ matches テーブルに登録
    MatchMapper.insertMatch(
        String.valueOf(me.getId()),
        String.valueOf(enemy.getId()),
        hand,
        cpuHand);

    // 表示用
    model.addAttribute("loginUser", me.getName());
    model.addAttribute("enemy", enemy);
    model.addAttribute("myHand", hand);
    model.addAttribute("cpuHand", cpuHand);

    return "match";
  }

  //ここまで
}
