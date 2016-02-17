/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Cliente
 */
public class Combination {

    public static Aviso getAviso(Relatorio relatorio, BigDecimal valor) throws IOException {
        int name = 0;
        Aviso aviso = new Aviso(valor, relatorio.getData());
        if (relatorio.valorRestante().compareTo(valor) == 0) {
            for (BigDecimal nstatus : relatorio.getValores()) {
                //allCombList.add(new ArrayList<>(Arrays.asList(nstatus)));
                try {
                // SE FOR POSSÍVEL ADICIONAR E RETORNO FOR TRUE, O QUE
                    // INDICA QUE O VALOR TOTAL FOI ENCONTRADO
                    if (aviso.addValor((BigDecimal) nstatus)) {
                        // ADICIONE O AVISO AOS REMOVIDOS
                        relatorio.addAviso(aviso);
                        FileManager.deleteAll();
                        // RETORNE O AVISO ENCONTRADO
                        return aviso;
                    }
                    aviso = new Aviso(valor, relatorio.getData());
                } catch (UnsupportedOperationException ex) {
                    // CASO O VALOR ULTRAPASSE, PARE A EXECUÇÃO DO FOR INTERNO
                    aviso = new Aviso(valor, relatorio.getData());
                }
            }
        } else {
            for (BigDecimal nstatus : relatorio.getValores()) {
                //allCombList.add(new ArrayList<>(Arrays.asList(nstatus)));
                try {
                // SE FOR POSSÍVEL ADICIONAR E RETORNO FOR TRUE, O QUE
                    // INDICA QUE O VALOR TOTAL FOI ENCONTRADO
                    if (aviso.addValor((BigDecimal) nstatus)) {
                        // ADICIONE O AVISO AOS REMOVIDOS
                        relatorio.addAviso(aviso);
                        FileManager.deleteAll();
                        // RETORNE O AVISO ENCONTRADO
                        return aviso;
                    }
                    aviso = new Aviso(valor, relatorio.getData());
                } catch (UnsupportedOperationException ex) {
                    // CASO O VALOR ULTRAPASSE, PARE A EXECUÇÃO DO FOR INTERNO
                    aviso = new Aviso(valor, relatorio.getData());
                }
                List l = new ArrayList<>(Arrays.asList(nstatus));
                // SE O VALOR TOTAL DA COMBINAÇÃO FOR MENOR QUE O VALOR TOTAL GRAVA NO ARQUIVO
                if (Aviso.getTotal(l).compareTo(valor) < 0) {
                    FileManager.write(name++, l);
                }
            }

            for (int nivel = 1; nivel < relatorio.getValores().size(); nivel++) {
                int nameAntes = name;
                for (int i = 0; i < nameAntes; i++) {
                    List<Comparable> novo = FileManager.getList(i);
                    novo.add(relatorio.getValores().get(nivel));
                    for (Comparable comp : novo) {
                        try {
                        // SE FOR POSSÍVEL ADICIONAR E RETORNO FOR TRUE, O QUE
                            // INDICA QUE O VALOR TOTAL FOI ENCONTRADO
                            if (aviso.addValor((BigDecimal) comp)) {
                                // ADICIONE O AVISO AOS REMOVIDOS
                                relatorio.addAviso(aviso);
                                FileManager.deleteAll();
                                // RETORNE O AVISO ENCONTRADO
                                return aviso;
                            }
                        } catch (UnsupportedOperationException ex) {
                            // CASO O VALOR ULTRAPASSE, PARE A EXECUÇÃO DO FOR INTERNO
                            break;
                        }
                    }
                // SE NÃO FOI ENCONTRADO AVISO ATÉ O MOMENTO ATRIBUA UM NOVO AVISO
                    // AO AVISO ATUAL PARA A PRÓXIMA INTERAÇÃO
                    aviso = new Aviso(valor, relatorio.getData());
                    // SE O VALOR TOTAL DA COMBINAÇÃO FOR MENOR QUE O VALOR TOTAL GRAVA NO ARQUIVO
                    if (Aviso.getTotal(novo).compareTo(valor) < 0) {
                        FileManager.write(name++, novo);
                    }
                }
            }
        }
        FileManager.deleteAll();
        return null;
    }

}
