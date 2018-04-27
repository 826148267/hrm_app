package dao.Provider;

import java.util.Map;

import entity.User;
import org.apache.ibatis.jdbc.SQL;
import static Util.common.HrmConstants.USERTABLE;
public class UserDynaSqlProvider {
    //分页动态查询
    public String selectWhitParam(final Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(USERTABLE);
                if (params.get("user")!=null){
                    User user = (User) params.get("user");
                    if (user.getUsername()!=null&&!user.getUsername().equals("")){
                        WHERE(" username like concat ('%',#{user.username},'%') ");
                    }
                    if (user.getStatus()!=null){
                        WHERE(" status like CONCAT ('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel")!=null){
            sql += "limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
        }
        return sql;
    }

    //动态查询总数量
    public String count(final Map<String,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(USERTABLE);
                if (params.get("user")!=null){
                    User user = (User) params.get("user");
                    if (user.getUsername()!=null&&user.getUsername().equals("")){
                        WHERE(" username like concat('%',#{user.username},'%')");
                    }
                    if (user.getStatus()!=null){
                        WHERE(" status like concat ('%',#{user.status},'%')");
                    }
                }
            }
        }.toString();
    }

    //动态插入用户
    public String insertUser(final User user){
        return new SQL(){
            {
                INSERT_INTO(USERTABLE);
                if (user.getUsername()!=null&&!user.getUsername().equals("")){
                    VALUES("username","#{user.username}");
                }
                if (user.getStatus()!=null&&!user.getUsername().equals("")){
                    VALUES("status","#{user.status}");
                }
                if (user.getLoginname()!=null&&!user.getLoginname().equals("")){
                    VALUES("loginname","#{user.loginname}");
                }
                if (user.getPassword()!=null&&user.getPassword().equals("")){
                    VALUES("password","#{user.password}");
                }
            }
        }.toString();
    }

    //动态更新
    public String updateUser(final User user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if (user.getUsername()!=null){
                    SET(" username = #{user.username}");
                }
                if (user.getLoginname()!=null){
                    SET(" loginname = #{user.loginname}");
                }
                if (user.getPassword()!=null){
                    SET(" password = #{user.password}");
                }
                if (user.getStatus()!=null){
                    SET(" status = #{user.status}");
                }
                if (user.getCreateDate()!=null){
                    SET(" create_date = #{user.createDate}");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
