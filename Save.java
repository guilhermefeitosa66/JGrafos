import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Save
{
  public void save(Grafo grafo, String fileName)
  {
    try
    {
      FileOutputStream file = new FileOutputStream("saves/" + fileName + ".grafo");
      ObjectOutputStream object = new ObjectOutputStream(file);
      object.writeObject(grafo);
      object.close();
      System.out.println("saved!");
    }catch(Exception e){
      System.out.println("error saving...");
    }
  }
}