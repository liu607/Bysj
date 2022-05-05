package com.fc.dao;

import com.fc.entity.Users;
import com.fc.entity.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

public interface UsersMapper {
    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Iterable<Users> selectByExample(String username, String userpwd, HttpServletRequest request);
}