/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author israel
 */
public abstract class AbstractAviso implements InterfaceAviso {

	private Date data;

	private BigDecimal valorTotal = new BigDecimal(0);

	private List<BigDecimal> valores = new ArrayList<>();
	private BigDecimal somatorio = new BigDecimal(0);

	public AbstractAviso(BigDecimal valorTotal, Date data) {
		this.valorTotal = valorTotal;
		this.data = data;
	}

	@Override
	public List<BigDecimal> list() {
		// TODO Auto-generated method stub
		return this.valores;
	}

	@Override
	public boolean addValor(BigDecimal valor) throws UnsupportedOperationException {
		// ADICIONANDO VALOR AO SOMATÓRIO
		this.somatorio = this.somatorio.add(valor);
		// VERIFICANDO SE SOMATÓRIO ULTRAPASSA O VALOR TOTAL
		if (this.somatorio.compareTo(this.valorTotal) > 0) {
			// SE SIM RESTAURE O VALOR ANTERIOR E LANCE EXCEÇÃO
			this.somatorio = this.somatorio.subtract(valor);
			throw new UnsupportedOperationException("Valor superior ao valor total");
		} else if (this.valorTotal.equals(this.somatorio)) {
			// SE NÃO E VALOR FOR IGUAL AO VALOR TOTAL ENTÃO RETORNE TRUE
			this.valores.add(valor);
			return SOMATORIO;
		} else {
			// CASO CONTRÁRIO SÓ ADICIONE
			this.valores.add(valor);
		}
		return !SOMATORIO;
	}

	@Override
	public boolean removeValor(BigDecimal valor) {
		// SE A LISTA NÃO FOR VAZIA
		if (!this.valores.isEmpty()) {
			// E SE O VALOR PODE SER REMOVIDO
			if (this.valores.remove(valor)) {
				// SUBTRAIA DO SOMATÓRIO
				this.somatorio = this.somatorio.subtract(valor);
			}
			return true;
		}
		return false;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<BigDecimal> getValores() {
		return valores;
	}

	public void setValores(List<BigDecimal> valores) {
		this.valores = valores;
	}

}
