package org.pokemon.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.pokemon.entities.PokemonEntity;

/**
 * 
 * @author Troy
 * @author NerdyGnome (Re-wrote and edited code)
 * 
 * I am not sure if we should just keep all Pokemon in
 * memory or load and unload them as needed.
 * 
 * Pokemon stored in memory size: 7266kb
 * 
 * Charmander 4:
 * Is missing a value, I stuck number 10 in there to test 
 * memory usage.
 *
 */
public class PokemonLoader {
	private static final PokemonLoader instance = new PokemonLoader();
	private static ArrayList<PokemonEntity> pokemon = new ArrayList<PokemonEntity>();
	private static String[] splitString;
	private static BufferedReader input;
	private static short lineNumber = 0;
	
	private static short id; 
	private static String name;
	private static byte[] type; 
	private static short hp;
	private static short attack;
	private static short defense;
	private static short spAttack;
	private static short spDefense;
	private static short speed;
	private static short statTotal;
	private static double chanceOfBeingMale;
	private static short captureRate;
	private static short exp;
	private static short[] startingMoves;
	private static short[] possibleMoves;
	private static short[] moveLevels;
	private static short[] evolvesInto;
	private static short[] evolveLevels;
	
	/**
	 * Loads all given Pokemon's into memory
	 * 
	 * @param pokemonId
	 */
	public static void load(short[] pokemonId) {
		String line;
					
		for(int i = 0 ; i < pokemonId.length ; i++) {
			short currentPokemon = pokemonId[i];
			input = new BufferedReader(new InputStreamReader(instance.getClass().getClassLoader().getResourceAsStream("resources\\data\\pokemon\\" + currentPokemon + ".pokemon")));

			try {
				while((line = input.readLine()) != null) {				
					switch(lineNumber) {
					case 0: 
						id = (short) Integer.parseInt(line);
						break;
					case 1:
						name = line;
						break;
					case 2: 
						splitString = line.split(",\\s*");
						type = new byte[splitString.length];
						for(int j = 0 ; j < splitString.length ; j++)
							type[j] = Byte.parseByte(splitString[j]);
						break;
					case 3:
						hp = (short) Integer.parseInt(line);
						break;
					case 4:
						attack = (short) Integer.parseInt(line);
						break;
					case 5:
						defense = (short) Integer.parseInt(line);
						break;
					case 6:
						spAttack = (short) Integer.parseInt(line);
						break;
					case 7:
						spDefense = (short) Integer.parseInt(line);
						break;
					case 8:
						speed = (short) Integer.parseInt(line);
						break;
					case 9:
						statTotal = (short) Integer.parseInt(line);
						break;
					case 10:
						chanceOfBeingMale = Double.parseDouble(line);
						break;
					case 11:
						exp = (short) Integer.parseInt(line);
						break;
					case 12:
						captureRate = (short) Integer.parseInt(line);
						break;
					case 13:
						splitString = line.split(",\\s*");
						startingMoves = new short[splitString.length];
						for(int j = 0 ; j < splitString.length ; j++)
							startingMoves[j] = (short) Integer.parseInt(splitString[j]);
						break;				
					case 14:
						splitString = line.split(",\\s*");
						possibleMoves = new short[splitString.length];
						for(int j = 0 ; j < splitString.length ; j++)
							possibleMoves[j] = (short) Integer.parseInt(splitString[j]);
						break;
					case 15:
						splitString = line.split(",\\s*");
						moveLevels = new short[splitString.length];
						for(int j = 0 ; j < splitString.length ; j++)
							moveLevels[j] = (short) Integer.parseInt(splitString[j]);
						break;
					case 16:
						splitString = line.split(",\\s*");
						evolvesInto = new short[splitString.length];
						for(int j = 0 ; j < splitString.length ; j++)
							evolvesInto[j] = (short) Integer.parseInt(splitString[j]);
						break;
					case 17:
						splitString = line.split(",\\s*");
						evolveLevels = new short[splitString.length];
						for(int j = 0 ; j < splitString.length ; j++)
							evolveLevels[j] = (short) Integer.parseInt(splitString[j]);
						break;
					default:
						System.out.println("Reading out of bounds! Line: " + lineNumber);
						break;
					}
					
					lineNumber++;				
				}
			} catch (IOException e) {
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(lineNumber);
			}
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			pokemon.add(new PokemonEntity(id,name,type,hp,attack,defense,spAttack,spDefense,speed,statTotal,
					chanceOfBeingMale,captureRate,exp,startingMoves,possibleMoves,moveLevels,evolvesInto,
					evolveLevels));
			lineNumber = 0;	
		}		
	}
	
	
	/**
	 * Returns an array list with all Pokemon's that 
	 * are loaded into the memory.
	 * 
	 * @return
	 */
	public static ArrayList<PokemonEntity> getPokemon() {
		return pokemon;
	}
	
	/**
	 * Takes a Pokemon id as a parameter and returns
	 * the Pokemon if it exists in the memory.
	 * 
	 * @param pokemonId
	 * @return
	 */
	public static PokemonEntity getPokemon(short pokemonId) {
		for(int i = 0 ; i < pokemon.size() ; i++)
			if(pokemon.get(i).getId() == pokemonId)
				return pokemon.get(i);
		
		System.out.println("ERROR: Pokemon with ID (" + pokemonId + ") is not loaded into memory");
		return null;		
	}
	
	/**
	 * Takes a Pokemon name as parameter and returns
	 * the Pokemon object if it exists in the memory. 
	 * 
	 * @param pokemonId
	 * @return Pokemon
	 */
	public static PokemonEntity getPokemon(PokemonName name) {
		int pokemonId = name.getIndex();
		
		for(int i = 0 ; i < pokemon.size() ; i++) 
			if(pokemon.get(i).getId() == pokemonId)
				return pokemon.get(i);	
		
		System.out.println("ERROR: Pokemon with ID (" + pokemonId + ") is not loaded into memory");
		return null;
	}
	
}