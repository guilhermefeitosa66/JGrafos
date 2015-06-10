import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable, MouseListener, MouseMotionListener
{
  BufferedImage backBuffer;
  private int FPS = 30;
  private int WINDOW_WIDTH = 800;
  private int WINDOW_HEIGHT = 500;
  Grafo grafo;
  Vertex vertexA, vertexB, vertexSelected;

  Tools tools;

  public void init()
  {
    setTitle("Grafos");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);
    setVisible(true);
    setLocation(0, 100);
    backBuffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    addMouseListener(this);
    addMouseMotionListener(this);

    grafo = new Grafo();
    vertexSelected = null;
    vertexA = null;
    vertexB = null;

    tools = new Tools(this);
  }

  public void update()
  {
    /*call functions*/
  }

  public void draw()
  {
    Graphics2D g = (Graphics2D) getGraphics();
    Graphics2D bbg = (Graphics2D) backBuffer.getGraphics();
    bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   
    /* set background color */
    bbg.setColor(new Color(255, 255, 255)); //Color.WHITE
    bbg.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    /*Draw edges*/
    bbg.setColor(new Color(0, 0, 0));
    bbg.setStroke(new BasicStroke(2));
    for(int i = 0; i < grafo.vertices.size(); i++)
    {
      for(int j = 0; j < grafo.vertices.get(i).relations.size(); j++)
      {
        bbg.drawLine(
          grafo.vertices.get(i).getX()+15,
          grafo.vertices.get(i).getY()+15,
          grafo.vertices.get(i).relations.get(j).getX()+15,
          grafo.vertices.get(i).relations.get(j).getY()+15
        );
      }
    }

    /*Draw vertices*/
    for(int i = 0; i < grafo.vertices.size(); i++)
    {
      bbg.setColor(grafo.vertices.get(i).color);
      bbg.fill(grafo.vertices.get(i).shape());
    }
    
    // bbg.setColor(new Color(0, 0, 0));
    // bbg.setStroke(new BasicStroke(2));
    // bbg.drawLine(200+15, 200+15, 300+15, 100+15);
    // bbg.drawLine(300+15, 100+15, 400+15, 200+15);
    // bbg.drawString(""+grafo.vertices.size(), 100,100);
    // bbg.setColor(new Color(180, 250, 100));
    // bbg.fill(v1.shape());
    // bbg.fill(v2.shape());
    // bbg.fill(v3.shape());

    g.drawImage(backBuffer, 0, 0, this);
  }

  public void run()
  {
    init();
    while(true)
    {
      update();
      draw();
      try
      {
        Thread.sleep(1000/FPS);
      }catch(Exception e) {
        System.out.println("Thread interrompida!");
      }
    }
  }

  public void mouseClicked(MouseEvent e)
  {
    if(tools.getTool() == 1) /*Select vertex*/
    {
      if(vertexSelected == null)
      {
        Vertex vertex = new Vertex(e.getX(), e.getY());
        vertexSelected = grafo.select(vertex);
      }else{
        vertexSelected.color = new Color(180, 250, 100);
        vertexSelected = null;
      }  
    }

    if(tools.getTool() == 2) /*Add vertex*/
    {
      Vertex vertex = new Vertex(e.getX()-15, e.getY()-15);
      if(!grafo.collision(vertex))
      {
        grafo.add(vertex);
      }
    }

    if(tools.getTool() == 3) /*Remove vertex*/
    {
      Vertex vertex = grafo.select(new Vertex(e.getX(), e.getY()));
      grafo.remove(vertex);
    }

    if(tools.getTool() == 4) /*Add relation*/
    {
      Vertex vertex = grafo.select(new Vertex(e.getX(), e.getY()));

      if(vertex != null)
      {
        if(vertexA == null || vertex.equals(vertexA))
        {
          vertexA = vertex;
        }else if(vertex != null){
          vertexB = vertex;
          vertexA.addRelation(vertexB);
          vertexB.addRelation(vertexA);
          vertexA.color = new Color(180, 250, 100);
          vertexB.color = new Color(180, 250, 100);
          vertexA = null;
          vertexB = null;
        }
      }
    }

    if(tools.getTool() == 5) /*Remove relation*/
    {
      Vertex vertex = grafo.select(new Vertex(e.getX(), e.getY()));

      if(vertex != null)
      {
        if(vertexA == null || vertex.equals(vertexA))
        {
          vertexA = vertex;
        }else if(vertex != null){
          vertexB = vertex;
          vertexA.removeRelation(vertexB);
          vertexB.removeRelation(vertexA);
          vertexA.color = new Color(180, 250, 100);
          vertexB.color = new Color(180, 250, 100);
          vertexA = null;
          vertexB = null;
        }
      }
    }
  }
 
  public void mouseEntered(MouseEvent e){}

  public void mouseExited(MouseEvent e){}

  public void mousePressed(MouseEvent e){}

  public void mouseReleased(MouseEvent e){}

  public void mouseDragged(MouseEvent e){}

  public void mouseMoved(MouseEvent e)
  {
    if(tools.getTool() == 1)
    {
      if(vertexSelected != null)
      {
        vertexSelected.offset(e.getX()-15, e.getY()-15);
      }
    }
  }

  public static void main(String[] args)
  {
    Main application = new Main();
    application.run();
  }
}