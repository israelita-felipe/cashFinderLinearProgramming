/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pub.cashfinder.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Israel Araújo
 */
public class Retorno {

    public static Relatorio getRetorno(String path) throws FileNotFoundException, ParseException, UnsupportedOperationException {
        List<String> arquivo = readLines(path);
        Date data = getData(arquivo.get(0));
        BigDecimal total = getTotal(arquivo.get(arquivo.size() - 1));
        List<BigDecimal> valores = getValores(arquivo);
        Relatorio r = new Relatorio(total, data);
        r.setValores(valores);
        return r;
    }

    private static List<String> readLines(String path) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(new FileReader(path));
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    private static Date getData(String line) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(line.substring(65, 73));
    }

    public static BigDecimal format(String toFloat) {
        String cents = toFloat.substring(toFloat.length() - 2, toFloat.length());
        String integer = toFloat.substring(0, toFloat.length() - 2);
        return new BigDecimal(integer + "." + cents);
    }

    private static List<BigDecimal> getValores(List<String> valores) {
        List<BigDecimal> list = new ArrayList<>();
        for (int i = 1; i < valores.size() - 1; i++) {
            list.add(format(valores.get(i).substring(81, 93)));
        }
        return list;
    }

    private static BigDecimal getTotal(String line) {
        return format(line.substring(8, 24));
    }

}
