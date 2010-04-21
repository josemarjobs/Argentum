package br.com.caelum.argentum.reader;

import java.io.StringReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.Negocio;

public class LeitorXMLTest {
	@Test
	public void testleitorDeXmlCarregaListaDeNegocio() {
		String xmlDeTeste = "<list>" +
				"<negocio>" +
				"<preco>43.5</preco> " +
				"<quantidade>100</quantidade>" +
				"<data>" +
				"<time>555454646</time>" +
				"</data>" +
				"</negocio>" +
				"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		Assert.assertEquals(1, negocios.size());
		Assert.assertEquals(43.5,negocios.get(0).getPreco());
	}
}
