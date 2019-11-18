package swingmvclab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/*
 * A megjelenítendõ ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    JTextField nameField;
    JTextField neptunField;
    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     * (táblázat, beviteli mezõ, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);
        table.setRowSorter(new TableRowSorter<StudentData>(data));
        
        this.add(new JScrollPane(table),BorderLayout.CENTER);
        
        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Number.class, new StudentTableCellRenderer(table.getDefaultRenderer(Number.class)));
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(table.getDefaultRenderer(Boolean.class)));
        
        JPanel panel = new JPanel(new FlowLayout());
        
        panel.add(new JLabel("Név:"));
        nameField = new JTextField(20);
        panel.add(nameField);
        
        panel.add(new JLabel("Neptun:"));
        neptunField = new JTextField(6);
        panel.add(neptunField);
        
        JButton button = new JButton("Felvesz");
        button.addActionListener(new MyActionListener());
        panel.add(button);
        
        this.add(panel,BorderLayout.SOUTH);

    }

    public class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String neptun = neptunField.getText();
            data.addStudent(name,neptun);

        }
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatói nyilvántartás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
    
    public class StudentTableCellRenderer implements TableCellRenderer {

   	 private TableCellRenderer renderer;

   	 public StudentTableCellRenderer(TableCellRenderer defRenderer) {
   		 this.renderer = defRenderer;
   	 }
   	 public Component getTableCellRendererComponent(JTable table,
   			 Object value, boolean isSelected, boolean hasFocus,
   			 int row, int column) {
   			 Component component = renderer.getTableCellRendererComponent(
   			 table, value, isSelected, hasFocus, row, column);
   			 // Kikeressük az éppen megjelenítendő hallgatót a külső,
   			 // StudentFrame osztály data tagváltozójából,
   			 Student actualStudent = data.students.get(table.getRowSorter().convertRowIndexToModel(row));
   			 // megállapítjuk, hogy bukásra áll-e vagy sem,
   			 // és ez alapján átállítjuk a komponens háttérszínét:
   			 // component.setBackground(...)
   				if(!actualStudent.hasSignature() || actualStudent.getGrade()<=1) {
   					component.setBackground(Color.RED);
   				}
   				else {
   					component.setBackground(Color.GREEN);
   				}

   			 return component;
   			 }
   }

}
