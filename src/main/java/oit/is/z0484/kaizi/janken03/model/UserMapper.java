package oit.is.z0484.kaizi.janken03.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM users")
  ArrayList<User> selectAllUsers();

  @Select("SELECT * FROM users WHERE ID=#{id}")
  User selectById(int id);

  @Select("SELECT * FROM users WHERE NAME=#{NAME}")
  User selectByName(String name);
}
