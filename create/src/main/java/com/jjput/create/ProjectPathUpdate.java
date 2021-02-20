package com.jjput.create;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @作者: JJ
 * @创建时间: 2020/11/1 上午12:47
 * @Version 1.0
 * @描述: 项目更名
 * 注意仅适用mac！！！！
 * 注意仅适用mac！！！！
 * 注意仅适用mac！！！！
 */
public class ProjectPathUpdate {

    //原来的项目名
    private static String sourceProjectName = "finance";
    //新的项目名
    private static String newProjectName = "spirngbasics";

    private static String sourcePath = "com/twj/" + sourceProjectName;
    private static String sourceData = "com.twj." + sourceProjectName;
    //修改后的包路径
    private static String newPath = "com/twj/" + newProjectName;
    private static String newData = "com.twj." + newProjectName;
    //要修改的module
    private static String modules[] = {"business", "eureka", "gateway", "server", "oss"};

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir");
        for (int i = 0; i < modules.length; i++) {
            File fileItems = new File(path + "/" + modules[i] + "/src/main/java/" + sourcePath);
            try {
                //修改所有文件中的包路径
                fdir(new File(path + "/" + modules[i] + "/src/main/"), sourceData, newData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //修改路径
            fileItems.renameTo(new File(path + "/" + modules[i] + "/src/main/java/" + newPath));
        }
        //修改ftl模板
        fdir(new File(path + "/create/src/main/java/com/twj/create/"), sourceData, newData);
        //module下的pom文件修改
        for (int i = 0; i < modules.length; i++) {
            File fileItems = new File(path + "/" + modules[i] + "/pom.xml");
            fFile(fileItems, sourceProjectName, newProjectName);
        }
        fFile(new File(path + "/create/pom.xml"), sourceProjectName, newProjectName);
        fFile(new File(path + "/oss/pom.xml"), sourceProjectName, newProjectName);
        //主pom文件修改
        File fileItems = new File(path + "/pom.xml");
        fFile(fileItems, sourceProjectName, newProjectName);
    }

    static void search(File a, String x, String n) throws IOException {//在文件a中的每行中查找x
        Scanner scan = new Scanner(a, "utf-8");
        StringBuilder stringBuilder = new StringBuilder();
        int k = 0;
        while (true) {
            if (scan.hasNext() == false) break;
            String s = scan.nextLine();
            k++;
            if (s.contains(x)) {
                System.out.println(a.getPath() + " 第" + k + "行 \n     " + s);
                stringBuilder.append(s.replaceAll(x, n) + "\n");
            } else {
                stringBuilder.append(s + "\n");
            }
        }
        //修改文件
        addFile(stringBuilder.toString(), a.getPath());
    }


    static void fFile(File a, String s, String n) throws IOException {//在a下所有文件中查找含有s的行,并将s修改为n
        if (a.isFile()) {//若a是文件，直接查找
            search(a, s, n);
        }
    }

    static void fdir(File a, String s, String n) throws IOException {//在a下所有文件中查找含有s的行,并将s修改为n

        File[] ff = a.listFiles();
        if (ff == null) return;
        for (File it : ff) {
            if (it.isFile()) {//若a是文件，直接查找
                search(it, s, n);
            }
            if (it.isDirectory()) {//若a是目录，则对其目录下的目录或文件继续查找
                fdir(it, s, n);
            }
        }
    }

    /**
     * 覆盖写入
     *
     * @param string
     * @param path
     * @return
     */
    public static boolean addFile(String string, String path) {

        PrintStream stream = null;
        try {

            stream = new PrintStream(path);//写入的文件path
            stream.print(string);//写入的字符串
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
