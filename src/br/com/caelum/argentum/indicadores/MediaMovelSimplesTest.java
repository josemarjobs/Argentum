package br.com.caelum.argentum.indicadores;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.argentum.Candle;

public class MediaMovelSimplesTest {

	@Test
	public void testMediaMovelSimplesComIndicadorDeFechamento() {
		Candle candle1 = new Candle(10, 11, 12, 13, 14, Calendar.getInstance());
		Candle candle2 = new Candle(20, 21, 22, 23, 24, Calendar.getInstance());
		Candle candle3 = new Candle(30, 31, 32, 33, 34, Calendar.getInstance());
		Candle candle4 = new Candle(40, 41, 42, 43, 44, Calendar.getInstance());
		List<Candle> candles = Arrays
				.asList(candle1, candle2, candle3, candle4);
		IndicadorDeFechamento idf = new IndicadorDeFechamento(candles);
		MediaMovelSimples mms = new MediaMovelSimples();

		Assert.assertEquals(21.0, mms.calcula(2, idf));

	}
	
	@Test
	public void testMediaMovelSimplesComIndicadorDeAbertura() {
		Candle candle1 = new Candle(10, 11, 12, 13, 14, Calendar.getInstance());
		Candle candle2 = new Candle(20, 21, 22, 23, 24, Calendar.getInstance());
		Candle candle3 = new Candle(30, 31, 32, 33, 34, Calendar.getInstance());
		Candle candle4 = new Candle(40, 41, 42, 43, 44, Calendar.getInstance());
		List<Candle> candles = Arrays
				.asList(candle1, candle2, candle3, candle4);
		IndicadorDeAbertura ida = new IndicadorDeAbertura(candles);
		MediaMovelSimples mms = new MediaMovelSimples();

		Assert.assertEquals(20.0, mms.calcula(2, ida));

	}

	
	@Test
	public void testMediaMovelSimplesComIndicadorDeVolume() {
		Candle candle1 = new Candle(10, 11, 12, 13, 14, Calendar.getInstance());
		Candle candle2 = new Candle(20, 21, 22, 23, 24, Calendar.getInstance());
		Candle candle3 = new Candle(30, 31, 32, 33, 34, Calendar.getInstance());
		Candle candle4 = new Candle(40, 41, 42, 43, 44, Calendar.getInstance());
		List<Candle> candles = Arrays
				.asList(candle1, candle2, candle3, candle4);
		IndicadorDeVolume idv = new IndicadorDeVolume(candles);
		MediaMovelSimples mms = new MediaMovelSimples();

		Assert.assertEquals(24.0, mms.calcula(2, idv));

	}
}
