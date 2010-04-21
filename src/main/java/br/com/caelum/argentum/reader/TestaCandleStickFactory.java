package br.com.caelum.argentum.reader;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class TestaCandleStickFactory {
	public static void main(String[] args) {

		Calendar hoje = Calendar.getInstance();

		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);

		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3,
				negocio4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candle candle = fabrica.construirCandleParaData(hoje, negocios);

		System.out.println(candle.getAbertura());
		System.out.println(candle.getFechamento());
		System.out.println(candle.getMinimo());
		System.out.println(candle.getMaximo());
		System.out.println(candle.getVolume());
		System.out.println(candle.getData().getTime());

	}
}
