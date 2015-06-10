import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public class Grafo implements Serializable
{
  ArrayList<Vertex> vertices;

  public Grafo()
  {
    vertices = new ArrayList<Vertex>();
  }

  public void add(Vertex vertex)
  {
    this.vertices.add(vertex);
  }

  public Vertex select(Vertex vertex)
  {
    vertex.shape().setFrame(vertex.getX(), vertex.getY(), 1, 1); //diminui a área de colisão
    Vertex v = null;
    for(int i = 0; i < vertices.size(); i++)
    {
      Area area = new Area(this.vertices.get(i).shape());
      area.intersect(new Area(vertex.shape()));
      if(!area.isEmpty())
      {
        v = this.vertices.get(i);
        v.color = new Color(205, 82, 0);
      }
    }

    return v;
  }

  public void remove(Vertex vertex)
  {
    if(vertex != null)
    {
      int index = 0;

      for(int i = 0; i < this.vertices.size(); i++)
      {
        for(int j = 0; j < this.vertices.get(i).relations.size(); j++)
        {
          if(vertex.equals(this.vertices.get(i).relations.get(j)))
          {
            this.vertices.get(i).relations.remove(j);
          }
        }

        if(vertex.equals(this.vertices.get(i)))
        {
          index = i;
        }
      }
      this.vertices.remove(index);
    }
  }

  public boolean collision(Vertex vertex)
  {
    vertex.shape().setFrame(vertex.getX(), vertex.getY(), 50, 50); //aumentando a área de colisão
    boolean collide = false;

    for(int i = 0; i < vertices.size(); i++)
    {
      Area area = new Area(this.vertices.get(i).shape());
      area.intersect(new Area(vertex.shape()));
      if(!area.isEmpty())
      {
        collide = true;
      }
    }

    return collide;
  }
}