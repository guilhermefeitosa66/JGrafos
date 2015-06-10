import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Tools extends JFrame
{
  private int WINDOW_WIDTH = 800;
  private int WINDOW_HEIGHT = 65;
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
    setTitle("Ferramentas");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    setLocation(0, 0);
    
    this.tool = 1;
    this.main = main;
    //setLayout(null);

    colorSelected = new Color(180, 250, 100);
    colorDefault = new Color(255, 255, 255);

    Container container = getContentPane();
    container.setLayout(new FlowLayout());

    toolSelect = new JButton("seleção");
    toolAddVertex = new JButton("vértice");
    toolRemoveVertex = new JButton("vértice");
    toolAddRelation = new JButton("relação");
    toolRemoveRelation = new JButton("relação");

    toolSelect.setIcon(new ImageIcon("icons/select.png"));
    toolAddVertex.setIcon(new ImageIcon("icons/add-v.png"));
    toolRemoveVertex.setIcon(new ImageIcon("icons/remove-v.png"));
    toolAddRelation.setIcon(new ImageIcon("icons/add-r.png"));
    toolRemoveRelation.setIcon(new ImageIcon("icons/remove-r.png"));

    defaults();
    toolSelect.setBackground(colorSelected);

    container.add(toolSelect);
    container.add(toolAddVertex);
    container.add(toolRemoveVertex);
    container.add(toolAddRelation);
    container.add(toolRemoveRelation);

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
    toolSelect.setBackground(colorDefault);
    toolAddVertex.setBackground(colorDefault);
    toolRemoveVertex.setBackground(colorDefault);
    toolAddRelation.setBackground(colorDefault);
    toolRemoveRelation.setBackground(colorDefault);
    /*Main objects*/
    if(this.main.vertexA != null){this.main.vertexA.color = new Color(180, 250, 100);}
    if(this.main.vertexB != null){this.main.vertexB.color = new Color(180, 250, 100);}
    if(this.main.vertexSelected != null){this.main.vertexSelected.color = new Color(180, 250, 100);}
    this.main.vertexA = null;
    this.main.vertexB = null;
    this.main.vertexSelected = null;
  }
}