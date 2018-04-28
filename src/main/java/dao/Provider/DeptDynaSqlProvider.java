package dao.Provider;

import java.util.Map;
import static Util.common.HrmConstants.DEPTTABLE;

import entity.Dept;
import org.apache.ibatis.jdbc.SQL;
public class DeptDynaSqlProvider {
    //分页动态查询
    public String selectWhitParams(final Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if (params.get("dept")!=null){
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName()!=null&&!dept.getName().equals("")){
                        WHERE(" name like concat ('%',#{dept.name},'%')");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel")!=null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
        }
        return sql;
    }

    //动态查询总数量
    public String count(final Map<String,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(DEPTTABLE);
                if (params.get("dept")!=null){
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName()!=null&&!dept.getName().equals("")){
                        WHERE(" name like concat ('%',#{dept.name},'%')");
                    }
                }
            }
        }.toString();
    }

    //动态插入
    public String insertDept(final Dept dept){
        return new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if (dept.getName()!=null&&!dept.getName().equals("")){
                    VALUES("name","#{name}");
                }
                if (dept.getRemark()!=null&&dept.getRemark().equals("")){
                    VALUES("remark","#{remark}");
                }
            }
        }.toString();
    }

    //动态更新
    public String updateDept(final Dept dept){
        return new SQL(){
            {
                UPDATE(DEPTTABLE);
                if (dept.getName()!=null&&dept.getName().equals("")){
                    SET(" name = #{dept.name} ");
                }
                if (dept.getRemark()!=null&&dept.getRemark().equals("")){
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
