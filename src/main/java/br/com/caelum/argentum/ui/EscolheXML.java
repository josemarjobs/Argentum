package br.com.caelum.argentum.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.LeitorXML;

public class EscolheXML {

	public List<Negocio> escolhe() {
		try{
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileFilter(new FileNameExtensionFilter("Apenas XML",
					"xml"));
			int retorno = fileChooser.showOpenDialog(null);

			if (retorno == JFileChooser.APPROVE_OPTION) {
				FileReader reader = new FileReader(fileChooser
						.getSelectedFile());
				List<Negocio> negocios = new LeitorXML().carrega(reader);
				return negocios;
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return Collections.emptyList();
			
	}

	public static void main(String[] args) {
		new EscolheXML().escolhe();
	}
}
