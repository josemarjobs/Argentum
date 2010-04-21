package br.com.caelum.argentum.indicadores;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.argentum.Candle;

public class IndicadorDeFechamentoTest {
	
	@Test
	public void testIndicadorDeFechamento(){
		Candle candle1 = new Candle(10, 11, 12, 13, 14, Calendar.getInstance());
		Candle candle2 = new Candle(20, 21, 22, 23, 24, Calendar.getInstance());
		Candle candle3 = new Candle(30, 31, 32, 33, 34, Calendar.getInstance());
		Candle candle4 = new Candle(40, 41, 42, 43, 44, Calendar.getInstance());
		List<Candle> candles = Arrays.asList(candle1, candle2, candle3, candle4);
		IndicadorDeFechamento idf = new IndicadorDeFechamento(candles);
		
		Assert.assertEquals(11.0, idf.getValor(0));
		Assert.assertEquals(21.0, idf.getValor(1));
		Assert.assertEquals(31.0, idf.getValor(2));
		
	}

	
}
