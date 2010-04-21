package br.com.caelum.argentum;

import java.util.Calendar;

public final class Negocio implements Comparable<Negocio> {

	private final double preco;
	private final int quantidade;
	private final Calendar data;

	public Negocio(double preco, int quantidade, Calendar data) {
		if (data == null)
			throw new IllegalArgumentException("A data nao pode ser nulla");
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		Calendar clone = (Calendar) data.clone();
		return clone;
	}

	public double getVolume() {
		return this.preco * this.quantidade;
	}

	@Override
	public int compareTo(Negocio o) {
		return this.getData().compareTo(o.getData());
	}

}
