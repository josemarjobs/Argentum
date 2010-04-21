package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.grafico.GeradorDeGraficos;
import br.com.caelum.argentum.indicadores.IndicadorDeAbertura;
import br.com.caelum.argentum.indicadores.IndicadorDeFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class ArgentumUI {
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	private JPanel painelBotoes;
	private JTabbedPane abas;
	private JFormattedTextField campoDataInicio;
	private JLabel labelData;
	private JCheckBoxMenuItem mediaFechamento;
	private JCheckBoxMenuItem mediaAbertura;

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR"));
		new ArgentumUI().montaTela();
	}

	public void montaTela() {
		montaJanela();
		montaMenu();
		montaPainelPrincipal();
		montaAbas();
		montaTabelaComScroll();
		montaTitulo();
		montaPainelBotoes();
		montaLabelData();
		montaCampoData();
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}

	private void montaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel(new BorderLayout());
		janela.add(painelPrincipal);
	}

	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				carregarDados();
			}
		});
		painelBotoes.add(botaoCarregar);
	}

	private void montaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelBotoes.add(botaoSair);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}

	private void montaTabelaComScroll() {
		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);
		abas.setComponentAt(0, scroll);
	}

	private void montaTitulo() {
		JLabel titulo = new JLabel("Lista de Negocios");
		titulo.setFont(new Font("Verdada", Font.BOLD, 25));
		titulo.setForeground(new Color(50, 50, 100));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		painelPrincipal.add(titulo, BorderLayout.NORTH);
	}

	private void montaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void montaAbas() {
		abas = new JTabbedPane();
		abas.addTab("Tabela de Negocios", null);
		abas.addTab("Grafico", null);
		painelPrincipal.add(abas);
	}

	private void montaCampoData() {
		try {
			MaskFormatter mask = new MaskFormatter("##/##/####");
			mask.setPlaceholderCharacter('_');

			campoDataInicio = new JFormattedTextField(mask);
			painelBotoes.add(campoDataInicio);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}

	private void montaLabelData() {
		labelData = new JLabel("Data de Inicio:");
		painelBotoes.add(labelData);
	}

	private void montaMenu() {
		JMenuBar menuBar = new JMenuBar();
		janela.setJMenuBar(menuBar);

		JMenu menuIndicadores = new JMenu("Indicadores");
		menuBar.add(menuIndicadores);

		mediaFechamento = new JCheckBoxMenuItem(
				"Media Movel Simples de Fechamento");
		menuIndicadores.add(mediaFechamento);

		mediaAbertura = new JCheckBoxMenuItem("Media Movel Simples de Abertura");
		menuIndicadores.add(mediaAbertura);
	}

	private void carregarDados() {
		List<Negocio> negocios = new EscolheXML().escolhe();
		filtrarPorData(negocios);
		NegociosTableModel ntm = new NegociosTableModel(negocios);
		ReflectionTableModel rtm=new ReflectionTableModel(negocios);
		tabela.setModel(rtm);

		CandlestickFactory candlestickFactory = new CandlestickFactory();

		List<Candle> candles = candlestickFactory.construirCandles(negocios);

		SerieTemporal serie = new SerieTemporal(candles);

		GeradorDeGraficos geradorDeGraficos = new GeradorDeGraficos(serie, 2,
				serie.getTotal() - 1);
		geradorDeGraficos.criaGrafico("Media Movel Simples");

		if (mediaFechamento.isSelected()) {
			geradorDeGraficos.plotarGrafico(new MediaMovelSimples(
					new IndicadorDeFechamento()));
		}
		
		if(mediaAbertura.isSelected()){
			geradorDeGraficos.plotarGrafico(new MediaMovelSimples(
					new IndicadorDeAbertura()));
		}
		
		JPanel grafico = geradorDeGraficos.getPanel();
		this.abas.setComponentAt(1, grafico);
	}

	private void filtrarPorData(List<Negocio> negocios) {
		try {
			String valor = (String) campoDataInicio.getValue();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			Date dataInicio = formato.parse(valor);

			Iterator<Negocio> it = negocios.iterator();
			while (it.hasNext()) {
				if (it.next().getData().getTime().before(dataInicio)) {
					it.remove();
				}
			}

		} catch (Exception e) {
			campoDataInicio.setValue(null);
		}
	}
}
