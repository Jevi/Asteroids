package org.cstutorials;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;

//import org.newdawn.slick.Graphics;

public class GameObjectManager
{
	private NavigableMap<String, VisibleObject> gameObjects = new TreeMap<String, VisibleObject>();

	public void update(int delta)
	{
		NavigableMap<String, VisibleObject> iteratableGameObjects;
		synchronized (this)
		{
			iteratableGameObjects = new TreeMap<String, VisibleObject>(gameObjects);
		}

		for (Map.Entry<String, VisibleObject> entry : iteratableGameObjects.entrySet())
		{
			entry.getValue().update(delta);
		}
	}

	public void render(Graphics g)
	{
		NavigableMap<String, VisibleObject> iteratableGameObjects;
		synchronized (this)
		{
			iteratableGameObjects = new TreeMap<String, VisibleObject>(gameObjects);
		}

		for (Map.Entry<String, VisibleObject> entry : iteratableGameObjects.entrySet())
		{
			VisibleObject vo = entry.getValue();
			Graphics.currentVertexHandle = vo.vertexHandle;
			Graphics.numCoordinates = vo.numCoordinates;
			Graphics.numPairVertices = vo.numPairVertices;
			Graphics.vertexBuffer = vo.vertexBuffer;
			vo.render(g);
		}
	}

	public void add(String name, VisibleObject object)
	{
		gameObjects.put(name, object);
		// System.out.println(name + " was added to Object Manager");
		// System.out.println("Current objectManager size: " + gameObjects.size());
	}

	public void remove(String name)
	{
		// System.out.println("Removing: " + name);
		for (Map.Entry<String, VisibleObject> entry : gameObjects.entrySet())
		{
			if (name.equals(entry.getKey()))
			{
				// System.out.println("Name: " + name + " Deleting Buffer: " + entry.getValue().vertexHandle);
				glDeleteBuffers(entry.getValue().vertexHandle);
			}
		}
		gameObjects.remove(name);
		// System.out.println("Current objectManager size: " + gameObjects.size());
	}

	public VisibleObject get(String name)
	{
		NavigableMap<String, VisibleObject> iteratableGameObjects;
		synchronized (this)
		{
			iteratableGameObjects = new TreeMap<String, VisibleObject>(gameObjects);
		}

		for (Map.Entry<String, VisibleObject> entry : iteratableGameObjects.entrySet())
		{
			if (entry.getKey().equals(name))
			{
				return entry.getValue();
			}
		}
		return null;
	}

	public String getLastObjectName(String name)
	{
		NavigableMap<String, VisibleObject> iteratableGameObjects;
		synchronized (this)
		{
			iteratableGameObjects = new TreeMap<String, VisibleObject>(gameObjects);
		}

		for (Map.Entry<String, VisibleObject> entry : iteratableGameObjects.descendingMap().entrySet())
		{
			if (entry.getKey().contains(name))
			{
				return entry.getKey();
			}
		}
		return null;
	}

	public void deleteBuffers()
	{
		NavigableMap<String, VisibleObject> iteratableGameObjects;
		synchronized (this)
		{
			iteratableGameObjects = new TreeMap<String, VisibleObject>(gameObjects);
		}

		for (Map.Entry<String, VisibleObject> entry : iteratableGameObjects.entrySet())
		{
			// System.out.println("Deleting Buffer: " + entry.getValue().vertexHandle);
			glDeleteBuffers(entry.getValue().vertexHandle);
		}
	}
}
