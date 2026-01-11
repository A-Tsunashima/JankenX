package oit.is.z2762.kaizi.janken_x.model;

public class Match {
  private int id;
  private String player;
  private String opponent;
  private String playerHand;
  private String opponentHand;
  private String result;

  // getters / setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public String getOpponent() {
    return opponent;
  }

  public void setOpponent(String opponent) {
    this.opponent = opponent;
  }

  public String getPlayerHand() {
    return playerHand;
  }

  public void setPlayerHand(String playerHand) {
    this.playerHand = playerHand;
  }

  public String getOpponentHand() {
    return opponentHand;
  }

  public void setOpponentHand(String opponentHand) {
    this.opponentHand = opponentHand;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
