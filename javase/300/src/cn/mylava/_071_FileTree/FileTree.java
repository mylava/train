package cn.mylava._071_FileTree;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 16/3/31.
 * 递归树形目录、文件搜索
 */
public class FileTree {
    public static void main(String[] args) {
        /*
        System.out.println("请输入想要打印的目录:");
        Scanner scanner = new Scanner(System.in);
        String dirName = scanner.nextLine();
        File file = new File(dirName);

        paintTree(file,1);
        */

        System.out.println("请输入想要搜索的目录:");
        Scanner scanner = new Scanner(System.in);
        String dirName = scanner.nextLine();
        File dir = new File(dirName);
        System.out.println("请输入想要搜索的文件:");
        scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        List<File> list = new LinkedList<>();
        searchFile(dir, fileName,list);
        for(File temp : list){
            System.out.println(temp.getAbsolutePath());
        }
    }
    //打印树形目录
    public static void paintTree(File file, int level) {
        //根据level决定打印-的个数
        for (int i = 0; i < level; i++) {
            if(i==0){
                System.out.print("|");
            }
            System.out.print("-");
        }
        System.out.println(file.getName());
        //递归查询子目录
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File temp : files) {
                paintTree(temp, level + 1);
            }
        }
    }
    //搜索文件
    public static List<File> searchFile(File directory, String fileName, final List<File> list) {
        //如果是目录
        if (directory.isDirectory()) {
            File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (directory.getName().toLowerCase().indexOf(fileName.toLowerCase()) > -1) {
                        if (!list.contains(directory)){
                            list.add(directory);
                        }
                    }
                    return true;
                }
            });
            //递归子目录
            for (File temp : files) {
                searchFile(temp, fileName,list);
            }
        } else {
            if (directory.getName().toLowerCase().indexOf(fileName.toLowerCase()) > -1) {
                if (!list.contains(directory)){
                    list.add(directory);
                }
            }
        }

        return list;
    }
}
