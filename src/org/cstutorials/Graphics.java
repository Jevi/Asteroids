package org.cstutorials;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Graphics
{

	public static int currentVertexHandle;
	public static int numPairVertices;
	public static int numCoordinates;
	public static FloatBuffer vertexBuffer;

	public Graphics()
	{
	}

	public void drawLines()
	{
		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, currentVertexHandle);
		glVertexPointer(numCoordinates, GL_FLOAT, 0, 0L);
		glDrawArrays(GL_LINES, 0, numPairVertices);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableClientState(GL_VERTEX_ARRAY);

	}

	public void drawQuads()
	{
		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, currentVertexHandle);
		glVertexPointer(numCoordinates, GL_FLOAT, 0, 0L);
		glDrawArrays(GL_QUADS, 0, numPairVertices);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableClientState(GL_VERTEX_ARRAY);
	}

	public void rotate(float x, float y, float angle)
	{
		glTranslatef(x, y, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-x, -y, 0);
	}
}
