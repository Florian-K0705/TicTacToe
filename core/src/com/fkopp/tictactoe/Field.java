package com.fkopp.tictactoe;

public class Field 
{
	private int[][] field;
	
	public Field()
	{
		this.field = new int[3][3];
	}

	public boolean make_sign(int x, int y, int name)
	{
		//Pr¸fe, ob das feld bei (x,y) besetzt ist:
		if (field[x][y] != 0)
			return false;
		
		if (x < 0 | x > 2 | y < 0 | y > 2)
			return false;
		
		// Besetze Feld
		field[x][y] = name;
		return true;
	}
	
	/*
	 * return 0, 1, 2, 3
	 * 0 heiﬂt, dass Spiel wurde mit unentschieden beendet.
	 * 1 heiﬂt, Spieler 1 hat gewonnen.
	 * 2 heiﬂt, Spieler 2 hat gewonnen.
	 * 3 heiﬂt, das Spiel ist noch nicht vorbei.
	 */
	public int isGameOver()
	{
		if (playerHasWon() == 0) {
			
			if (isFieldComplete())
			{
				return 0;
			}
			
			return 3;
			
		}
		
		return playerHasWon();
	}
	
	private boolean isFieldComplete()
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
	
	private int playerHasWon()
	{
		// Pr¸fe alle Horizontalen Reihen:
		for (int i=0; i<field.length; i++)
		{
			if ( (field[i][0] == field[i][1]) && (field[i][1] == field[i][2]) && field[i][0] != 0)
				return field[i][0];
		}
		
		// Pr¸fe alle Vertikalen Reihen:
		for (int i=0; i<field[0].length; i++)
		{
			if ( (field[0][i] == field[1][i]) && (field[1][i] == field[2][i]) && field[0][i] != 0)
				return field[0][i];
		}
		
		// Pr¸fe die Diagonalen
		if ((field[0][0] == field[1][1]) && (field[1][1] == field[2][2]) )
			return field[0][0];
		
		if ((field[0][2] == field[1][1]) && (field[1][1] == field[2][0]) )
			return field[0][2];
		
		return 0;
	}
	
	public int[][] getField()
	{
		return this.field;
	}
	
	
	
	
	public String printGame(String spieler1, String spieler2)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\n");
		
		for (int i=0; i<field.length-1; i++)
		{
			for (int j=0; j < field[i].length-1; j++)
			{
				if (field[i][j] == 1)
					builder.append(spieler1 + " | ");
				else if (field[i][j] == 2)
					builder.append(spieler2 + " | ");
				else if (field[i][j] == 0)
					builder.append("-" + " | ");
			}
			
			if (field[i][field[i].length-1] == 1)
				builder.append(spieler1);
			if (field[i][field[i].length-1] == 2)
				builder.append(spieler2);
			if (field[i][field[i].length-1] == 0)
				builder.append("-");
			
			builder.append("\n---------\n");
		}
		
		for (int j=0; j < field[field.length-1].length-1; j++)
		{
			if (field[field.length-1][j] == 1)
				builder.append(spieler1 + " | ");
			if (field[field.length-1][j] == 2)
				builder.append(spieler2 + " | ");
			if (field[field.length-1][j] == 0)
				builder.append("-" + " | ");
		}
		
		if (field[field.length-1][field[field.length-1].length-1] == 1)
			builder.append(spieler1 + "\n");
		if (field[field.length-1][field[field.length-1].length-1] == 2)
			builder.append(spieler2 + "\n");
		if (field[field.length-1][field[field.length-1].length-1] == 0)
			builder.append("-" + "\n");
		
		return builder.toString();
	}
	
}
