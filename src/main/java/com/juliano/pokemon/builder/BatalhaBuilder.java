package com.juliano.pokemon.builder;

import com.juliano.pokemon.api.Model.Batalha;

public class BatalhaBuilder {
    private Batalha batalha;

    private BatalhaBuilder(){}

    public static BatalhaBuilder umaBatalha(){
        BatalhaBuilder builder = new BatalhaBuilder();
        builder.batalha = new Batalha();
        builder.batalha.setId_conta1(1L);
        return builder;
    }
    public Batalha agora(){
        return batalha;
    }
}
