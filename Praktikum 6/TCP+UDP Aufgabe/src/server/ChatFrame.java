package server;

import java.awt.*;
import java.awt.event.WindowEvent;

public class ChatFrame extends Frame 
{ 

   protected TextArea output; 
   protected TextField input;

   protected Thread listener;
  
   public ChatFrame(String title)
   {
       super (title); 
    
       setLayout (new BorderLayout ()); 
       add ("Center", output = new TextArea ()); 
       output.setEditable (false); 
       add ("South", input = new TextField ()); 
    
       pack ();
       show (); 
       input.requestFocus (); 
    }

    public void windowClosing(WindowEvent e) {
        this.dispose();
    }


}

