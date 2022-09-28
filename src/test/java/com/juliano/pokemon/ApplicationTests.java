package com.juliano.pokemon;

import com.juliano.pokemon.repository.BatalhaRepository;
import com.juliano.pokemon.service.BatalhaService;

import javassist.NotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.juliano.pokemon.builder.BatalhaBuilder.umaBatalha;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class ApplicationTests {
	@Mock
	private BatalhaRepository br;
	@InjectMocks
	private BatalhaService bt;
/*
	@Test
	public void deveIniciarBatalha(){
		Personagem p = new Personagem();
		p.setId(1L);
		p.setPkmu_ids("1");
		p.setHold_ids("1");
		p.setId_conta(1L);
		p.setNome("teste");
		Optional<PokemonUnico> pu = Optional.of(new PokemonUnico());
		pu.get().setId(1L);
		pu.get().setId_pokemon(1L);
		List<PokemonUnico> listpkm = new ArrayList<>();
		when(psr.findById_conta(1L)).thenReturn(p);
		when(pkmur.findById(1L)).thenReturn(pu);
		when(br.save(new Batalha())).thenReturn(umaBatalha().agora());
		bt.iniciarBatalha(1L, 2L);
		verify(bt).iniciarBatalha(anyLong(), anyLong());
	}*/
	@Test
	public void teste(){
		assertEquals(true, this.bt.teste());
	}

	@Test
	public void shouldGetBatalha() throws NotFoundException{
		when(br.findById(1L)).thenReturn(Optional.ofNullable(umaBatalha().agora()));
		assertEquals(1L, bt.getBatalha(1L).getId_conta1());
	}

}
