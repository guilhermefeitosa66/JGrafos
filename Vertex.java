import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public class Vertex implements Serializable
{
  private int x;
  private int y;
  ArrayList<Vertex> relations;
  Color color;

  public Vertex(int x, int y)
  {
    this.x = x;
    this.y = y;
    this.relations = new ArrayList<Vertex>();
    this.color = new Color(180, 250, 100);
  }

  public boolean equals(Vertex v)
  {
    if(v == null)
    {
      return false;
    }else if(v.x == this.x && v.y == this.y)
    {
      return true;
    }else{
      return false;
    }
  }

  public void addRelation(Vertex vertex)
  {
    boolean equal = false;

    for(int i = 0; i < this.relations.size(); i++)
    {
      if(vertex.equals(this.relations.get(i)))
      {
        equal = true;
      }  
    }

    if(!equal)
    {
      this.relations.add(vertex);
    }
  }

  public void removeRelation(Vertex vertex)
  {
    if(vertex != null)
    {
      for(int i = 0; i < this.relations.size(); i++)
      {
        if(vertex.equals(this.relations.get(i)))
        {
          this.relations.remove(i);
        }
      }
    }
  }

  public void offset(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public Ellipse2D shape()
  {
    Ellipse2D s = new Ellipse2D.Float();
    s.setFrame(this.x, this.y, 30, 30);
    return s;
  }
}