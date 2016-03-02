/**
 * 
 */
package br.pub.cashfinder.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import br.pub.cashfinder.view.CustomTableModel;
import ilog.concert.IloConstraint;
import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;

/**
 * @author israel
 *
 */
public class Model {

	private int sizeX;
	private int sizeY;
	private Scanner reader;
	private AbstractTableModel model;

	/**
	 * @throws FileNotFoundException
	 * 
	 */
	public Model(String fileName) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		this.reader = new Scanner(new File(fileName));
	}

	public AbstractTableModel solve() throws Exception {
		try {

			IloCplex cplex = new IloCplex();

			sizeY = reader.nextInt();
			sizeX = reader.nextInt();

			double search[] = new double[sizeY];
			double relatorioSistema[] = new double[sizeX];

			for (int i = 0; i < sizeY; i++) {
				search[i] = reader.nextDouble();
			}

			for (int i = 0; i < sizeX; i++) {
				relatorioSistema[i] = reader.nextDouble();
			}

			// VARIÁVEIS binárias
			IloIntVar x[][] = new IloIntVar[sizeX][];
			IloIntVar y[] = cplex.intVarArray(sizeY, 0, 1);

			// INICIALIZANDO AS VARIAVEIS
			for (int k = 0; k < sizeY; k++) {
				y[k].setName("y[" + k + "]");
				cplex.add(y[k]);
			}

			for (int i = 0; i < sizeX; i++) {
				x[i] = cplex.intVarArray(sizeY, 0, 1);
				for (int k = 0; k < sizeY; k++) {
					x[i][k].setName("x[" + i + "," + k + "]");
					cplex.add(x[i][k]);
				}
			}

			// Função Objetivo
			IloNumExpr obj = cplex.numExpr();

			for (int i = 0; i < sizeY; i++) {
				obj = cplex.sum(obj, y[i]);
			}

			cplex.addMaximize(obj);

			Set<IloConstraint> constraints = new HashSet<IloConstraint>();
			for (int k = 0; k < sizeY; k++) {
				// variável do somatorio
				IloNumExpr somatorio = cplex.numExpr();
				for (int i = 0; i < sizeX; i++) {
					somatorio = cplex.sum(somatorio, cplex.prod(relatorioSistema[i], x[i][k]));
				}
				constraints.add(cplex.addEq(cplex.prod(search[k], y[k]), somatorio));
			}

			for (int i = 0; i < sizeX; i++) {
				// variável do somatorio
				IloNumExpr somatorio = cplex.numExpr();
				for (int k = 0; k < sizeY; k++) {
					somatorio = cplex.sum(somatorio, x[i][k]);
				}
				constraints.add(cplex.addLe(somatorio, 1));
			}

			cplex.exportModel("teste.lp");

			// solve
			if (cplex.solve()) {

				// Imprimindo a solução
				double header[] = new double[sizeY];

				for (int i = 0; i < sizeY; i++) {
					header[i] = search[i] * cplex.getValue(y[i]);
				}

				double data[][] = new double[sizeX][sizeY];
				for (int i = 0; i < sizeX; i++) {
					for (int k = 0; k < sizeY; k++) {
						int v = (int) cplex.getValue(x[i][k]);
						data[i][k] = v * relatorioSistema[i];
					}
				}
				this.model = new CustomTableModel(header, data);
			} else {
				throw new Exception("The model didn't find solutions");
			}

		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
}
