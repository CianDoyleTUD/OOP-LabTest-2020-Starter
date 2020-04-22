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
	int boundX1Left;
	int boundX1Right;
	int boundX2Left;
	int boundX2Right;
	float mapValue;
	float midValue;

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

		textSize(10);

		maxTime = Integer.parseInt(tasks.get(tasks.size() - 1).toString("end")); // Store the last task's end time the chart into the variable maxTime, in this case it will be 30.

		colorMode(HSB, 100);
		stroke(0, 0, 100);
		fill(0, 0, 100);

		for (int i = 0; i < maxTime; i++) { // Create lines and times

			text(i + 1, ( (i * lineSpacing) - 1) + lineOffset, 35);
			line((i * lineSpacing) + lineOffset, 50, (i * lineSpacing) + lineOffset, ((tasks.size() - 1) * 50) + textOffset + 15); // Making sure the lines are an appropriate length, given how many tasks are present and factoring in text size
			

		}	

		textSize(15);

		for (int i = 0; i < tasks.size(); i++) { // Everything else

			fill(0, 0, 100);

			text(tasks.get(i).toString("name"), 30, (i * 50) + textOffset);

			taskStart = Integer.parseInt(tasks.get(i).toString("start"));
			taskEnd = Integer.parseInt(tasks.get(i).toString("end"));

			rectW = taskEnd - taskStart;
			midValue = (taskStart + taskEnd) / 2;

			mapValue = map(midValue, 0, 30, 0, 100);

			fill(mapValue, 100, 100);
			stroke(mapValue, 100, 100);
			
			rect( ( (taskStart - 1) * lineSpacing) + lineOffset, (i * 50) + 45, rectW * lineSpacing, 50);
			tasks.get(i).updatePos( ( ( (taskStart - 1) * lineSpacing) + lineOffset), ( ( (taskEnd - 1) * lineSpacing) + lineOffset) ); // Update the task objects position values

		}
		
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
		
		for (int i = 0; i < tasks.size(); i++) { 

			// Setting boundaries for dragging a task
			boundX1Left = Integer.parseInt(tasks.get(i).toString("posX1")) - 20;
			boundX1Right = Integer.parseInt(tasks.get(i).toString("posX1")) + 20;

			boundX2Left = Integer.parseInt(tasks.get(i).toString("posX2")) - 20;
			boundX2Right = Integer.parseInt(tasks.get(i).toString("posX2")) + 20;

			if ( ( mouseX > boundX1Left) && ( mouseX < boundX1Right) )  { // If we are clicking the start of the box


			}
			else if ( ( mouseX > boundX2Left) && ( mouseX < boundX2Right) ) { // If we are clicking the end of the box


			}

		}
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
