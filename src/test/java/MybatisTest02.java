import com.qf.dao.EmpMapper;
import com.qf.dao.GroupMapper;
import com.qf.popj.Employee;
import com.qf.popj.Group;
import com.qf.util.MyBatisUtils;
import org.junit.Test;

import java.util.List;

public class MybatisTest02 {

    private static EmpMapper empMapper;
    private static GroupMapper groupMapper;

    static {
        empMapper = MyBatisUtils.getMapper(EmpMapper.class);
        groupMapper = MyBatisUtils.getMapper(GroupMapper.class);
    }

    //根据组号查询员工信息 --- 主表查询
    @Test
    public void testSelectEmployeeByGid(){
        List<Employee> employees = empMapper.selectEmployeeByGid(1);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //查询小组信息 --- 及联调用
    @Test
    public void testSelectGroupById(){
        Group group = groupMapper.selectGroupById(1);
        System.out.println(group);
    }
}
