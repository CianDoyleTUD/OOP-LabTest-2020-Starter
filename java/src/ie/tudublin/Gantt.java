package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
 
public class Gantt extends PApplet
{	
	
	Table table;
	int end1; 

	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		table = loadTable("tasks.csv", "header");

		println(table.getRowCount() + " total rows in table");
		for (TableRow row : table.rows()) {

			String task = row.getString("Task");
			int start = row.getInt("Start");
			int end = row.getInt("End");
		
			println(task + start + end);
		  }
	  
	}

	public void printTasks()
	{
		
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
	}
	
	public void draw()
	{			
		background(0);
		textSize(32);
		text("Test", 10, 30); 
		fill(0, 102, 153);
	}
}
