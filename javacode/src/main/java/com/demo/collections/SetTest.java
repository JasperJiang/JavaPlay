package com.demo.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    private TreeSet<Course> CourseToSelect;

    public SetTest() {
        CourseToSelect = new TreeSet<Course>();
    }


    /**
     * 添加课程
     * @param cid
     * @param cname
     */
    public void AddCourse(String cid,String cname){
        Course cr = new Course(cid,cname);
        CourseToSelect.add(cr);
//        Course temp = CourseToSelect.get(CourseToSelect.size()-1);
        System.out.println(String.format("添加了课程:%s:%s",cr.getCid(),cr.getCname()));
    }


    /**
     * 修改课程
     * @param cid
     * @param cname
     */
    public void ModifyCourse(String cid,String cname){

    }

    /**
     * 显示所有课程
     */
    public void ShowCourse(){
        for (Course cr:CourseToSelect) {
            System.out.println(String.format("id:%s,name:%s",cr.getCid(),cr.getCname()));
        }
    }

    /**
     * 删除课程
     * @param cid
     */
    public void DeletCourse(String cid){

    }


    /**
     * 按照id查找
     * @param cid
     * @return
     */
    public Course FindCourseByID(String cid){
        for (Course cr_tmp:CourseToSelect){
            if (cr_tmp.getCid() == cid){
                return cr_tmp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        SetTest st = new SetTest();
//        System.out.println("-----------添加课程----------");
//        st.AddCourse("1","JAVA");
//        st.AddCourse("2","C++");
//        System.out.println("-----------输出所有课程----------");
//        st.ShowCourse();

        System.out.println("------Hashset--------");
        Set<Course> s = new HashSet<Course>();
        s.add(new Course("1","JAVA"));
        s.add(new Course("2","JAVA"));
        s.add(new Course("2","JAVA"));
        s.add(new Course("3","JAVA"));
        for (Course cr:s) {
            System.out.println(cr.getCid()+":"+cr.getCname());
        }
        System.out.println("------LinkedHashSet--------");
        LinkedHashSet<Course> ls = new LinkedHashSet<Course>();
        ls.add(new Course("1","JAVA"));
        ls.add(new Course("2","JAVA"));
        ls.add(new Course("3","JAVA"));
        ls.add(new Course("4","JAVA"));
        for (Course cr:ls) {
            System.out.println(cr.getCid()+":"+cr.getCname());
        }
        System.out.println("删除id为1的课");
        Course cr_tmp = null;
        for (Course cr:ls){
            if (cr.getCid().equals("1")){
                cr_tmp = cr;
            }
        }
        ls.remove(cr_tmp);
        for (Course cr:ls) {
            System.out.println(cr.getCid()+":"+cr.getCname());
        }
        cr_tmp = null;
        System.out.println("修改id为2的课");
        for (Course cr:ls) {
            if (cr.getCid().equals("2")) {
                cr_tmp = cr;
            }
        }

        if (cr_tmp!=null) {
            cr_tmp.setCname("C++");
            ls.add(cr_tmp);
        }

        for (Course cr:ls) {
            System.out.println(cr.getCid()+":"+cr.getCname());
        }

        System.out.println("------TreeSet--------");
        TreeSet<Integer> ts = new TreeSet<Integer>();
        ts.add(2);
        ts.add(5);
        ts.add(3);
        ts.add(1);
        for (Integer cr:ts) {
            System.out.println(cr);
        }
    }

}
