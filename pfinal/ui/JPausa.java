package pfinal.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pfinal.util.Audio;

/** 
    La clase JPausa contiene la lógica para gestionar la ventana de pausa
    por lo que hereda de JFrame.
    @author Fernando Pérez Alba
*/

public class JPausa  extends JFrame {
    private JButton btnReinicio;
    private JButton btnInstrucciones;
    private JButton btnMenu;
    private JButton btnContinuar;

    private CardLayout cardLayout = new CardLayout();
    private JPanel pnlVentana;

    private JVentana ventana;

    public JPausa(JVentana ventana)
    {
        super("Pausa");
        this.ventana = ventana;

        pnlVentana = new JPanel(cardLayout);
        this.add(pnlVentana);

        this.pantallaPausa();

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                JPausa.this.dispose();
                ventana.continuar();
            }
        });
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void pantallaPausa()
    {        
        JPanel pnlPausa = new JPanel(new FlowLayout());

        PanelImagenFondo pnlFondo = new PanelImagenFondo(true);

        btnReinicio = new JButton("Reiniciar");;
        btnInstrucciones = new JButton("Instrucciones");
        btnMenu = new JButton("Menú");
        btnContinuar = new JButton("Continuar");

        pnlFondo.add(btnReinicio);
        pnlFondo.add(btnInstrucciones);
        pnlFondo.add(btnMenu);
        pnlFondo.add(btnContinuar);

        btnReinicio.setPreferredSize(new Dimension(200,50));
        btnReinicio.setFont(new Font("Serif", Font.PLAIN, 30));
        btnReinicio.setBackground(new Color(22,86,152));
        btnReinicio.setForeground(Color.WHITE);
        btnReinicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnInstrucciones.setPreferredSize(new Dimension(200,50));
        btnInstrucciones.setFont(new Font("Serif", Font.PLAIN, 30));
        btnInstrucciones.setBackground(new Color(22,86,152));
        btnInstrucciones.setForeground(Color.WHITE);
        btnInstrucciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnMenu.setPreferredSize(new Dimension(200,50));
        btnMenu.setFont(new Font("Serif", Font.PLAIN, 30));
        btnMenu.setBackground(new Color(22,86,152));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnContinuar.setPreferredSize(new Dimension(200,50));
        btnContinuar.setFont(new Font("Serif", Font.PLAIN, 30));
        btnContinuar.setBackground(new Color(22,86,152));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnReinicio.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ventana.setFin(true);
                ventana.juego();
                Audio.reiniciarSonido();
                JPausa.this.dispose();
            }
        });

        btnInstrucciones.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JPausa.this.instrucciones();
            }
        });

        btnMenu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ventana.setFin(true);
                ventana.menu();
                JPausa.this.dispose();
            }
        });

        btnContinuar.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                ventana.continuar();
                Audio.continuarSonido();
                JPausa.this.dispose();
            }
        });

        pnlPausa.add(pnlFondo);
        pnlVentana.setPreferredSize(new Dimension(383, 389));
        
		pnlVentana.add(pnlPausa,"pausa");
        cardLayout.show(pnlVentana, "pausa");
    }    

    public void instrucciones() {
        JPanel pnlInstrucciones = new JPanel(new FlowLayout());
        JTextArea txtInstrucciones = new JTextArea("Hola ingeniero.\nTe encuentras ante un desafío. Tienes que\nconseguir todas las prácticas para poder\naprobar. Muévete con las flechas y pulsa\nel espacio para usar la llave. Para parar\nel juego pulsa ESC.\nEncuentra la vacuna para protegerte del\nvirus. Aún así deberás ponerte la mascarilla\npor precaución.\nDate prisa que el tiempo corre.\nSi no encuentras la vacuna antes de 5 mins\nte contagiarás y te echarán por lo que no\npodrás aprobar.\n¡Suerte!");
        txtInstrucciones.setEditable(false);
        txtInstrucciones.setPreferredSize(new Dimension(286, 310));
        txtInstrucciones.setFont(new Font("Serif", Font.PLAIN, 15));
        txtInstrucciones.setMargin( new Insets(10,10,10,10) );

        JButton btnVolver = new JButton("Volver");

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
                    JPausa.this.pantallaPausa();
                }
            });

        pnlFondo.add(txtInstrucciones);
        pnlFondo.add(btnVolver);
        
        pnlInstrucciones.add(pnlFondo);
        pnlVentana.setPreferredSize(new Dimension(383, 389));

        pnlVentana.add(pnlInstrucciones, "instrucciones");
        cardLayout.show(pnlVentana, "instrucciones");    
    }
}