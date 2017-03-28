/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intersys.iamas.sistema;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import javax.persistence.PreUpdate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.intersys.persistencia.PersistenciaException;
import com.intersys.utilmessage.UtilMensage;

/**
 *
 * @author PROGRAMADOR-02
 */
public class ProdutoViewCtrl implements Serializable {

	private static ProdutoViewCtrl instancia;
	private ProdutoBO produtoBO;

	public static synchronized ProdutoViewCtrl getInstancia() {
		if (instancia == null) {
			instancia = new ProdutoViewCtrl();
			return instancia;
		}
		return instancia;
	}

	private ProdutoView produtoView;

	private JButton btnPesquisa;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnCaneclar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JComboBox<String> jComboBox;
	private JButton btnLimpar;
	private JButton btnMaior;
	private JButton btnMenor;
	private JButton btnFast;
	private JButton btnLast;

	private JTable jTableLista;
	private JPanel jPainelCadastro;
	private JPanel jPainelLista;
	private JTextField tfCodSubGru;
	private JTextField tfCodPro;
	private JTextField tfCodgru;
	private JTextField tfEstoque;
	private JTextField tfGrupo;
	private JTextField tfSubGru;
	private JTextField tfDescricao;
	private JTextField tfEspecie;

	public void inicializar() {
		this.iniciaComponentes();
		this.iniciarTabela();
		this.preencherCampo();
		this.desativatComponete();
		this.iniciaListene();
		this.desativaFocussalvar();
	}

	private void iniciarTabela() {
		try {
			ProdutoBO produtoBO = ProdutoBO.getInstancia();
			produtoBO.setjTableLista(this.jTableLista);
			ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
			produtoCtrl.setProdutoView(produtoView);
			produtoCtrl.preencherTabela();
			this.jTableLista.setRowSelectionInterval(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void atualizarTabela() {
		try {
			int item = this.jTableLista.getRowCount();
			ProdutoBO produtoBO = ProdutoBO.getInstancia();
			produtoBO.setjTableLista(this.jTableLista);
			ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
			produtoCtrl.setProdutoView(produtoView);
			produtoCtrl.preencherTabela();
			this.jTableLista.setRowSelectionInterval(item, item);
			this.jTableLista
					.scrollRectToVisible(this.jTableLista.getCellRect(this.jTableLista.getSelectedRow(), 0, false));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void iniciaTelacadastro() {
		this.produtoView.getJtPainelComGuias().setSelectedComponent(jPainelCadastro);
	}

	private void preencherCampo() {
		try {
			produtoBO = ProdutoBO.getInstancia();
			produtoBO.preencherCampos();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}

	private void iniciaComponentes() {
		this.btnNovo = this.produtoView.getBtnNovo();
		this.btnSalvar = this.produtoView.getBtnSalvar();
		this.btnCaneclar = this.produtoView.getBtnCancelar();
		this.btnAlterar = this.produtoView.getBtnAlterar();
		this.btnExcluir = this.produtoView.getBtnExcluir();
		this.jComboBox = this.produtoView.getjComboBox();
		this.btnPesquisa = this.produtoView.getBtnPesquisar();
		this.btnLimpar = this.produtoView.getBtnLimpar();
		this.btnMaior = this.produtoView.getBtnMaior();
		this.btnMenor = this.produtoView.getBtnMenor();
		this.btnFast = this.produtoView.getBtnFast();
		this.btnLast = this.produtoView.getBtnLast();

		this.tfCodPro = this.produtoView.getTfCodProduto();
		this.tfCodgru = this.produtoView.getTfCodGrupo();
		this.tfCodSubGru = this.produtoView.getTfCodSubGrupo();
		this.tfEstoque = this.produtoView.getTfEstoque();
		this.tfGrupo = this.produtoView.getTfGrupo();
		this.tfSubGru = this.produtoView.getTfSubGrupo();
		this.tfDescricao = this.produtoView.getTfDescricao();
		this.tfEspecie = this.produtoView.getTfEspecie();

		this.jTableLista = this.produtoView.getjTabeleLista();
		this.jPainelCadastro = this.produtoView.getjPainelCadastro();
		this.jPainelLista = this.produtoView.getjPainelLista();
		this.produtoView.getJtPainelComGuias().setSelectedComponent(jPainelLista);
		this.jTableLista.setSelectionBackground(Color.BLUE);
		this.focusTabela();
	}

	private void focusTabela() {
		this.jTableLista.requestFocus();
	}

	private void ativarComponente() {
		this.ativarDesativaComponente(true);
	}

	private void desativatComponete() {
		this.ativarDesativaComponente(false);
	}

	private void ativarDesativaComponente(boolean ativar) {
		if (ativar) {
			this.btnNovo.setEnabled(false);
			this.btnSalvar.setEnabled(true);
			this.btnCaneclar.setEnabled(true);
			this.btnAlterar.setEnabled(false);
			this.btnExcluir.setEnabled(false);
			this.jComboBox.setEnabled(false);
			this.btnPesquisa.setEnabled(false);
			this.btnLimpar.setEnabled(true);
			this.btnMaior.setVisible(true);
			this.btnMenor.setVisible(true);

			this.produtoView.getTfCodProduto().setEnabled(true);
			this.produtoView.getTfCodProduto().setEditable(true);
			this.produtoView.getTfDescricao().setEnabled(true);
			this.produtoView.getTfCodGrupo().setEnabled(true);
			this.produtoView.getTfGrupo().setEditable(false);
			this.produtoView.getTfGrupo().setEnabled(false);
			this.produtoView.getTfGrupo().setCaretColor(new Color(0, 0, 0));
			this.produtoView.getTfCodSubGrupo().setEnabled(true);
			this.produtoView.getTfSubGrupo().setEditable(false);
			this.produtoView.getTfSubGrupo().setEnabled(false);
			this.produtoView.getTfEspecie().setEnabled(true);
			this.produtoView.getTfEstoque().setEnabled(true);
			this.produtoView.getTfCombobox().setEnabled(true);

			this.produtoView.getJtPainelComGuias().setSelectedComponent(this.produtoView.getjPainelLista());
			this.produtoView.getJtPainelComGuias().setEnabledAt(1, false);
			this.produtoView.getJtPainelComGuias().setSelectedComponent(jPainelCadastro);

			this.btnMaior.setEnabled(false);
			this.btnMenor.setEnabled(false);
			this.btnFast.setEnabled(false);
			this.btnLast.setEnabled(false);

		} else {
			this.btnNovo.setEnabled(true);
			this.btnSalvar.setEnabled(false);
			this.btnCaneclar.setEnabled(false);
			this.btnAlterar.setEnabled(true);
			this.btnExcluir.setEnabled(true);
			this.jComboBox.setEnabled(true);
			this.btnPesquisa.setEnabled(true);
			this.btnLimpar.setEnabled(false);
			this.btnMaior.setVisible(true);
			this.btnMenor.setVisible(true);

			this.produtoView.getTfCodProduto().setEnabled(false);
			this.produtoView.getTfDescricao().setEnabled(false);
			this.produtoView.getTfCodGrupo().setEnabled(false);
			this.produtoView.getTfGrupo().setEnabled(false);
			this.produtoView.getTfCodSubGrupo().setEnabled(false);
			this.produtoView.getTfSubGrupo().setEnabled(false);
			this.produtoView.getTfEspecie().setEnabled(false);
			this.produtoView.getTfEstoque().setEnabled(false);
			this.produtoView.getTfCombobox().setEnabled(true);
			this.produtoView.getJtPainelComGuias().setEnabledAt(1, true);

			this.produtoView.getTfCodProduto().setDisabledTextColor(Color.black);
			this.produtoView.getTfDescricao().setDisabledTextColor(Color.black);
			this.produtoView.getTfCodGrupo().setDisabledTextColor(Color.black);
			this.produtoView.getTfGrupo().setDisabledTextColor(Color.black);
			this.produtoView.getTfCodSubGrupo().setDisabledTextColor(Color.black);
			this.produtoView.getTfSubGrupo().setDisabledTextColor(Color.black);
			this.produtoView.getTfEspecie().setDisabledTextColor(Color.black);
			this.produtoView.getTfEstoque().setDisabledTextColor(Color.black);

			this.produtoView.getJtPainelComGuias().setSelectedComponent(this.produtoView.getjPainelLista());
			this.produtoView.getJtPainelComGuias().setEnabledAt(1, true);
			this.produtoView.getJtPainelComGuias().setSelectedComponent(jPainelLista);
			this.produtoView.getRootPane().setDefaultButton(btnPesquisa);

			this.btnMaior.setEnabled(true);
			this.btnMenor.setEnabled(true);
			this.btnFast.setEnabled(true);
			this.btnLast.setEnabled(true);
		}
	}

	private void ativarCompAlterar() {
		this.btnNovo.setEnabled(false);
		this.btnSalvar.setEnabled(true);
		this.btnCaneclar.setEnabled(true);
		this.btnAlterar.setEnabled(false);
		this.btnExcluir.setEnabled(false);
		this.jComboBox.setEnabled(false);
		this.btnPesquisa.setEnabled(false);
		this.btnLimpar.setEnabled(true);
		this.btnMaior.setEnabled(true);
		this.btnMenor.setEnabled(true);

		this.produtoView.getTfCodProduto().setEditable(false);
		this.produtoView.getTfCodProduto().setEnabled(false);
		this.produtoView.getTfDescricao().setEnabled(true);
		this.produtoView.getTfCodGrupo().setEnabled(true);
		this.produtoView.getTfCodSubGrupo().setEnabled(true);
		this.produtoView.getTfGrupo().setEnabled(true);
		this.produtoView.getTfGrupo().setEditable(false);
		this.produtoView.getTfSubGrupo().setEditable(false);
		this.produtoView.getTfSubGrupo().setEnabled(true);
		this.produtoView.getTfEspecie().setEnabled(true);
		this.produtoView.getTfEstoque().setEnabled(true);
		this.produtoView.getTfCombobox().setEnabled(false);

		this.produtoView.getJtPainelComGuias().setSelectedComponent(produtoView.getjPainelLista());
		this.produtoView.getJtPainelComGuias().setEnabledAt(1, false);
		this.produtoView.getJtPainelComGuias().setSelectedComponent(jPainelCadastro);

		this.btnMaior.setEnabled(true);
		this.btnMenor.setEnabled(true);
		this.btnFast.setEnabled(true);
		this.btnLast.setEnabled(true);

	}

	private void ativafocusSalvar() {
		this.ativardesativafocus(true);
	}

	private void desativaFocussalvar() {
		this.ativardesativafocus(false);
	}

	private void ativardesativafocus(boolean ativa) {
		if (ativa) {
			this.btnSalvar.setBackground(Color.BLUE);
		} else {
			this.btnSalvar.setBackground(Color.white);
		}
	}

	private void limparcomponente() {
		this.produtoView.getTfCodProduto().setText(null);
		this.produtoView.getTfDescricao().setText(null);
		this.produtoView.getTfCodGrupo().setText("");
		this.produtoView.getTfCodSubGrupo().setText(null);
		this.produtoView.getTfGrupo().setText(null);
		this.produtoView.getTfSubGrupo().setText(null);
		this.produtoView.getTfEspecie().setText(null);
		this.produtoView.getTfEstoque().setText(null);
		this.produtoView.getTfCodProduto().requestFocus();
	}

	private void existeCopro() throws Exception {
		ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
		produtoCtrl.setProdutoView(produtoView);
		produtoCtrl.validaCodPro();
	}

	private void focusPesquisa() {
		this.produtoView.getTfCombobox().selectAll();
		this.produtoView.getTfCombobox().requestFocus();
	}

	private void abriTelaGrupo() {
		GrupoView grupoView = new GrupoView();
		GrupoViewCtrl grupoViewCtrl = GrupoViewCtrl.getInstancia();
		grupoViewCtrl.getComboBox().setSelectedIndex(0);
		grupoViewCtrl.abriTelaCadastro();
		this.tfCodgru.setText(null);
		grupoView.setVisible(true);
	}

	private void abriTelaSubGru() {
		SubGruView subGruView = new SubGruView();
		SubGruViewCtrl subGruViewCtrl = SubGruViewCtrl.getInstancia();
		subGruViewCtrl.getjComboBox().setSelectedIndex(0);
		subGruViewCtrl.abriTelaSubGru();
		this.tfCodSubGru.setText(null);
		subGruView.setVisible(true);
	}

	private void salvar() {
		ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
		produtoCtrl.setProdutoView(produtoView);
		try {
			if (!tfCodPro.isEnabled()) {
				produtoCtrl.atualizar();
				System.out.println();
				UtilMensage.message("Atualizado com sucesso!");
				preencherCampo();
				iniciarTabela();
				desativatComponete();
				focusTabela();

			} else {
				existeCopro();
				produtoCtrl.salvar();
				UtilMensage.message("salvo com sucesso");
				preencherCampo();
				atualizarTabela();
				desativatComponete();
				focusTabela();

			}
		} catch (Exception exception) {
			validaMensagemCampos(exception.getMessage());
		}
	}

	private void iniciaListene() {

		this.jTableLista.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				preencherCampo();
				if (e.getClickCount() == 2) {
					preencherCampo();
					iniciaTelacadastro();

				}
			}

		});

		this.tfCodPro.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				verifCampoPro();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			private void verifCampoPro() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					produtoCtrl.verifcampoPro();
				} catch (Exception e) {
					validaCampoPro(e.getMessage());
				}
			}
		});

		this.tfDescricao.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					produtoView.getTfCodGrupo().requestFocus();
					;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		this.tfDescricao.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validaDescricao();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void validaDescricao() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					produtoCtrl.validaDescricao();
				} catch (Exception e) {
					validaCampoDescricao(e.getMessage());
				}
			}
		});

		this.tfEspecie.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validaEspecie();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void validaEspecie() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					produtoCtrl.validaEspecie();
				} catch (Exception e) {
					validaCampoEspecie(e.getMessage());
				}
			}
		});

		this.tfCodPro.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				tfCodPro.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						if (!tfCodPro.getText().isEmpty()) {
							existeCopro();
						}
					} catch (Exception e) {
						validaCampoPro(e.getMessage());
					}
					produtoView.getTfDescricao().requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent evt) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		this.tfCodgru.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				tfCodgru.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!tfCodgru.getText().isEmpty()) {
						existeCodGru();
					}
					produtoView.getTfCodSubGrupo().requestFocus();
				}

			}

			@Override
			public void keyReleased(KeyEvent evt) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			private void existeCodGru() {
				ProdutoCtrl produtoCtrl = new ProdutoCtrl();
				produtoCtrl.setProdutoView(produtoView);
				try {
					produtoCtrl.existeCodGru();
				} catch (Exception e) {
					abriTelaGrupo();
				}
			}

		});

		this.tfCodSubGru.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				tfCodSubGru.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!tfCodSubGru.getText().isEmpty()) {
						existeCodSubGru();
					}
					produtoView.getTfEspecie().requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent evt) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			private void existeCodSubGru() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					produtoCtrl.existeCodSubGru();
				} catch (Exception e) {
					abriTelaSubGru();
				}
			}

		});

		this.tfCodgru.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				verificarCodGru();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

			private void verificarCodGru() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				try {
					produtoCtrl.verifcampoGru();
				} catch (Exception e) {
					validaCampoGru();
				}
			}

		});

		this.tfCodSubGru.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				verificaCodSubru();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void verificaCodSubru() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				try {
					produtoCtrl.verifiCampoSubgru();
				} catch (Exception e) {
					validaCampoSubGru();
				}
			}
		});

		this.tfCodSubGru.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					produtoView.getTfEspecie().requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		this.tfEspecie.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					produtoView.getTfEstoque().requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		this.tfEstoque.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				verifCampoEstoque();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

			private void verifCampoEstoque() {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					produtoCtrl.verifCampoEstoque();
				} catch (Exception e) {
					validaCampoEstoque(e.getMessage());
				}
			}
		});

		this.tfEstoque.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() == KeyEvent.VK_TAB || evt.getKeyChar() == KeyEvent.VK_ENTER) {
					btnSalvar.requestFocus(true);
					btnSalvar.setSelected(true);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		this.btnSalvar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent evt) {
				if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
					salvar();
					desativaFocussalvar();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		this.btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					ativarCompAlterar();
					produtoCtrl.preencherCampos();
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});

		this.btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ativarComponente();
					tfCodPro.requestFocus();
					limparcomponente();

				} catch (Exception e2) {
				}
			}
		});

		this.btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}

		});

		this.btnCaneclar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparcomponente();
				preencherCampo();
				desativatComponete();
				produtoView.getJtPainelComGuias().setSelectedComponent(jPainelLista);

			}
		});

		this.btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UtilMensage.message("Produto não pode ser excluido!");
			}
		});

		this.btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limparcomponente();
			}
		});

		this.btnPesquisa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				produtoView.getRootPane().getDefaultButton();
				String intemSelecionado = (String) jComboBox.getSelectedItem();
				String codpro = produtoView.getTfCombobox().getText();
				ProdutoCtrl produtoCtrl = ProdutoCtrl.getInstancia();
				produtoCtrl.setProdutoView(produtoView);
				try {
					if (intemSelecionado.equals("Código")) {
						produtoCtrl.buscaID();
						produtoView.getTfCombobox().requestFocus();
					}

					if (intemSelecionado.equals("Descrição")) {
						produtoCtrl.buscaDescricao();
						produtoView.getTfCombobox().requestFocus();
					}
					if (codpro.isEmpty()) {
						iniciarTabela();
					}
				} catch (Exception e1) {
					UtilMensage.message(e1.getMessage());
					focusPesquisa();
				}
			}
		});

		this.btnMaior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				next();
				preencherCampo();
				focusBarraRolagem();
			}
		});


		this.btnMenor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previus();
				preencherCampo();
				focusBarraRolagem();
			}
		});

		this.btnFast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fast();
				preencherCampo();
				focusBarraRolagem();
			}
		});

		this.btnLast.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				last();
				preencherCampo();
				focusBarraRolagem();
			}
		});
	}

	private void focusBarraRolagem() {
		jTableLista.scrollRectToVisible(jTableLista.getCellRect(jTableLista.getSelectedRow(), 0, false));
	}

	// private void validaMensagem(String strMsg) {
	// int resposta = UtilMensage.message(strMsg);
	// if (resposta == 0) {
	// if (strMsg.contains("código")) {
	// this.tfCodPro.setText(null);
	// this.tfCodPro.requestFocus();
	// }
	//
	// if (strMsg.contains("grupo")) {
	// this.tfCodgru.requestFocus();
	// }
	//
	// if (strMsg.contains("subgrupo")) {
	// this.tfCodSubGru.requestFocus();
	// }
	//
	// if (strMsg.contains("estoque")) {
	// this.produtoView.getTfEstoque().requestFocus();
	// }
	//
	// }
	// }

	private void validaMensagemCampos(String strMsg) {
		int resposta = UtilMensage.message(strMsg);
		if (resposta == 0) {
			if (strMsg.contains("produto") || strMsg.contains("existe")) {
				this.tfCodPro.requestFocus();
				this.tfCodPro.setText("");
			}

			if (strMsg.contains("descrição")) {
				this.produtoView.getTfDescricao().requestFocus();
			}

			if (strMsg.contains("grupo")) {
				this.tfCodgru.requestFocus();
			}

			if (strMsg.contains("subgrupo")) {
				this.tfCodSubGru.requestFocus();
			}

			if (strMsg.contains("unidade")) {
				this.produtoView.getTfEspecie().requestFocus();
			}

			if (strMsg.contains("estoque")) {
				this.produtoView.getTfEstoque().requestFocus();
			}

		}
	}

	private void validaCampoPro(String str) {
		int resposta = UtilMensage.message(str);
		if (resposta == 0) {

			if (str.contains("código")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						tfCodPro.setText("");
						tfCodPro.requestFocus();
					}
				});
			}

		}
	}

	private void validaCampoGru() {
		if (this.tfCodgru.getText().length() > 0) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					GrupoView grupoView = new GrupoView();
					GrupoViewCtrl grupoViewCtrl = GrupoViewCtrl.getInstancia();
					grupoViewCtrl.getTfPesquisa().setText(tfCodgru.getText());
					grupoViewCtrl.getComboBox().setSelectedIndex(1);
					tfCodgru.setText(null);
					tfCodgru.requestFocus();
					grupoView.setVisible(true);
				}

			});
		}
	}

	private void validaCampoSubGru() {
		if (this.tfCodSubGru.getText().length() > 0) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					SubGruView subGruView = new SubGruView();
					SubGruViewCtrl subGruViewCtrl = SubGruViewCtrl.getInstancia();
					subGruViewCtrl.getTfPesquisa().setText(tfCodSubGru.getText());
					subGruViewCtrl.getjComboBox().setSelectedIndex(1);
					tfCodSubGru.setText(null);
					tfCodSubGru.requestFocus();
					subGruView.setVisible(true);
				}

			});
		}

	}

	private void validaCampoDescricao(String strMsg) {
		int resposta = UtilMensage.message(strMsg);

		if (resposta == 0) {
			if (strMsg.contains("Descrição")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						tfDescricao.setText(null);
						tfDescricao.requestFocus();
					}
				});
			}
		}
	}

	private void validaCampoEspecie(String strMsg) {
		int resposta = UtilMensage.message(strMsg);

		if (resposta == 0) {
			if (strMsg.contains("especie")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						tfEspecie.setText(null);
						tfEspecie.requestFocus();
					}
				});
			}
		}
	}

	private void validaCampoEstoque(String str) {
		int resposta = UtilMensage.message(str);
		if (resposta == 0) {
			if (str.contains("Estoque")) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						tfEstoque.setText(null);
						tfEstoque.requestFocus();
					}
				});
			}
		}
	}

	public void next() {
		int y = this.jTableLista.getSelectedRow();
		int count = this.jTableLista.getRowCount();
		int i = y;
		if (y < count) {
			i++;
			try {
				if (i != count) {
					this.jTableLista.setRowSelectionInterval(i, i);
				}
			} catch (Exception e) {
				this.jTableLista.setRowSelectionInterval(count, count);
			}
		}
		y = i;
	}

	public void previus() {
		int y = this.jTableLista.getSelectedRow();
		int count = this.jTableLista.getRowCount();
		int i = y;
		if (y < count) {
			i--;
			try {
				this.jTableLista.setRowSelectionInterval(i, i);
			} catch (Exception e) {
				this.jTableLista.setRowSelectionInterval(0, 0);
			}
		}
		y = i;
	}

	private void last() {
		this.jTableLista.setRowSelectionInterval(0, 0);

	}

	private void fast() {
		int y = this.jTableLista.getSelectedRow();
		int count = this.jTableLista.getRowCount();
		int i = y;
		if (y < count) {
			i = count - 1;
			try {
				if (i != count) {
					this.jTableLista.setRowSelectionInterval(i, i);
				}
			} catch (Exception e) {
				this.jTableLista.setRowSelectionInterval(count, count);
			}
		}
		y = i;
	}

	public ProdutoView getProdutoView() {
		return produtoView;
	}

	public void setProdutoView(ProdutoView produtoView) {
		this.produtoView = produtoView;
	}

	public JTable getjTableLista() {
		return jTableLista;
	}

	public void setjTableLista(JTable jTableLista) {
		this.jTableLista = jTableLista;
	}

	public JTextField getTfCodSubGru() {
		return tfCodSubGru;
	}

	public void setTfCodSubGru(JTextField tfCodSubGru) {
		this.tfCodSubGru = tfCodSubGru;
	}

	public JTextField getTfCodgru() {
		return tfCodgru;
	}

	public void setTfCodgru(JTextField tfCodgru) {
		this.tfCodgru = tfCodgru;
	}

	public JTextField getTfGrupo() {
		return tfGrupo;
	}

	public void setTfGrupo(JTextField tfGrupo) {
		this.tfGrupo = tfGrupo;
	}

	public JTextField getTfSubGru() {
		return tfSubGru;
	}

	public void setTfSubGru(JTextField tfSubGru) {
		this.tfSubGru = tfSubGru;
	}

}
