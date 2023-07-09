package pfinal.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pfinal.dominio.Jugador;
import pfinal.dominio.Usuario;
import pfinal.io.IOJuego;
import pfinal.util.Audio;
import pfinal.util.Util;

public class JVentana extends JFrame {

  private static final long serialVersionUID = 1L;

  private Lienzo lienzo;
  private CardLayout cardLayout = new CardLayout();
  private JPanel pnlVentana;

  private JLabel lblPracticas;
  private JLabel lblVacuna;
  private JLabel lblLlave;
  private JLabel lblTiempo;

  private int segundos_pasados = 0;
  private int minutos_pasados = 0;

  private boolean fin = true;

  private List<Usuario> usuarios;
  private String nombre;
  private int minutos;
  private int segundos;
  private boolean nuevo = false;
    
  public static void main(String args[]) {
    new JVentana();
  }

  public JVentana() {
    super("ICAI LEGENDS");
    pnlVentana = new JPanel(cardLayout);
    pnlVentana.setPreferredSize(new Dimension(384, 389));

    this.add(pnlVentana);

    if (IOJuego.importarUsuarios() != null)
			usuarios = IOJuego.importarUsuarios();

    Util.contarTiempo(this);

    this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				int code = ke.getKeyCode();
        if(!getPausa()) {
          if (code == KeyEvent.VK_RIGHT)
            lienzo.moverJugador("derecha");
          else if (code == KeyEvent.VK_LEFT)
            lienzo.moverJugador("izquierda");
          else if (code == KeyEvent.VK_UP)
            lienzo.moverJugador("arriba");				
          else if (code == KeyEvent.VK_DOWN)
            lienzo.moverJugador("abajo");
          else if (code == KeyEvent.VK_SPACE)
            lienzo.abrirPuerta();
          else if (code == KeyEvent.VK_ESCAPE)
            if(!fin) {
              lienzo.setPausa(true);
              Audio.pararSonido();
              new JPausa(JVentana.this);
            }
        }
			}
		});
    
    this.setBackground(Color.WHITE);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

    this.inicio();

  }

  public void inicio() {
    JPanel pnlInicio = new JPanel(new FlowLayout(FlowLayout.CENTER,0,135));
    
    PanelImagenFondo pnlFondo = new PanelImagenFondo(false);

    pnlInicio.setPreferredSize(new Dimension(384, 389));
    pnlVentana.setPreferredSize(new Dimension(384, 389));

    pnlInicio.add(pnlFondo);

    pnlInicio.setBackground(new Color(0, 0, 0));

    for(int i=0; i<52; i++) {
      try {
        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      pnlFondo.setAlpha((5*i)/255f);
      pnlFondo.repaint();
      pnlVentana.add(pnlInicio, "inicio");
		  this.cambiarCard("inicio");
    }

    for(int i=0; i<51; i++) {
      try {
        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(75);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      pnlFondo.setAlpha((255-5*i)/255f);
      pnlFondo.repaint();
      pnlVentana.add(pnlInicio, "inicio");
		  this.cambiarCard("inicio");
    }

    elegirUsuario();
  }

  public void elegirUsuario() {
    JPanel pnlUsuario = new JPanel(new FlowLayout());
    JButton btnNuevo = new JButton("NUEVO");
    JLabel lblElegir = new JLabel("ELEGIR USUARIO:");
    JPanel pnlElegir = new JPanel(new FlowLayout());

    PanelImagenFondo pnlFondo = new PanelImagenFondo(true);

    lblElegir.setFont(new Font("Serif", Font.PLAIN, 40));
    lblElegir.setBackground(Color.WHITE);
    lblElegir.setForeground(new Color(22,86,152));
    
    pnlElegir.add(lblElegir);
    pnlFondo.add(pnlElegir);

    JButton btnUsuario1;
		JButton btnUsuario2;
		JButton btnUsuario3;
		JButton btnUsuario4;
    JButton btnUsuario5;

		if(usuarios != null)
			for (int i = 0; i < usuarios.size(); i++) {
				if (i == 0) {
					btnUsuario1 = new JButton(usuarios.get(i).getNombre());
					btnUsuario1.setPreferredSize(new Dimension(200,50));
          btnUsuario1.setFont(new Font("Serif", Font.PLAIN, 30));
          btnUsuario1.setBackground(new Color(22,86,152));
          btnUsuario1.setForeground(Color.WHITE);
          btnUsuario1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					pnlFondo.add(btnUsuario1);

					btnUsuario1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							nombre = usuarios.get(0).getNombre();
							minutos = usuarios.get(0).getMinutos();
							segundos = usuarios.get(0).getSegundos();
							JVentana.this.menu();
						}
					});
				}
				if (i == 1) {
					btnUsuario2 = new JButton(usuarios.get(i).getNombre());
					btnUsuario2.setPreferredSize(new Dimension(200,50));
          btnUsuario2.setFont(new Font("Serif", Font.PLAIN, 30));
          btnUsuario2.setBackground(new Color(22,86,152));
          btnUsuario2.setForeground(Color.WHITE);
          btnUsuario2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					pnlFondo.add(btnUsuario2);

					btnUsuario2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							nombre = usuarios.get(0).getNombre();
							minutos = usuarios.get(0).getMinutos();
							segundos = usuarios.get(0).getSegundos();
							JVentana.this.menu();
						}
					});
				}
				if (i == 2) {
          btnUsuario3 = new JButton(usuarios.get(i).getNombre());
					btnUsuario3.setPreferredSize(new Dimension(200,50));
          btnUsuario3.setFont(new Font("Serif", Font.PLAIN, 30));
          btnUsuario3.setBackground(new Color(22,86,152));
          btnUsuario3.setForeground(Color.WHITE);
          btnUsuario3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					pnlFondo.add(btnUsuario3);

					btnUsuario3.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							nombre = usuarios.get(0).getNombre();
							minutos = usuarios.get(0).getMinutos();
							segundos = usuarios.get(0).getSegundos();
							JVentana.this.menu();
						}
					});
				}
				if (i == 3) {
					btnUsuario4 = new JButton(usuarios.get(i).getNombre());
					btnUsuario4.setPreferredSize(new Dimension(200,50));
          btnUsuario4.setFont(new Font("Serif", Font.PLAIN, 30));
          btnUsuario4.setBackground(new Color(22,86,152));
          btnUsuario4.setForeground(Color.WHITE);
          btnUsuario4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					pnlFondo.add(btnUsuario4);

					btnUsuario4.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							nombre = usuarios.get(0).getNombre();
							minutos = usuarios.get(0).getMinutos();
							segundos = usuarios.get(0).getSegundos();
							JVentana.this.menu();
						}
					});
				}
        if (i == 4) {
					btnUsuario5 = new JButton(usuarios.get(i).getNombre());
					btnUsuario5.setPreferredSize(new Dimension(200,50));
          btnUsuario5.setFont(new Font("Serif", Font.PLAIN, 30));
          btnUsuario5.setBackground(new Color(22,86,152));
          btnUsuario5.setForeground(Color.WHITE);
          btnUsuario5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					pnlFondo.add(btnUsuario5);

					btnUsuario5.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							nombre = usuarios.get(0).getNombre();
							minutos = usuarios.get(0).getMinutos();
							segundos = usuarios.get(0).getSegundos();
							JVentana.this.menu();
						}
					});
				}
			}

    if(usuarios.size()<5) {
      btnNuevo.setPreferredSize(new Dimension(200,50));
      btnNuevo.setFont(new Font("Serif", Font.PLAIN, 30));
      btnNuevo.setBackground(new Color(151,102,23));
      btnNuevo.setForeground(Color.WHITE);
      btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

      btnNuevo.addActionListener(new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          nuevo = true;
          JVentana.this.nuevoUsuario();
          JVentana.this.activarFocus();
        }
      });

      pnlFondo.add(btnNuevo);
    }

    pnlVentana.setPreferredSize(new Dimension(383, 389));

    pnlUsuario.add(pnlFondo);

    pnlVentana.add(pnlUsuario, "usuario");
    cambiarCard("usuario");
	}

  public void nuevoUsuario() {
    JPanel pnlNuevoUsuario = new JPanel(new FlowLayout());
    JButton btnAceptar = new JButton("Aceptar");
    JTextField txtNombre = new JTextField(10);

    JLabel lblTitulo = new JLabel("NUEVO NOMBRE");
    JPanel pnlTitulo = new JPanel(new FlowLayout());

    PanelImagenFondo pnlFondo = new PanelImagenFondo(true);

    lblTitulo.setFont(new Font("Serif", Font.PLAIN, 40));
    lblTitulo.setBackground(Color.WHITE);
    lblTitulo.setForeground(new Color(22,86,152));
    
    btnAceptar.setPreferredSize(new Dimension(200,50));
    btnAceptar.setFont(new Font("Serif", Font.PLAIN, 30));
    btnAceptar.setBackground(new Color(22,86,152));
    btnAceptar.setForeground(Color.WHITE);
    btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnAceptar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
        if(txtNombre.getText().equals(""))
          JOptionPane.showMessageDialog(JVentana.this, "Debe introducir un valor");
        else if(txtNombre.getText().length()>7)
          JOptionPane.showMessageDialog(JVentana.this, "El nombre de usuario no puede tener más de 7 caracteres");
        else{
          boolean existe=false;
          for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getNombre().equals(txtNombre.getText()))
              existe = true;
          }
          if(existe)
            JOptionPane.showMessageDialog(JVentana.this, "El nombre de usuario introducido ya existe. Por favor, introduzca otro");
          else {
            nombre = txtNombre.getText();
            JVentana.this.menu();
          }
          
        }
			}
		});

    txtNombre.setPreferredSize(new Dimension(200,50));
    txtNombre.setFont(new Font("Serif", Font.PLAIN, 30));

    pnlTitulo.add(lblTitulo);

    pnlFondo.add(pnlTitulo);
    pnlFondo.add(txtNombre);
    pnlFondo.add(btnAceptar);

    pnlVentana.setPreferredSize(new Dimension(383, 389));

    pnlNuevoUsuario.add(pnlFondo);

    pnlVentana.add(pnlNuevoUsuario, "nuevoUsuario");
    cambiarCard("nuevoUsuario");
  }

  public void menu() {
    JPanel pnlMenu = new JPanel(new FlowLayout());
    JButton btnInicio = new JButton("Iniciar Juego");
    JButton btnInstrucciones = new JButton("Instrucciones");
    JButton btnRanking = new JButton("Ranking");

    JLabel lblTitulo = new JLabel("ICAI LEGENDS");
    JPanel pnlTitulo = new JPanel(new FlowLayout());

    PanelImagenFondo pnlFondo = new PanelImagenFondo(true);

    lblTitulo.setFont(new Font("Serif", Font.PLAIN, 40));
    lblTitulo.setBackground(Color.WHITE);
    lblTitulo.setForeground(new Color(22,86,152));
    
    btnInicio.setPreferredSize(new Dimension(200,50));
    btnInicio.setFont(new Font("Serif", Font.PLAIN, 30));
    btnInicio.setBackground(new Color(22,86,152));
    btnInicio.setForeground(Color.WHITE);
    btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnInicio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JVentana.this.juego();
        JVentana.this.activarFocus();
			}
		});

    btnInstrucciones.setPreferredSize(new Dimension(200,50));
    btnInstrucciones.setFont(new Font("Serif", Font.PLAIN, 30));
    btnInstrucciones.setBackground(new Color(22,86,152));
    btnInstrucciones.setForeground(Color.WHITE);
    btnInstrucciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnInstrucciones.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JVentana.this.instrucciones();
			}
		});

    btnRanking.setPreferredSize(new Dimension(200,50));
    btnRanking.setFont(new Font("Serif", Font.PLAIN, 30));
    btnRanking.setBackground(new Color(22,86,152));
    btnRanking.setForeground(Color.WHITE);
    btnRanking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnRanking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JVentana.this.ranking();
			}
		});
    
    pnlTitulo.add(lblTitulo);

    pnlFondo.add(pnlTitulo);
    pnlFondo.add(btnInicio);
    pnlFondo.add(btnInstrucciones);
    pnlFondo.add(btnRanking);

    pnlVentana.setPreferredSize(new Dimension(383, 389));

    pnlMenu.add(pnlFondo);

    pnlVentana.add(pnlMenu, "menu");
    cambiarCard("menu");
  }

  public void instrucciones() {
    JPanel pnlInstrucciones = new JPanel(new FlowLayout());
    JTextArea txtInstrucciones = new JTextArea("Hola ingeniero.\nTe encuentras ante un desafío. Tienes que\nconseguir todas las prácticas para poder\naprobar. Muévete con las flechas y pulsa\nel espacio para usar la llave. Para parar\nel juego pulsa ESC.\nEncuentra la vacuna para protegerte del\nvirus. Aún así deberás ponerte la mascarilla\npor precaución.\nDate prisa que el tiempo corre.\nSi no encuentras la vacuna antes de 5 mins\nte contagiarás y te echarán por lo que no\npodrás aprobar.\n¡Suerte!");
    txtInstrucciones.setEditable(false);
    txtInstrucciones.setPreferredSize(new Dimension(286, 310));
    txtInstrucciones.setFont(new Font("Serif", Font.PLAIN, 15));
    txtInstrucciones.setMargin( new Insets(10,10,10,10) );

    JButton btnVolver = new JButton("Volver al menú");

    JLabel lblTitulo = new JLabel("Instrucciones");

    PanelImagenFondo pnlFondo = new PanelImagenFondo(true);

    lblTitulo.setFont(new Font("Serif", Font.PLAIN, 40));
    lblTitulo.setBackground(Color.WHITE);
    lblTitulo.setForeground(new Color(22,86,152));

    btnVolver.setPreferredSize(new Dimension(150,30));
    btnVolver.setFont(new Font("Serif", Font.PLAIN, 16));
    btnVolver.setBackground(new Color(22,86,152));
    btnVolver.setForeground(Color.WHITE);
    btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JVentana.this.menu();
			}
		});

    pnlFondo.add(txtInstrucciones);
    pnlFondo.add(btnVolver);
    
    pnlInstrucciones.add(pnlFondo);
    pnlVentana.setPreferredSize(new Dimension(383, 389));

    pnlVentana.add(pnlInstrucciones, "instrucciones");
    cambiarCard("instrucciones");
  }

  public void ranking() {
    JPanel pnlRanking = new JPanel(new FlowLayout());
    JButton btnVolver = new JButton("Volver");

    JLabel lblTitulo = new JLabel("RANKING");
    JPanel pnlTitulo = new JPanel(new FlowLayout());

    PanelImagenFondo pnlFondo = new PanelImagenFondo(true);

    lblTitulo.setFont(new Font("Serif", Font.PLAIN, 40));
    lblTitulo.setBackground(Color.WHITE);
    lblTitulo.setForeground(new Color(22,86,152));
    
    btnVolver.setPreferredSize(new Dimension(175,40));
    btnVolver.setFont(new Font("Serif", Font.PLAIN, 25));
    btnVolver.setBackground(new Color(151,102,23));
    btnVolver.setForeground(Color.WHITE);
    btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JVentana.this.menu();
			}
		});

    pnlTitulo.add(lblTitulo);
    pnlFondo.add(pnlTitulo);

    JLabel lblRecord1 = new JLabel("1");
    JLabel lblRecord2 = new JLabel("2");
    JLabel lblRecord3 = new JLabel("3");
    JLabel lblRecord4 = new JLabel("4");
    JLabel lblRecord5 = new JLabel("5");
    JPanel pnlRecords1 = new JPanel(new BorderLayout());
    JPanel pnlRecords2 = new JPanel(new BorderLayout());
    JPanel pnlRecords3 = new JPanel(new BorderLayout());
    JPanel pnlRecords4 = new JPanel(new BorderLayout());
    JPanel pnlRecords5 = new JPanel(new BorderLayout());

    pnlRecords1.setBackground(new Color(22,86,152));
    pnlRecords2.setBackground(new Color(22,86,152));
    pnlRecords3.setBackground(new Color(22,86,152));
    pnlRecords4.setBackground(new Color(22,86,152));
    pnlRecords5.setBackground(new Color(22,86,152));

    pnlRecords1.setPreferredSize(new Dimension(250,45));
    pnlRecords2.setPreferredSize(new Dimension(250,45));
    pnlRecords3.setPreferredSize(new Dimension(250,45));
    pnlRecords4.setPreferredSize(new Dimension(250,45));
    pnlRecords5.setPreferredSize(new Dimension(250,45));

    ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) usuarios;
    boolean ordenado = false;
    Usuario temp;
    while(!ordenado) {
        ordenado = true;
        for(int i = 0; i < listaUsuario.size()-1; i++) {
            if(listaUsuario.get(i).getMinutos() > listaUsuario.get(i+1).getMinutos()) {
                temp = listaUsuario.get(i);
                listaUsuario.set(i,listaUsuario.get(i+1));
                listaUsuario.set(i+1,temp);
                ordenado = false;
            }
            if(listaUsuario.get(i).getMinutos() == listaUsuario.get(i+1).getMinutos()) {
              if(listaUsuario.get(i).getSegundos() > listaUsuario.get(i+1).getSegundos()) {
                temp = listaUsuario.get(i);
                listaUsuario.set(i,listaUsuario.get(i+1));
                listaUsuario.set(i+1,temp);
                ordenado = false;
              }
            }
        }
    }

    if(usuarios != null)
      for (int i = 0; i < listaUsuario.size(); i++) {
        if (i == 0) {
          if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord1.setText(" 1. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()>=10)
            lblRecord1.setText(" 1. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()>=10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord1.setText(" 1. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());
          else
            lblRecord1.setText(" 1. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());

          lblRecord1.setPreferredSize(new Dimension(250,45));
          lblRecord1.setFont(new Font("Serif", Font.PLAIN, 30));
          lblRecord1.setBackground(new Color(22,86,152));
          lblRecord1.setForeground(Color.WHITE);
          pnlRecords1.add(lblRecord1, BorderLayout.CENTER);
          pnlFondo.add(pnlRecords1);
        }
        if (i == 1) {
          if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord2.setText(" 2. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()>=10)
            lblRecord2.setText(" 2. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()>=10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord2.setText(" 2. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());
          else
            lblRecord2.setText(" 2. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());

          lblRecord2.setPreferredSize(new Dimension(250,45));
          lblRecord2.setFont(new Font("Serif", Font.PLAIN, 30));
          lblRecord2.setBackground(new Color(22,86,152));
          lblRecord2.setForeground(Color.WHITE);
          pnlRecords2.add(lblRecord2, BorderLayout.CENTER);
          pnlFondo.add(pnlRecords2);
        }
        if (i == 2) {
          if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord3.setText(" 3. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()>=10)
            lblRecord3.setText(" 3. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()>=10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord3.setText(" 3. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());
          else
            lblRecord3.setText(" 3. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());

          lblRecord3.setPreferredSize(new Dimension(250,45));
          lblRecord3.setFont(new Font("Serif", Font.PLAIN, 30));
          lblRecord3.setBackground(new Color(22,86,152));
          lblRecord3.setForeground(Color.WHITE);
          pnlRecords3.add(lblRecord3, BorderLayout.CENTER);
          pnlFondo.add(pnlRecords3);
        }
        if (i == 3) {
          if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord4.setText(" 4. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()>=10)
            lblRecord4.setText(" 4. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()>=10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord4.setText(" 4. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());
          else
            lblRecord4.setText(" 4. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());

          lblRecord4.setPreferredSize(new Dimension(250,45));
          lblRecord4.setFont(new Font("Serif", Font.PLAIN, 30));
          lblRecord4.setBackground(new Color(22,86,152));
          lblRecord4.setForeground(Color.WHITE);
          pnlRecords4.add(lblRecord4, BorderLayout.CENTER);
          pnlFondo.add(pnlRecords4);
        }
        if (i == 4) {
          if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord5.setText(" 5. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()<10 && listaUsuario.get(i).getMinutos()>=10)
            lblRecord5.setText(" 5. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":0"+listaUsuario.get(i).getSegundos());
          else if(listaUsuario.get(i).getSegundos()>=10 && listaUsuario.get(i).getMinutos()<10)
            lblRecord5.setText(" 5. "+listaUsuario.get(i).getNombre()+" - 0"+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());
          else
            lblRecord5.setText(" 5. "+listaUsuario.get(i).getNombre()+" - "+listaUsuario.get(i).getMinutos()+":"+listaUsuario.get(i).getSegundos());

          lblRecord5.setPreferredSize(new Dimension(250,45));
          lblRecord5.setFont(new Font("Serif", Font.PLAIN, 30));
          lblRecord5.setBackground(new Color(22,86,152));
          lblRecord5.setForeground(Color.WHITE);
          pnlRecords5.add(lblRecord5, BorderLayout.CENTER);
          pnlFondo.add(pnlRecords5);
        }
      }

    pnlFondo.add(btnVolver);

    pnlVentana.setPreferredSize(new Dimension(383, 389));

    pnlRanking.add(pnlFondo);

    pnlVentana.add(pnlRanking, "ranking");
    cambiarCard("ranking");
  }

  public void juego() {
    JPanel pnlJuego = new JPanel(new BorderLayout());
    JPanel pnlSur = new JPanel(new BorderLayout());

    JPanel pnlSuroeste = new JPanel(new FlowLayout());
    JLabel lblPracticas1 = new JLabel("Prácticas: ");
    lblPracticas1.setFont(new Font("Serif", Font.PLAIN, 16));
    pnlSuroeste.add(lblPracticas1);
    lblPracticas = new JLabel("0/15");
    pnlSuroeste.add(lblPracticas);

    JPanel pnlSureste = new JPanel(new FlowLayout());
    lblVacuna = new JLabel("Vacuna: No,");
    lblLlave = new JLabel("Llave: No");

    pnlSureste.add(lblVacuna);
    pnlSureste.add(lblLlave);

    JPanel pnlSurCentro = new JPanel(new FlowLayout());
    lblTiempo = new JLabel("00:00");
    pnlSurCentro.add(lblTiempo);

    lblLlave.setFont(new Font("Serif", Font.PLAIN, 16));
    lblVacuna.setFont(new Font("Serif", Font.PLAIN, 16));
    lblPracticas.setFont(new Font("Serif", Font.PLAIN, 16));
    lblTiempo.setFont(new Font("Serif", Font.PLAIN, 20));

    pnlSur.add(pnlSureste, BorderLayout.EAST);
    pnlSur.add(pnlSurCentro, BorderLayout.CENTER);
    pnlSur.add(pnlSuroeste, BorderLayout.WEST);

    JPanel pnlNorte = new JPanel(new FlowLayout());
    lienzo = new Lienzo(this);
    pnlNorte.add(lienzo);

    pnlSur.setBackground(Color.WHITE);
    pnlSureste.setBackground(Color.WHITE);
    pnlSurCentro.setBackground(Color.WHITE);
    pnlSuroeste.setBackground(Color.WHITE);

    pnlSur.setPreferredSize(new Dimension(384, 40));

    pnlJuego.add(pnlNorte, BorderLayout.NORTH);
    pnlJuego.add(pnlSur, BorderLayout.SOUTH);

    pnlVentana.setPreferredSize(new Dimension(384, 424));

    fin = false;
    restart();

    pnlVentana.add(pnlJuego, "juego");
    cambiarCard("juego");
  }

  public void cambiarCard(String s) {
    cardLayout.show(pnlVentana, s);
    this.pack();
  }

  public void desactivarFocus() {
    this.setFocusable(false);
  }

  public void activarFocus() {
    this.setFocusable(true);
    requestFocus();
  }

  public void setLlave() {
    lblLlave.setText("Llave: Sí");
  }

  public void setVacuna() {
    lblVacuna.setText("Vacuna: Sí");
  }

  public void addPractica(Jugador jugador) {
    lblPracticas.setText(jugador.getNumPracticas()+"/15");
  }

  public void addSegundo() {
    if(segundos_pasados != 59)
      segundos_pasados++;
    else{
      segundos_pasados = 0;
      minutos_pasados++;
    }

    if(segundos_pasados<10 && minutos_pasados<10)
      lblTiempo.setText("0"+minutos_pasados+":0"+segundos_pasados);
    else if(segundos_pasados<10 && minutos_pasados>=10)
      lblTiempo.setText(minutos_pasados+":0"+segundos_pasados);
    else if(segundos_pasados>=10 && minutos_pasados<10)
      lblTiempo.setText("0"+minutos_pasados+":"+segundos_pasados);
    else
      lblTiempo.setText(minutos_pasados+":"+segundos_pasados);

    if(minutos_pasados==5 && !lienzo.getJugador().isVacuna()) {
      setFin(true);
      Audio.pararSonido();
      JOptionPane.showMessageDialog(this,"Te has contagiado del covid. Te han echado de ICAI.");
      menu();
    }
  }

  public void finJuego() {
    String mensajeTiempo = lblTiempo.getText();
    String mensajeRendimiento;

    minutos = minutos_pasados;
    segundos = segundos_pasados;
    if(minutos<4)
      mensajeRendimiento = "¡ENHORABUENA, HAS APROBADO CON MATRÍCULA!\nSolo has tardado: ";
    else if(minutos<5)
      mensajeRendimiento = "¡TE HA SALIDO BIEN!\nSe nota que has estudiado\nHas tardado: ";
    else if(minutos<6)
      mensajeRendimiento = "¡HAS APROBADO POR LOS PELOS!\nHas tardado: ";
    else if(minutos<8)
      mensajeRendimiento = "Lo siento, has tardado tanto que ya se han ido todos los profesores de vacaciones.\nNo queda nadie para corregir tu examen.\nHas tardado: ";
    else
      mensajeRendimiento = "Te recomiendo cambiar a la facultad de enfrente.\nHas tardado: ";

    JOptionPane.showMessageDialog(this,mensajeRendimiento+mensajeTiempo);
    if(nuevo){
      usuarios.add(new Usuario(nombre,minutos, segundos));
      nuevo=false;
    }
    for(Usuario u:usuarios)
      if(u.equals(new Usuario(nombre))) {
        if(u.getMinutos()>minutos) {
          u.setMinutos(minutos);
          u.setSegundos(segundos);
        }
        if(u.getMinutos()==minutos)
          if(u.getSegundos()>segundos) {
            u.setMinutos(minutos);
            u.setSegundos(segundos);
          }
      }
    IOJuego.exportarUsuarios(usuarios);
    menu();
  }

  public void setFin(boolean fin) {
    this.fin = fin;
  }

  public boolean getFin() {
    return fin;
  }

  public void restart() {
    segundos_pasados = 0;
    minutos_pasados = 0;
  }

  public boolean getPausa() {
    return lienzo.getPausa();
  }

  public void continuar() {
    lienzo.setPausa(false);
  }

}