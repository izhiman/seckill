package cn.izhiman.seckill.dao;

import cn.izhiman.seckill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Mapper
public interface UserDao {
    /**
     * test
     *
     * @param id id
     * @return user
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(@Param("id") int id);

    @Insert("insert into user(id,name) values(#{id},#{name})")
    void insert(@Param("id") int id, @Param("name") String name);
}
