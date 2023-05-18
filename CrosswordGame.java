import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CrosswordGame
{
   public static void main(String[] args) {
    //  String[] topic = {"ascii", "bust", "colors", "depth", "easel", "frame", "gloss", "hatch", "indie", "jazz", "kiln", "logo", "motif", "novel", "order", "paint", "quilt", "resin", "shade", "trace", "unity", "verso", "wedge", "yayoi"};
     // String[] topic = {"acute", "base", "count", "data", "equal", "faces", "graph", "helix", "inch", "jump", "kilo", "length", "median", "number", "obtuse", "prime", "quart", "right", "square", "term", "unit", "vertex", "width", "yard"};
      String[] topic = {"atom", "biome", "carbon", "delta", "elbow", "fauna", "germ", "hybrid", "immune", "joule", "kelvin", "larva", "mutant", "nerve", "organ", "phylum", "quark", "radio", "solute", "taxon", "uracil", "virus", "wave", "yield"};
     // int i = 0;
      ArrayList<String> blank = new ArrayList <>();
      Crossword generator = new Crossword(20, 20, topic);
      generator.fillGrid();
      
      int ran = (int)(Math.random()*21);
      int i = ran;
      int count = 0;
      while(count<100)
      {
         if(i == topic.length-1)
         {
            i = 0;
            System.out.println(count);
            count++;
         }
         generator.placeWord(topic[i], "aaaaa");
         i++;
      }
      
      generator.print();
      System.out.println("\\nWORD STARTED WITH "+ topic[ran]);
      generator.printInfo();
      
      char[][]grid = generator.getGrid();
      
      JFrame frame = new JFrame("Crossword Table");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   ///////////////////////////////////////////////////////////////////////    
      Object[][] data = new Object[grid.length][grid[0].length];  //creates a 2d array object of length grid
      for(int row=0; row<grid.length; row++) {                   //fills in new 2d array object to be compatible with JTable. 
         for(int col=0; col<grid[0].length; col++) {
            data[row][col] = Character.toString(grid[row][col]); 
         }
      }
    
      Object[] columnNames = new Object[grid[0].length];
      for(int j=0; j<grid[0].length; j++) {
         columnNames[j] = "Column " + j;        
      }
    /////////////////////////////////////////////////////////////////
    
      DefaultTableModel model = new DefaultTableModel(data, columnNames);
      
      JTable table = new JTable(model);
      
      //table.setRowHeight(50);
     // table.getColumnModel().getColumn(0).setPreferredWidth(50);
      JTableHeader header = table.getTableHeader();
     
      header.setDefaultRenderer(new RectangleHeaderRenderer());  
      header.setPreferredSize(new Dimension(header.getWidth(),header.getHeight()+10));         
    //  header.add(rectangle);
      
    
      Font font = new Font("Courier New Bold", Font.PLAIN, 10);
      table.setFont(font);
    
     // JPanel panel = new JPanel();
     // panel.add(table);  
      JLabel words = new JLabel("WORDS");
      words.setHorizontalAlignment(JLabel.CENTER);
      words.setBounds(50, 50, 200, 30);
     // panel.add(words);
      
      frame.add(new JScrollPane(table));
      frame.pack();
      frame.setVisible(true);
           
   }
   
    // Custom table header renderer to display the rectangle
   static class RectangleHeaderRenderer extends DefaultTableCellRenderer {
      @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         JPanel rectanglePanel = 
            new JPanel() {
               @Override
                protected void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  g.setColor(Color.RED);
                  g.fillRect(0, getHeight() - 10, getWidth(), 10); // Adjust the position and size of the rectangle as needed
               }
            };
         rectanglePanel.setOpaque(false);
         rectanglePanel.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getWidth(), 10)); // Adjust the height of the rectangle as needed
         ((JComponent) cellComponent).add(rectanglePanel);
         return cellComponent;
      }
   }

   
   
}