package com.intersys.iamas.sistema;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.intersys.utilmessage.UtilMensage;

public class SubGruViewCtrl {

	private static SubGruViewCtrl instancia;

	public static synchronized SubGruViewCtrl getInstancia() {
		if (instancia == null) {
			instancia = new SubGruViewCtrl();
		}
		return instancia;
	}

	private SubGruView subGruView;

	private JButton btnLista;
	private JButton btnSair;
	private JButton btnAlterar;
	private JButton btnNovo;
	private JComboBox<String> jComboBox;
	private JButton btnNext;
	private JButton btnPrevius;
	private JButton btnPesquisa;
	private JButton btnExcluir;
	private JButton btnLast;
	private JButton btnFast;
	private JTable jTabelaSubGru;
	private JPanel PanelCadastro;
	private JPanel PanelLista;
	private JButton btnSalvar;
	private JButton btnCencelar;
	private JTextField tfPesquisa;

	public void inicializar() {
		this.inicializaComponente();
		this.preencherTabela();
		this.preencherCampos();
		this.inicaListene();
	}

	private void inicializaComponente() {
		this.btnNovo = this.subGruView.getBtnNovo();
		this.btnAlterar = this.subGruView.getBtnAlterar();
		this.btnNext = this.subGruView.getBtnNext();
		this.btnPrevius = this.subGruView.getBtnPrevius();
		this.btnPesquisa = this.subGruView.getBtnPesquisa();
		this.jComboBox = this.subGruView.getjComboBoxPesquisa();
		this.btnExcluir = this.subGruView.getBtnExcluir();
		this.btnSair = this.subGruView.getBtnSair();
		this.btnLast = this.subGruView.getBtnLast();
		this.btnFast = this.subGruView.getBtnFast();
		this.jTabelaSubGru = this.subGruView.getTabelaSubGrupo();
		this.PanelCadastro = this.subGruView.getjPanel5();
		this.PanelLista = this.subGruView.getjPanel3();
		this.btnSalvar = this.subGruView.getBtnSalvar();
		this.btnCencelar = this.subGruView.getBtnCancelar();
		this.tfPesquisa = this.subGruView.getTfPesquisa();
		this.subGruView.setResizable(false);
		this.subGruView.getjTabbedPane1().setSelectedComponent(PanelLista);
		this.jTabelaSubGru.requestFocus();
		this.subGruView.getRootPane().setDefaultButton(btnPesquisa);
		this.focusPesquisa();
	}

	private void selecionarLinhaTabela() {
		this.jTabelaSubGru.setRowSelectionInterval(0, 0);
	}

	private void preencherTabela() {
		SubGruCtrl subGruCtrl = SubGruCtrl.getInstancia();
		subGruCtrl.setTabelaSubGru(jTabelaSubGru);
		try {
			subGruCtrl.preecherTabela();
			this.selecionarLinhaTabela();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void preencherCamposProduto() {
		int item = this.jTabelaSubGru.getSelectedRow();
		String codGru = this.jTabelaSubGru.getValueAt(item, 0).toString();
		String nomeGru = this.jTabelaSubGru.getValueAt(item, 1).toString();
		ProdutoViewCtrl produtoViewCtrl = ProdutoViewCtrl.getInstancia();
		produtoViewCtrl.getTfCodSubGru().setText(codGru);
		produtoViewCtrl.getTfSubGru().setText(nomeGru);
	}

	private void preencherCamposSalvar() {
		int item = this.jTabelaSubGru.getRowCount();
		item -= 1;
		String codGru = this.jTabelaSubGru.getValueAt(item, 0).toString();
		String nomeGru = this.jTabelaSubGru.getValueAt(item, 1).toString();
		ProdutoViewCtrl produtoViewCtrl = ProdutoViewCtrl.getInstancia();
		produtoViewCtrl.getTfCodSubGru().setText(codGru);
		produtoViewCtrl.getTfSubGru().setText(nomeGru);
		this.subGruView.dispose();
	}

	public void abriTelaSubGru() {
		limparComponente();
		ativarComponente();
		focus();
	}

	private void inicaListene() {

		this.jTabelaSubGru.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				preencherCampos();
				if (arg0.getClickCount() == 2) {
					preencherCamposProduto();
					subGruView.dispose();
				}
			}
		});

		this.btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limparComponente();
				ativarComponente();
				focus();
			}
		});

		this.btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SubGruCtrl subGruCtrl = SubGruCtrl.getInstancia();
				subGruCtrl.setSubGruView(subGruView);
				try {
					if (btnFast.isVisible()) {
						subGruCtrl.atualizar();
						UtilMensage.message("Atualizado com sucesso!");
						preencherTabela();
						preencherCampos();
						desativarComponente();
						focusPesquisa();
						

					} else {
						subGruCtrl.inserir();
						UtilMensage.message("salvo com sucesso!");
						preencherTabela();
						preencherCampos();
						desativarComponente();
						preencherCamposSalvar();
					}

				} catch (Exception e1) {
					UtilMensage.message(e1.getMessage());
					focus();
				}
			}
		});

		this.btnCencelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preencherCampos();
				desativarComponente();
			}
		});

		this.btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				next();
				preencherCampos();
				focus();
				focusBarraRolagem();
			}
		});

		this.btnPrevius.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previus();
				preencherCampos();
				focus();
				focusBarraRolagem();
			}
		});

		this.btnLast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				last();
				preencherCampos();
				focus();
				focusBarraRolagem();
			}
		});

		this.btnFast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fast();
				preencherCampos();
				focus();
				focusBarraRolagem();
			}
		});

		this.btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preencherCampos();
				ativarAltera();
				focus();
			}
		});

		this.btnPesquisa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String itemselecionado = jComboBox.getSelectedItem().toString();
				SubGruCtrl subGruCtrl = SubGruCtrl.getInstancia();
				subGruCtrl.setSubGruView(subGruView);

				if (itemselecionado.equals("Código")) {
					try {
						subGruCtrl.buscaId();
						focusPesquisa();
					} catch (Exception e1) {
						validarCombo(e1.getMessage());
					}
					selecionarLinhaTabela();
				}

				if (itemselecionado.equals("SubGrupo")) {
					try {
						subGruCtrl.buscaNome();
						focusPesquisa();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					selecionarLinhaTabela();
				}

				if (subGruView.getTfPesquisa().getText().isEmpty()) {
					try {
						subGruCtrl.preecherTabela();
						focusPesquisa();
						selecionarLinhaTabela();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		this.btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UtilMensage.message("Subgrupo não pode ser excluido!");
			}
		});

		this.btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				subGruView.dispose();
			}
		});

	}

	private void next() {
		int y = this.jTabelaSubGru.getSelectedRow();
		int count = this.jTabelaSubGru.getRowCount();
		int i = y;
		if (y < count) {
			i++;
			try {
				if (i != count) {
					this.jTabelaSubGru.setRowSelectionInterval(i, i);
				}
			} catch (Exception e2) {
				this.jTabelaSubGru.setRowSelectionInterval(count, count);
			}
		}
		y = i;
	}

	private void previus() {
		int y = this.jTabelaSubGru.getSelectedRow();
		int count = this.jTabelaSubGru.getRowCount();
		int i = y;
		if (y < count) {
			i--;
			try {
				if (i != count) {
					this.jTabelaSubGru.setRowSelectionInterval(i, i);
				}
			} catch (Exception e2) {
				selecionarLinhaTabela();
			}
		}
		y = i;

	}

	private void last() {
		selecionarLinhaTabela();
	}

	private void fast() {
		int y = this.jTabelaSubGru.getSelectedRow();
		int count = this.jTabelaSubGru.getRowCount();
		int i = y;
		if (y < count) {
			i = count - 1;
			try {
				if (i != count) {
					this.jTabelaSubGru.setRowSelectionInterval(i, i);
				}
			} catch (Exception e2) {
				this.jTabelaSubGru.setRowSelectionInterval(count, count);
			}
		}
		y = i;
	}

	private void preencherCampos() {
		int intem = this.jTabelaSubGru.getSelectedRow();
		String codgru = this.jTabelaSubGru.getValueAt(intem, 0).toString();
		String nomeGru = this.jTabelaSubGru.getValueAt(intem, 1).toString();
		this.subGruView.getLbCodigoDoGrupo().setText(codgru);
		this.subGruView.getTfNomeDoGrupo().setText(nomeGru);
	}

	private void ativarComponente() {
		this.ativadesativaComponente(true);
	}

	public void desativarComponente() {
		this.ativadesativaComponente(false);
	}

	private void ativadesativaComponente(boolean habilita) {
		if (habilita) {
			this.subGruView.getLbCodigoDoGrupo().setVisible(false);
			this.subGruView.getLebalCodigo().setVisible(false);
			this.subGruView.getTfNomeDoGrupo().setEnabled(true);
			this.subGruView.getBtnCancelar().setVisible(true);
			this.subGruView.getBtnSalvar().setVisible(true);
			this.btnPrevius.setVisible(false);
			this.btnNext.setVisible(false);

			this.btnAlterar.setEnabled(false);
			this.btnNovo.setEnabled(false);
			this.btnExcluir.setEnabled(false);
			this.btnPesquisa.setEnabled(false);
			this.subGruView.getjComboBoxPesquisa().setEnabled(false);
			this.subGruView.getTfPesquisa().setEnabled(false);
			this.btnFast.setVisible(false);
			this.btnLast.setVisible(false);

			this.subGruView.getjTabbedPane1().setSelectedComponent(PanelLista);
			this.subGruView.getjTabbedPane1().setEnabledAt(1, true);
			this.subGruView.getjTabbedPane1().setSelectedComponent(PanelCadastro);

			this.subGruView.getRootPane().setDefaultButton(btnSalvar);
		} else {
			this.subGruView.getTfNomeDoGrupo().setDisabledTextColor(Color.BLACK);
			this.subGruView.getLbCodigoDoGrupo().setVisible(true);
			this.subGruView.getLebalCodigo().setVisible(true);
			this.subGruView.getTfNomeDoGrupo().setEnabled(false);
			this.subGruView.getBtnCancelar().setVisible(false);
			this.subGruView.getBtnSalvar().setVisible(false);
			this.btnPrevius.setVisible(true);
			this.btnNext.setVisible(true);

			this.btnAlterar.setEnabled(true);
			this.btnNovo.setEnabled(true);
			this.btnExcluir.setEnabled(true);
			this.btnPesquisa.setEnabled(true);
			this.subGruView.getjComboBoxPesquisa().setEnabled(true);
			this.subGruView.getTfPesquisa().setEnabled(true);
			this.btnFast.setVisible(true);
			this.btnLast.setVisible(true);

			this.subGruView.getjTabbedPane1().setSelectedComponent(PanelLista);
			this.subGruView.getjTabbedPane1().setEnabledAt(1, true);
			this.subGruView.getjTabbedPane1().setSelectedComponent(PanelLista);
			this.subGruView.getRootPane().setDefaultButton(btnPesquisa);
		}

	}

	private void ativarAltera() {
		this.ativardesativarAlterar(true);
	}

	private void ativardesativarAlterar(boolean ativar) {
		if (ativar) {
			this.subGruView.getTfNomeDoGrupo().setDisabledTextColor(Color.BLACK);
			this.subGruView.getLbCodigoDoGrupo().setVisible(true);
			this.subGruView.getLebalCodigo().setVisible(true);
			this.subGruView.getTfNomeDoGrupo().setEnabled(true);
			this.subGruView.getBtnCancelar().setVisible(true);
			this.subGruView.getBtnSalvar().setVisible(true);
			this.btnPrevius.setVisible(true);
			this.btnNext.setVisible(true);
			this.btnAlterar.setEnabled(true);
			this.btnNovo.setEnabled(true);
			this.btnExcluir.setEnabled(true);
			this.btnPesquisa.setEnabled(true);
			this.btnFast.setVisible(true);
			this.btnLast.setVisible(true);

			this.subGruView.getjComboBoxPesquisa().setEnabled(true);
			this.subGruView.getTfPesquisa().setEnabled(true);
			this.subGruView.getjTabbedPane1().setSelectedComponent(PanelLista);
			this.subGruView.getjTabbedPane1().setEnabledAt(1, false);
			this.subGruView.getjTabbedPane1().setSelectedComponent(PanelCadastro);
			this.subGruView.getRootPane().setDefaultButton(btnSalvar);
			this.tfPesquisa.setText(null);
		}
	}

	private void focus() {
		this.subGruView.getTfNomeDoGrupo().requestFocus();
	}

	private void focusBarraRolagem() {
		this.jTabelaSubGru
				.scrollRectToVisible(this.jTabelaSubGru.getCellRect(this.jTabelaSubGru.getRowCount(), 0, false));
	}

	private void validarCombo(String str) {
		int resposta = UtilMensage.message(str);
		if (resposta == 0) {
			this.subGruView.getTfPesquisa().selectAll();
			this.subGruView.getTfPesquisa().requestFocus();
		}
	}

	private void focusPesquisa() {
		this.subGruView.getTfPesquisa().requestFocus();
	}

	private void limparComponente() {
		this.subGruView.getTfNomeDoGrupo().setText("");
	}

	public SubGruView getSubGruView() {
		return subGruView;
	}

	public void setSubGruView(SubGruView subGruView) {
		this.subGruView = subGruView;
	}

	public JButton getBtnLista() {
		return btnLista;
	}

	public void setBtnLista(JButton btnLista) {
		this.btnLista = btnLista;
	}

	public JComboBox<String> getjComboBox() {
		return jComboBox;
	}

	public void setjComboBox(JComboBox<String> jComboBox) {
		this.jComboBox = jComboBox;
	}

	public JTextField getTfPesquisa() {
		return tfPesquisa;
	}

	public void setTfPesquisa(JTextField tfPesquisa) {
		this.tfPesquisa = tfPesquisa;
	}

}
