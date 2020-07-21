package com.example.fragmenttest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManager {
    //定义一个内部类，作为系统的业务对象.
    public static class Book {
        public Integer id;
        public String title;
        public String desc;

        public Book(Integer id, String title, String desc) {
            this.id = id;
            this.title = title;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return title;
        }
    }
        //使用ListView集合记录系统所包含的Book对象。
        public static List<Book> ITEMS = new ArrayList<>();
        //使用Map集合记录系统所包含的Book对象。
        public static Map<Integer,Book> ITEM_MAP = new HashMap<>();
        static {
            //使用静态初始化代码，将Book对象添加到List集合、Map集合中
            addItem(new Book(1,"疯狂Java讲义","十年沉淀的必读Java经典，"+"经典教材"));
            addItem(new Book(2,"高等数学","从入门到入坟"));
            addItem(new Book(3,"女装宝典","从入门到嫁人"));
        }
        public static void addItem(Book book){
            ITEMS.add(book);
            ITEM_MAP.put(book.id,book);
        }

}
