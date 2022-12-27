package oit.is.z0484.kaizi.janken03.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * FROM matches")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, winner) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{winner});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match matches);

  @Update("UPDATE matches SET user1Hand = #{user1Hand}, user2Hand = #{user2Hand}, winner = #{winner} WHERE ID = #{id}")
  void updateById(Match matches);
}
