package com.intersys.iamas.sistema;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.intersys.utilmessage.UtilMensage;

public class GrupoViewCtrl {

	private static GrupoViewCtrl instancia;

	public static synchronized GrupoViewCtrl getInstancia() {
		if (instancia == null) {
			instancia = new GrupoViewCtrl();
		}
		return instancia;
	}

	private GrupoView grupoView;
	private JButton btnLista;
	private JButton btnAlterar;
	private JButton btnNovo;
	private JButton btnPesquisa;
	private JComboBox<String> comboBox;
	private JButton btnPrevius;
	private JButton btnNext;
	private JButton btnSair;
	private JButton btnlast;
	private JButton btnFast;
	private JTable jTabelaGrupo;
	private JPanel PanelCadastro;
	private JPanel PanelLista;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JTextField tfPesquisa;

	public void inicialiazar() {
		this.iniciaComponentes();
		this.preencherTabela();
		this.preencherCampos();
		this.desativarComponente();
		this.iniciaListerne();

	}

	private void iniciaComponentes() {
		this.btnNovo = this.grupoView.getBtnNovo();
		this.btnAlterar = this.grupoView.getBtnAlterar();
		this.btnPesquisa = this.grupoView.getBtnPesquisa();
		this.tfPesquisa = this.grupoView.getTfPesquisa();
		this.comboBox = this.grupoView.getjComboBoxPesquisa();
		this.btnNext = this.grupoView.getBtnNext();
		this.btnPrevius = this.grupoView.getBtnPrevius();
		this.btnSair = this.grupoView.getBtnSair();
		this.btnlast = this.grupoView.getBtnLast();
		this.btnFast = this.grupoView.getBtnFast();
		this.jTabelaGrupo = this.grupoView.getTabelaGrupo();
		this.PanelCadastro = this.grupoView.getjPanelCadastro();
		this.PanelLista = this.grupoView.getjPanelLista();
		this.btnCancelar = this.grupoView.getBtnCancelar();
		this.btnSalvar = this.grupoView.getBtnSalvar();
		this.btnExcluir = this.grupoView.getBtnExcluir();
		this.tfPesquisa = this.grupoView.getTfPesquisa();
		this.focusTabela();
		this.grupoView.getjTabbedPane1().setSelectedComponent(PanelLista);
		this.grupoView.getRootPane().setDefaultButton(btnPesquisa);
		this.focusPesquisa();
	}

	public void abriTelaCadastro() {
		limpacomponente();
		ativarComponente();
		focus();
	}

	private void focusTabela() {
		this.jTabelaGrupo.requestFocus();
	}

	private void selecionarLinhaTabela() {
		this.jTabelaGrupo.setRowSelectionInterval(0, 0);
	}

	private void preencherTabela() {
		GrupoBO grupoBO = GrupoBO.getInstancia();
		grupoBO.setTabelaGrupo(jTabelaGrupo);
		try {
			grupoBO.preencherTabela();
			this.selecionarLinhaTabela();
		} catch (Exception e) {
			UtilMensage.message(e.getMessage());
		}
	}

	private void preencherCampos() {
		int intem = this.jTabelaGrupo.getSelectedRow();
		String codgru = this.jTabelaGrupo.getValueAt(intem, 0).toString();
		String nomeGru = this.jTabelaGrupo.getValueAt(intem, 1).toString();
		this.grupoView.getLbCodigoDoGrupo().setText(codgru);
		this.grupoView.getTfNomeDoGrupo().setText(nomeGru);
	}

	private void preencherCamposPro() {
		int intem = this.jTabelaGrupo.getSelectedRow();
		String codgru = this.jTabelaGrupo.getValueAt(intem, 0).toString();
		String nomeGru = this.jTabelaGrupo.getValueAt(intem, 1).toString();
		ProdutoViewCtrl produtoViewCtrl = ProdutoViewCtrl.getInstancia();
		produtoViewCtrl.getTfCodgru().setText(codgru);
		produtoViewCtrl.getTfGrupo().setText(nomeGru);
	}

	private void preenceheSalvar() {
		int item = this.jTabelaGrupo.getRowCount();
		item -= 1;
		String codgru = this.jTabelaGrupo.getValueAt(item, 0).toString();
		String nome = this.jTabelaGrupo.getValueAt(item, 1).toString();
		ProdutoViewCtrl produtoViewCtrl = ProdutoViewCtrl.getInstancia();
		produtoViewCtrl.getTfCodgru().setText(codgru);
		produtoViewCtrl.getTfGrupo().setText(nome);
		this.grupoView.dispose();
	}

	private void iniciaListerne() {

		this.jTabelaGrupo.addMouseListener(new MouseListener() {

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
					preencherCamposPro();
					grupoView.dispose();
				}
			}
		});

		this.btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpacomponente();
				ativarComponente();
				focus();
			}
		});

		this.btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GrupoCtrl grupoCtrl = GrupoCtrl.getInstancia();
				grupoCtrl.setGrupoView(grupoView);
				try {
					if (btnFast.isVisible()) {
						grupoCtrl.atualizar();
						UtilMensage.message("Atualizado com sucesso!");
						preencherTabela();
						preencherCampos();
						desativarComponente();
						focusPesquisa();

					} else {
						grupoCtrl.inseri();
						UtilMensage.message("salvo com sucesso!");
						preencherTabela();
						preencherCampos();
						desativarComponente();
						preenceheSalvar();
					}

				} catch (Exception e1) {
					UtilMensage.message(e1.getMessage());
					focus();
				}
			}
		});

		this.btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preencherCampos();
				ativaAlterar();
				focus();
			}
		});

		this.btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				desativarComponente();
				preencherCampos();
			}
		});

		this.btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UtilMensage.message("Grupo não pode ser excluido!");
			}
		});

		this.btnPesquisa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String intemSelecionado = comboBox.getSelectedItem().toString();
				GrupoCtrl grupoCtrl = GrupoCtrl.getInstancia();
				grupoCtrl.setGrupoView(grupoView);

				if (intemSelecionado.equals("Código")) {
					try {
						grupoCtrl.buscaId();
						focusPesquisa();
					} catch (Exception e1) {
						validarCombo(e1.getMessage());
					}
					selecionarLinhaTabela();
				}

				if (intemSelecionado.equals("Grupo")) {
					try {
						grupoCtrl.buscaDescricao();
						focusPesquisa();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					selecionarLinhaTabela();
				}

				if (tfPesquisa.getText().isEmpty()) {
					try {
						preencherTabela();
						selecionarLinhaTabela();
						focusPesquisa();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		this.btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				next();
				preencherCampos();
				focus();
				FocusBarraRolagem();

			}
		});

		this.btnPrevius.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previus();
				preencherCampos();
				focus();
				FocusBarraRolagem();
			}
		});

		this.btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				grupoView.dispose();
			}
		});

		this.btnlast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				last();
				FocusBarraRolagem();
			}
		});

		this.btnFast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fast();
				FocusBarraRolagem();
			}
		});

	}

	private void next() {
		int y = this.jTabelaGrupo.getSelectedRow();
		int count = this.jTabelaGrupo.getRowCount();
		int i = y;
		if (y < count) {
			i++;
			try {
				if (i != count) {
					this.jTabelaGrupo.setRowSelectionInterval(i, i);
				}
			} catch (Exception e2) {
				this.jTabelaGrupo.setRowSelectionInterval(count, count);
			}
		}
		y = i;
	}

	private void previus() {
		int y = this.jTabelaGrupo.getSelectedRow();
		int count = this.jTabelaGrupo.getRowCount();
		int i = y;
		if (y < count) {
			i--;
			try {
				if (i != count) {
					this.jTabelaGrupo.setRowSelectionInterval(i, i);
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
		int y = this.jTabelaGrupo.getSelectedRow();
		int count = this.jTabelaGrupo.getRowCount();
		int i = y;
		if (y < count) {
			i = count - 1;
			try {
				if (i != count) {
					this.jTabelaGrupo.setRowSelectionInterval(i, i);
				}
			} catch (Exception e2) {
				this.jTabelaGrupo.setRowSelectionInterval(count, count);
			}
		}
		y = i;
	}

	private void focus() {
		this.grupoView.getTfNomeDoGrupo().requestFocus();
	}

	private void focusPesquisa() {
		this.grupoView.getTfPesquisa().requestFocus();
	}

	private void ativarComponente() {
		this.ativadesativaComponente(true);

	}

	public void desativarComponente() {
		this.ativadesativaComponente(false);
	}

	private void ativadesativaComponente(boolean habilitar) {
		if (habilitar) {
			this.grupoView.getTfNomeDoGrupo().setEnabled(true);
			this.grupoView.getLbCodigoDoGrupo().setVisible(false);
			this.grupoView.getLebalCodigo().setVisible(false);
			this.grupoView.getBtnCancelar().setVisible(true);
			this.grupoView.getBtnSalvar().setVisible(true);
			this.btnPrevius.setVisible(false);
			this.btnNext.setVisible(false);
			this.btnFast.setVisible(false);
			this.btnlast.setVisible(false);
			this.grupoView.getjTabbedPane1().setSelectedComponent(PanelLista);
			this.grupoView.getjTabbedPane1().setEnabledAt(1, false);
			this.grupoView.getjTabbedPane1().setSelectedComponent(PanelCadastro);

			this.btnAlterar.setEnabled(false);
			this.btnNovo.setEnabled(false);
			this.btnExcluir.setEnabled(false);
			this.btnPesquisa.setEnabled(false);
			this.grupoView.getjComboBoxPesquisa().setEnabled(false);
			this.tfPesquisa.setEnabled(false);
			this.grupoView.getRootPane().setDefaultButton(btnSalvar);

		} else {
			this.grupoView.getTfNomeDoGrupo().setEnabled(false);
			this.grupoView.getTfNomeDoGrupo().setDisabledTextColor(Color.BLACK);
			this.grupoView.getLbCodigoDoGrupo().setVisible(true);
			this.grupoView.getLebalCodigo().setVisible(true);
			this.grupoView.getBtnCancelar().setVisible(false);
			this.grupoView.getBtnSalvar().setVisible(false);
			this.btnPrevius.setVisible(true);
			this.btnNext.setVisible(true);
			this.btnFast.setVisible(true);
			this.btnlast.setVisible(true);
			this.grupoView.getjTabbedPane1().setSelectedComponent(PanelLista);
			this.grupoView.getjTabbedPane1().setEnabledAt(1, true);
			this.grupoView.getjTabbedPane1().setSelectedComponent(PanelLista);

			this.btnAlterar.setEnabled(true);
			this.btnNovo.setEnabled(true);
			this.btnExcluir.setEnabled(true);
			this.btnPesquisa.setEnabled(true);
			this.grupoView.getjComboBoxPesquisa().setEnabled(true);
			this.tfPesquisa.setEnabled(true);
			this.grupoView.getRootPane().setDefaultButton(btnPesquisa);

		}
	}

	private void ativaAlterar() {
		this.grupoView.getTfNomeDoGrupo().setEnabled(true);
		this.grupoView.getLbCodigoDoGrupo().setVisible(true);
		this.grupoView.getLebalCodigo().setVisible(true);
		this.grupoView.getBtnCancelar().setVisible(true);
		this.grupoView.getBtnSalvar().setVisible(true);
		this.btnPrevius.setVisible(true);
		this.btnNext.setVisible(true);
		this.grupoView.getjTabbedPane1().setSelectedComponent(PanelLista);
		this.grupoView.getjTabbedPane1().setEnabledAt(1, false);
		this.grupoView.getjTabbedPane1().setSelectedComponent(PanelCadastro);
		this.grupoView.getRootPane().setDefaultButton(btnSalvar);
		this.tfPesquisa.setText(null);

	}

	private void FocusBarraRolagem() {
		this.jTabelaGrupo
				.scrollRectToVisible(this.jTabelaGrupo.getCellRect(this.jTabelaGrupo.getSelectedRow(), 0, false));
	}

	private void validarCombo(String str) {
		int resposta = UtilMensage.message(str);
		if (resposta == 0) {
			this.grupoView.getTfPesquisa().selectAll();
			this.grupoView.getTfPesquisa().requestFocus();
		}
	}

	private void limpacomponente() {
		this.grupoView.getTfNomeDoGrupo().setText("");
		this.grupoView.getLbCodigoDoGrupo().setText("");
	}

	public GrupoView getGrupoView() {
		return grupoView;
	}

	public void setGrupoView(GrupoView grupoView) {
		this.grupoView = grupoView;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnLista() {
		return btnLista;
	}

	public void setBtnLista(JButton btnLista) {
		this.btnLista = btnLista;
	}

	public JTextField getTfPesquisa() {
		return tfPesquisa;
	}

	public void setTfPesquisa(JTextField tfPesquisa) {
		this.tfPesquisa = tfPesquisa;
	}

}
