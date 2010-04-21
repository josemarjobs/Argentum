package br.com.caelum.argentum.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExemploBotoes {

	public static void main(String[] args) {
		new ExemploBotoes().mostra();
	}

	private void mostra() {
		JButton botaoCarregar = new JButton("Carregar XML");
		JButton botaoSair = new JButton("Sair");
		//botaoSair.addActionListener(new EventoBotao());
		botaoSair.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("saindo pelo botao, usando classe anonima");
				System.exit(0);
			}
			
		});
		JPanel panel=new JPanel();
		panel.add(botaoCarregar);
		panel.add(botaoSair);
		
		JFrame frame=new JFrame("Argentum");
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);		
	}
	
	class EventoBotao implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("saindo pelo botao");
			System.exit(0);
		}
	}
}
