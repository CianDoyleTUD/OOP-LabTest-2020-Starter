package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import java.util.ArrayList;
 
public class Gantt extends PApplet
{	
	
	ArrayList<Task> tasks = new ArrayList<Task>();
	Table table;

	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		table = loadTable("tasks.csv", "header"); 

		for (TableRow row : table.rows()) { 

			Task t = new Task(row);
			tasks.add(t);
			
		  }
	  
	}

	public void printTasks()
	{
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println(tasks.get(i));
		}
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
		printTasks();
		
	}
	
	public void draw()
	{			
		background(0);
		textSize(32);
		text("Test", 10, 30); 
		fill(0, 102, 153);
	}
}
