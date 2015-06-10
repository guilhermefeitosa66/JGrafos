import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Open
{
  public Grafo open(String fileName)
  {
    Grafo grafo;
    try
    {
      FileInputStream file = new FileInputStream("saves/" + fileName + ".grafo");
      ObjectInputStream object = new ObjectInputStream(file);
      grafo = (Grafo) object.readObject();
      object.close();
      System.out.println("opened!");
      return grafo;
    }catch(Exception e){
      System.out.println("error opening...");
      return null;
    }
  }
}