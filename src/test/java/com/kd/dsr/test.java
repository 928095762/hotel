package com.kd.dsr;

import cn.hutool.core.date.DateTime;

import java.time.LocalDate;
import java.time.Period;

public class test {





    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();  //当前时间

        LocalDate birthday = LocalDate.of(2020,03,26);//要计算的时间

        Period betweenDate = Period.between(birthday, localDate); //计算时间间隔

        System.out.println("localdate:"+localDate);
        System.out.println("birthday:"+birthday);
        System.out.println("day:"+betweenDate.getDays());
    }
}
