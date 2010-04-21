package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	public boolean isMesmoDia(Calendar c1, Calendar c2){
		return c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH)
			&& c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)
			&& c2.get(Calendar.YEAR)==c2.get(Calendar.YEAR);
	}
	
	/**
	 * Construir candle para uma determinada data
	 * @param data
	 * @param negocios
	 * @return
	 */
	public Candle construirCandleParaData(Calendar data,
			List<Negocio> negocios) {
		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(
				negocios.size() - 1).getPreco();
		double min = negocios.isEmpty() ? 0 : Double.MAX_VALUE;
		double max = negocios.isEmpty() ? 0 : Double.MIN_VALUE;
		double volume = 0.0;
		for (Negocio negocio : negocios) {
			if (negocio.getPreco() < min) {
				min = negocio.getPreco();
			} 
			if (negocio.getPreco() > max) {
				max = negocio.getPreco();
			}
			volume += negocio.getVolume();
		}

		return new Candle(abertura, fechamento, min, max, volume, data);
	}
	/**
	 * Construir candles, para cada dia
	 * @param todosNegocios
	 */
	public List<Candle> construirCandles(List<Negocio> todosNegocios){
		List<Candle> candles= new ArrayList<Candle>();
		List<Negocio> negociosMesmoDia=new ArrayList<Negocio>();
		Calendar dataNegocio=todosNegocios.isEmpty()?Calendar.getInstance():todosNegocios.get(0).getData();
		
		for (Negocio n : todosNegocios) {
			if (dataNegocio.after(n.getData())){
				throw new IllegalStateException("negocios em ordem errada");
			}
			if(!isMesmoDia(n.getData(),dataNegocio)){
				fechaCandle(candles, negociosMesmoDia, dataNegocio);
				negociosMesmoDia=new ArrayList<Negocio>();
				dataNegocio=n.getData();
			}
			negociosMesmoDia.add(n);
			
		}
		fechaCandle(candles, negociosMesmoDia, dataNegocio);
		
		return candles; 
	}

	private void fechaCandle(List<Candle> candles,
			List<Negocio> negociosMesmoDia, Calendar dataNegocio) {
		candles.add(construirCandleParaData(dataNegocio, negociosMesmoDia));
	}
	
	
}
