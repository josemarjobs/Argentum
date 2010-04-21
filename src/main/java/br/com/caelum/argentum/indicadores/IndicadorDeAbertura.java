package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class IndicadorDeAbertura implements Indicador {

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getAbertura();
	}

	@Override
	public String toString() {
		return "Indicador de Abertura";
	}

	
}