package com.example.campus;

import android.util.Log;

import static java.lang.Double.NaN;

public class Map {
    Node []nodes;
    double[][]map;
    double [][]edge;
    double [][]p;
    static double Infinity=NaN;
    public Map(){
        map = new double[Data_Graph.Nodes.length][Data_Graph.Nodes.length];
        for (int i = 0; i <Data_Graph.Nodes.length; i++) {
            for (int j = 0; j < Data_Graph.Nodes.length; j++) {
                if (i == j) {
                    map[i][j] = 0;
                }
                else{
                    map[i][j] = Data_Graph.map[i][j];
                }

            }
        }
        p = new double[Data_Graph.Nodes.length][Data_Graph.Nodes.length];
        for (int i =0;i<Data_Graph.Nodes.length;i++){
            for(int j=0;j<Data_Graph.Nodes.length;j++){
                p[i][j]=j;
            }
        }

        /*map[0][1]=350;
        map[0][4]=200;

        map[1][0]=350;
        map[1][2]=200;
        map[1][4]=480;
        map[1][5]=280;

        map[2][1]=200;
        map[2][3]=100;
        map[2][6]=100;

        map[3][2]=100;
        map[3][6]=100;

        map[4][0]=200;
        map[4][1]=480;
        map[4][5]=400;
        map[4][7]=500;
        map[4][10]=500;

        map[5][1]=280;
        map[5][4]=400;
        map[5][8]=160;
        map[5][9]=300;

        map[6][2] = 100;
        map[6][3] = 100;
        map[6][9] = 100;

        map[7][4] = 500;
        map[7][8] = 150;
        map[7][11] = 400;

        map[8][5] = 160;
        map[8][7] = 150;
        map[8][9] = 200;
        map[8][11] = 500;

        map[9][5] = 300;
        map[9][6] = 100;
        map[9][8] = 200;
        map[9][11] = 600;

        map[10][4] = 500;
        map[10][11] = 400;

        map[11][8] = 500;
        map[11][7] = 400;
        map[11][10] = 400;
        map[11][9] = 600;*/

    }
    public void Floyd(){
        edge = new double[Data_Graph.Nodes.length][Data_Graph.Nodes.length];
        for (int i=0;i<Data_Graph.Nodes.length;i++){
            for (int j=0;j<Data_Graph.Nodes.length;j++){
                edge[i][j]=Data_Graph.map[i][j];
                //Log.d("hehe",String.valueOf(edge[i][j]));
            }
        }
        for (int k=0;k<Data_Graph.Nodes.length;k++){
            for (int i=0;i<Data_Graph.Nodes.length;i++){
                for (int j=0;j<Data_Graph.Nodes.length;j++){
                    if(Double.isNaN(edge[i][k]+edge[k][j])){

                    }
                    else{
                        if(Double.isNaN(edge[i][j])==false){
                            if(edge[i][j]>edge[i][k]+edge[k][j]) {
                                edge[i][j] = edge[i][k] + edge[k][j];
                                p[i][j]=p[i][k];
                                //Log.d("huhe",String.valueOf(edge[i][j]));
                            }
                        }
                        else{
                            edge[i][j] = edge[i][k] + edge[k][j];
                            p[i][j]=p[i][k];
                            //Log.d("huhe",String.valueOf(edge[i][j]));
                        }

                    }

                }
            }
        }
        /*for (int i =0;i<nodes.length;i++){
            for(int j=0;j<nodes.length;j++){
                System.out.print(p[i][j]+" ");
            }
            System.out.println("\n");
        }*/
    }
   /* public static void main(String[] args) {
        String[] nodes ={ "体检中心","操场","校门北口","银杏景观","邯郸音乐厅","图书馆","餐厅","信息学部","花园景观","校门东口","网计学院","校门南口"};
        double loc[][] = {{115.568462,38.889908},{115.572751,38.888992},{115.575428,38.889129},
                {115.578042,38.88901},{115.56892,38.889466},{115.57259,38.887921},
                {115.575815,38.887946},{115.571355,38.88723},{115.572253,38.886089},
                {115.575366,38.887395},{115.569562,38.885766},{115.571916,38.883186}};
        Node []n = new Node[nodes.length];
        for (int i =0;i<nodes.length;i++){
            n[i] = new Node(nodes[i],loc[i][1],loc[i][0]);
        }
        Map m =new Map(n);
        m.Floyd();
        System.out.println(m.edge[1][11]);
        System.out.println(n[0].lat+"  "+n[0].lng);
    }*/
}
