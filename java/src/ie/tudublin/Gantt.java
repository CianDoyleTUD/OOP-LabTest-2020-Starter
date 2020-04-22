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
	int boundYTop;
	int boundYBottom;
	int currBox;
	int mouseXStart;
	int movedAmount;
	int rectL;
	boolean draggingBox;
	boolean locked = false;
	float mapValue;
	float midValue;
	String toMove;

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

			rect( Integer.parseInt(tasks.get(i).toString("posX1")), (i * 50) + 45, rectW * lineSpacing, 50);

		}
		
	}
	
	public void mousePressed()
	{

		for (int i = 0; i < tasks.size(); i++) { 

			// Setting boundaries for dragging a task
			boundX1Left = Integer.parseInt(tasks.get(i).toString("posX1")) - 20;
			boundX1Right = Integer.parseInt(tasks.get(i).toString("posX1")) + 20;

			boundX2Left = Integer.parseInt(tasks.get(i).toString("posX2")) - 20;
			boundX2Right = Integer.parseInt(tasks.get(i).toString("posX2")) + 20;

			boundYTop = (i * 50) + 45;
			boundYBottom = (i * 50) + 95;

			if ((mouseY > boundYTop) && (mouseY < boundYBottom)) { // Check if we are between the y-boundaries of this box, if so, continue

				if ( ( mouseX > boundX1Left) && ( mouseX < boundX1Right) )  { // If we are clicking the start of the box

					draggingBox = true;
					mouseXStart = mouseX;
					currBox = i;
					toMove = "start";
	
				}
				else if ( ( mouseX > boundX2Left) && ( mouseX < boundX2Right) ) { // If we are clicking the end of the box
	
					draggingBox = true;
					mouseXStart = mouseX;
					currBox = i;
					toMove = "end";
	
				}

			}

		}

	}

	public void mouseReleased() {
		
		movedAmount = (mouseX - mouseXStart) / lineSpacing;

		rectL = Integer.parseInt(tasks.get(currBox).toString("end")) - Integer.parseInt(tasks.get(currBox).toString("start")); 

		if (toMove == "start" && movedAmount < rectL) {

			if ( ((Integer.parseInt(tasks.get(currBox).toString("start")) + movedAmount) >= 0) && ((Integer.parseInt(tasks.get(currBox).toString("start")) + movedAmount) <= maxTime) ) { // If the change is between the allow values

				tasks.get(currBox).updatePos(Integer.parseInt(tasks.get(currBox).toString("posX1")) + (movedAmount * lineSpacing), Integer.parseInt(tasks.get(currBox).toString("posX2")));
				tasks.get(currBox).updateLength( Integer.parseInt(tasks.get(currBox).toString("start")) + movedAmount, Integer.parseInt(tasks.get(currBox).toString("end")) );
			
			}

		}
		else if (toMove == "end" && movedAmount < rectL) {

			if ( ((Integer.parseInt(tasks.get(currBox).toString("end")) + movedAmount) >= 0) && ((Integer.parseInt(tasks.get(currBox).toString("end")) + movedAmount) <= maxTime) ) { // If the change is between the allow values

				tasks.get(currBox).updatePos(Integer.parseInt(tasks.get(currBox).toString("posX1")), Integer.parseInt(tasks.get(currBox).toString("posX2")) + (movedAmount * lineSpacing));
				tasks.get(currBox).updateLength( Integer.parseInt(tasks.get(currBox).toString("start")), Integer.parseInt(tasks.get(currBox).toString("end")) + movedAmount );

			}

		}

		printTasks();
		

	}

	public void mouseDragged()
	{
		// I used mouseReleased instead as I found it more convenient. It's a bit less interactive but its achieves the same effect.
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

		if(draggingBox) { 

			locked = true; 

		}
		else {

			locked = false;

		}

		
	}
}
