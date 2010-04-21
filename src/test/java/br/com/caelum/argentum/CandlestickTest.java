package br.com.caelum.argentum;

import java.util.Calendar;

import org.junit.Test;


public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void testPrecoMaximoNaoPodeSerMenorQueMinimo(){
		Calendar hoje=Calendar.getInstance();
		new Candle(10,15, 20,10,100,hoje);
	}
}
