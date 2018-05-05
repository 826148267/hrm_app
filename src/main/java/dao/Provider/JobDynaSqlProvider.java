package dao.Provider;

import entity.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.StringTokenizer;

import static Util.common.HrmConstants.JOBTABLE;

public class JobDynaSqlProvider {
    //分页动态查询
    public String selectWhitParam(final Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(JOBTABLE);
                if (params.get("job")!=null){
                    Job job = (Job)params.get("job");
                    if (job.getName()!=null&&!job.getName().equals("")){
                        WHERE(" name like concat ('%',#{job.name},'%') ");
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
                FROM(JOBTABLE);
                if (params.get("job")!=null){
                    Job job = (Job) params.get("job");
                    if (job.getName()!=null&&!job.getName().equals("")){
                        WHERE(" name like concat ('%',#{job.name},'%')");
                    }
                }
            }
        }.toString();
    }

    //动态插入
    public String insertJob(final Job job){
        return new SQL(){
            {
                INSERT_INTO(JOBTABLE);
                if (job.getName()!=null&&!job.getName().equals("")){
                    VALUES("name","#{name}");
                }
                if (job.getRemark())
            }
        }.toString();
    }


}
