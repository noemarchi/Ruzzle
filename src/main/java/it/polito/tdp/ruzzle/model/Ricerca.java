package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca 
{
	/**
	 * Restituisce una lista di posizioni nella Board della parola passata per parametro, se c'è
	 * 
	 * @param parola
	 * @param matrice
	 * @return
	 */
	public List<Pos> trovaParola(String parola, Board board)
	{
		for(Pos p: board.getPositions())
		{	// la lettere in pos è == alla prima lettera di parola?
			
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0))
			{ 	// posso far partire la ricorsione
				
				List<Pos> parziale = new ArrayList<Pos>();
				
				parziale.add(p);
				if(cerca(parola, board, 1, parziale))
				{
					return parziale;
				}
			}	
			
		}
		
		return null;
	}
	
	public boolean cerca(String parola, Board board, int livello, List<Pos> percorso)
	{
		// CASO TERMINALE
		if(livello == parola.length())
		{	// abbiamo trovato la parola
			return true;
		}
		
		// CASO NORMALE
		Pos ultima = percorso.get(percorso.size() - 1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		for (Pos a : adiacenti) 
		{
			// se la cella adiacente 'a' contiene la lettera della parola
			// e se non sono ancora passato sulla cella
			
			if(!percorso.contains(a) 
					&& board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello))
			{
					// posso 'continuare' il mio percorso facendo andare avanti la ricorsionre
					percorso.add(a);
					
					// uscita rapida dalla ricorsione
					if (cerca(parola, board, livello + 1, percorso))
						return true;
					
					// backtracking
					percorso.remove(percorso.size() -1);
			}
		}
		
		return false;
	}

}
