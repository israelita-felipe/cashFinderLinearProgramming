/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Cliente
 */
public class FileManager {

    private static FileWriter writer;
    private static Scanner reader;
    private static final String path = "COMB/";

    public static boolean write(int sequence, List<Comparable> toWrite) throws IOException {
        writer = new FileWriter(path + String.valueOf(sequence));
        String toFile = "";
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < toWrite.size(); i++) {
            total = total.add((BigDecimal) toWrite.get(i));
            if (i < toWrite.size() - 1) {
                toFile = toFile + String.valueOf((BigDecimal) toWrite.get(i)) + "#";
            } else {
                toFile = toFile + String.valueOf((BigDecimal) toWrite.get(i));
            }
        }
        toFile = toFile + "=" + total;
        writer.write(toFile);
        writer.close();
        return false;
    }

    public static Aviso read(int sequence) throws FileNotFoundException {
        File file = new File(path + String.valueOf(sequence));
        reader = new Scanner(file);
        String[] arquivo = reader.nextLine().split("=");
        Aviso aviso = new Aviso(new BigDecimal(arquivo[1]), new Date());
        for (String value : arquivo[0].split("#")) {
            aviso.addValor(new BigDecimal(value));
        }
        reader.close();
        //file.delete();
        return aviso;
    }

    public static void deleteAll() {
        new Thread(() -> {
            File folder = new File("COMB");
            if (folder.isDirectory()) {
                File[] sun = folder.listFiles();
                for (File toDelete : sun) {
                    toDelete.delete();
                }
            }
        }).start();
    }

    public static List<Comparable> getList(int sequence) throws FileNotFoundException {
        List<Comparable> list = new ArrayList<>();
        for (BigDecimal f : read(sequence).getValores()) {
            list.add(f);
        }
        return list;
    }
}
