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
	int textOffset = 80;
	int lineOffset = 180;
	int lineSpacing = 20;
	int taskStart;
	int taskEnd;
	int rectW;

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

		textSize(15);

		maxTime = Integer.parseInt(tasks.get(tasks.size() - 1).toString("end")); // Store the last task's end time the chart into the variable maxTime, in this case it will be 30.

		for (int i = 0; i < maxTime; i++) { // Create lines

			stroke(126);
			line((i * lineSpacing) + lineOffset, 50, (i * lineSpacing) + lineOffset, ((tasks.size() - 1) * 50) + textOffset + 15); // Making sure the lines are an appropriate length, given how many tasks are present and factoring in text size

		}

		for (int i = 0; i < tasks.size(); i++) { // Everything else

			text(tasks.get(i).toString("name"), 30, (i * 50) + textOffset);

			taskStart = Integer.parseInt(tasks.get(i).toString("start"));
			taskEnd = Integer.parseInt(tasks.get(i).toString("end"));
			rectW = taskEnd - taskStart;

			rect( ( (taskStart - 1) * lineSpacing) + lineOffset, (i * 50) + 45, rectW * lineSpacing, 50);

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
