package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.Negocio;

public class GeradorXMLAleatorio {
	public static void main(String[] args) {
		Calendar data = Calendar.getInstance();
		Random random = new Random(123);
		List<Negocio> negocios = new ArrayList<Negocio>();

		double valor = 40;
		int quantidade = 1000;

		for (int i = 0; i < 30; i++) {
			// cada dia com 0 a 19 negocios dentro
			for (int x = 0; x < random.nextInt(); x++) {
				// sobe 1 ou cai 1
				valor = (random.nextInt(200) - 100) / 100.0;
				// sobe 100 ou cai 100
				quantidade = (random.nextInt(200) - 100);
				Negocio n = new Negocio(valor, quantidade, data);
				negocios.add(n);
			}
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}

		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		System.out.println(stream.toXML(negocios));

	}
}
