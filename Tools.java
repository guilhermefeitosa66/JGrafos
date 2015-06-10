import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Tools extends JFrame
{
  private int WINDOW_WIDTH = 800;
  private int WINDOW_HEIGHT = 65;
  private JButton toolNew;
  private JButton toolSave;
  private JButton toolOpen;
  private JButton toolSelect;
  private JButton toolAddVertex;
  private JButton toolRemoveVertex;
  private JButton toolAddRelation;
  private JButton toolRemoveRelation;
  private int tool;
  private Color colorSelected;
  private Color colorDefault;
  Main main;

  public Tools(Main main)
  {
    setTitle("JGrafos - Ferramentas");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    setLocation(0, 0);
    
    this.tool = 1;
    this.main = main;
    //setLayout(null);

    //colorSelected = new Color(255, 132, 0);
    colorSelected = new Color(200, 200, 200);
    colorDefault = new Color(255, 255, 255);

    Container container = getContentPane();
    container.setLayout(new FlowLayout());

    toolNew = new JButton("Novo");
    toolSave = new JButton("Salvar");
    toolOpen = new JButton("Abrir");
    toolSelect = new JButton("Mover");
    toolAddVertex = new JButton("[+]Vértice");
    toolRemoveVertex = new JButton("[-]Vértice");
    toolAddRelation = new JButton("[+]Relação");
    toolRemoveRelation = new JButton("[-]Relação");

    // toolSelect.setIcon(new ImageIcon("icons/select.png"));
    // toolAddVertex.setIcon(new ImageIcon("icons/add-v.png"));
    // toolRemoveVertex.setIcon(new ImageIcon("icons/remove-v.png"));
    // toolAddRelation.setIcon(new ImageIcon("icons/add-r.png"));
    // toolRemoveRelation.setIcon(new ImageIcon("icons/remove-r.png"));

    defaults();
    toolSelect.setBackground(colorSelected);

    container.add(toolNew);
    container.add(toolSave);
    container.add(toolOpen);
    container.add(toolSelect);
    container.add(toolAddVertex);
    container.add(toolRemoveVertex);
    container.add(toolAddRelation);
    container.add(toolRemoveRelation);

    toolNew.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        main.grafo = new Grafo();
      }
    });

    toolSave.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String fileName = JOptionPane.showInputDialog(main, "Nome do arquivo");
        Save save = new Save();
        save.save(main.grafo, fileName);
      }
    });

    toolOpen.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String fileName = JOptionPane.showInputDialog(main, "Nome do arquivo");
        Open open = new Open();
        main.grafo = open.open(fileName);
      }
    });

    toolSelect.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 1;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(colorSelected);
      }
    });

    toolAddVertex.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 2;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(colorSelected);
      }
    });

    toolRemoveVertex.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 3;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(colorSelected);
      }
    });

    toolAddRelation.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 4;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(colorSelected);
      }
    });

    toolRemoveRelation.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 5;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(colorSelected);
      }
    });
  }

  public int getTool()
  {
    return this.tool;
  }

  public void defaults()
  {
    /*Button colors*/
    toolNew.setBackground(colorDefault);
    toolSave.setBackground(colorDefault);
    toolOpen.setBackground(colorDefault);
    toolSelect.setBackground(colorDefault);
    toolAddVertex.setBackground(colorDefault);
    toolRemoveVertex.setBackground(colorDefault);
    toolAddRelation.setBackground(colorDefault);
    toolRemoveRelation.setBackground(colorDefault);
    /*Main objects*/
    if(this.main.vertexA != null){this.main.vertexA.color = new Color(255, 132, 0);}
    if(this.main.vertexB != null){this.main.vertexB.color = new Color(255, 132, 0);}
    if(this.main.vertexSelected != null){this.main.vertexSelected.color = new Color(255, 132, 0);}
    this.main.vertexA = null;
    this.main.vertexB = null;
    this.main.vertexSelected = null;
  }
}