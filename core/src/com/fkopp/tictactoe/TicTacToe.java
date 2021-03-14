package com.fkopp.tictactoe;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class TicTacToe extends ApplicationAdapter 
{
	
	SpriteBatch batch;
	ComputerAi computer;
	Field field;
	ShapeRenderer renderer;
	int[] computerPos;
	int x;
	int y;
	int zeile;
	int spalte;
	int xCoordinate;
	int yCoordinate;
	Label winner;
	Label loser;
	Label drawn;
	boolean isFinished = false;
	
	@Override
	public void create () 
	{
		
		
		/*
		 * Das sorgt dafür, dass nicht kontinuierlich gerendert wird, sondern erst,
		 * wenn ein request erfolgt. D.h. wenn iwas passiert, wird gerendert, ansonsten nicht.
		 * 
		 * Dies ist nützlich, wenn eine Scene gerendert wird, in der nichts passiert (still frames)
		 * z.B. in Tic Tac Toe. In spielen wie GTA muss natürlich kontinuierlich gerendert werden.
		 */
		Gdx.graphics.setContinuousRendering(false);
		
		/*
		 * diese Methode triggert explizit, dass gerendert wird.
		 * Wenn also ein Zeichen in TicTacToe gezeichnet werden soll, muss diese
		 * Methode ausgeführt werden!
		 */
		
		//Gdx.graphics.requestRendering();
		LabelStyle style = new LabelStyle();
		style.font = new BitmapFont();
		style.fontColor = Color.YELLOW;
		
		winner = new Label("DU HAST GEWONNEN!", style);
		loser = new Label("DER COMPUTER HAT GEWONNEN!", style);
		drawn = new Label("UNENTSCHIEDEN", style);
		batch = new SpriteBatch();
		computer = new ComputerAi();
		field = new Field();
		renderer = new ShapeRenderer();
		x = 0;
		y = 0;
		zeile = 0;
		spalte = 0;
		
	}

	@Override
	public void render () 
	{
		
		/*
		 * Gdx.input.method() ist das Interface, womit sämtliche Eingaben erfasst werden können.
		 */
		if (Gdx.input.isTouched()) {
			
			if (isFinished)
				Gdx.app.exit();
			
			
			x = Gdx.input.getX();
			y = Gdx.input.getY();
			Gdx.app.log(String.valueOf(x), String.valueOf(y));
			
			zeile = (int)(x / 100);
			spalte = 2-((int)(y / 100));
			
			if (field.make_sign(zeile, spalte, 1)) 
			{
				computerPos = computer.getPosition(field.getField());
				field.make_sign(computerPos[0], computerPos[1], 2);
			}
		}
		
		
		
		
		/*
		 * Diese Methoden sorgen in jedem render()-call, dass der Hintergrund gecleart wird und 
		 * erneut gezeichnet wird. Ansonsten würde der hintergrund bleiben und alles neue einfach 
		 * darüber gezeichnet werden.
		 */
		Gdx.gl.glClearColor( 1, 1, 1, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		

		// Spielfeld zeichnen
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.BLACK);
		renderer.rectLine(99, 0, 99, 299, 3);
		renderer.rectLine(199, 0, 199, 299, 3);
		
		renderer.rectLine(0, 199, 299, 199, 3);
		renderer.rectLine(0, 99, 299, 99, 3);
		renderer.end();
		//--------------
		
		renderer.begin(ShapeType.Filled);
		
		for (int i=0; i<field.getField().length; i++)
		{
			for (int j=0; j<field.getField()[i].length; j++)
			{
				xCoordinate = i*100+49;
				yCoordinate = j*100+49;
				
				if (field.getField()[i][j] == 1)
				{
					renderer.setColor(Color.RED);
					renderer.circle(xCoordinate, yCoordinate, 40);
					renderer.setColor(Color.WHITE);
					renderer.circle(xCoordinate, yCoordinate, 32);
				}
				else if (field.getField()[i][j] == 2)
				{
					renderer.setColor(Color.GREEN);
					renderer.rectLine(xCoordinate+40, yCoordinate+40, xCoordinate-40, yCoordinate-40, 5);
					renderer.rectLine(xCoordinate-40, yCoordinate+40, xCoordinate+40, yCoordinate-40, 5);
				}
			}
		}
		
		renderer.end();
		
		batch.begin();
		if (field.isGameOver() != 3)
		{
			if (field.isGameOver() == 1)
				winner.draw(batch, 1);
			else if (field.isGameOver() == 2)
				loser.draw(batch, 1);
			else
				drawn.draw(batch, 1);
			
			isFinished = true;
		}
		batch.end();
	}
	
	
}
