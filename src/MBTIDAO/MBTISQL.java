package MBTIDAO;

import MBTIDTO.MBTIUser;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MBTISQL {
    Scanner sc = new Scanner(System.in);
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean run2 = true;
    boolean run3 = true;

    // 1. DB 접속 메소드
    public void connect() {
        con = DBConnection.DBConnect();
    }

    // 2. 번호 생성 메소드
    public int genNo() {
        int uNo = 0;
        String sql = "SELECT MAX(UNO) FROM MUSER";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                uNo = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uNo;
    }

    // 3-1. 랜덤 index 배열 생성 메소드
    public List<Integer> randArrayI(int length) {
        List<Integer> list = new ArrayList<Integer>();
        int a = 0;
        boolean run = true;
        while (run) {
            a = (int) (((Math.random()) * length) + 1);
            list.add(a);
            if (list.size() == length) {
                run = false;
            }
        }
        run = true;
        while (run) {
            int cnt = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    boolean run2 = true;
                    while (run2) {
                        if (list.get(i) == list.get(j)) {
                            cnt++;
                            a = (int) (((Math.random() * length) + 1));
                            list.set(j, a);
                        } else {
                            run2 = false;
                        }
                    }
                }
            }
            if (cnt == 0) {
                for (int i = 0; i < list.size(); i++) {
                    int temp = list.get(i);
                    list.set(i, temp - 1);
                }
                run = false;
            }
        }
        return list;
    }

    // 3-2. 1~12 랜덤 배열 발생 메소드
    public List<Integer> randArray ( int length){
        List<Integer> list = new ArrayList<Integer>();
        int a = 0;
        boolean run = true;
        while (run) {
            a = (int) (((Math.random()) * length) + 1);
            list.add(a);
            if (list.size() == length) {
                run = false;
            }
        }
        run = true;
        while (run) {
            int cnt = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    boolean run2 = true;
                    while (run2) {
                        if (list.get(i) == list.get(j)) {
                            cnt++;
                            a = (int) (((Math.random() * length) + 1));
                            list.set(j, a);
                        } else {
                            run2 = false;
                        }
                    }
                }
            }
            if (cnt == 0) {
                run = false;
            }
        }
        return list;
    }

    // 3-3. 랜덤 배열 발생 메소드 2
    public void randArray2 () {
        int qEI = 0;
        int qNS = 0;
        int qFT = 0;
        int qPJ = 0;
        List<Integer> listEI = new ArrayList<Integer>();
        List<Integer> listNS = new ArrayList<Integer>();
        List<Integer> listFT = new ArrayList<Integer>();
        List<Integer> listPJ = new ArrayList<Integer>();
        List<Integer> listMix = new ArrayList<Integer>();
        List<Integer> listSum = new ArrayList<Integer>();
        String sql1 = "SELECT QNO FROM QUESTIONS WHERE QTYPE = 'EI'";
        String sql2 = "SELECT QNO FROM QUESTIONS WHERE QTYPE = 'NS'";
        String sql3 = "SELECT QNO FROM QUESTIONS WHERE QTYPE = 'FT'";
        String sql4 = "SELECT QNO FROM QUESTIONS WHERE QTYPE = 'PJ'";
        try {
            pstmt = con.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listEI.add(rs.getInt(1));
            }
            pstmt = con.prepareStatement(sql2);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listNS.add(rs.getInt(1));
            }
            pstmt = con.prepareStatement(sql3);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listFT.add(rs.getInt(1));
            }
            pstmt = con.prepareStatement(sql4);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listPJ.add(rs.getInt(1));
            }
            System.out.println(listEI);
            System.out.println(listNS);
            System.out.println(listFT);
            System.out.println(listPJ);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listMix = randArrayI(listEI.size());
        for (int i = 0; i < listEI.size(); i++) {
            int tempi = listEI.get(i);
            int tempj = listEI.get(listMix.get(i));
            listEI.set(i, tempj);
            listEI.set(listMix.get(i), tempi);
        }
        listMix = randArrayI(listNS.size());
        for (int i = 0; i < listNS.size(); i++) {
            int tempi = listNS.get(i);
            int tempj = listNS.get(listMix.get(i));
            listNS.set(i, tempj);
            listNS.set(listMix.get(i), tempi);
        }
        listMix = randArrayI(listFT.size());
        for (int i = 0; i < listFT.size(); i++) {
            int tempi = listFT.get(i);
            int tempj = listFT.get(listMix.get(i));
            listFT.set(i, tempj);
            listFT.set(listMix.get(i), tempi);
        }
        listMix = randArrayI(listPJ.size());
        for (int i = 0; i < listPJ.size(); i++) {
            int tempi = listPJ.get(i);
            int tempj = listPJ.get(listMix.get(i));
            listPJ.set(i, tempj);
            listPJ.set(listMix.get(i), tempi);
        }
        System.out.println(listEI);
        System.out.println(listNS);
        System.out.println(listFT);
        System.out.println(listPJ);

        for (int i = 0; i < 3; i++) {
            listSum.add(listEI.get(i));
        }
        for (int i = 0; i < 3; i++) {
            listSum.add(listNS.get(i));
        }
        for (int i = 0; i < 3; i++) {
            listSum.add(listFT.get(i));
        }
        for (int i = 0; i < 3; i++) {
            listSum.add(listPJ.get(i));
        }
        System.out.println(listSum);
        listMix = randArrayI(listSum.size());
        for (int i = 0; i < listSum.size(); i++) {
            int tempi = listSum.get(i);
            int tempj = listSum.get(listMix.get(i));
            listSum.set(i, tempj);
            listSum.set(listMix.get(i), tempi);
        }
        System.out.println(listSum);
    }

    // 3-4. 질문 메소드
    public String questions(int i) {
        String ques = null;
        String sql = "SELECT QDESC FROM QUESTIONS WHERE QNO = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, i);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("\n----------------------------------------------------------------");
                ques = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ques;
    }

    // 3-5. MBTI 유형 분류 메소드
    public String classify(List<Integer> list) {
        String newType = "";
        ArrayList<Integer> uType = new ArrayList<Integer>();
        String ans = null;
        int cntEI = 0;
        int cntNS = 0;
        int cntFT = 0;
        int cntPJ = 0;
        boolean run = true;
        int j = 0;

        while (run) {
            if (j == list.size()) {
                run = false;
                break;
            }
            System.out.println(questions(list.get(j)));
            System.out.print("답변선택 (1 또는 2) >> ");
            ans = sc.next();
            switch (ans) {
                case "1":
                    if ((list.get(j) == 1 || list.get(j) == 2 || list.get(j) == 3)) {
                        cntEI++;
                        j++;
                    } else if ((list.get(j) == 4 || list.get(j) == 5 || list.get(j) == 6)) {
                        cntNS++;
                        j++;
                    } else if ((list.get(j) == 7 || list.get(j) == 8 || list.get(j) == 9)) {
                        cntFT++;
                        j++;
                    } else if ((list.get(j) == 10 || list.get(j) == 11 || list.get(j) == 12)) {
                        cntPJ++;
                        j++;
                    }
                    break;
                case "2":
                    j++;
                    break;
                default:
                    System.out.println("1 또는 2 중에 입력하세요...");
                    break;
            }
        }

        uType.add(cntEI);
        uType.add(cntNS);
        uType.add(cntFT);
        uType.add(cntPJ);

        for (int i = 0; i < uType.size(); i++) {
            if (i == 0) {
                if (uType.get(i) == 3 || uType.get(i) == 2) {
                    newType += "E";
                } else {
                    newType += "I";
                }
            } else if (i == 1) {
                if (uType.get(i) == 3 || uType.get(i) == 2) {
                    newType += "N";
                } else {
                    newType += "S";
                }
            } else if (i == 2) {
                if (uType.get(i) == 3 || uType.get(i) == 2) {
                    newType += "F";
                } else {
                    newType += "T";
                }
            } else if (i == 3) {
                if (uType.get(i) == 3 || uType.get(i) == 2) {
                    newType += "P";
                } else {
                    newType += "J";
                }
            }
        }
        return newType;
    }

    // 3-6. m2type 정의 메소드
    public String newMbti(String mbti1) {
        String m2type = null;
        String sql = "SELECT M2TYPE FROM MBTI2TYPE WHERE M1TYPE = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mbti1);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                m2type = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m2type;
    }

    // 4. muser update 메소드
    public void updateMuser(MBTIUser muser) {
        String sql = "INSERT INTO MUSER VALUES(?, ?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, muser.getuNo());
            pstmt.setString(2, muser.getuName());
            pstmt.setString(3, muser.getMbti1());
            pstmt.setString(4, muser.getMbti2());
            pstmt.setString(5, muser.getDate());
            int result = pstmt.executeUpdate();

            if (result < 0) {
                System.out.println("검사 실패! 에러 발생...");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 5-1. 결과 출력 메소드
    public void selectResult(MBTIUser muser) {
        String sql = "SELECT M2.M2TYPE, M2.M1TYPE, D1.M1DESC FROM MBTI2TYPE M2, MBTI1TYPE M1, MBTI1DESC D1\n" +
                "WHERE M2.M1TYPE = M1.MTYPE AND M2.M1TYPE = D1.M1TYPE AND M2.M1TYPE = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, muser.getMbti1());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("\n%s 님! 당신의 유형은 '%s(%s)'으로 나왔습니다!\n당신은 아마 이런 사람일지도..?\n"
                        , muser.getuName(), rs.getString(1), rs.getString(2));
                System.out.println("------------------------------------------------------------------------------");
                System.out.println(rs.getString(3));
                System.out.println("------------------------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 5-2. muser table is null 확인 메소드
    public boolean isNull() {
        boolean isnull = false;
        String sql = "SELECT * FROM MUSER";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                isnull = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isnull;
    }

    // 5-3. 검사 결과 목록 메소드
    public boolean selectUser() {
        boolean checkCnt = false;
        if (isNull()) {     // 목록이 비어있는지 확인
            System.out.println("조회할 회원이 없습니다...");
        } else {
            checkCnt = true;
            String sql1 = "SELECT RPAD(UNAME, 10) FROM MUSER";
            String sql2 = "SELECT UNO, U1TYPE, U2TYPE, UDATE FROM MUSER";
            String uname = null;
            try {
                pstmt = con.prepareStatement(sql1);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    uname = rs.getString(1);
                }
                pstmt = con.prepareStatement(sql2);
                rs = pstmt.executeQuery();
                System.out.println("\nNo.\t\tName\t\tType\t\t\t\tDate");
                System.out.println("===========================================================");
                while (rs.next()) {
                    System.out.printf("[%d]\t\t%s\t%s(%s)   \t%s\n", rs.getInt(1), uname, rs.getString(3), rs.getString(2), rs.getString(4));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return checkCnt;
    }

    // 5-4. 다른 user의 검사결과 보기
    public void userInfo(int no) {
        String sql = "SELECT M.UNAME, M.U1TYPE, M.U2TYPE, D1.M1DESC FROM MUSER M, MBTI1DESC D1\n" +
                "WHERE D1.M1TYPE = M.U1TYPE AND M.UNO = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("조회할 회원이 없습니다...");
            } else {
                System.out.printf("\n%s 님의 정보입니다.\n유형 : %s(%s) \n", rs.getString(1), rs.getString(3), rs.getString(2));
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("%s\n\n", rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 6-1. 잘 맞는 mbti 메소드
    public void match1(MBTIUser muser) {
        String sql = "SELECT D.L2TYPE, C.M2TYPE, B.M1DESC FROM MBTI1TYPE A, MBTI1DESC B, MBTI2TYPE C, MATCH D " +
                "WHERE B.M1TYPE = A.MTYPE AND C.M1TYPE = A.MTYPE AND D.L2TYPE = A.MTYPE AND D.L1TYPE = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, muser.getMbti1());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nMBTI : " + rs.getString(1));
                System.out.println("송편 이름 : " + rs.getString(2));
                System.out.println("--------------------------------이 유형의 성격은--------------------------------");
                System.out.println(rs.getString(3));
                System.out.println("------------------------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 6-2. 잘 안맞는 mbti 메소드
    public void match2(MBTIUser muser) {
        String sql = "SELECT D.L3TYPE, C.M2TYPE, B.M1DESC FROM MBTI1TYPE A, MBTI1DESC B, MBTI2TYPE C, MATCH D " +
                "WHERE B.M1TYPE = A.MTYPE AND C.M1TYPE = A.MTYPE AND D.L3TYPE = A.MTYPE AND D.L1TYPE = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, muser.getMbti1());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("\nMBTI : " + rs.getString(1));
                System.out.println("송편 이름 : " + rs.getString(2));
                System.out.println("--------------------------------이 유형의 성격은--------------------------------");
                System.out.println(rs.getString(3));
                System.out.println("------------------------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



