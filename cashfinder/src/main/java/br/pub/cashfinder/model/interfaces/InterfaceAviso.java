/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pub.cashfinder.model.interfaces;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author israel
 */
public interface InterfaceAviso {

    final boolean SOMATORIO = true;

    /**
     * Retorna true quando valor total igual ao somatório dos valores
     *
     * @param valor
     * @return boolean
     */
    boolean addValor(BigDecimal valor) throws UnsupportedOperationException;

    /**
     * Retorna verdadeiro se o valor pode ser retirado
     *
     * @param valor
     * @return booelan
     */
    boolean removeValor(BigDecimal valor);
    
    List<BigDecimal> list();
}
