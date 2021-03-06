package com.masterinformatica.bingo.entities;

import java.util.Random;

import com.masterinformatica.bingo.exceptions.ExceptionBombo;
import com.masterinformatica.bingo.messages.BingoNumber;
import com.masterinformatica.bingo.views.Painter;

/**
 * Esta clase contiene el bombo genera un numero aleatorio
 * entre 0..N-1 que no haya salido antes...
 * @author javi y jonathan.
 *
 */
public class Bombo implements Painter {

	public static final int MAX_NUMBERS = 100; // El bombo solo se visualizará de forma correcta
											   // con una cantidad de números multiplo de 20, es 
											   // es la manera en la que está calculada la vista 
											   // del juego del bombo.
											   
	private boolean numbers[]; 
	private Random seed;
	
	public Bombo() {
		this.seed = new Random();
		this.numbers = new boolean[MAX_NUMBERS];
		
		for (int i = 0; i < MAX_NUMBERS; i++) {
			this.numbers[i] = true;
		}
	}
	
	public BingoNumber generate() throws ExceptionBombo {
		if (this.empty()) {
			throw new ExceptionBombo();
		}
				
		for (;;) {
			int numb = this.seed.nextInt(Bombo.MAX_NUMBERS) + 1;
			
			if (this.numbers[numb - 1]) {
				this.numbers[numb - 1] = false;
				return new BingoNumber(numb);
			}
		}
	}
	
	private boolean empty() {
		for (int i = 0; i < MAX_NUMBERS; ++i) {
			if (this.numbers[i]) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer format = new StringBuffer();
		
		format.append("Bombo\n");
		for (int i = 0; i < MAX_NUMBERS; i++) {
			format.append(String.format(" %d %s ", i, (this.numbers[i]) ? "In" : "Out"));
			if (i % 10 == 0 && i != 0) {
				format.append('\n');
			}
		}
		return format.toString();
	}

	@Override
	public int[][] getPaint() {
		int rows = MAX_NUMBERS / 20;
		int cols = MAX_NUMBERS / rows;
		int[][] paint = new int[rows][cols];

		int numb = 1; // Primer número del bingo...
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				paint[i][j] = numb++;
			}
		}
		return paint;
	}
		
}
