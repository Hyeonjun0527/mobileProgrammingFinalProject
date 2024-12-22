package com.example.final_project.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {

    public static List<String[]> readGameResults(Context context, String fileName) {
        List<String[]> results = new ArrayList<>();

        try {
            File directory = context.getFilesDir();
            File file = new File(directory, fileName);

            if (!file.exists()) {
                return results; // 파일이 없으면
            }

            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" : ");
                if (parts.length == 2) {
                    results.add(parts);
                }
            }

            reader.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public static int readMoney(Context context, String fileName) {
        try {
            File directory = context.getFilesDir();
            File file = new File(directory, fileName);

            if (!file.exists()) {
                return 0;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String moneyString = reader.readLine();
            reader.close();
            return Integer.parseInt(moneyString);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0; // 오류가 있으면
        }
    }

    public static void saveMoney(Context context, String fileName, int money) {
        try {
            File directory = context.getFilesDir();
            File file = new File(directory, fileName);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(String.valueOf(money).getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean readItemState(Context context, String fileName, String itemName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(itemName) && parts[1].equals("true")) {
                    reader.close();
                    fis.close();
                    return true;
                }
            }
            reader.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void saveItemState(Context context, String fileName, String itemName, boolean state) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            StringBuilder fileContent = new StringBuilder();
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(itemName + ":")) {
                        fileContent.append(line).append("\n");
                    }
                }
                reader.close();
                fis.close();
            }

            // 새로운 상태 추가
            fileContent.append(itemName).append(":").append(state).append("\n");

            // 파일 저장
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileContent.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Boolean> readAllItemStates(Context context, String fileName) {
        Map<String, Boolean> itemStates = new HashMap<>();
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    itemStates.put(parts[0], Boolean.parseBoolean(parts[1]));
                }
            }
            reader.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemStates;
    }


}
