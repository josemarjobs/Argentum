package br.com.caelum.argentum.indicadores;

//import br.com.caelum.argentum.Candle;
//import br.com.caelum.argentum.SerieTemporal;

public class MediaMovelSimples {

	/*
	 * public double calcula(int posicao, SerieTemporal serie){ double soma=0.0;
	 * for (int i=posicao;i>=posicao-2;i--){ Candle c=serie.getCandle(i);
	 * soma+=c.getFechamento(); //soma+=c.getValor(); } return soma/3; }
	 * 
	 * /
	 */
	public double calcula(int posicao, Indicador serie) {
		double soma = 0.0;
		for (int i = posicao; i >= posicao - 2; i--) {
			soma += serie.getValor(i);
		}
		return soma / 3.0;
	}

}
