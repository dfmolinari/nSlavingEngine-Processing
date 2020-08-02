/**
 * you can put a one sentence description of your tool here.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 *
 * @author   ##author##
 * @modified ##date##
 * @version  ##tool.prettyVersion##
 */

package nse.tool;

import processing.app.Base;
import processing.app.SketchCode;
import processing.app.tools.Tool;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NSETools implements Tool {
  Base base;

  public String getMenuTitle() {
    return "##tool.name##";
  }


  public void init(Base base) {
    // Store a reference to the Processing application itself
    this.base = base;
  }


  public void run() {
    Object[] tools = { "Create Script","Reference" };
    String option = (String)JOptionPane.showInputDialog(null, "Select a tool","nSlavinEngine Tools",JOptionPane.QUESTION_MESSAGE,null,tools,"Create Script");

    if(option == "Create Script") //Create a new script file already setup
    {
      String scriptName = JOptionPane.showInputDialog(null,"Enter script name", "Create Script", JOptionPane.PLAIN_MESSAGE);

      if(scriptName != null)
      {
        File script = new File(base.getActiveEditor().getSketch().getFolder().getAbsolutePath()+"\\"+scriptName+".pde");
        try
        {
          if(script.createNewFile())
          {
            StringBuilder sb = new StringBuilder();
            sb.append("class " + scriptName + " extends NSEScript\n");
            sb.append("{\n\n\t");
            sb.append("//Gets executed when the level is loaded\n\tvoid Start()\n\t{\n\t\t\n\t}\n\n\t");
            sb.append("//Gets executed once every frame\n\tvoid Update()\n\t{\n\t\t\n\t}\n\n");
            sb.append("}");
            FileWriter writer = new FileWriter(script);
            writer.write(sb.toString());
            writer.close();
            System.out.println("Created script: " + scriptName);
          }
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      return;
    } else if(option == "Reference") //Open reference page
    {
      if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
      {
        try
        {
          Desktop.getDesktop().browse(new URI("https://dfmolinari.github.io/nSlavingEngine-Processing/wiki.html"));
          System.out.println("Opening Reference Website");
          return;
        } catch(IOException | URISyntaxException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}