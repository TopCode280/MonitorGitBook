package com.example.zhaotao.camera_test;

import com.example.zhaotao.camera_test.utils.TimePlanManager;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addTask(){
        int i = TimePlanManager.getInstance().addTask("3:00-5:15");
        int i2 = TimePlanManager.getInstance().addTask("6:01-15:15");
        TimePlanManager.getInstance().updateTask("6:11-15:15",1);
        String s = TimePlanManager.getInstance().toPlan();
        System.out.println(s);
        ArrayList<String> arrayList = TimePlanManager.getInstance().transformToString(TimePlanManager.getInstance().getSlotArrayList());
        for (String s1 : arrayList) {
            System.out.println(s1);
        }
        assertEquals(1,i2);

    }
}