package oit.is.z2762.kaizi.janken_x.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("SELECT * FROM matches")
  ArrayList<Match> selectAllMatches();

  //以下追加分
  @Insert("""
        INSERT INTO matches (user1, user2, user1Hand, user2Hand)
        VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand})
      """)
  void insertMatch(String user1, String user2,
      String user1Hand, String user2Hand);
}
