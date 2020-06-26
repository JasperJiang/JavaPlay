package com.demo.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    private List<Course> CourseToSelect;

    public ListTest() {
        CourseToSelect = new ArrayList<Course>();
    }


    /**
     * 添加课程
     * @param cid
     * @param cname
     */
    public void AddCourse(String cid,String cname){
        Course cr = new Course(cid,cname);
        CourseToSelect.add(cr);
        Course temp = CourseToSelect.get(CourseToSelect.size()-1);
        System.out.println(String.format("添加了课程:%s:%s",temp.getCid(),temp.getCname()));
    }


    /**
     * 修改课程
     * @param cid
     * @param cname
     */
    public void ModifyCourse(String cid,String cname){
        Course cr = new Course(cid,cname);
        int index = CourseToSelect.indexOf(FindCourseByID(cid));
        CourseToSelect.set(index,cr);
        System.out.println(String.format("已修改课程%s:%s",cr.getCid(),cr.getCname()));
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
        Course cr = FindCourseByID(cid);
        int index = CourseToSelect.indexOf(cr);
        CourseToSelect.remove(index);
        System.out.println(String.format("已删除课程%s:%s",cr.getCid(),cr.getCname()));
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
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");

        List<String> list2 = new ArrayList<String>();
        list2.add("C");
        list2.add("B");
        list2.add("D");
        // 并集
        list1.addAll(list2);
        // 去重复并集
        list2.removeAll(list1);
        list1.addAll(list2);
        // 交集
        list1.retainAll(list2);
        // 差集
        list1.removeAll(list2);

        ListTest lt = new ListTest();
        System.out.println("-----------添加课程----------");
        lt.AddCourse("1","JAVA");
        lt.AddCourse("2","C++");
        System.out.println("-----------输出所有课程----------");
        lt.ShowCourse();
        System.out.println("-----------查找id为1的课----------");
        Course cr = lt.FindCourseByID("1");
        System.out.println(String.format("id:%s,name:%s",cr.getCid(),cr.getCname()));
        System.out.println("-----------删除id为1的课----------");
        lt.DeletCourse("1");
        System.out.println("-----------输出所有课程----------");
        lt.ShowCourse();
        System.out.println("-----------修改id为2的课变成Python----------");
        lt.ModifyCourse("2","Python");
        System.out.println("-----------输出所有课程----------");
        lt.ShowCourse();
    }
}
