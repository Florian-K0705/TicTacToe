package com.fkopp.tictactoe;

import java.util.ArrayList;

public class ComputerAi 
{
	
	private int[] pos;
	
	private int minimax(int[][] field, boolean maximizingPlayer)
	{
		int tmp = isGameOver(field);
		
		if (tmp != 3)
		{
			if (tmp == 1)
				return -10;
			else if (tmp == 2)
				return 10;
			else
				return 0;
		}
		
		
		ArrayList<int[]> positions = getFreePositions(field);
		
		int[] rating = new int[positions.size()];
		
		int[] p;
		
		for (int i=0; i<positions.size(); i++)
		{
			p = positions.get(i);
			
			if (maximizingPlayer)
				field[p[0]][p[1]] = 2; 
			else
				field[p[0]][p[1]] = 1;
			
			rating[i] = minimax(field.clone(), !maximizingPlayer);

			field[p[0]][p[1]] = 0;
			
		}
		
		int max = 0;
		int min = 0;
		
		for (int i=0; i<rating.length; i++)
		{
			if (rating[max] < rating[i])
				max = i;
			
			if (rating[min] > rating[i])
				min = i;
		}
		
		this.pos = positions.get(max);
		
		
		if (maximizingPlayer)
			return rating[max];
		else
			return rating[min];
	}

	
	private ArrayList<int[]> getFreePositions(int[][] field)
	{
		ArrayList<int[]> result = new ArrayList<>();
		
		for (int i=0; i<field.length; i++)
		{
			for (int j=0; j<field[i].length; j++)
			{
				if (field[i][j] == 0)
					result.add(new int[] {i, j});
			}
		}
		
		return result;
	}
	
	public int[] getPosition(int[][] field)
	{
		int[][] r = new int[3][3];
		
		for (int i=0; i<field.length; i++)
		{
			for (int j=0; j<field[0].length; j++)
			{
				r[i][j] = field[i][j];
			}
		}
		
		minimax(r, true);
		return this.pos;
	}
	
	
	
	/*
	 * 
	 * Dieser Code ist einfach aus der Klasse Field kopiert. Ist somit also rendundant.
	 * Unschöne Lösung. Öberlege, ob diese Methoden nicht in eine 3. Klasse können!
	 * Alternative Architekturen sind auch erwünscht.
	 * 
	 */
	
	public int isGameOver(int[][] field)
	{
		int x = playerHasWon(field);
		boolean y = isFieldComplete(field);
		
		if (x == 0) {
			
			if (y)
			{
				return 0;
			}
			
			return 3;
			
		}
		
		return x;
	}
	
	private boolean isFieldComplete(int[][] field)
	{
		for (int i=0; i<field.length; i++)
		{
			for (int j=0; j<field[i].length; j++)
			{
				if (field[i][j] == 0)
					return false;
			}
		}
		
		return true;
	}
	
	private int playerHasWon(int[][] field)
	{
		// Prüfe alle Horizontalen Reihen:
		for (int i=0; i<field.length; i++)
		{
			if ( (field[i][0] == field[i][1]) && (field[i][1] == field[i][2]) && field[i][0] != 0)
				return field[i][0];
		}
		
		// Prüfe alle Vertikalen Reihen:
		for (int i=0; i<field[0].length; i++)
		{
			if ( (field[0][i] == field[1][i]) && (field[1][i] == field[2][i]) && field[0][i] != 0)
				return field[0][i];
		}
		
		// Prüfe die Diagonalen
		if ((field[0][0] == field[1][1]) && (field[1][1] == field[2][2]) )
			return field[0][0];
		
		if ((field[0][2] == field[1][1]) && (field[1][1] == field[2][0]) )
			return field[0][2];
		
		return 0;
	}
	
	
}
