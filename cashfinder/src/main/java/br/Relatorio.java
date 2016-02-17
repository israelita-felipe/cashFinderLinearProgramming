package br;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author israel
 */
public class Relatorio extends AbstractAviso {

	private final ArrayList<Aviso> removidos = new ArrayList<>();

	private BigDecimal totalRemovido = new BigDecimal(0);

	public Relatorio(BigDecimal valorTotal, Date data) {
		super(valorTotal, data);
	}

	public BigDecimal valorRestante() {
		return this.getValorTotal().subtract(this.totalRemovido);
	}

	public ArrayList<Aviso> getRemovidos() {
		return removidos;
	}

	/**
	 * Adiciona os removidos de avisos
	 *
	 * @param aviso
	 * @return boolean
	 */
	public boolean addAviso(Aviso aviso) throws UnsupportedOperationException {
		// ADICIONA AO VALOR TOTAL DE REMOVIDOS
		this.totalRemovido = this.totalRemovido.add(aviso.getValorTotal());
		// VERIFICA SE O VALOR REMOVER É MAIOR QUE O PERMITIDO
		if (this.totalRemovido.compareTo(this.getValorTotal()) > 0) {
			// SE SIM RESTAURA O VALOR ORIGINAL E LANÇA EXCESSÃO
			this.totalRemovido = this.totalRemovido.subtract(aviso.getValorTotal());
			throw new UnsupportedOperationException("Valor do aviso é maior que o esperado");
		} else if (totalRemovido.equals(this.getValorTotal())) {
			// SE NÃO E O VALOR TOTAL FOR IQUAL AO REMOVIDO
			// ADICIONA AOS REMOVIDOS
			this.removidos.add(aviso);
			// REMOVE DO TOTAL
			this.removeAviso(aviso);
			// RETORNA TRUE
			return true;
		} else {
			// ADICIONA AOS REMOVIDOS
			this.removidos.add(aviso);
			// REMOVE DO TOTAL
			this.removeAviso(aviso);
		}
		return false;
	}

	/**
	 * Remove um aviso dos valores totais do relatório
	 *
	 * @param aviso
	 * @return
	 */
	public boolean removeAviso(Aviso aviso) {
		// PARA CADA VALOR DO AVISO
		// REMOVE O VALOR DO RELATÓRIO ATUAL
		return aviso.getValores().stream().noneMatch((f) -> (!removeValor(f)));
	}

	/**
	 * Busca um aviso no relatório no qual a soma dos possíveis valores é igual
	 * a <code>Float valor</code> passado como parâmetro
	 *
	 * @param valor
	 * @return
	 * @throws java.io.IOException
	 */
	public Aviso buscar(BigDecimal valor) throws IOException {
		return Combination.getAviso(this, valor);
	}

	@Override
	public String toString() {
		return "Relatorio{" + "Data=" + getData() + ", Valor total=" + getValorTotal() + ", Impostos=" + getValores()
				+ '}';
	}

}
