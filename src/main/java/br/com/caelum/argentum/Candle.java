package br.com.caelum.argentum;

import java.util.Calendar;

public final class Candle {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;

	/**
	 * @param abertura
	 * @param fechamento
	 * @param minimo
	 * @param maximo
	 * @param volume
	 * @param data
	 */
	public Candle(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		if(minimo>maximo){
			throw new IllegalArgumentException("O valor Maximo nao pode ser menor que o minimo");
		}
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public boolean isAlta() {
		return this.abertura < this.fechamento;
	}
	
	boolean isBaixa(){
		return this.abertura>this.fechamento;
	}

}
