/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author israel
 */
public class Aviso extends AbstractAviso {

    public Aviso(BigDecimal valorTotal, Date data) {
        super(valorTotal, data);
    }

    @Override
    public String toString() {
        return ""+new DecimalFormat("R$ #,##0.00").format(this.getValorTotal());
    }    
    public static BigDecimal getTotal(List<Comparable> list){
        BigDecimal total = new BigDecimal(0);
        for(Comparable b:list){
            total = total.add((BigDecimal)b);
        }
        return total;
    }
}
