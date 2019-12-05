package com.example.campus;

import static java.lang.Double.NaN;

public class Data_Graph {
    static String[] Nodes ={ "体检中心","操场","校门北口","银杏景观","邯郸音乐厅","图书馆","餐厅","信息学部","花园景观","校门东口","网计学院","校门南口"};
    static double loc[][] = {{38.8835060000,115.5620340000},{38.8831010000,115.5663310000},{38.8827750000,115.5691900000},
            {38.8827790000,115.5714750000},{38.8830510000,115.5625440000},{38.8816440000,115.5662770000},
            {38.8818360000,115.5694640000},{38.8798730000,115.5646680000},{38.8795010000,115.5657940000},
            {38.88052,115.568402},{38.8793550000,115.5629190000},{38.8770710000,115.5658860000}};
    static double [][]map={{0.0,350.0,NaN,NaN,200.0,NaN,NaN,NaN,NaN,NaN,NaN,NaN,},
            {350.0,0.0,200.0,NaN,480.0,280.0,NaN,NaN,NaN,NaN,NaN,NaN,},
            {NaN,200.0,0.0,100.0,NaN,NaN,100.0,NaN,NaN,NaN,NaN,NaN,},
            {NaN,NaN,100.0,0.0,NaN,NaN,100.0,NaN,NaN,NaN,NaN,NaN,},
            {200.0,480.0,NaN,NaN,0.0,400.0,NaN,500.0,NaN,NaN,500.0,NaN,},
            {NaN,280.0,NaN,NaN,400.0,0.0,NaN,NaN,160.0,300.0,NaN,NaN,},
            {NaN,NaN,100.0,100.0,NaN,NaN,0.0,NaN,NaN,100.0,NaN,NaN,},
            {NaN,NaN,NaN,NaN,500.0,NaN,NaN,0.0,150.0,NaN,NaN,400.0,},
            {NaN,NaN,NaN,NaN,NaN,160.0,NaN,150.0,0.0,200.0,NaN,500.0,},
            {NaN,NaN,NaN,NaN,NaN,300.0,100.0,NaN,200.0,0.0,NaN,600.0,},
            {NaN,NaN,NaN,NaN,500.0,NaN,NaN,NaN,NaN,NaN,0.0,400.0,},
            {NaN,NaN,NaN,NaN,NaN,NaN,NaN,400.0,500.0,600.0,400.0,0.0,}};
}
