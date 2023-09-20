package MBTIMain;

import MBTIDAO.MBTISQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;


public class MBTItest {
    public static void main(String[] args) {
        MBTISQL sql = new MBTISQL();
        List<Integer> list = new ArrayList<Integer>();
        sql.connect();
        sql.randArray2();
    }
}
