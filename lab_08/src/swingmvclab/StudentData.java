package swingmvclab;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 Student student = students.get(rowIndex);
		 switch(columnIndex) {
		 case 0: return student.getName();
		 case 1: return student.getNeptun();
		 case 2: return student.hasSignature();
		 default: return student.getGrade();
		 }
	}
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0: return "Név";
		case 1: return "Neptun";
		case 2: return "Aláírás";
		default: return "Jegy";
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		
		switch(columnIndex) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return Boolean.class;
		default: return Integer.class;
		
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Student s = students.get(rowIndex);
		if(columnIndex == 2) {
			s.setSignature((Boolean) aValue);
		}
		else if(columnIndex == 3) {
			s.setGrade((Integer) aValue);
		}
		students.set(rowIndex, s);
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}

	public void addStudent(String name, String neptun){
		Student s = new Student(name, neptun, false, 0);
		students.add(s);
		this.fireTableDataChanged();
	}
}
