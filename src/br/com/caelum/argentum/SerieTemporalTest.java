package br.com.caelum.argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;


public class SerieTemporalTest {

	@Test(expected=IndexOutOfBoundsException.class)
	public void testSerietemporalIndexErrado(){
		Candle c= new Candle(10,20,10,20,200,Calendar.getInstance());
		List<Candle> candles=Arrays.asList(c);
		SerieTemporal st = new SerieTemporal(candles);
		st.getCandle(3);
	}
}
