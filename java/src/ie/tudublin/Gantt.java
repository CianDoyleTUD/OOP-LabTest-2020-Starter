package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import java.util.ArrayList;
 
public class Gantt extends PApplet
{	
	
	ArrayList<Task> tasks = new ArrayList<Task>();
	Table table;
	int maxTime;

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
			System.out.println(tasks.get(i).toString());
		}
	}

	public void displayTasks() {

		textSize(20);

		maxTime = Integer.parseInt(tasks.get(tasks.size() - 1).toString("end")); // Store the last task's end time the chart into the variable maxTime, in this case it will be 30.

		for (int i = 0; i < tasks.size(); i++) {

			text(tasks.get(i).toString("name"), 30, (i * 50) + 50);
			stroke(126);
			line((i * 50) + 50, 50,(i * 50) + 500, 75);

		}

		

		
		fill(255, 255, 255);
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
		displayTasks();
	}
}
