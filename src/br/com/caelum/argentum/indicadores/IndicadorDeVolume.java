package br.com.caelum.argentum.indicadores;

import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.SerieTemporal;

public class IndicadorDeVolume extends SerieTemporal implements Indicador{

	public IndicadorDeVolume(List<Candle> candles) {
		super(candles);
	}

	@Override
	public double getValor(int pos) {
		return super.getCandle(pos).getVolume();
	}

}
