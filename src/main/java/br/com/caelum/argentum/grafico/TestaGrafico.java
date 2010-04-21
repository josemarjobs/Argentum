package br.com.caelum.argentum.grafico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.indicadores.IndicadorDeFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class TestaGrafico {

	static SerieTemporal criaSerie(double... valores) {
		List<Candle> candle = new ArrayList<Candle>();
		for (double d : valores) {
			candle.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candle);
	}

	public static void main(String[] args) throws IOException {
		SerieTemporal serie = criaSerie(1, 2, 3, 4, 5, 6, 7, 8,8, 9,9, 4, 3, 2, 1,
				2, 2, 4, 5, 6, 7, 8, 9, 10, 11, 10, 6, 3, 2, 6, 7, 8, 9);
		
		GeradorDeGraficos g = new GeradorDeGraficos(serie, 3, 32);
		g.criaGrafico("Media movel simples");
		g.plotarGrafico(new MediaMovelSimples(new IndicadorDeFechamento()));
		g.salvar(new FileOutputStream(new File("grafico.png")));

	}
}
