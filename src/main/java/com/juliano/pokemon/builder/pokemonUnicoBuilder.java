package com.juliano.pokemon.builder;

import com.juliano.pokemon.api.Model.PokemonUnico;

public class pokemonUnicoBuilder {

    private PokemonUnico pokemon;
    private pokemonUnicoBuilder(){};

    public static pokemonUnicoBuilder umPokemonUnico(){
        pokemonUnicoBuilder builder = new pokemonUnicoBuilder();
        builder.pokemon = new PokemonUnico();
        builder.pokemon.setId_pokemon(1L);
        builder.pokemon.setApelido("Teste");
        builder.pokemon.setNivel(1);
        builder.pokemon.setTipo("grass");
        builder.pokemon.setNome_pokemon("Bulbasaur");
        return builder;
    }
    public PokemonUnico agora(){
        return pokemon;
    }
}
