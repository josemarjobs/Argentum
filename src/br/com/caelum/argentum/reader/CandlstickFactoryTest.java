package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class CandlstickFactoryTest {

	@Test
	public void testSimplesSequenciaDeNegocios() {
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3,
				negocio4);

		CandlstickFactory fabrica = new CandlstickFactory();
		Candle candle = fabrica.construirCandleParaData(hoje, negocios);

		Assert.assertEquals(40.5, candle.getAbertura());
		Assert.assertEquals(42.3, candle.getFechamento());
		Assert.assertEquals(39.8, candle.getMinimo());
		Assert.assertEquals(45.0, candle.getMaximo());
		Assert.assertEquals(16760.0, candle.getVolume());

	}

	@Test
	public void testSemNegocios() {
		Calendar hoje = Calendar.getInstance();
		List<Negocio> negocios = Arrays.asList();
		CandlstickFactory fabrica = new CandlstickFactory();
		Candle candle = fabrica.construirCandleParaData(hoje, negocios);

		Assert.assertEquals(0.0, candle.getVolume());
	}

	@Test
	public void testComApenasUmNegocio() {
		Calendar hoje = Calendar.getInstance();

		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		List<Negocio> negocios = Arrays.asList(negocio1);
		CandlstickFactory fabrica = new CandlstickFactory();
		Candle candle = fabrica.construirCandleParaData(hoje, negocios);

		Assert.assertEquals(40.5, candle.getAbertura());
		Assert.assertEquals(40.5, candle.getFechamento());
		Assert.assertEquals(40.5, candle.getMinimo());
		Assert.assertEquals(40.5, candle.getMaximo());
		Assert.assertEquals(4050.0, candle.getVolume());

	}

	@Test
	public void testCompraMesmoDiaCalendar() {
		CandlstickFactory factory = new CandlstickFactory();
		Calendar hoje = Calendar.getInstance();
		Calendar hoje2 = Calendar.getInstance();

		Assert.assertTrue(factory.isMesmoDia(hoje, hoje2));
	}

	@Test
	public void testConstruirCandlesDeMuitosDias() {
		Calendar hoje = Calendar.getInstance();

		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		Negocio negocio5 = new Negocio(48.8, 100, amanha);
		Negocio negocio6 = new Negocio(49.3, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		Negocio negocio7 = new Negocio(51.8, 100, depois);
		Negocio negocio8 = new Negocio(52.3, 100, depois);

		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3,
				negocio4, negocio5, negocio6, negocio7, negocio8);
		
		CandlstickFactory fabrica=new CandlstickFactory();
		List<Candle> candles=fabrica.construirCandles(negocios);
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(40.5, candles.get(0).getAbertura());
		Assert.assertEquals(42.3, candles.get(0).getFechamento());
		Assert.assertEquals(48.8, candles.get(1).getAbertura());
		Assert.assertEquals(49.3, candles.get(1).getFechamento());
		Assert.assertEquals(51.8, candles.get(2).getAbertura());
		Assert.assertEquals(52.3, candles.get(2).getFechamento());
		
		
	}
	
	@Test
	public void testConstruirCandlesDeMuitosDiasComListaVazia() {

		List<Negocio> negocios = new ArrayList<Negocio>();
		CandlstickFactory fabrica = new CandlstickFactory();
		List<Candle> candle = fabrica.construirCandles(negocios);
		
		Assert.assertEquals(1, candle.size());
		Assert.assertEquals(0.0, candle.get(0).getAbertura());
		Assert.assertEquals(0.0, candle.get(0).getFechamento());
		Assert.assertEquals(0.0, candle.get(0).getMinimo());
		Assert.assertEquals(0.0, candle.get(0).getMaximo());
		Assert.assertEquals(0.0, candle.get(0).getVolume());

	}
	
	@Test(expected=IllegalStateException.class)
	public void testConstruirCandleForeDeEmOrdem(){
		
		Calendar amanha = Calendar.getInstance();
		
		Calendar hoje = (Calendar) amanha.clone();
		Calendar depois = Calendar.getInstance();
		hoje.add(Calendar.DAY_OF_MONTH, 1);
		depois.add(Calendar.DAY_OF_MONTH, 1);
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);

		
		Negocio negocio5 = new Negocio(48.8, 100, amanha);
		Negocio negocio6 = new Negocio(49.3, 100, amanha);


		Negocio negocio7 = new Negocio(51.8, 100, depois);
		Negocio negocio8 = new Negocio(52.3, 100, depois);

		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3,
				negocio4, negocio5, negocio6, negocio7, negocio8);
		
		CandlstickFactory fabrica=new CandlstickFactory();
		@SuppressWarnings("unused")
		List<Candle> candles=fabrica.construirCandles(negocios);

		
	}
}
