package ie.tudublin;

import processing.data.TableRow;
    
public class Task
{
    private String name;
    private int start;
    private int end;
    private int posX1;
    private int posX2;

    public Task(String name, int start, int end)
    {
        this.name = name;
        this.start = start;
        this.end = end;
        this.posX1 = ((start - 1) * 20) + 180;
        this.posX2 = ((end - 1) * 20) + 180;
    }

    public Task(TableRow tr)
    {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    public void updatePos(int posX1, int posX2) { // For keeping track of a tasks position on the graph, helpful with the last part of test.
        this.posX1 = posX1;
        this.posX2 = posX2;
    } 

    public String toString()
    {
       return this.name + ", " + this.start + ", " + this.end;
    } 

    public String toString(String field) // Method overloading so that we can specify a subsection of the string to be returned
    {   
        switch(field) {

            case "name":
                return this.name;

            case "start":
                return this.start + "";

            case "end":
                return this.end + "";

            case "posX1":
                return this.posX1 + "";

            case "posX2":
                return this.posX2 + "";

            default:
                return "";
        }
       
    } 
}