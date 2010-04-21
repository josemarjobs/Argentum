package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class IndicadorDeVolume implements Indicador{

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getVolume();
	}

	@Override
	public String toString() {
		return "Indicador de Volume";
	}
}
