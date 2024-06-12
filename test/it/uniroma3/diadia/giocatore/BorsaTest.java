package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import org.junit.*;

import com.sun.tools.javac.util.*;

public class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsaUnitaria;
	private Borsa borsa2Oggetti;
	private Borsa borsa2OggettiInvertiti;
	private Borsa borsa2OggettiPesiUguali;
	private Borsa borsa3OggettiPesiDiv;
	private final Attrezzo unitario = new Attrezzo("unitario", 1);
	private final Attrezzo due = new Attrezzo("due", 2);
	private final Attrezzo dueNomeDiv = new Attrezzo("A", 2);
	private final Attrezzo tre = new Attrezzo("tre", 3);


	@Before
	public void setUp() {
		/*Dichiarazioni*/
		borsaVuota = new Borsa();
		borsaUnitaria = new Borsa();
		borsa2Oggetti = new Borsa();
		borsa2OggettiInvertiti = new Borsa();
		borsa2OggettiPesiUguali = new Borsa();
		borsa3OggettiPesiDiv = new Borsa();

		/*Assegnazioni*/
		borsaUnitaria.addAttrezzo(unitario);
		borsa2Oggetti.addAttrezzo(unitario);
		borsa2Oggetti.addAttrezzo(due);
		borsa2OggettiInvertiti.addAttrezzo(due);
		borsa2OggettiInvertiti.addAttrezzo(unitario);
		borsa2OggettiPesiUguali.addAttrezzo(due);
		borsa2OggettiPesiUguali.addAttrezzo(dueNomeDiv);
		borsa3OggettiPesiDiv.addAttrezzo(unitario);
		borsa3OggettiPesiDiv.addAttrezzo(due);
		borsa3OggettiPesiDiv.addAttrezzo(tre);
	}

	@Test
	public void testAddAttrezzoNullo() {
		assertFalse(new Borsa().addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzoValido() {
		assertTrue(new Borsa().addAttrezzo(new Attrezzo("spada", 3)));
	}

	@Test
	public void testAddAttrezzoNonValido() {
		assertFalse(new Borsa().addAttrezzo(new Attrezzo("Martello", 11)));
	}

	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0, new Borsa().getPeso());
	}

	@Test public void testGetPesoBorsaConAttrezzo() {
		Borsa borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("spada", 3));
		assertEquals(3, borsa.getPeso());
	}

	@Test
	public void testGetAttrezzoNullo() {
		assertNull(new Borsa().getAttrezzo(null));
	}

	@Test
	public void testGetAttrezzoValido() {
		Borsa borsa = new Borsa();
		Attrezzo spada = new Attrezzo("spada", 3);
		borsa.addAttrezzo(spada);
		assertEquals(spada, borsa.getAttrezzo(spada.getNome()));
	}

	@Test
	public void testOrdinaBorsaVuotaLista() { 
		assertTrue(borsaVuota.getContenutoOrdinatoPerPeso().isEmpty()); //Lista
	}

	@Test
	public void testOrdinaBorsaVuotaSetNome() {
		assertTrue(borsaVuota.getContenutoOrdinatoPerNome().isEmpty());
	}

	@Test
	public void testOrdinaBorsaVuotaSetPeso() {
		assertTrue(borsaVuota.getSortedSetOrdinatoPerPeso().isEmpty());
	}

	@Test
	public void testOrdinaBorsaVuotaMappa() {
		assertTrue(borsaVuota.getContenutoRaggruppatoPerPeso().isEmpty());
	}

	@Test
	public void testOrdinaBorsaUnitariaLista() {
		assertEquals(1, borsaUnitaria.getContenutoOrdinatoPerPeso().size());
		assertEquals(unitario, borsaUnitaria.getContenutoOrdinatoPerPeso().get(0));		
	}

	@Test
	public void testOrdinaBorsaUnitariaMappa() {
		assertEquals(1, borsaUnitaria.getContenutoRaggruppatoPerPeso().size());
		assertTrue(borsaUnitaria.getContenutoRaggruppatoPerPeso().get(1).contains(unitario));
	}

	@Test
	public void testOrdinaBorsaUnitariaSetNome() {
		assertEquals(1, borsaUnitaria.getContenutoOrdinatoPerNome().size());
		assertEquals(unitario, borsaUnitaria.getContenutoOrdinatoPerNome().first());
	}

	@Test
	public void testOrdinaBorsaUnitariaSetPeso() {
		assertEquals(1, borsaUnitaria.getSortedSetOrdinatoPerPeso().size());
		assertEquals(unitario, borsaUnitaria.getSortedSetOrdinatoPerPeso().first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaLista() {
		assertEquals(2, borsa2Oggetti.getContenutoOrdinatoPerPeso().size());
		assertEquals(unitario, borsa2Oggetti.getContenutoOrdinatoPerPeso().get(0));
		assertEquals(due, borsa2Oggetti.getContenutoOrdinatoPerPeso().get(1));
	}

	@Test
	public void testOrdinaBorsaDoppiettaSetNome() {
		SortedSet<Attrezzo> setNome = borsa2Oggetti.getContenutoOrdinatoPerNome();
		assertEquals(2, setNome.size());
		assertEquals(due, setNome.first());
		setNome.remove(due);
		assertEquals(unitario, setNome.first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaSetPeso() {
		SortedSet<Attrezzo> setPeso = borsa2Oggetti.getSortedSetOrdinatoPerPeso();
		assertEquals(2, setPeso.size());
		assertEquals(unitario, setPeso.first());
		setPeso.remove(unitario);
		assertEquals(due, setPeso.first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaMappa() {
		assertEquals(2, borsa2Oggetti.getContenutoRaggruppatoPerPeso().size());
		assertTrue(borsa2Oggetti.getContenutoRaggruppatoPerPeso().get(1).contains(unitario));
		assertTrue(borsa2Oggetti.getContenutoRaggruppatoPerPeso().get(2).contains(due));
	}	

	@Test
	public void testOrdinaBorsaDoppiettaInvertitiLista() {
		assertEquals(2, borsa2OggettiInvertiti.getContenutoOrdinatoPerPeso().size());
		assertEquals(unitario, borsa2OggettiInvertiti.getContenutoOrdinatoPerPeso().get(0));
		assertEquals(due, borsa2OggettiInvertiti.getContenutoOrdinatoPerPeso().get(1));
	}

	@Test
	public void testOrdinaBorsaDoppiettaInvertitiSetNome() {
		SortedSet<Attrezzo> setNome = borsa2OggettiInvertiti.getContenutoOrdinatoPerNome();
		assertEquals(2, setNome.size());
		assertEquals(due, setNome.first());
		setNome.remove(due);
		assertEquals(unitario, setNome.first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaInvertitiSetPeso() {
		SortedSet<Attrezzo> setPeso = borsa2OggettiInvertiti.getSortedSetOrdinatoPerPeso();
		assertEquals(2, setPeso.size());
		assertEquals(unitario, setPeso.first());
		setPeso.remove(unitario);
		assertEquals(due, setPeso.first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaInvertitiMappa() {
		assertEquals(2, borsa2OggettiInvertiti.getContenutoRaggruppatoPerPeso().size());
		assertTrue(borsa2OggettiInvertiti.getContenutoRaggruppatoPerPeso().get(1).contains(unitario));
		assertTrue(borsa2OggettiInvertiti.getContenutoRaggruppatoPerPeso().get(2).contains(due));
	}	

	@Test
	public void testOrdinaBorsaDoppiettaStessoPesoLista() {
		assertEquals(2, borsa2OggettiPesiUguali.getContenutoOrdinatoPerPeso().size());
		assertEquals(dueNomeDiv, borsa2OggettiPesiUguali.getContenutoOrdinatoPerPeso().get(0));
		assertEquals(due, borsa2OggettiPesiUguali.getContenutoOrdinatoPerPeso().get(1));
	}

	@Test
	public void testOrdinaBorsaDoppiettaStessoPesoSetNome() {
		SortedSet<Attrezzo> setNome = borsa2OggettiPesiUguali.getContenutoOrdinatoPerNome();
		assertEquals(2, setNome.size());
		assertEquals(dueNomeDiv, setNome.first());
		setNome.remove(dueNomeDiv);
		assertEquals(due, setNome.first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaStessoPesoSetPeso() {
		SortedSet<Attrezzo> setPeso = borsa2OggettiPesiUguali.getSortedSetOrdinatoPerPeso();
		assertEquals(2, setPeso.size());
		assertEquals(dueNomeDiv, setPeso.first());
		setPeso.remove(dueNomeDiv);
		assertEquals(due, setPeso.first());
	}

	@Test
	public void testOrdinaBorsaDoppiettaStessoPesoMappa() {
		assertEquals(1, borsa2OggettiPesiUguali.getContenutoRaggruppatoPerPeso().size());
		assertTrue(borsa2OggettiPesiUguali.getContenutoRaggruppatoPerPeso().get(2).contains(dueNomeDiv));
		assertTrue(borsa2OggettiPesiUguali.getContenutoRaggruppatoPerPeso().get(2).contains(due));
	}

	@Test
	public void testOrdinaBorsaTriplettaLista() {
		assertEquals(3, borsa3OggettiPesiDiv.getContenutoOrdinatoPerPeso().size());
		assertEquals(unitario, borsa3OggettiPesiDiv.getContenutoOrdinatoPerPeso().get(0));
		assertEquals(due, borsa3OggettiPesiDiv.getContenutoOrdinatoPerPeso().get(1));
		assertEquals(tre, borsa3OggettiPesiDiv.getContenutoOrdinatoPerPeso().get(2));
	}

	@Test
	public void testOrdinaBorsaTriplettaSetNome() {
		SortedSet<Attrezzo> setNome = borsa3OggettiPesiDiv.getContenutoOrdinatoPerNome();
		assertEquals(3, setNome.size());
		assertEquals(due, setNome.first());
		setNome.remove(due);
		assertEquals(tre, setNome.first());
		setNome.remove(tre);
		assertEquals(unitario, setNome.first());
	}

	@Test
	public void testOrdinaBorsaTriplettaSetPeso() {
		SortedSet<Attrezzo> setPeso = borsa3OggettiPesiDiv.getSortedSetOrdinatoPerPeso();
		assertEquals(3, setPeso.size());
		assertEquals(unitario, setPeso.first());
		setPeso.remove(unitario);
		assertEquals(due, setPeso.first());
		setPeso.remove(due);
		assertEquals(tre, setPeso.first());
	}

	@Test
	public void testOrdinaBorsaTriplettaMappa() {
		assertEquals(3, borsa3OggettiPesiDiv.getContenutoRaggruppatoPerPeso().size());
		assertTrue(borsa3OggettiPesiDiv.getContenutoRaggruppatoPerPeso().get(1).contains(unitario));
		assertTrue(borsa3OggettiPesiDiv.getContenutoRaggruppatoPerPeso().get(2).contains(due));
		assertTrue(borsa3OggettiPesiDiv.getContenutoRaggruppatoPerPeso().get(3).contains(tre));
	}

}
