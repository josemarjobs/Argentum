package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public interface Indicador {
	double calcula(int posicao, SerieTemporal serie);
}
