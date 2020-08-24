package com.example.recycleviewitemdragdemo;

import java.util.ArrayList;

public class ItemBean {
    String name;
    Boolean isVisible=true;

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemBean(String name) {
        this.name = name;
    }

    public static ArrayList<ItemBean> getItemBeans(){
        ArrayList<ItemBean> itemBeans=new ArrayList<>();
        itemBeans.add(new ItemBean("亚特兰大老鹰"));
        itemBeans.add(new ItemBean("夏洛特黄蜂"));
        itemBeans.add(new ItemBean("迈阿密热火"));
        itemBeans.add(new ItemBean("奥兰多魔术"));
        itemBeans.add(new ItemBean("华盛顿奇才"));
        itemBeans.add(new ItemBean("波士顿凯尔特人"));
        itemBeans.add(new ItemBean("布鲁克林篮网"));
        itemBeans.add(new ItemBean("纽约尼克斯"));
        itemBeans.add(new ItemBean("费城76人"));
        itemBeans.add(new ItemBean("多伦多猛龙"));
        itemBeans.add(new ItemBean("芝加哥公牛"));
        itemBeans.add(new ItemBean("克里夫兰骑士"));
        itemBeans.add(new ItemBean("底特律活塞"));
        itemBeans.add(new ItemBean("印第安纳步行者"));
        itemBeans.add(new ItemBean("密尔沃基雄鹿"));
        itemBeans.add(new ItemBean("达拉斯独行侠"));
        itemBeans.add(new ItemBean("休斯顿火箭"));
        itemBeans.add(new ItemBean("孟菲斯灰熊"));
        itemBeans.add(new ItemBean("新奥尔良鹈鹕"));
        itemBeans.add(new ItemBean("圣安东尼奥马刺"));
        itemBeans.add(new ItemBean("丹佛掘金"));
        itemBeans.add(new ItemBean("明尼苏达森林狼"));
        itemBeans.add(new ItemBean("俄克拉荷马城雷霆"));
        itemBeans.add(new ItemBean("波特兰开拓者"));
        itemBeans.add(new ItemBean("犹他爵士"));
        itemBeans.add(new ItemBean("金州勇士"));
        itemBeans.add(new ItemBean("洛杉矶快船"));
        itemBeans.add(new ItemBean("洛杉矶湖人"));
        itemBeans.add(new ItemBean("菲尼克斯太阳"));
        itemBeans.add(new ItemBean("萨克拉门托国王"));
        return itemBeans;
    }
}
