package com.juliano.pokemon;

import com.juliano.pokemon.api.Model.Batalha;
import com.juliano.pokemon.builder.BatalhaBuilder;
import com.juliano.pokemon.repository.BatalhaRepository;
import com.juliano.pokemon.service.BatalhaService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith({SpringExtension.class})
class ApplicationTests {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	@Mock
	private BatalhaRepository br;

	@InjectMocks
	private BatalhaService bt;
	@Test
	void contextLoads() {
	}

	@Test
	public void deveIniciarBatalha(){
		when(br.save(new Batalha())).thenReturn(BatalhaBuilder.umaBatalha().agora());
		Batalha b = this.bt.iniciarBatalha(1L, 2L);
		//b.setId_conta1(1L);
		assertEquals(1L, b.getId_conta1());
		//error.checkThat(b.getId_conta1(), is(equalTo(1L)));
	}
	@Test
	public void teste(){
		assertEquals(true, this.bt.teste());
	}
}
