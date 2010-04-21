package br.com.caelum.argentum.reader;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class CandleSemNegocio {
	public static void main(String[] args) {

		Calendar hoje = Calendar.getInstance();

		List<Negocio> negocios = Arrays.asList();

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
