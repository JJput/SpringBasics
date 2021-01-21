package com.jjput.create.util.excel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @作者: JJ
 * @创建时间: 2020/7/21 下午5:10
 * @Version 1.0
 * @描述:
 */
public class ImportSubjectUtils {

    private static Connection con;

    private static final String sourcePath = "/Users/hilew/Downloads/QQCode/会计制度科目列表/";
    private static ArrayList<String> listAllFilePath = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            ImportSubjectUtils.improtXls();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void improtXls() throws FileNotFoundException, SQLException {
        Connection connection = getConnection();
        getAllFileName(sourcePath, listAllFilePath);
        long allCount = 0;
        //过滤不是xls的文件
        for (int i = 0; i < listAllFilePath.size(); i++) {
            //获取最后一个.的位置
            int lastIndexOf = listAllFilePath.get(i).lastIndexOf(".");
            //获取文件的后缀名
            String suffix = listAllFilePath.get(i).substring(lastIndexOf);
            if (!(suffix.equals(".xls") || suffix.equals(".xlsx"))) {
                listAllFilePath.remove(i);
            }
        }
        for (int i = 0; i < listAllFilePath.size(); i++) {
            File f = new File(listAllFilePath.get(i));
            InputStream inputStream = new FileInputStream(f);
            ExcelLogs logs = new ExcelLogs();
            Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
            int item = 0;
            /*
            for (Map m : importExcel) {
                System.out.print(f.getName() + ":" + (item));
                SysSubject subject = new SysSubject();
                subject.insert("1");
                ;
                subject.setLargeClass(m.get("类别").toString());
                subject.setCode(m.get("编码").toString());
                subject.setName(m.get("名称").toString());
                subject.setLargeClass(getLargeClass(f.getName().substring(0, 1), m.get("编码").toString().substring(0, 1)));
                subject.setSmallClass(m.get("类别").toString());
                subject.setBalanceDirection(m.get("余额方向").toString().endsWith("借") ? 1 : 2);
                subject.setAccountSystem(f.getName().substring(0, 1));
                subject.setInfoAccountSetId(f.getName().substring(0, 1));

//                ModelToSQL modelToSQL = new ModelToSQL(ModelToSQL.SqlType.INSERT, subject);

                String sql = "INSERT INTO sys_subject_copy" +
                        "(remake,money,spare2,spare3,spare4,spare5,spare6,spare7,spare8,spare9,code,name,large_class,small_class,balance_direction,foreign_currency,sub_status,supplementary_account_id,account_system,info_account_set_id,id,revision,created_by,created_time,update_by,update_time,dele)" +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                //remake
                stmt.setString(1, null);
                //money
                stmt.setString(2, null);
                //spare2
                stmt.setString(3, null);
                //spare3
                stmt.setString(4, null);
                //spare4
                stmt.setString(5, null);
                //spare5
                stmt.setString(6, null);
                //spare6
                stmt.setString(7, null);
                //spare7
                stmt.setString(8, null);
                //spare8
                stmt.setString(9, null);
                //spare9
                stmt.setString(10, subject.getCode().length() > 4 ? subject.getCode().substring(0, subject.getCode().length() - 2) : null);
                //code
                stmt.setString(11, subject.getCode());
                //name
                stmt.setString(12, subject.getName());
                //large_class
                stmt.setString(13, subject.getLargeClass());
                //small_class
                stmt.setString(14, subject.getSmallClass());
                //balance_direction
                stmt.setInt(15, subject.getBalanceDirection());
                //foreign_currency
                stmt.setString(16, subject.getForeignCurrency());
                //sub_status
                stmt.setString(17, "1");    //状态
                //supplementary_account_id
                stmt.setString(18, subject.getSupplementaryAccountId());
                //account_system
                stmt.setString(19, subject.getAccountSystem());
                //info_account_set_id
                stmt.setString(20, subject.getInfoAccountSetId());
                //id
                stmt.setString(21, subject.getId());
                stmt.setInt(22, 1);
                stmt.setString(23, subject.getCreatedBy());
                stmt.setDate(24, new Date(subject.getCreatedTime().getTime()));
                stmt.setString(25, subject.getUpdateBy());
                stmt.setDate(26, new Date(subject.getUpdateTime().getTime()));
                stmt.setString(27, subject.getDele());


                stmt.executeUpdate();// 返回值代表收到影响的行数
                allCount++;
                item++;
                System.out.print("\r");
            }
             */
            System.out.println(f.getName() + ":" + (item));

        }
        System.out.println("合计:" + allCount);

    }

    private static String getLargeClass(String name, String t) {
        if (name.equals("1")) {
            switch (t) {
                case "1":
                    return "资产";
                case "2":
                    return "负债";
                case "3":
                    return "权益";
                case "4":
                    return "成本";
                case "5":
                    return "损益";
                case "6":
                    return "损益";
                default:
                    return "null";
            }
        } else if (name.equals("2")) {
            switch (t) {
                case "1":
                    return "资产";
                case "2":
                    return "负债";
                case "3":
                    return "共同";
                case "4":
                    return "权益";
                case "5":
                    return "成本";
                case "6":
                    return "损益";
                default:
                    return "null";
            }
        } else if (name.equals("3")) {
            switch (t) {
                case "1":
                    return "资产";
                case "2":
                    return "负债";
                case "3":
                    return "净资产";
                case "4":
                    return "收入费用";
                case "5":
                    return "收入费用";
                default:
                    return "null";
            }
        } else if (name.equals("4")) {
            switch (t) {
                case "1":
                    return "资产";
                case "2":
                    return "负债";
                case "3":
                    return "共同";
                case "4":
                    return "权益";
                case "5":
                    return "成本";
                case "6":
                    return "损益";
                default:
                    return "null";
            }
        } else if (name.equals("5")) {
            switch (t) {
                case "1":
                    return "资产";
                case "2":
                    return "负债";
                case "3":
                    return "共同";
                case "4":
                    return "权益";
                case "5":
                    return "成本";
                case "6":
                    return "损益";
                default:
                    return "null";
            }
        }
        return "null";
    }

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
//            con = DriverManager.getConnection("jdbc:mysql://222.77.187.138:10000/finance?characterEncoding=UTF8&autoReconnect=true", "finance", "aabbcc123");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/finance?characterEncoding=UTF8&autoReconnect=true", "root", "123@admin");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 获取一个文件夹下的所有文件全路径
     *
     * @param path
     * @param listFileName
     */
    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                completNames[i] = path + names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
        for (File a : files) {
            if (a.isDirectory()) {//如果文件夹下有子文件夹，获取子文件夹下的所有文件全路径。
                getAllFileName(a.getAbsolutePath() + "\\", listFileName);
            }
        }
    }
}
