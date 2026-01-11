package oit.is.z2762.kaizi.janken_x.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.ArrayList;

@Mapper
public interface UserMapper { // 直接DBのUserテーブルにアクセスするためのインターフェース

  @Select("SELECT username from users")
  ArrayList<String> SelectUsername();

  @Select("SELECT * from users")
  ArrayList<User> selectUser();

}
