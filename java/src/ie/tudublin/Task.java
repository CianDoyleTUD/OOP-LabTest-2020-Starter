package ie.tudublin;

import processing.data.TableRow;

public class Task
{
    private String name;
    private int start;
    private int end;

    public Task(String name, int start, int end)
    {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Task(TableRow tr)
    {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    public String toString()
    {
        return this.name + ", " + this.start + ", " + this.end;
    } 
}